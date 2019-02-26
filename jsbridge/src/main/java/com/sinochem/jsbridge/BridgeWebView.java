package com.sinochem.jsbridge;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.CallSuper;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jackydu
 * @date 2019/2/25
 */
public class BridgeWebView extends WebView implements WebViewJavascriptBridge{

    public BridgeWebView(Context context) {
        this(context,null);
    }

    public BridgeWebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet,-1);
    }

    public BridgeWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }



    public static final String toLoadJs = "WebViewJavascriptBridge.js";
    Map<String, CallbackFunction> responseCallbacks = new HashMap<String, CallbackFunction>();
    Map<String, BridgeHandler> messageHandlers = new HashMap<String, BridgeHandler>();
    BridgeHandler defaultHandler = new DefaultHandler();

    private OnErrorListener mOnJsErrorListener;

    public interface OnErrorListener {
        void onJsError();
    }

    public void setOnJsErrorListener(OnErrorListener onJsErrorListener) {
        mOnJsErrorListener = onJsErrorListener;
    }

    private List<Message> startupMessage = new ArrayList<Message>();

    List<Message> getStartupMessage() {
        return startupMessage;
    }

    void setStartupMessage(List<Message> startupMessage) {
        this.startupMessage = startupMessage;
    }

    private long uniqueId = 0;

    /**
     * @param handler default handler,handle messages send by js without assigned handler name,
     * if js message has handler name, it will be handled by named handlers registered by native
     */
    public void setDefaultHandler(BridgeHandler handler) {
        this.defaultHandler = handler;
    }

    private void init() {
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        this.getSettings()
                .setJavaScriptEnabled(true);
        this.setWebViewClient(generateBridgeWebViewClient());
        setWebChromeClient(new WebChromeClient());
    }

    @Override public void setWebChromeClient(final WebChromeClient webChromeClient) {
        super.setWebChromeClient(new WebChromeClient() {
            @Override public void onReceivedTitle(WebView webView, String s) {
                super.onReceivedTitle(webView, s);
                //Log.d("mLogU", "onReceivedTitle: " + s);
                webChromeClient.onReceivedTitle(webView, s);
            }

            @Override public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                webChromeClient.onProgressChanged(webView, i);
            }

            @CallSuper
            @Override public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                handlerReturnData(message);
                result.confirm();//必须要调这一句
                return true;
            }

            @CallSuper @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue,
                                      JsPromptResult result) {
                if (mOnJsErrorListener != null) {
                    mOnJsErrorListener.onJsError();
                }
                result.confirm();
                return true;
            }

        });
    }

    private BridgeWebViewClient generateBridgeWebViewClient() {
        return new BridgeWebViewClient(this);
    }

    void handlerReturnData(String url) {
        String functionName = BridgeUtil.getFunctionFromReturnUrl(url);
        CallbackFunction f = responseCallbacks.get(functionName);
        String data = BridgeUtil.getDataFromReturnUrl(url);
        if (f != null) {
            f.onCallback(data);
            return;
        }
    }

    @Override public void send(String data) {
        send(data, null);
    }

    @Override public void send(String data, CallbackFunction responseCallback) {
        doSend(null, data, responseCallback);
    }

    private void doSend(String handlerName, String data, CallbackFunction responseCallback) {
        Message m = new Message();
        if (!TextUtils.isEmpty(data)) {
            m.setData(data);
        }
        if (responseCallback != null) {
            String callbackStr = String.format(BridgeUtil.CALLBACK_ID_FORMAT, ++uniqueId + (BridgeUtil.UNDERLINE_STR
                    + SystemClock.currentThreadTimeMillis()));
            responseCallbacks.put(callbackStr, responseCallback);
            m.setCallbackId(callbackStr);
        }
        if (!TextUtils.isEmpty(handlerName)) {
            m.setHandlerName(handlerName);
        }
        queueMessage(m);
    }

    private void queueMessage(Message m) {
        if (startupMessage != null) {
            startupMessage.add(m);
        } else {
            dispatchMessage(m);
        }
    }

    void dispatchMessage(Message m) {
        String messageJson = m.toJson();
        //escape special characters for json string
        //messageJson = messageJson.replaceAll("(\\\\)([^utrn])", "\\\\\\\\$1$2");
        //messageJson = messageJson.replaceAll("(?<=[^\\\\])(\")", "\\\\\"");
        //messageJson = messageJson.replaceAll("'", "\\\\\'");

        /*
            对"'等字符进行转换，因为WebView在load时会decode一次，需要encode两次
         */
        messageJson = Uri.encode(messageJson);
        messageJson = messageJson.replaceAll("'", "%27");
        messageJson = Uri.encode(messageJson);

        String javascriptCommand = String.format(BridgeUtil.JS_HANDLE_MESSAGE_FROM_JAVA, messageJson);
        if (isAlive() && Thread.currentThread() == Looper.getMainLooper()
                .getThread()) {
            this.loadUrl(javascriptCommand); // 会decode一次
        }
    }

    private boolean isAlive() {
        if (getContext() == null) {
            return false;
        }

        if (getContext() instanceof Activity) {
            if (((Activity) getContext()).isFinishing()) {
                //Log.d("bridge", "activity is finishing");
                return false;
            }
        }

        return true;
    }

    void flushMessageQueue() {
        if (isAlive() && Thread.currentThread() == Looper.getMainLooper()
                .getThread()) {
            loadUrl(BridgeUtil.JS_FETCH_QUEUE_FROM_JAVA, new CallbackFunction() {

                @Override public void onCallback(String data) {

                    //Log.d(TAG, data);

                    // deserializeMessage
                    List<Message> list = null;
                    try {
                        list = Message.toArrayList(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                    if (list == null || list.size() == 0) {
                        return;
                    }
                    for (int i = 0; i < list.size(); i++) {
                        Message m = list.get(i);
                        String responseId = m.getResponseId();
                        // 是否是response
                        if (!TextUtils.isEmpty(responseId)) {
                            CallbackFunction function = responseCallbacks.get(responseId);
                            String responseData = m.getResponseData();
                            function.onCallback(responseData);
                            responseCallbacks.remove(responseId);
                        } else {
                            CallbackFunction responseFunction = null;
                            // if had callbackId
                            final String callbackId = m.getCallbackId();
                            if (!TextUtils.isEmpty(callbackId)) {
                                responseFunction = new CallbackFunction() {
                                    @Override public void onCallback(String data) {
                                        Message responseMsg = new Message();
                                        responseMsg.setResponseId(callbackId);
                                        responseMsg.setResponseData(data);
                                        queueMessage(responseMsg);
                                    }
                                };
                            } else {
                                responseFunction = new CallbackFunction() {
                                    @Override public void onCallback(String data) {
                                        // do nothing
                                    }
                                };
                            }
                            BridgeHandler handler;
                            if (!TextUtils.isEmpty(m.getHandlerName())) {
                                handler = messageHandlers.get(m.getHandlerName());
                            } else {
                                handler = defaultHandler;
                            }
                            if (handler != null) {
                                handler.handler(m.getData(), responseFunction);
                                //Log.d(TAG, m.toJson());
                            }
                        }
                    }
                }
            });
        }
    }

    private void loadUrl(String jsUrl, CallbackFunction returnCallback) {
        this.loadUrl(jsUrl);
        responseCallbacks.put(BridgeUtil.parseFunctionName(jsUrl), returnCallback);
    }

    /**
     * register handler,so that javascript can call it
     */
    public void registerHandler(String handlerName, BridgeHandler handler) {
        if (handler != null) {
            messageHandlers.put(handlerName, handler);
        }
    }

    @Override public void setMessageHandlers(Map<String, BridgeHandler> messageHandlers) {
        if (messageHandlers == null) {
            return;
        }

        this.messageHandlers = messageHandlers;
    }

    /**
     * call javascript registered handler
     *
     * @param callBack null is ok as well
     */
    public void callHandler(String handlerName, String data, CallbackFunction callBack) {
        doSend(handlerName, data, callBack);
    }

}

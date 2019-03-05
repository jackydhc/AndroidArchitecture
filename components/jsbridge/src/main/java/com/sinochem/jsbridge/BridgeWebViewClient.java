package com.sinochem.jsbridge;

import android.graphics.Bitmap;
import android.util.Log;

import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * @author jackydu
 * @date 2019/2/26
 */
class BridgeWebViewClient extends WebViewClient{
    private BridgeWebView webView;

    protected BridgeWebViewClient(BridgeWebView webView) {
        this.webView = webView;
    }

    @android.support.annotation.CallSuper @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith(BridgeUtil.YY_RETURN_DATA)) { // 如果是返回数据
            webView.handlerReturnData(url);
            return true;
        } else if (url.startsWith(BridgeUtil.YY_OVERRIDE_SCHEMA)) { //
            webView.flushMessageQueue();
            return true;
        } else {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @android.support.annotation.CallSuper @Override public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        if (BridgeWebView.toLoadJs != null) {
            BridgeUtil.webViewLoadLocalJs(view, BridgeWebView.toLoadJs);
        }

        //
        if (webView.getStartupMessage() != null) {
            for (Message m : webView.getStartupMessage()) {
                webView.dispatchMessage(m);
            }
            webView.setStartupMessage(null);
        }
    }

    @Override public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        Log.d("mLogU", "onReceivedError1:" + failingUrl + ", " + errorCode + ", " + description);
    }

    @Override public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest,
                                              WebResourceResponse webResourceResponse) {
        super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        Log.d("mLogU", "onReceivedHttpError: " + webResourceRequest.getUrl()
                .toString());
    }

    @Override public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest,
                                          WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        // var3.getErrorCode(), var3.getDescription().toString(), var2.getUrl().toString()
        Log.d("mLogU", "onReceivedError2: " + webResourceError.getErrorCode() + ", " + webResourceError.getDescription()
                .toString() + ", " + webResourceRequest.getUrl()
                .toString());
    }

    @Override public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
        Log.d("mLogU", "onReceivedSslError");
        sslErrorHandler.proceed();
    }
}

package com.sinochem.jsbridge;

import java.util.Map;

/**
 * @author jackydu
 * @date 2019/2/26
 */
public interface WebViewJavascriptBridge {
    public void send(String data);

    public void send(String data, CallbackFunction responseCallback);

    public void setMessageHandlers(Map<String, BridgeHandler> messageHandlers);

}

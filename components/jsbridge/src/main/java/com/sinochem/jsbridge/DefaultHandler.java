package com.sinochem.jsbridge;

import android.support.annotation.Nullable;

/**
 * @author jackydu
 * @date 2019/2/26
 */
public class DefaultHandler implements BridgeHandler {
    @Override
    public void handler(@Nullable String data, CallbackFunction function) {
        if (data != null && function != null) {
            function.onCallback(data);
        }
    }
}

package com.sinochem.jsbridge;

import android.support.annotation.Nullable;

/**
 * @author jackydu
 * @date 2019/2/25
 */
public interface BridgeHandler {
    void handler(@Nullable String data,CallbackFunction function);
}

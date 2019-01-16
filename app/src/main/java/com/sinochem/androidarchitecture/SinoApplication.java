package com.sinochem.androidarchitecture;

import com.sinochem.corelibrary.CoreApplication;
import com.sinochem.corelibrary.utils.GlobalConfig;

import okhttp3.OkHttpClient;

/**
 * @author jackydu
 * @date 2019/1/16
 */
public class SinoApplication extends CoreApplication {
    @Override
    public GlobalConfig initGlobalConfig() {
        return null;
    }

    @Override
    public OkHttpClient provideOkhttpClient() {
        return null;
    }
}

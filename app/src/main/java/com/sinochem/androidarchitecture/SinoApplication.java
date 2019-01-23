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
        return GlobalConfig.newBuilder().baseUrl("http://www.baidu.com").setTimeOut(30).setUA("ua").build();
    }

    @Override
    public OkHttpClient provideOkhttpClient() {
        return null;
    }
}

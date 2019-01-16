package com.sinochem.corelibrary.api;

import com.sinochem.corelibrary.utils.GlobalConfig;

import okhttp3.OkHttpClient;

/**
 * @author jackydu
 * @date 2019/1/14
 */
public interface AppDelegate {

    GlobalConfig initGlobalConfig();
    OkHttpClient provideOkhttpClient();//



}

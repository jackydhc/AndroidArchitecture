package com.sinochem.corelibrary.utils;

import android.text.TextUtils;

import okhttp3.Cache;

/**
 * @author jackydu
 * @date 2019/1/14
 */
public class GlobalConfig {

    private String baseUrl;
    private Cache httpCache;
    private String uaConfig;
    private int httpOutTime;//second

    private GlobalConfig(GlobalconfigBuilder builder){
        baseUrl = builder.baseUrl;
        httpCache = builder.httpCache;
        uaConfig = builder.uaConfig;
        httpOutTime = builder.httpOutTime;
    }

    public static GlobalconfigBuilder newBuilder(){
        return  new GlobalconfigBuilder();
    }

    public static final class GlobalconfigBuilder {
        private String baseUrl;
        private Cache httpCache;
        private String uaConfig;
        private int httpOutTime;//second


        public GlobalconfigBuilder baseUrl(String baseUrl) {
            if (TextUtils.isEmpty(baseUrl)) throw new ConfigExecption("baseUrl is empty");
            this.baseUrl = baseUrl;
            return this;
        }

        public GlobalconfigBuilder addHttpCache(Cache cache) {
            if (cache == null) throw new ConfigExecption("cache is null");
            this.httpCache = cache;
            return this;
        }

        public GlobalconfigBuilder setUA(String uaConfig) {
            if (TextUtils.isEmpty(uaConfig)) throw new ConfigExecption("ua is empty");
            this.uaConfig = uaConfig;
            return this;
        }

        public GlobalconfigBuilder setTimeOut(int httpOutTime) {
            if (httpOutTime <= 10) throw new ConfigExecption("httpOutTime cannot below 10");
            this.httpOutTime = httpOutTime;
            return this;
        }

        public GlobalConfig build(){
            return  new GlobalConfig(this);
        }
    }

}

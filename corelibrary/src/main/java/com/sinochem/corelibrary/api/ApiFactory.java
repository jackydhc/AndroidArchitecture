package com.sinochem.corelibrary.api;


import com.sinochem.corelibrary.CoreApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * jacky du
 */
public enum ApiFactory {
    INSTANCE;
    Map<String,Object> apis = new HashMap<>();
    public <T> T getApi(Class<T> api){
        if (apis.containsKey(api.getSimpleName())) return (T) apis.get(api.getSimpleName());
        T t = CoreApplication.getBaseApplication().getGlobalConfig().getRetrofit().create(api);
        apis.put(api.getSimpleName(),t);
        return t;
    }
}

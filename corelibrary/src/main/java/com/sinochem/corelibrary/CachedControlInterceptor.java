package com.sinochem.corelibrary;


import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author: jackydu
 * date: on 17/2/21 11:10
 * description:
 */
class CachedControlInterceptor implements Interceptor {
    @Override public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isAvailableByPing()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        if (NetworkUtils.isAvailableByPing()) {
            int maxAge = request.cacheControl().maxAgeSeconds();//有网缓存时长
            if(maxAge > 1){
                //LogUtil.d("maxAge: "+maxAge);
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            }else {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "no-cache,no-store")
                        .build();
            }
        }
        //else {
        //    int maxStale = 60 * 60 * 6; // 没网失效6小时
        //    response = response.newBuilder()
        //            .removeHeader("Pragma")
        //            .removeHeader("Cache-Control")
        //            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
        //            .build();
        //}
        return response;

    }
}

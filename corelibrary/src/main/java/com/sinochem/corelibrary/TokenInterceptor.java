package com.sinochem.corelibrary;


import com.facebook.stetho.common.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class TokenInterceptor implements Interceptor {

    public TokenInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request().newBuilder()
                .build();

        Response response = chain.proceed(request);
        MediaType mediaType = response.body().contentType();
        String result = response.body().string();

        LogUtil.i("\nrequest url:" + request.url()
                + "\nconnection:" + chain.connection()
                + "\nheaders:" + request.headers()
                + "\n" + result);

        return response.newBuilder()
                .body(ResponseBody.create(mediaType, result))//body.string()只能读取一次,读取后重新创建一个返回
                .build();
    }
}
package com.sinochem.corelibrary;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * created by jackydu
 * desp: 可以根据base不同设置不同的enum类型
 */
public enum SinoRetrofit  {
    INSTANCE();

    @SuppressWarnings("ImmutableEnumChecker")
    private final Retrofit retrofit;

    SinoRetrofit() {
        retrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(OKHttpFactory.INSTANCE.getOkHttpClient())
                //baseUrl
                .baseUrl("")
                //gson转化器
                .addConverterFactory(GsonConverterFactory.create())
                //Rx转换
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //创建
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public <T> T create(Class<T> tClass) {
        return retrofit.create(tClass);
    }

}

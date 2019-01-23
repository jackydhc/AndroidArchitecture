package com.sinochem.corelibrary.utils;

import android.os.Environment;
import android.text.TextUtils;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.sinochem.corelibrary.BuildConfig;
import com.sinochem.corelibrary.CachedControlInterceptor;
import com.sinochem.corelibrary.HttpLoggingInterceptor;
import com.sinochem.corelibrary.OKHttpFactory;
import com.sinochem.corelibrary.UserAgentInterceptor;

import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author jackydu
 * @date 2019/1/14
 */
public class GlobalConfig {

    private String baseUrl;
    private Cache httpCache;
    private String uaConfig;
    private int httpOutTime = 30;//second
    private Retrofit retrofit;
    private OkHttpClient client;
    private SSLSocketFactory sslSocketFactory;

    private GlobalConfig(){}
    private GlobalConfig(Builder builder){
        baseUrl = builder.baseUrl;
        httpCache = builder.httpCache;
        uaConfig = builder.uaConfig;
        httpOutTime = builder.httpOutTime;
        client = builder.client;
        sslSocketFactory = builder.sslSocketFactory;
        init();
    }

    public static Builder newBuilder(){
        return  new Builder();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private void init(){
        if (client == null){
            if (httpCache == null){
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mC";
                File file = new File(filePath);
                httpCache = new Cache(file,10 * 1024 * 1024);
            }

            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    //添加UA
                    .addInterceptor(new UserAgentInterceptor(TextUtils.isEmpty(uaConfig) ? generateDefaultUa():uaConfig))
                    //添加缓存
                    .cache(httpCache)
                    .addInterceptor(new CachedControlInterceptor())
                    .addNetworkInterceptor(new CachedControlInterceptor())
                    //失败重连
                    .retryOnConnectionFailure(true)
                    //time out
                    .readTimeout(httpOutTime, TimeUnit.SECONDS)
                    .connectTimeout(httpOutTime, TimeUnit.SECONDS)
                    .sslSocketFactory(Objects.requireNonNull(sslSocketFactory == null ? cretaeDefaultFactory() : sslSocketFactory));

            if (BuildConfig.DEBUG) {
                builder.addNetworkInterceptor(new StethoInterceptor())
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(
                                HttpLoggingInterceptor.Level.BODY));
            }
            client = builder.build();
        }
        retrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(client)
                //baseUrl
                .baseUrl(baseUrl)
                //gson转化器
                .addConverterFactory(GsonConverterFactory.create())
                //Rx转换
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //创建
                .build();

    };

    private String generateDefaultUa(){
        return "ua";
    }

    private SSLSocketFactory cretaeDefaultFactory(){
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                if (chain == null) {
                    throw new IllegalArgumentException("checkServerTrusted: X509Certificate array is null");
                } else if (chain.length <= 0) {
                    throw new IllegalArgumentException("checkServerTrusted: X509Certificate is empty");
                } else {
                    try {
                        chain[0].checkValidity();
                        if (authType == null || !authType.toUpperCase().contains("RSA")) {
                            throw new CertificateException("checkServerTrusted: AuthType is not RSA:" + authType);
                        }
                    } catch (Exception e) {
                        throw new CertificateException("Certificate not valid or trusted.");
                    }
                }
            }
        }};
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, trustAllCerts, null);
            return sslcontext.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final class Builder {
        private String baseUrl;
        private Cache httpCache;
        private String uaConfig;
        private int httpOutTime;//second
        private  OkHttpClient client;
        private SSLSocketFactory sslSocketFactory;

        public Builder baseUrl(String baseUrl) {
            if (TextUtils.isEmpty(baseUrl)) throw new ConfigExecption("baseUrl is empty");
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder addHttpCache(Cache cache) {
            if (cache == null) throw new ConfigExecption("cache is null");
            this.httpCache = cache;
            return this;
        }

        public Builder setUA(String uaConfig) {
            if (TextUtils.isEmpty(uaConfig)) throw new ConfigExecption("ua is empty");
            this.uaConfig = uaConfig;
            return this;
        }

        public Builder setTimeOut(int httpOutTime) {
            if (httpOutTime <= 10) throw new ConfigExecption("httpOutTime cannot below 10");
            this.httpOutTime = httpOutTime;
            return this;
        }

        public Builder setClient(OkHttpClient client) {
            if (client == null) throw new ConfigExecption("okhttpclient is null");
            this.client= client;
            return this;
        }

        public Builder setSslSocketFactory(SSLSocketFactory sslSocketFactory){
            if (sslSocketFactory == null) throw new ConfigExecption("okhttpclient is null");
            this.sslSocketFactory= sslSocketFactory;
            return this;
        }

        public GlobalConfig build(){
            return  new GlobalConfig(this);
        }
    }

}

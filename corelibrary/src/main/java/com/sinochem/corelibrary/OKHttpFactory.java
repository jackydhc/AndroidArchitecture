package com.sinochem.corelibrary;

import android.os.Environment;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * author: jackydu
 * date: on 16/5/16 16:43
 * description:
 */
public enum OKHttpFactory {
    INSTANCE;

    @SuppressWarnings("ImmutableEnumChecker") private final OkHttpClient okHttpClient;

    private static final int TIMEOUT_READ = 30;
    private static final int TIMEOUT_CONNECTION = 30;

    OKHttpFactory() {

        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mC";
        File file = new File(filePath);
        Cache cache = new Cache(file,10 * 1024 * 1024);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                //添加UA
                .addInterceptor(new UserAgentInterceptor("ua"))
                //添加缓存
                .cache(cache)
                .addInterceptor(new CachedControlInterceptor())
                .addNetworkInterceptor(new CachedControlInterceptor())
                .sslSocketFactory(cretaeDefaultFactory())
                //失败重连
                .retryOnConnectionFailure(true)
                //time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new StethoInterceptor())
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(
                            HttpLoggingInterceptor.Level.BODY));
        }

        okHttpClient = builder.build();
    }


    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
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
}

package com.sinochem.androidarchitecture.present;

import com.blankj.utilcode.util.LogUtils;
import com.sinochem.androidarchitecture.apis.NewsApi;
import com.sinochem.androidarchitecture.contracts.FuzzyContract;
import com.sinochem.androidarchitecture.enities.FuzzyResult;
import com.sinochem.corelibrary.api.ApiFactory;
import com.sinochem.corelibrary.utils.rx.TransFormUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.reactivex.functions.Consumer;

/**
 * @author jackydu
 * @date 2019/3/7
 */
public class FuzzyPresent extends FuzzyContract.IPresent {

    @Override
    public void start() {

    }

    @Override
    public void doSearch(String searchWorld) {
        searchWorld = "张三";

        long tid = System.currentTimeMillis();
        try {
            searchWorld = URLEncoder.encode(searchWorld,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder urlSb = new StringBuilder();

        urlSb.append(NewsApi.SearchHost).append("?account=zhzbzs&name=")
                .append(searchWorld).append("&reqTid=").append(tid).append("&sign=")
                .append(getSign(searchWorld,tid).toUpperCase());

        ApiFactory.INSTANCE.getApi(NewsApi.class).search(urlSb.toString())
                .compose(TransFormUtils.switchSchedulers())
                .subscribe(new Consumer<FuzzyResult>() {
                    @Override
                    public void accept(FuzzyResult fuzzyResult) throws Exception {
                        if (fuzzyResult.resCode.equals("0000") && fuzzyResult.data != null)
                        getMvpView().showData(fuzzyResult.data.items);
                    }
                });
    }

    private String getSign(String searchWorld,long tid){
        StringBuilder signs = new StringBuilder();
        signs.append("accountzhzbzscCode").append("name").append(searchWorld).append("pageNo7").append("reqTid").append(tid).append("b3705eb9fcdf01b8");
        LogUtils.d("fuzzy","sign:"+signs.toString());
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(signs.toString().getBytes());
            return byteArrayToHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return  "";
    }

    private static final String hexDigIts[] = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};



    public String byteArrayToHexString(byte b[]){
        StringBuffer resultSb = new StringBuffer();
        for(int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    public String byteToHexString(byte b){
        int n = b;
        if(n < 0){
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigIts[d1] + hexDigIts[d2];
    }
}

package com.sinochem.androidarchitecture;

import android.os.Bundle;
import android.util.Log;


import com.sinochem.corelibrary.CoreApplication;
import com.sinochem.corelibrary.base.BaseActivity;


public class MainActivity extends BaseActivity {

    @Override
    public int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        boolean aiyou = CoreApplication.getBaseApplication().getGlobalConfig().getRetrofit() == null;
        Log.d("main","retrofit is empty:"+aiyou);
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void loginExpire() {

    }
}

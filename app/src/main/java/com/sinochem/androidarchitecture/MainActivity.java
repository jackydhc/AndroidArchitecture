package com.sinochem.androidarchitecture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.sinochem.corelibrary.base.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    @Override
    public int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {

    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void loginExpire() {

    }
}

package com.sinochem.androidarchitecture.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.sinochem.androidarchitecture.MainActivity;
import com.sinochem.androidarchitecture.R;
import com.sinochem.corelibrary.base.BaseActivity;
import com.sinochem.corelibrary.utils.rx.TransFormUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;


import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.welcome_img)
    ImageView welcomeImg;

    @BindView(R.id.txt_countDown)
    TextView txtCountDown;

    private Disposable countDownEnd;

    @Override
    public int provideLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        new RxPermissions(this).requestEach(Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .compose(TransFormUtils.switchSchedulers())
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        begainIn();
                    }
                });


    }

    private void begainIn() {
        countDownEnd = Flowable.intervalRange(0, 300, 0, 10, TimeUnit.MILLISECONDS)
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .take(300)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        try{
                            txtCountDown.setText(String.valueOf(aLong));
                        }catch (Exception e){
                            LogUtils.e(e.getMessage());
                        }

                    }
                }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
//                ToastUtils.showShort("count down end");
                toMain();
            }
        }).doOnCancel(new Action() {
            @Override
            public void run() throws Exception {
                LogUtils.d(TAG,"doOnCancel");
                toMain();
            }
        }).subscribe();
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void loginExpire() {

    }

    @OnClick(R.id.txt_countDown)
    public void onViewClicked() {
        countDownEnd.dispose();
        txtCountDown.setText("hello");
    }

    private void toMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

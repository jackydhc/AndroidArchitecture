package com.sinochem.corelibrary.utils.rx;

import com.blankj.utilcode.util.LogUtils;
import com.sinochem.corelibrary.BuildConfig;
import com.sinochem.corelibrary.mvp.MvpView;

import rx.Subscriber;

/**
 * @author jackydu
 * @date 2019/1/16
 */
public abstract class SimpleSubscriber<T> extends Subscriber<T> {

    @Override
    public void onNext(T t) {
        if (mMvpView != null && mMvpView.isAlive()){
            handleNext(t);
        }
    }
    private MvpView mMvpView;

    public SimpleSubscriber(MvpView showReLoginDialog) {
        this.mMvpView = showReLoginDialog;
    }


    @Override
    public void onError(Throwable e) {
        if (mMvpView == null || !mMvpView.isAlive())return;
        if (BuildConfig.DEBUG){
            LogUtils.d(e.getMessage());
        }
        if (e instanceof LoginExpireExep){
            handlerError(e,true);
            mMvpView.loginExpire();
            return;
        }
        handlerError(e,false);
    }

    @Override
    public void onCompleted() {

    }
    protected abstract void handleNext(T t);

    protected abstract void handlerError(Throwable e,boolean isLoginExpire);
}

package com.sinochem.corelibrary.utils.rx;

import com.sinochem.corelibrary.mvp.MvpView;

import io.reactivex.functions.Consumer;

/**
 * @author jackydu
 * @date 2019/1/23
 */
public abstract class ErrorConsumer implements Consumer<Throwable> {
    MvpView mvpView;
    public ErrorConsumer(MvpView mvpView){
        this.mvpView = mvpView;
    }

    @Override
    public void accept(Throwable throwable) throws Exception {
        if (throwable instanceof LoginExpireExep){//token 超时，，回到login页面
            mvpView.loginExpire();
        }else {
            doError(throwable);
        }
    }

    protected abstract void doError(Throwable throwable);
}

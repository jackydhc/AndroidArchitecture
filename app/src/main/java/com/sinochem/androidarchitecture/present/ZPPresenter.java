package com.sinochem.androidarchitecture.present;

import com.sinochem.corelibrary.mvp.BasePresenter;
import com.sinochem.corelibrary.mvp.multiState.IMultiView;
import com.sinochem.corelibrary.utils.rx.TransFormUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


/**
 * @author jackydu
 * @date 2019/2/19
 */
public class ZPPresenter extends BasePresenter<IMultiView> {

    @Override
    public void start() {
        getMvpView().showLoading();
        Observable.interval(3, TimeUnit.SECONDS)
                .compose(TransFormUtils.switchSchedulers())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        getMvpView().showContent();
                    }
                });
    }
}

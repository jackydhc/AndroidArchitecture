package com.sinochem.corelibrary.utils.rx;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author jackydu
 * @date 2019/1/16
 */
public class TransFormUtils {

    public static <T> ObservableTransformer<T, T> switchSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static <T> FlowableTransformer<T,T> switchDisposable(){
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}

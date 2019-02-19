package com.sinochem.corelibrary.utils.rx;

import com.sinochem.corelibrary.mvp.MvpView;
import com.sinochem.corelibrary.utils.Preconditions;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;


import io.reactivex.annotations.NonNull;

/**
 * @author jackydu
 * @date 2019/1/23
 */
public class RxBindUtils {

    /**
     * 绑定 Activity/Fragment 的生命周期
     *
     * @param <T>
     * @param view
     * @return
     */
    public static <T> LifecycleTransformer bindToLifecycle(@NonNull MvpView view) {
        Preconditions.checkNotNull(view, "container == null");
        if (view instanceof LifecycleProvider) {
            return ((LifecycleProvider) view).bindToLifecycle();
        } else {
            throw new IllegalArgumentException("container isn't Lifecycleable");
        }
    }

}

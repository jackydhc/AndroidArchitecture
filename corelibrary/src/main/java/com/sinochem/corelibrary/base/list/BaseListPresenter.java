package com.sinochem.corelibrary.base.list;

import android.support.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.sinochem.corelibrary.api.ApiResponse;
import com.sinochem.corelibrary.utils.rx.ErrorConsumer;
import com.sinochem.corelibrary.utils.rx.FunctionsUtils;
import com.sinochem.corelibrary.utils.rx.RxBindUtils;
import com.sinochem.corelibrary.utils.rx.SimpleSubscriber;
import com.sinochem.corelibrary.utils.rx.TransFormUtils;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author jackydu
 * @date 2019/1/16
 */
public abstract class BaseListPresenter<E,T> extends BaseListContract.IRefreshPresenter<List<T>> {


    public boolean isLoading;

    @Override
    public void onLoadMoreRequested() {
        loadData(false);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        loadData(true);
    }

    @Override
    public void start() {

    }

    protected void loadData(boolean isRefresh){
        if (isLoading) return;
        isLoading = true;
        Disposable subscribe = provideObservable(isRefresh)
                .map(FunctionsUtils.extractResponse())
                .map(this::provideConverter)
                .compose(TransFormUtils.switchSchedulers())
                .compose(RxBindUtils.bindToLifecycle(getMvpView()))
                .subscribe((Consumer<List<T>>) list -> {
                    if (list != null) {
                        onPrepareList(list, isRefresh);
                        getMvpView().showContent(list, isRefresh);
                    }
                    if ((list == null || list.isEmpty())) {
                        if (isRefresh) getMvpView().showEmpty();
                        getMvpView().showComplete();
                    }
                    isLoading = false;
                }, new ErrorConsumer(getMvpView()) {
                    @Override
                    protected void doError(Throwable throwable) {
                        LogUtils.d(BaseListPresenter.class.getSimpleName(),throwable.getMessage());
                    }
                });

        addSubscription(subscribe);

    }

    protected abstract Observable<ApiResponse<E>> provideObservable(boolean isRefresh);

    /**
     * http字处理部分
     * @param e
     * @return
     */
    protected abstract List<T> provideConverter(@NonNull E e);

    protected abstract void onPrepareList(List<T> list,boolean isRefresh);

}

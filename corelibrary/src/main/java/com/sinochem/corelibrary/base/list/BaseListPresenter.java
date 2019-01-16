package com.sinochem.corelibrary.base.list;

import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.sinochem.corelibrary.api.ApiResponse;
import com.sinochem.corelibrary.utils.rx.Functions;
import com.sinochem.corelibrary.utils.rx.SimpleSubscriber;
import com.sinochem.corelibrary.utils.rx.TransFormUtils;

import java.util.List;

import rx.Observable;

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
        addSubscription(provideObservable(isRefresh)
                .map(Functions.extractResponse())
                .map(this::provideConverter)
                .compose(TransFormUtils.switchSchedulers())
                .subscribe(new SimpleSubscriber<List<T>>(getMvpView()) {
                    @Override
                    protected void handleNext(List<T> list) {
                        if (list != null) {
                            onPrepareList(list,isRefresh);
                            getMvpView().showContent(list,isRefresh);
                        }
                        if ((list == null || list.isEmpty())){
                            if (isRefresh) getMvpView().showEmpty();
                            getMvpView().showComplete();
                        }
                        isLoading = false;
                    }

                    @Override
                    protected void handlerError(Throwable e, boolean isLoginExpire) {
                        isLoading = false;
                    }
                })
        );

    }

    protected abstract Observable<ApiResponse<E>> provideObservable(boolean isRefresh);

    protected abstract List<T> provideConverter(@NonNull E e);

    protected abstract void onPrepareList(List<T> list,boolean isRefresh);

}

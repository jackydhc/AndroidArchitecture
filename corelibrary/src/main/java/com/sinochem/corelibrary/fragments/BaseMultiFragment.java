package com.sinochem.corelibrary.fragments;

import com.blankj.utilcode.util.LogUtils;
import com.sinochem.corelibrary.mvp.BasePresenter;
import com.sinochem.corelibrary.mvp.multiState.IMultiView;
import com.sinochem.multistateview.MultiStateView;

/**
 * @author jackydu
 * @date 2019/1/14
 */
public abstract class BaseMultiFragment<P extends BasePresenter<? extends IMultiView>> extends LazyFragment<P> implements IMultiView{

    public MultiStateView multiStateView;
    public abstract MultiStateView proovideMultiView();
    public abstract MultiStateView.RetryListener provideRetryListener();
    @Override
    protected void initOnCreateView() {
        multiStateView = proovideMultiView();
        MultiStateView.RetryListener retryListener = provideRetryListener();
        if (retryListener != null) multiStateView.addRetryListener(retryListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (multiStateView != null && multiStateView.getViewState() == MultiStateView.VIEW_STATE_LOADING) {
            multiStateView.showEmpty(null);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (multiStateView != null && multiStateView.getViewState() == MultiStateView.VIEW_STATE_LOADING) {
            multiStateView.showEmpty(null);
        }
    }

    @Override
    public void showLoading() {
        if (multiStateView != null) {
            LogUtils.d("multistate","showloading");
            multiStateView.showLoading();
        }
    }

    @Override
    public void showContent() {
        if (multiStateView != null) {
            multiStateView.showContent();
        }
    }

    @Override
    public void showEmpty() {
        if (multiStateView != null) {
            multiStateView.showEmpty("");
        }
    }

    @Override
    public void showError(String error) {
        if (multiStateView != null) {
            multiStateView.showError(error);
        }
    }
}

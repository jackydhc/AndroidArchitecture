package com.sinochem.corelibrary.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinochem.corelibrary.CoreApplication;
import com.sinochem.corelibrary.mvp.BasePresenter;
import com.sinochem.corelibrary.mvp.MvpView;
import com.squareup.leakcanary.RefWatcher;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;

/**
 * @author jackydu
 * @date 2018/8/15
 */
public abstract class BaseFragment<P extends BasePresenter> extends RxFragment implements MvpView {

    /**
     * 绑定到当前的attach的activity上.可强转
     */
    public Context mContext;
    protected View container;
    protected P mPresenter;


    @Override public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (this.container == null) this.container = inflater.inflate(provideLayoutId(), container, false);
        ButterKnife.bind(this, this.container);
        initOnCreateView();
        mPresenter = providePresent();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        return this.container;
    }

    public abstract int provideLayoutId();

    protected abstract void initOnCreateView();

    protected abstract P providePresent();

    @Override public void onResume() {
        super.onResume();
    }

    @Override public void onPause() {
        super.onPause();
    }

    @Override public void onDestroyView() {
        dismissCustomDialog();
//        RefWatcher refWatcher = CoreApplication.getRefWatcher();
//        if (refWatcher != null) {
//            refWatcher.watch(this);
//        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    /**
     * 除特殊情况，存在于指定Fragment中的自定义Dialog，必须在此方法中进行关闭
     */
    protected void dismissCustomDialog() {

    }

    @Override public boolean isAlive() {
        //noinspection SimplifiableIfStatement
        if (getActivity() != null && !getActivity().isFinishing()) {
            return true;
        }
        return isAdded();
    }
}

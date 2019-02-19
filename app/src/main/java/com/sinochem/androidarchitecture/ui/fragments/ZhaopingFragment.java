package com.sinochem.androidarchitecture.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.sinochem.androidarchitecture.R;
import com.sinochem.androidarchitecture.present.ZPPresenter;
import com.sinochem.corelibrary.fragments.BaseMultiFragment;

/**
 * @author jackydu
 * @date 2019/2/18
 */
public class ZhaopingFragment extends BaseMultiFragment<ZPPresenter> {

    @Override
    public int provideLayoutId() {
        return R.layout.sino_fragment_zp;
    }

    @Override
    protected void initOnCreateView() {
        multiStateView = container.findViewById(R.id.multi_state);
        LogUtils.d("multistate",multiStateView);
    }

    @Override
    protected ZPPresenter providePresent() {
        return new ZPPresenter();
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void loginExpire() {

    }

    @Override
    protected void loadData() {
        LogUtils.d("multistate","loadData");
        mPresenter.start();
    }
}

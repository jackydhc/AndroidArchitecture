package com.sinochem.androidarchitecture.ui.fragments;


import com.blankj.utilcode.util.LogUtils;
import com.sinochem.androidarchitecture.R;
import com.sinochem.androidarchitecture.present.ZPPresenter;
import com.sinochem.corelibrary.fragments.BaseMultiFragment;
import com.sinochem.multistateview.MultiStateView;

/**
 * @author jackydu
 * @date 2019/2/18
 */
public class ZhaopingFragment extends BaseMultiFragment<ZPPresenter> implements MultiStateView.RetryListener {

    @Override
    public int provideLayoutId() {
        return R.layout.sino_fragment_zp;
    }

    @Override
    public MultiStateView proovideMultiView() {
        return container.findViewById(R.id.multi_state);
    }

    @Override
    public MultiStateView.RetryListener provideRetryListener() {
        return this;
    }

    @Override
    protected void initOnCreateView() {

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

    @Override
    public void onRetry() {
        mPresenter.start();
    }
}

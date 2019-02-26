package com.sinochem.androidarchitecture.ui.fragments;

import com.sinochem.androidarchitecture.present.DetailPresenter;
import com.sinochem.corelibrary.fragments.BaseFragment;
import com.sinochem.corelibrary.mvp.BasePresenter;

/**
 * @author jackydu
 * @date 2019/2/22
 */
public class DetailFragment extends BaseFragment<DetailPresenter> {
    @Override
    public int provideLayoutId() {
        return 0;
    }

    @Override
    protected void initOnCreateView() {

    }

    @Override
    protected DetailPresenter providePresent() {
        return null;
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void loginExpire() {

    }
}

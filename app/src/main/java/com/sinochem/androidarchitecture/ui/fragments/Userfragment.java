package com.sinochem.androidarchitecture.ui.fragments;

import com.sinochem.androidarchitecture.R;
import com.sinochem.androidarchitecture.present.UserPresent;
import com.sinochem.corelibrary.fragments.BaseFragment;

/**
 * @author jackydu
 * @date 2019/2/1
 */
public class Userfragment extends BaseFragment<UserPresent> {
    @Override
    public int provideLayoutId() {
        return R.layout.sino_fragment_user;
    }

    @Override
    protected void initOnCreateView() {

    }

    @Override
    protected UserPresent providePresent() {
        return null;
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void loginExpire() {

    }
}

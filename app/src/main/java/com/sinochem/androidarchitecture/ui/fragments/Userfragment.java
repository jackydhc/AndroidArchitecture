package com.sinochem.androidarchitecture.ui.fragments;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sinochem.androidarchitecture.R;
import com.sinochem.androidarchitecture.present.UserPresent;
import com.sinochem.corelibrary.fragments.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author jackydu
 * @date 2019/2/1
 */
public class Userfragment extends BaseFragment<UserPresent> implements OnRefreshListener {
    @BindView(R.id.nestview)
    NestedScrollView nestview;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;

    @Override
    public int provideLayoutId() {
        return R.layout.sino_fragment_user;
    }

    @Override
    protected void initOnCreateView() {
        smartRefresh.setOnRefreshListener(this);
        smartRefresh.autoRefresh();
    }

    @Override
    protected UserPresent providePresent() {
        UserPresent userPresent = new UserPresent();
        return userPresent;
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void loginExpire() {

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

    }
}

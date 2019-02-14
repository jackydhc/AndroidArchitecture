package com.sinochem.androidarchitecture.ui.fragments;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sinochem.androidarchitecture.enities.HomeListDataBean;
import com.sinochem.androidarchitecture.present.HomeListPresent;
import com.sinochem.corelibrary.base.list.BaseListFragment;

/**
 * @author jackydu
 * @date 2019/2/1
 */
public class HomeListFragment extends BaseListFragment<HomeListDataBean,HomeListPresent>{

    @Override
    protected BaseQuickAdapter<HomeListDataBean, ? extends BaseViewHolder> provideAdapter() {
        return null;
    }

    @Override
    public int provideLayoutId() {
        return 0;
    }

    @Override
    protected HomeListPresent providePresent() {
        return null;
    }

    @Override
    protected RecyclerView provideRecyclerView() {
        return null;
    }

    @Override
    protected RecyclerView.LayoutManager provideLayoutManager() {
        return null;
    }

    @Override
    protected SmartRefreshLayout provideRefreshLayout() {
        return null;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void loginExpire() {

    }
}

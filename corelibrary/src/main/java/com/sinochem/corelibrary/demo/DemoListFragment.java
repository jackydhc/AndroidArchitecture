package com.sinochem.corelibrary.demo;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sinochem.corelibrary.base.list.BaseListFragment;

/**
 * @author jackydu
 * @date 2019/1/15
 */
public class DemoListFragment extends BaseListFragment<Bean,DemoListPresent> {
    @Override
    protected BaseQuickAdapter provideAdapter() {
        return null;
    }

    @Override
    public int provideLayoutId() {
        return 0;
    }

    @Override
    protected DemoListPresent providePresent() {
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

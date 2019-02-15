package com.sinochem.androidarchitecture.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sinochem.androidarchitecture.R;
import com.sinochem.androidarchitecture.enities.HomeListDataBean;
import com.sinochem.androidarchitecture.present.HomeListPresent;
import com.sinochem.corelibrary.base.list.BaseListFragment;
import com.sinochem.multistateview.MultiStateView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author jackydu
 * @date 2019/2/1
 */
public class HomeListFragment extends BaseListFragment<HomeListDataBean, HomeListPresent> implements BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.ptr)
    SmartRefreshLayout ptr;
    @BindView(R.id.multi_state)
    MultiStateView multiState;

    @Override
    protected BaseQuickAdapter<HomeListDataBean, ? extends BaseViewHolder> provideAdapter() {
        BaseQuickAdapter<HomeListDataBean, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<HomeListDataBean, BaseViewHolder>(R.layout.layout_home_list_item) {
            @Override
            protected void convert(BaseViewHolder helper, HomeListDataBean item) {
            }
        };
        baseQuickAdapter.setOnItemChildClickListener(HomeListFragment.this);
        baseQuickAdapter.setEnableLoadMore(true);
        return baseQuickAdapter;
    }

    @Override
    public int provideLayoutId() {
        return R.layout.layout_base_list;
    }

    @Override
    protected HomeListPresent providePresent() {
        return new HomeListPresent();
    }

    @Override
    protected RecyclerView provideRecyclerView() {

        return recycler;
    }

    @Override
    protected RecyclerView.LayoutManager provideLayoutManager() {
        return new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
    }

    @Override
    protected SmartRefreshLayout provideRefreshLayout() {
        return ptr;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void loginExpire() {

    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        HomeListDataBean dataBean = (HomeListDataBean) adapter.getData().get(position);


    }
}

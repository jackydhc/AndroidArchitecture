package com.sinochem.androidarchitecture.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.sinochem.androidarchitecture.R;
import com.sinochem.androidarchitecture.enities.HomeListDataBean;
import com.sinochem.androidarchitecture.enities.JobsBean;
import com.sinochem.androidarchitecture.present.HomeListPresent;
import com.sinochem.androidarchitecture.present.JobsListPresent;
import com.sinochem.corelibrary.base.list.BaseListFragment;
import com.sinochem.multistateview.MultiStateView;

/**
 * @author jackydu
 * @date 2019/3/4
 */
public class HomeJobsFragment extends BaseListFragment<JobsBean,JobsListPresent> implements BaseQuickAdapter.OnItemChildClickListener, MultiStateView.RetryListener, OnLoadmoreListener {

    public static HomeJobsFragment newInstance(String type){
        Bundle args = new Bundle();
        args.putString("type",type);
        HomeJobsFragment homeListFragment = new HomeJobsFragment();
        homeListFragment.setArguments(args);
        return homeListFragment;
    }
    @SuppressLint("ValidFragment")
    private HomeJobsFragment(){}

    @Override
    protected BaseQuickAdapter<JobsBean, ? extends BaseViewHolder> provideAdapter() {
        BaseQuickAdapter<JobsBean, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<JobsBean, BaseViewHolder>(R.layout.layout_home_list_item) {
            @Override
            protected void convert(BaseViewHolder helper, JobsBean item) {
                TextView txtTitle = helper.itemView.findViewById(R.id.tv_item_title);
                helper.setText(R.id.tv_time,item.getPublishDate());
            }
        };
        baseQuickAdapter.setOnItemChildClickListener(HomeJobsFragment.this);
        baseQuickAdapter.setEnableLoadMore(true);
        return baseQuickAdapter;
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
    public int provideLayoutId() {
        return R.layout.layout_base_list;
    }

    @Override
    protected JobsListPresent providePresent() {
        String type="";
        Bundle arguments = getArguments();
        if (arguments != null){
            type = arguments.getString("type");
        }
        return new JobsListPresent();
    }

    @Override
    protected RecyclerView provideRecyclerView() {
        return container.findViewById(R.id.recycler);
    }

    @Override
    protected RecyclerView.LayoutManager provideLayoutManager() {
        return new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
    }

    @Override
    protected SmartRefreshLayout provideRefreshLayout() {
        SmartRefreshLayout refre = container.findViewById(R.id.ptr);
        refre.setOnLoadmoreListener(this);
        return refre;
    }

    @Override
    protected void loadData() {
        mPresenter.start();
    }

    @Override
    public void loginExpire() {

    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        HomeListDataBean dataBean = (HomeListDataBean) adapter.getData().get(position);

    }

    @Override
    public void onRetry() {
        mPresenter.loadData(true);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        LogUtils.d("list","onloadmore");
        mPresenter.loadData(false);
    }
}

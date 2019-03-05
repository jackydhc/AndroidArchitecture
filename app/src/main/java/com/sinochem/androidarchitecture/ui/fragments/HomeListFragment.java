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
import com.sinochem.androidarchitecture.present.HomeListPresent;
import com.sinochem.androidarchitecture.ui.WebDetailAct;
import com.sinochem.corelibrary.base.list.BaseListFragment;
import com.sinochem.multistateview.MultiStateView;


/**
 * @author jackydu
 * @date 2019/2/1
 */
public class HomeListFragment extends BaseListFragment<HomeListDataBean, HomeListPresent> implements BaseQuickAdapter.OnItemChildClickListener, MultiStateView.RetryListener, OnLoadmoreListener, BaseQuickAdapter.OnItemClickListener {

    public static HomeListFragment newInstance(String type){
        Bundle args = new Bundle();
        args.putString("type",type);
        HomeListFragment homeListFragment = new HomeListFragment();
        homeListFragment.setArguments(args);
        return homeListFragment;
    }
    @SuppressLint("ValidFragment")
    private HomeListFragment(){}

    @Override
    protected BaseQuickAdapter<HomeListDataBean, ? extends BaseViewHolder> provideAdapter() {
        BaseQuickAdapter<HomeListDataBean, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<HomeListDataBean, BaseViewHolder>(R.layout.layout_home_list_item) {
            @Override
            protected void convert(BaseViewHolder helper, HomeListDataBean item) {
                TextView txtTitle = helper.itemView.findViewById(R.id.tv_item_title);
                txtTitle.setText(item.getTitle());
                helper.setText(R.id.txt_content,item.getSummary());
                helper.setText(R.id.tv_time,item.getPublishDate());
            }
        };
        baseQuickAdapter.setOnItemChildClickListener(HomeListFragment.this);
        baseQuickAdapter.setOnItemClickListener(HomeListFragment.this);
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
    protected HomeListPresent providePresent() {
        String type="";
        Bundle arguments = getArguments();
        if (arguments != null){
            type = arguments.getString("type");
        }
        return new HomeListPresent(type);
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
        WebDetailAct.toWebDetail(dataBean.getMobileUrl(),dataBean.getTitle(),mContext);
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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeListDataBean dataBean = (HomeListDataBean) adapter.getData().get(position);
        WebDetailAct.toWebDetail(dataBean.getMobileUrl(),dataBean.getTitle(),mContext);
    }
}

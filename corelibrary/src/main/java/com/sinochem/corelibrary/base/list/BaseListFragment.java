package com.sinochem.corelibrary.base.list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.sinochem.corelibrary.R;
import com.sinochem.corelibrary.fragments.BaseMultiFragment;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;


/**
 * @author jackydu
 * @date 2018/8/21
 */
public abstract class BaseListFragment<E, P extends BaseListContract.IRefreshPresenter<List<E>>> extends BaseMultiFragment<P> implements BaseListContract.IListMvpView<List<E>> {

    RecyclerView recycler;
    SmartRefreshLayout refreshLayout;

    private BaseQuickAdapter<E,? extends BaseViewHolder> mAdapter;

    //提供类似于layout_base_list的layout
//    @Override
//    public int provideLayoutId() {//
//        return R.layout.layout_base_list;
//    }


    protected abstract BaseQuickAdapter<E,? extends BaseViewHolder> provideAdapter();
    protected abstract RecyclerView provideRecyclerView();
    protected abstract RecyclerView.LayoutManager provideLayoutManager();
    protected abstract SmartRefreshLayout provideRefreshLayout();
    public boolean isFirstLoading = true;

    @Override
    protected void initOnCreateView() {
        super.initOnCreateView();
        LogUtils.d("BaseListFragment","initOnCreateView");
        recycler = provideRecyclerView();
        if (recycler == null) throw new IllegalArgumentException("recycler is null");
        recycler.setLayoutManager(provideLayoutManager() == null ? new LinearLayoutManager(mContext) :provideLayoutManager());
        mAdapter = provideAdapter();
        recycler.setAdapter(mAdapter);
        recycler.addItemDecoration(getDefaultItemDecoration());
        if (mAdapter.isLoadMoreEnable()) mAdapter.setOnLoadMoreListener(mPresenter,recycler);
        refreshLayout = provideRefreshLayout();
        if (refreshLayout != null) {
            refreshLayout.setOnRefreshListener(mPresenter);
        }
        showLoading(true);
    }

    protected RecyclerView.ItemDecoration getDefaultItemDecoration() {
        return new HorizontalDividerItemDecoration.Builder(recycler.getContext())
                .colorResId(R.color.default_divider_color)
                .sizeResId(R.dimen.default_divider_height)
                .build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showEmpty() {
        super.showEmpty();
        if (mAdapter == null) {
            return;
        }
        refreshLayout.finishRefresh();
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        super.showError(error);
        LogUtils.d("BASELIST:","showError:"+error);
        if (mAdapter == null) {
            return;
        }
        refreshLayout.finishRefresh();
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showComplete() {
        LogUtils.d("BASELIST:","showComplete:");
        mAdapter.loadMoreComplete();
    }

    @Override
    public void showContent(List<E> data, boolean refresh) {
        isFirstLoading = false;
        showContent();

        if (!refresh )  refreshLayout.finishLoadmore();
        else  refreshLayout.finishRefresh();
        LogUtils.d("BASELIST:","showContent:"+data.size());
        if (refresh) {
            mAdapter.setNewData(data);
        } else {
            mAdapter.addData(data);
        }
    }

    public void smoothScrollToTop() {
        if (recycler != null) {
            recycler.smoothScrollToPosition(0);
        }
    }

    @Override
    public void showLoading(boolean show) {
        if (isFirstLoading) showLoading();
    }
}

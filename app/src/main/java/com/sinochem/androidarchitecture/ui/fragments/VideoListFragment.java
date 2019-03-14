package com.sinochem.androidarchitecture.ui.fragments;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sinochem.androidarchitecture.enities.VideoDataBean;
import com.sinochem.androidarchitecture.present.VideoListPresent;
import com.sinochem.corelibrary.base.list.BaseListFragment;
import com.sinochem.multistateview.MultiStateView;

/**
 * @author jackydu
 * @date 2019/3/7
 */
public class VideoListFragment extends BaseListFragment<VideoDataBean,VideoListPresent> {
    @Override
    protected BaseQuickAdapter<VideoDataBean, ? extends BaseViewHolder> provideAdapter() {
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
    public MultiStateView proovideMultiView() {
        return null;
    }

    @Override
    public MultiStateView.RetryListener provideRetryListener() {
        return null;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public int provideLayoutId() {
        return 0;
    }

    @Override
    protected VideoListPresent providePresent() {
        return null;
    }

    @Override
    public void loginExpire() {

    }
}

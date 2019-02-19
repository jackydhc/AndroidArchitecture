package com.sinochem.androidarchitecture.ui.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sinochem.androidarchitecture.R;
import com.sinochem.androidarchitecture.enities.HomeListDataBean;
import com.sinochem.androidarchitecture.present.HomeListPresent;
import com.sinochem.corelibrary.base.list.BaseListFragment;


/**
 * @author jackydu
 * @date 2019/2/1
 */
public class HomeListFragment extends BaseListFragment<HomeListDataBean, HomeListPresent> implements BaseQuickAdapter.OnItemChildClickListener {

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
    protected void initOnCreateView() {
        multiStateView = container.findViewById(R.id.multi_state);
        super.initOnCreateView();

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
        return container.findViewById(R.id.recycler);
    }

    @Override
    protected RecyclerView.LayoutManager provideLayoutManager() {
        return new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
    }

    @Override
    protected SmartRefreshLayout provideRefreshLayout() {
        return container.findViewById(R.id.ptr);
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

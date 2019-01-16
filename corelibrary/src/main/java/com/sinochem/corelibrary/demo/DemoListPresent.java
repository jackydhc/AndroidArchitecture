package com.sinochem.corelibrary.demo;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.sinochem.corelibrary.base.list.BaseListContract;
import com.sinochem.corelibrary.mvp.BasePresenter;

import java.util.List;

/**
 * @author jackydu
 * @date 2019/1/15
 */
public class DemoListPresent extends BaseListContract.IRefreshPresenter<List<Bean>> {


    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getMvpView().showContent();
    }

    @Override
    public void start() {

    }
}

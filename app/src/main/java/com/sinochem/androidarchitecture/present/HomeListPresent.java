package com.sinochem.androidarchitecture.present;

import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.sinochem.androidarchitecture.enities.HomeListBean;
import com.sinochem.androidarchitecture.enities.HomeListDataBean;
import com.sinochem.corelibrary.api.ApiResponse;
import com.sinochem.corelibrary.base.list.BaseListPresenter;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author jackydu
 * @date 2019/2/13
 */
public class HomeListPresent extends BaseListPresenter<HomeListBean,HomeListDataBean> {


    @Override
    protected Observable<ApiResponse<HomeListBean>> provideObservable(boolean isRefresh) {
        return null;
    }

    @Override
    protected List<HomeListDataBean> provideConverter(@NonNull HomeListBean homeListBean) {
        return null;
    }

    @Override
    protected void onPrepareList(List<HomeListDataBean> list, boolean isRefresh) {

    }

    @Override
    protected void loadData(boolean isRefresh) {
        super.loadData(isRefresh);
    }
}

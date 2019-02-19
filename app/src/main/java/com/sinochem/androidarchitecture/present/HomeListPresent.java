package com.sinochem.androidarchitecture.present;

import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.sinochem.androidarchitecture.apis.NewsApi;
import com.sinochem.androidarchitecture.enities.HomeListBean;
import com.sinochem.androidarchitecture.enities.HomeListDataBean;
import com.sinochem.corelibrary.api.ApiFactory;
import com.sinochem.corelibrary.api.ApiResponse;
import com.sinochem.corelibrary.base.list.BaseListPresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * @author jackydu
 * @date 2019/2/13
 */
public class HomeListPresent extends BaseListPresenter<HomeListBean,HomeListDataBean> {

    private String type;

    @Override
    protected Observable<ApiResponse<HomeListBean>> provideObservable(boolean isRefresh) {
        return ApiFactory.INSTANCE.getApi(NewsApi.class).getNewsList(type,"",20).map(new Function<HomeListBean, ApiResponse<HomeListBean>>() {
            @Override
            public ApiResponse<HomeListBean> apply(HomeListBean homeListBean) throws Exception {
                return new ApiResponse<>(0,homeListBean,"success");
            }
        });
    }

    @Override
    protected List<HomeListDataBean> provideConverter(@NonNull HomeListBean homeListBean) {

        return homeListBean.getData();
    }

    @Override
    protected void onPrepareList(List<HomeListDataBean> list, boolean isRefresh) {

    }

    @Override
    protected void loadData(boolean isRefresh) {
        super.loadData(isRefresh);
    }
}

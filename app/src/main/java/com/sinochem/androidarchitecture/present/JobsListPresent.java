package com.sinochem.androidarchitecture.present;

import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.sinochem.androidarchitecture.apis.NewsApi;
import com.sinochem.androidarchitecture.enities.HomeListBean;
import com.sinochem.androidarchitecture.enities.JobsBean;
import com.sinochem.androidarchitecture.utils.TimeUtils;
import com.sinochem.corelibrary.api.ApiFactory;
import com.sinochem.corelibrary.api.ApiResponse;
import com.sinochem.corelibrary.base.list.BaseListPresenter;

import java.util.List;
import java.util.StringTokenizer;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * @author jackydu
 * @date 2019/3/5
 */
public class JobsListPresent extends BaseListPresenter<HomeListBean<JobsBean>,JobsBean> implements OnLoadmoreListener{

    public String cursor = "";

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {

    }

    @Override
    protected Observable<ApiResponse<HomeListBean<JobsBean>>> provideObservable(boolean isRefresh) {
        if (isRefresh) cursor = "";
        return ApiFactory.INSTANCE.getApi(NewsApi.class).getJobsList(cursor,10).map(new Function<HomeListBean<JobsBean>, ApiResponse<HomeListBean<JobsBean>>>() {
            @Override
            public ApiResponse<HomeListBean<JobsBean>> apply(HomeListBean<JobsBean> jobsBeanHomeListBean) throws Exception {
                return new ApiResponse<>(0,jobsBeanHomeListBean,"success");
            }
        });
    }

    @Override
    protected List<JobsBean> provideConverter(@NonNull HomeListBean<JobsBean> jobsBeanHomeListBean) {
        return jobsBeanHomeListBean.getData();
    }

    @Override
    protected void onPrepareList(List<JobsBean> list, boolean isRefresh) {
        if (list != null && list.size()>0){
            JobsBean jobsBean = list.get(list.size() - 1);
            cursor = String.valueOf(TimeUtils.parseTime("yyyy-MM-dd'T'HH:mm:ss.SSS",jobsBean.getPublishDate()));
        }
    }

    @Override
    public void loadData(boolean isRefresh) {
        super.loadData(isRefresh);
    }
}

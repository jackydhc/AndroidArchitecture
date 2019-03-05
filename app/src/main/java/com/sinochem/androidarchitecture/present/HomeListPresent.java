package com.sinochem.androidarchitecture.present;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.sinochem.androidarchitecture.apis.NewsApi;
import com.sinochem.androidarchitecture.enities.HomeListBean;
import com.sinochem.androidarchitecture.enities.HomeListDataBean;
import com.sinochem.androidarchitecture.enities.JobsBean;
import com.sinochem.androidarchitecture.enities.TitleBean;
import com.sinochem.androidarchitecture.enities.TopicBean;
import com.sinochem.androidarchitecture.utils.TimeUtils;
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
public class HomeListPresent extends BaseListPresenter<HomeListBean<HomeListDataBean>,HomeListDataBean> implements OnLoadmoreListener{

    private String type;
    private String lastCursor;

    public HomeListPresent(String type){
        this.type = type;
    }

    @Override
    protected Observable<ApiResponse<HomeListBean<HomeListDataBean>>> provideObservable(boolean isRefresh) {
        if (TextUtils.isEmpty(type)) return null;

        Observable<HomeListBean<HomeListDataBean>> newsList = ApiFactory.INSTANCE.getApi(NewsApi.class).getNewsList(type, isRefresh ? "" : lastCursor, 20);

        return newsList
                .map(new Function<HomeListBean<HomeListDataBean>, ApiResponse<HomeListBean<HomeListDataBean>>>() {
            @Override
            public ApiResponse<HomeListBean<HomeListDataBean>> apply(HomeListBean<HomeListDataBean> homeListBean) throws Exception {
                return new ApiResponse<HomeListBean<HomeListDataBean>>(0,homeListBean,"success");
            }
        });
    }

    @Override
    protected List<HomeListDataBean> provideConverter(@NonNull HomeListBean<HomeListDataBean> homeListBean) {
        LogUtils.d("homelistptr",homeListBean);
        return homeListBean.getData();
    }

    @Override
    protected void onPrepareList(List<HomeListDataBean> list, boolean isRefresh) {
        LogUtils.d("homelistptr",TimeUtils.formatTime());

        if (list != null && list.size() > 0) {
            HomeListDataBean t = list.get(list.size() - 1);
            String publishDate =t.getPublishDate();
            lastCursor = String.valueOf(TimeUtils.parseTime("yyyy-MM-dd'T'HH:mm:ss.SSS",publishDate));
        }
    }

    @Override
    public void loadData(boolean isRefresh) {
        super.loadData(isRefresh);

    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        LogUtils.d("homelist","onLoadmore");
        loadData(false);
    }
}

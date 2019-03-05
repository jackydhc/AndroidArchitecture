package com.sinochem.androidarchitecture.present;

import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.sinochem.androidarchitecture.apis.NewsApi;
import com.sinochem.androidarchitecture.enities.HomeListBean;
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
 * @date 2019/3/4
 */
public class TopicPresenter extends BaseListPresenter<HomeListBean<TopicBean>,TopicBean> implements OnLoadmoreListener {

    private String type;
    private String lastCursor;

    public TopicPresenter(String type){
        this.type = type;
    }

    @Override
    protected Observable<ApiResponse<HomeListBean<TopicBean>>> provideObservable(boolean isRefresh) {
        if (isRefresh) lastCursor = "";
        return ApiFactory.INSTANCE.getApi(NewsApi.class).getTopicList(lastCursor,10).map(new Function<HomeListBean<TopicBean>, ApiResponse<HomeListBean<TopicBean>>>() {
            @Override
            public ApiResponse<HomeListBean<TopicBean>> apply(HomeListBean<TopicBean> topicBeanHomeListBean) throws Exception {
                return new ApiResponse<>(0,topicBeanHomeListBean,"success");
            }
        });
    }

    @Override
    protected List<TopicBean> provideConverter(@NonNull HomeListBean<TopicBean> topicBeanHomeListBean) {
        return topicBeanHomeListBean.getData();
    }

    @Override
    protected void onPrepareList(List<TopicBean> list, boolean isRefresh) {
        if (list != null && list.size() > 0) {
            TopicBean t = list.get(list.size() - 1);
            String publishDate =t.getPublishDate();
            lastCursor = String.valueOf(TimeUtils.parseTime("yyyy-MM-dd'T'HH:mm:ss.SSS",publishDate));
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        loadData(false);
    }

    @Override
    public void loadData(boolean isRefresh) {
        super.loadData(isRefresh);
    }
}

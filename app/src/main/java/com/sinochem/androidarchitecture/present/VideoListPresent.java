package com.sinochem.androidarchitecture.present;

import android.support.annotation.NonNull;

import com.sinochem.androidarchitecture.apis.VideoApi;
import com.sinochem.androidarchitecture.enities.VideoBean;
import com.sinochem.androidarchitecture.enities.VideoDataBean;
import com.sinochem.corelibrary.api.ApiFactory;
import com.sinochem.corelibrary.api.ApiResponse;
import com.sinochem.corelibrary.base.list.BaseListPresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * @author jackydu
 * @date 2019/3/7
 */
public class VideoListPresent extends BaseListPresenter<VideoBean,VideoDataBean> {



    @Override
    protected Observable<ApiResponse<VideoBean>> provideObservable(boolean isRefresh) {
        return ApiFactory.INSTANCE.getApi(VideoApi.class).getFeed("").map(new Function<VideoBean, ApiResponse<VideoBean>>() {
            @Override
            public ApiResponse<VideoBean> apply(VideoBean videoBean) throws Exception {
                return new ApiResponse<>(0,videoBean,"");
            }
        });
    }

    @Override
    protected List<VideoDataBean> provideConverter(@NonNull VideoBean videoBean) {
        return null;
    }

    @Override
    protected void onPrepareList(List<VideoDataBean> list, boolean isRefresh) {

    }
}

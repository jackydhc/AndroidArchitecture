package com.sinochem.androidarchitecture.apis;

import com.sinochem.androidarchitecture.enities.HomeListBean;
import com.sinochem.corelibrary.api.ApiResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author jackydu
 * @date 2019/2/13
 */
public interface NewsApi {

    /**
     * 获取新闻列表页数据
     * @param type 新闻频道
     * @param cursor
     * @param pageSize
     * @return
     */
    @GET("/{type}")
    Observable<ApiResponse<HomeListBean>> getNewsList(@Path("type") String type,
                                                      @Query("lastCursor") String cursor,
                                                      @Query("pageSize") int pageSize
                                               );




}

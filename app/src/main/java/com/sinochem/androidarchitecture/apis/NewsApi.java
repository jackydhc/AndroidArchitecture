package com.sinochem.androidarchitecture.apis;

import com.sinochem.androidarchitecture.enities.HomeListBean;
import com.sinochem.androidarchitecture.enities.HomeListDataBean;
import com.sinochem.androidarchitecture.enities.JobsBean;
import com.sinochem.androidarchitecture.enities.TopicBean;
import com.sinochem.corelibrary.api.ApiResponse;


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
     * @param type 新闻频道   value :blockchain(区块链资讯)；topics(热门话题);news（科技动态）;technews(开发者资讯);jobs(招聘行情)
     * @param cursor
     * @param pageSize
     * @return
     */
    @GET("/{type}")
    Observable<HomeListBean<HomeListDataBean>> getNewsList(@Path("type") String type,
                                                           @Query("lastCursor") String cursor,
                                                           @Query("pageSize") int pageSize
                                               );



    /**
     * 获取新闻列表页数据
     * 新闻频道   value :blockchain(区块链资讯)；topics(热门话题);news（科技动态）;technews(开发者资讯);jobs(招聘行情)
     * @param cursor
     * @param pageSize
     * @return
     */
    @GET("/jobs")
    Observable<HomeListBean<JobsBean>> getJobsList(
                                                   @Query("lastCursor") String cursor,
                                                   @Query("pageSize") int pageSize
    );



    /**
     * 获取新闻列表页数据
     *  新闻频道   value :blockchain(区块链资讯)；topic(热门话题);news（科技动态）;technews(开发者资讯);jobs(招聘行情)
     * @param cursor
     * @param pageSize
     * @return
     */
    @GET("/topic")
    Observable<HomeListBean<TopicBean>> getTopicList(
                                                    @Query("lastCursor") String cursor,
                                                    @Query("pageSize") int pageSize
    );
}

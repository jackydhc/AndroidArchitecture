package com.sinochem.androidarchitecture.apis;

import com.sinochem.androidarchitecture.enities.VideoBean;
import com.sinochem.corelibrary.api.ApiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author jackydu
 * @date 2019/3/6
 */
public interface VideoApi {
    String BASE_URL = "http://baobab.kaiyanapp.com/api";
    /**
     * 首页精选
     * ("v2/feed?")
     */
    @GET
    Observable<VideoBean> getFeed(@Url String url);

    /**
     * 根据 nextPageUrl 请求数据下一页数据
     */
    @GET
    Observable getMoreData(@Url String url);



    /**
     * 根据item id获取相关视频
     * url v4/video/related?id=
     */
    @GET("v4/video/related?")
    Observable getRelatedData(@Query("id") Long id);
//
//    /**
//     * 获取分类
//     */
//    @GET("v4/categories")
//    fun getCategory(): Observable<ArrayList<CategoryBean>>
//
//    /**
//     * 获取分类详情List
//     */
//    @GET("v4/categories/videoList?")
//    fun getCategoryDetailList(@Query("id") id: Long): Observable<HomeBean.Issue>
//
//    /**
//     * 获取更多的 Issue
//     */
//    @GET
//    fun getIssueData(@Url url: String): Observable<HomeBean.Issue>
//
//    /**
//     * 获取全部排行榜的Info（包括，title 和 Url）
//     */
//    @GET("v4/rankList")
//    fun getRankList():Observable<TabInfoBean>
//
//    /**
//     * 获取搜索信息
//     */
//    @GET("v1/search?&num=10&start=10")
//    fun getSearchData(@Query("query") query :String) : Observable<HomeBean.Issue>
//
//    /**
//     * 热门搜索词
//     */
//    @GET("v3/queries/hot")
//    fun getHotWord():Observable<ArrayList<String>>
//
//    /**
//     * 关注
//     */
//    @GET("v4/tabs/follow")
//    fun getFollowInfo():Observable<HomeBean.Issue>
//
//    /**
//     * 作者信息
//     */
//    @GET("v4/pgcs/detail/tab?")
//    fun getAuthorInfo(@Query("id") id: Long):Observable<AuthorInfoBean>

}

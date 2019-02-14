package com.sinochem.androidarchitecture.apis;

import com.sinochem.corelibrary.api.ApiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author jackydu
 * @date 2019/1/16
 */
public interface UserApi {

    @GET("/topic")
    Observable<ApiResponse<User>> getUserInfo(@Query("lastCursor") String latCursor,
                                              @Query("pageSize") int pageSize);


}

package com.sinochem.androidarchitecture.apis;

import com.sinochem.corelibrary.api.ApiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author jackydu
 * @date 2019/1/16
 */
public interface UserApi {

    @GET()
    Observable<ApiResponse<User>> getUserInfo();

}

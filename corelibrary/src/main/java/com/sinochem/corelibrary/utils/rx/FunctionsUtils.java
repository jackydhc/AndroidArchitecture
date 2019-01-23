package com.sinochem.corelibrary.utils.rx;

import com.facebook.stetho.common.LogUtil;
import com.sinochem.corelibrary.api.ApiConstants;
import com.sinochem.corelibrary.api.ApiResponse;

import io.reactivex.functions.Function;


/**
 * @author jackydu
 * @date 2019/1/16
 */
public class   FunctionsUtils {


    public static <T extends ApiResponse> Function<T,T> filterResponse(){
        return apiRespose -> {
            if (apiRespose == null ) throw new NullResponceExe();
            if (apiRespose.code == ApiConstants.LOGIN_EXPIRE_CODE) throw new LoginExpireExep();
            return apiRespose;
        };
    }

    public static <E> Function<ApiResponse<E> , E> extractResponse() {
        return apiResponse -> {
            if (apiResponse == null ) throw new NullResponceExe();
            if (apiResponse.code == ApiConstants.LOGIN_EXPIRE_CODE) throw new LoginExpireExep();

            if (apiResponse.code != 0) {
                throw new NullResponceExe(apiResponse.msg);
            }

            //会判断data
            if (apiResponse.data == null) {
                throw new NullResponceExe(apiResponse.msg);
            }
            LogUtil.d("apiResponse:"+apiResponse);
            return apiResponse.data;
        };
    }

}

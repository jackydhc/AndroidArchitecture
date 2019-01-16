package com.sinochem.corelibrary.utils.rx;

import com.facebook.stetho.common.LogUtil;
import com.sinochem.corelibrary.api.ApiConstants;
import com.sinochem.corelibrary.api.ApiResponse;


import rx.functions.Func1;

/**
 * @author jackydu
 * @date 2019/1/16
 */
public class Functions {

    public static <T extends ApiResponse> Func1<T,T> filterResponse(){
        return apiRespose -> {
            if (apiRespose == null ) throw new NullResponceExe();
            if (apiRespose.code == ApiConstants.LOGIN_EXPIRE_CODE) throw new LoginExpireExep();
            return apiRespose;
        };
    }

    public static <T> Func1<ApiResponse<T>, T> extractResponse() {
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

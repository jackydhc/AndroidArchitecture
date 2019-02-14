package com.sinochem.androidarchitecture;


import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.sinochem.corelibrary.CoreApplication;
import com.sinochem.corelibrary.utils.GlobalConfig;


/**
 * @author jackydu
 * @date 2019/1/16
 */
public class SinoApplication extends CoreApplication {

    @Override
    public GlobalConfig initGlobalConfig() {
        return GlobalConfig.newBuilder().baseUrl("https://api.readhub.cn").setTimeOut(30).setUA(getUA()).build();
    }

    private String getUA(){
        StringBuilder builder = new StringBuilder();
        builder.append("Android").append(PhoneUtils.getIMEI()).append(DeviceUtils.getSDKVersion()).append(DeviceUtils.getModel());
        LogUtils.d(SinoApplication.class.getSimpleName(),builder.toString());
        return builder.toString();
    }
}

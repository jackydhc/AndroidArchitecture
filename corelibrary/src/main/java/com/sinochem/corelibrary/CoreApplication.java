package com.sinochem.corelibrary;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDexApplication;

import com.sinochem.corelibrary.api.ApiConstants;
import com.sinochem.corelibrary.api.AppDelegate;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * @author jackydu
 * @date 2018/8/15
 */
public abstract class CoreApplication extends MultiDexApplication implements AppDelegate{

    private static CoreApplication instance;
    private RefWatcher refWatcher;

    public static Context getBaseApplication() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        refWatcher = LeakCanary.install(this);

    }
    @Nullable
    public static RefWatcher getRefWatcher() {
        return instance.refWatcher;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }
}

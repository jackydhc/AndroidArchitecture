package com.sinochem.corelibrary.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sinochem.corelibrary.R;


/**
 * Created by zhaozhe on 2017/6/13.
 */
public class ContainerOverlayToolbarActivity extends ContainerToolbarActivity {


    public static Intent newInstance(Context context, String title, String fname) {
        return new Intent(context, ContainerOverlayToolbarActivity.class).putExtra(TITLE, title)
                .putExtra(FRAGMENT_NAME, fname);
    }

    public static Intent newInstance(Context context, String title, String fname, Bundle bundle) {
        return newInstance(context, title, fname, bundle, false);
    }

    public static Intent newInstance(Context context, String title, String fname, Bundle bundle, boolean startMain) {
        return new Intent(context, ContainerOverlayToolbarActivity.class).putExtra(TITLE, title)
                .putExtra(FRAGMENT_NAME, fname)
                .putExtra(FRAGMENT_BUNDLE, bundle)
                .putExtra(BaseActivity.EXTRA_START_MAIN, startMain);
    }

    @Override public int provideLayoutId() {
        return R.layout.activity_container_toolbar;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

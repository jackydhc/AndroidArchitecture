package com.sinochem.corelibrary.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.MenuItem;

import com.sinochem.corelibrary.R;


/**
 * 适用于单Fragment
 * <p>
 * author: jackydu
 * date: on 17/3/24 19:00
 * description:
 */
public class ContainerToolbarActivity extends SwipeBackActivity implements BackHandledInterface {
    public static final String TITLE = "title";
    public static final String FRAGMENT_NAME = "fname";
    public static final String FRAGMENT_BUNDLE = "fbundle";

    private BackPressedInterface mBackHandledFragment;

    public static Intent newInstance(Context context, String title, String fname) {
        return new Intent(context, ContainerToolbarActivity.class).putExtra(TITLE, title)
                .putExtra(FRAGMENT_NAME, fname);
    }

    public static Intent newInstance(Context context, String title, String fname, Bundle bundle) {
        return new Intent(context, ContainerToolbarActivity.class).putExtra(TITLE, title)
                .putExtra(FRAGMENT_NAME, fname)
                .putExtra(FRAGMENT_BUNDLE, bundle);
    }


    @Override public int provideLayoutId() {
        return R.layout.activity_container_toolbar;
    }

    @Override protected void initOnCreate(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra(TITLE);
        String fname = getIntent().getStringExtra(FRAGMENT_NAME);
        Bundle bundle = getIntent().getBundleExtra(FRAGMENT_BUNDLE);

        if (TextUtils.isEmpty(fname)) {
            finish();
            return;
        }

        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }

        if (bundle != null) {
            Fragment fragment = Fragment.instantiate(this, fname, bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return;
        }

        Fragment fragment = Fragment.instantiate(this, fname);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }


    @Override protected void initActionBar(ActionBar actionBar) {
        actionBar.setDisplayUseLogoEnabled(true);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override public void setSelectedFragment(BackPressedInterface fragment) {
        this.mBackHandledFragment = fragment;
    }

    @Override public void onBackPressed() {
        if (mBackHandledFragment == null || !mBackHandledFragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void loginExpire() {

    }
}

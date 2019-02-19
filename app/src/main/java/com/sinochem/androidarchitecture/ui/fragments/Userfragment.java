package com.sinochem.androidarchitecture.ui.fragments;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sinochem.androidarchitecture.R;
import com.sinochem.androidarchitecture.present.UserPresent;
import com.sinochem.corelibrary.fragments.BaseFragment;
import com.sinochem.corelibrary.utils.rx.TransFormUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author jackydu
 * @date 2019/2/1
 */
public class Userfragment extends BaseFragment<UserPresent> implements OnRefreshListener {
    @BindView(R.id.nestview)
    NestedScrollView nestview;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;

    @Override
    public int provideLayoutId() {
        return R.layout.sino_fragment_user;
    }

    @Override
    protected void initOnCreateView() {
        smartRefresh.setOnRefreshListener(this);
        smartRefresh.autoRefresh();
    }

    @Override
    protected UserPresent providePresent() {
        UserPresent userPresent = new UserPresent();
        return userPresent;
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void loginExpire() {

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        LogUtils.d("userFragment","onrefresh");
        Observable.interval(3, TimeUnit.SECONDS).observeOn(Schedulers.computation()).compose(this.bindToLifecycle()).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        smartRefresh.finishRefresh();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(Userfragment.class.getSimpleName(),"ondestroy");
    }
}

package com.sinochem.androidarchitecture;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.LoginFilter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.sinochem.androidarchitecture.ui.fragments.HomeFragment;
import com.sinochem.androidarchitecture.ui.fragments.HomeListFragment;
import com.sinochem.androidarchitecture.ui.fragments.Userfragment;
import com.sinochem.androidarchitecture.ui.fragments.ZhaopingFragment;
import com.sinochem.corelibrary.base.BaseActivity;
import com.squareup.haha.perflib.Main;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;
    private FragmentManager fm;
    private int lastIndex;
    private Fragment lastFragment;


    @Override
    public int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        if (fm == null) fm = getSupportFragmentManager();
        Fragment home = fm.getFragment(new Bundle(), "home");
        if (home == null) home = new HomeFragment();
        fm.beginTransaction().add(R.id.container,home,"home").commit();
        lastFragment = home;
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void loginExpire() {

    }
    @OnClick({R.id.rb_home, R.id.rb_mine})
    public void onViewClicked(View view) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.rb_home:
               if (lastIndex == 0)return;
               lastIndex = 0;
                Fragment home = fm.findFragmentByTag( HomeFragment.class.getSimpleName());
                LogUtils.d("Userfragment",home);
                if (home == null) home = new HomeFragment();
                setTab(fragmentTransaction,home,HomeFragment.class.getSimpleName());
                break;
            case R.id.rb_mine:
                if (lastIndex == 1)return;
                lastIndex = 1;
                Fragment user = fm.findFragmentByTag(ZhaopingFragment.class.getSimpleName());
                LogUtils.d("Userfragment",user);
                if (user == null) user = new ZhaopingFragment();
                setTab(fragmentTransaction,user, ZhaopingFragment.class.getSimpleName());
                break;
        }
    }

    private void setTab(FragmentTransaction transaction,Fragment fragment,String tag){
        if (lastFragment != null) {
            transaction.hide(lastFragment);
        }
        if (fragment.isAdded()) transaction.show(fragment);
        else transaction.add(R.id.container,fragment,tag);
        transaction.commit();
        lastFragment = fragment;
    }
}

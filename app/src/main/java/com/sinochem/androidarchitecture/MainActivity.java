package com.sinochem.androidarchitecture;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.sinochem.androidarchitecture.ui.fragments.HomeFragment;
import com.sinochem.androidarchitecture.ui.fragments.Userfragment;
import com.sinochem.corelibrary.base.BaseActivity;
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


    @Override
    public int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        if (fm == null) fm = getSupportFragmentManager();
        Fragment home = fm.getFragment(new Bundle(), "home");
        if (home == null) home = new HomeFragment();
        fm.beginTransaction().add(home,"home").commit();
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void loginExpire() {

    }
    @OnClick({R.id.rb_home, R.id.rb_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
               if (lastIndex == 0)return;
               lastIndex = 0;
                Fragment home = fm.getFragment(new Bundle(), "home");
                if (home == null) {
                    home = new HomeFragment();
                    fm.beginTransaction().add(R.id.content,home,"home").commit();
                }else fm.beginTransaction().show(home).commit();
                break;
            case R.id.rb_mine:
                if (lastIndex == 1)return;
                lastIndex = 1;
                Fragment user = fm.getFragment(new Bundle(), "user");
                if (user == null) {
                    user = new Userfragment();
                    fm.beginTransaction().add(R.id.content,user,"user").commit();
                }else fm.beginTransaction().show(user).commit();
                break;
        }
    }
}

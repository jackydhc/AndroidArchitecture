package com.sinochem.androidarchitecture.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.sinochem.androidarchitecture.R;
import com.sinochem.androidarchitecture.contracts.HomeContract;
import com.sinochem.androidarchitecture.present.HomePresent;
import com.sinochem.corelibrary.fragments.BaseFragmentPagerAdapter;
import com.sinochem.corelibrary.fragments.BaseMultiFragment;

import butterknife.BindView;


/**
 * @author jackydu
 * @date 2019/2/1
 */
public class HomeFragment extends BaseMultiFragment<HomePresent> implements HomeContract.IView{
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void loadData() {

    }

    @Override
    public int provideLayoutId() {
        return R.layout.sino_fragment_home;
    }

    @Override
    protected void initOnCreateView() {
        viewPager.setAdapter(new HomeAdapter(getChildFragmentManager(),getContext()));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected HomePresent providePresent() {
        return new HomePresent();
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void loginExpire() {

    }

    @Override
    public void showAD() {

    }

    @Override
    public void showMenu() {

    }

    private class HomeAdapter extends BaseFragmentPagerAdapter {

        String[] titles = new String[]{"hello","android","ios","html"};
        private Context mContext;
        private FragmentManager fm;
        public HomeAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.mContext = context;
            this.fm = fm;
        }


        @Override
        public int getCount() {
            return titles.length;
        }
//
        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putString("type",titles[position]);
            LogUtils.d("homeFragment","getItem:"+position);
            switch (titles[position]){
                case "android":
                    return Fragment.instantiate(mContext,Userfragment.class.getName(),bundle);
                case "ios":
                    return Fragment.instantiate(mContext,Userfragment.class.getName(),bundle);
                    default:
                        return Fragment.instantiate(mContext,Userfragment.class.getName(),bundle);
            }
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            return titles[position];

        }

    }


}

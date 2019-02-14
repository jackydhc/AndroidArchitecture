package com.sinochem.androidarchitecture.ui.fragments;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sinochem.androidarchitecture.R;
import com.sinochem.androidarchitecture.present.HomePresent;
import com.sinochem.corelibrary.fragments.BaseMultiFragment;

import butterknife.BindView;

/**
 * @author jackydu
 * @date 2019/2/1
 */
public class HomeFragment extends BaseMultiFragment<HomePresent> {
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
        viewPager.setAdapter(new HomeAdapter());
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

    private class HomeAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return false;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return super.getItemPosition(object);
        }
    }

}

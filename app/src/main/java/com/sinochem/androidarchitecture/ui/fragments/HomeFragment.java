package com.sinochem.androidarchitecture.ui.fragments;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import com.sinochem.androidarchitecture.R;
import com.sinochem.androidarchitecture.contracts.HomeContract;
import com.sinochem.androidarchitecture.present.HomePresent;
import com.sinochem.corelibrary.fragments.BaseMultiFragment;



/**
 * @author jackydu
 * @date 2019/2/1
 */
public class HomeFragment extends BaseMultiFragment<HomePresent> implements HomeContract.IView{
//    @BindView(R.id.tab_layout)
//    TabLayout tabLayout;
//    @BindView(R.id.view_pager)
//    ViewPager viewPager;

    @Override
    protected void loadData() {

    }

    @Override
    public int provideLayoutId() {
        return R.layout.layout_user;
    }

    @Override
    protected void initOnCreateView() {
//        viewPager.setAdapter(new HomeAdapter());
//        tabLayout.setupWithViewPager(viewPager);

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

    private class HomeAdapter extends FragmentStatePagerAdapter{

        String[] titles = new String[]{"hello","android","ios","html"};
        private Context mContext;
        public HomeAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (titles[position]){
                case "android":
                    return Fragment.instantiate(mContext,Userfragment.class.getName());
                case "ios":
                    return Fragment.instantiate(mContext,Userfragment.class.getName());
                    default:
                        return Fragment.instantiate(mContext,Userfragment.class.getName());
            }
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return false;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            return titles[position];

        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return super.getItemPosition(object);
        }


    }


}

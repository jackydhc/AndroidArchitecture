package com.sinochem.androidarchitecture.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.blankj.utilcode.util.LogUtils;
import com.sinochem.androidarchitecture.R;
import com.sinochem.androidarchitecture.contracts.HomeContract;
import com.sinochem.androidarchitecture.enities.TitleBean;
import com.sinochem.androidarchitecture.present.HomePresent;
import com.sinochem.corelibrary.fragments.BaseFragmentPagerAdapter;
import com.sinochem.corelibrary.fragments.LazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author jackydu
 * @date 2019/2/1
 */
public class HomeFragment extends LazyFragment<HomePresent> implements HomeContract.IView{
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
        LogUtils.d("HomeFragment","initOnCreateView");
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

        List<TitleBean> titles = new ArrayList<>();
        private Context mContext;
        private FragmentManager fm;
        public HomeAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.mContext = context;
            this.fm = fm;
            initTitle();
        }
        //value :blockchain(区块链资讯)；topics(热门话题);news（科技动态）;technews(开发者资讯);jobs(招聘行情)
        private void initTitle(){
            if (titles.size() == 0){
                titles.add(new TitleBean("查询",TitleBean.TYPE_FUZZY));
                titles.add(new TitleBean("热门话题",TitleBean.TYPE_TOPIC));
                titles.add(new TitleBean("科技动态",TitleBean.TYPE_NEWS));
                titles.add(new TitleBean("区块链资讯",TitleBean.TYPE_BLOCKCHAIN));
                titles.add(new TitleBean("开发者资讯",TitleBean.TYPE_TECHNEWS));
                titles.add(new TitleBean("招聘行情",TitleBean.TYPE_JOBS));
            }
        }


        @Override
        public int getCount() {
            return titles.size();
        }
//
        @Override
        public Fragment getItem(int position) {
            String type = titles.get(position).type;
            LogUtils.d("homeFragment","getItem:"+position);
            Fragment fragment = null;
            switch (type){
                case TitleBean.TYPE_BLOCKCHAIN:
                case TitleBean.TYPE_NEWS:
                case TitleBean.TYPE_TECHNEWS:
                    fragment =  HomeListFragment.newInstance(type);
                    break;
                case TitleBean.TYPE_TOPIC:
                    fragment = HomeTopicFragment.newInstance(type);
                    break;
                case TitleBean.TYPE_JOBS:
                    fragment = HomeJobsFragment.newInstance(type);
                    break;
                case TitleBean.TYPE_FUZZY:
                    fragment = Fragment.instantiate(mContext,FuzzyFragment.class.getName());
                    break;
                default:fragment = HomeListFragment.newInstance(type);
                break;

            }
            return fragment;
        }


        @Override
        public String getItemTag(int position) {
            return titles.get(position).type;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            return titles.get(position).title;

        }

    }


}

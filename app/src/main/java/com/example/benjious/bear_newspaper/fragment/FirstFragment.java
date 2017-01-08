package com.example.benjious.bear_newspaper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.benjious.bear_newspaper.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static butterknife.ButterKnife.Finder.VIEW;

/**
 * Created by Benjious on 2016/12/21.
 */

public class FirstFragment extends Fragment {
    @Bind(R.id.main_tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    public static final int ONE =0 ;
    public static final int TWO =1 ;
    public static final int THREE =2 ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.first_fragment, null);
        ButterKnife.bind(view);
        initView();
        return view;
    }

    private void initView(){
        setUpViewPager(mViewPager);
        mTabLayout.addTab(mTabLayout.newTab().setText("头条"));
        mTabLayout.addTab(mTabLayout.newTab().setText("NBA"));
        mTabLayout.addTab(mTabLayout.newTab().setText("汽车"));

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void setUpViewPager(ViewPager upViewPager){
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        myPagerAdapter.addFragment(FirstListFragment.newInstance(ONE),"头条");
        myPagerAdapter.addFragment(FirstListFragment.newInstance(TWO),"NBA");
        myPagerAdapter.addFragment(FirstListFragment.newInstance(THREE),"汽车");
        upViewPager.setAdapter(myPagerAdapter);
    }

    public static class  MyPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentsTitle = new ArrayList<>();

        public void addFragment(Fragment fragment,String title){
            mFragments.add(fragment);
            mFragmentsTitle.add(title);
        }

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentsTitle.get(position);
        }


    }
}

package com.umeng.soexample.materialdesign.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/5.
 */
public class TabViewPagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<Fragment> fragments;
    private final String[] titles;

    public TabViewPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragments,String[] titles) {
        super(fragmentManager);
        this.fragments = fragments;
        this.titles = titles;
    }
    

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

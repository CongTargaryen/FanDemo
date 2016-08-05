package com.example.congzhang.fanyou.xiaoxi_fragement;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by CongZhang on 2016/7/16.
 */
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter{

    private ArrayList<Fragment> fragments;



    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.fragments = list;

    }
}

package com.example.congzhang.fanyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.congzhang.fanyou.fabu.FaBuActivity;
import com.example.congzhang.fanyou.main_viewpager_fragment.FanTuanFragment;
import com.example.congzhang.fanyou.main_viewpager_fragment.MeFragment;
import com.example.congzhang.fanyou.main_viewpager_fragment.ShouYeFragment;
import com.example.congzhang.fanyou.main_viewpager_fragment.XiaoXiFragment;
import com.example.congzhang.fanyou.xiaoxi_fragement.MyFragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by CongZhang on 2016/7/19.
 */
public class AppMainActivity extends FragmentActivity{

    private ViewPager mPager;
    private ArrayList<Fragment> fragmentArrayList;
    LinearLayout myfabu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity_viewpager);
        myfabu = (LinearLayout) findViewById(R.id.myfabu);
        InitViewPager();
        myfabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyApplication.getInstance(), FaBuActivity.class);
                startActivity(intent);
            }
        });


    }

    private void InitViewPager() {
        mPager = (ViewPager) findViewById(R.id.viewpager);
        fragmentArrayList = new ArrayList<Fragment>();
        ShouYeFragment shouYeFragment = new ShouYeFragment();
        FanTuanFragment fanTuanFragment = new FanTuanFragment();
        XiaoXiFragment xiaoXiFragment = new XiaoXiFragment();
        MeFragment meFragment = new MeFragment();
        fragmentArrayList.add(shouYeFragment);
        fragmentArrayList.add(fanTuanFragment);
        fragmentArrayList.add(xiaoXiFragment);
        fragmentArrayList.add(meFragment);

        mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentArrayList));
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}

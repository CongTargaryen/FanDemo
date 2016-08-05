package com.example.congzhang.fanyou.main_viewpager_fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.congzhang.fanyou.R;
import com.example.congzhang.fanyou.xiaoxi_fragement.MyFanYouFragment;
import com.example.congzhang.fanyou.xiaoxi_fragement.MyFragmentPagerAdapter;
import com.example.congzhang.fanyou.xiaoxi_fragement.MyXiaoXiFragment;
import com.example.congzhang.fanyou.xiaoxi_fragement.xiaoxi_listview.MyRcycAdapter;
import com.example.congzhang.fanyou.xiaoxi_fragement.xiaoxi_listview.MyXiaoXiListviewAdapter;
import com.example.congzhang.fanyou.xiaoxi_fragement.xiaoxi_listview.XiaoXiItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CongZhang on 2016/7/19.
 */
public class XiaoXiFragment extends Fragment{

    private List<XiaoXiItem> mlist;
    private RecyclerView recyclerView;
    private Button button_left;
    private Button button_right;
    private LayoutInflater mInflater;
    private MyRcycAdapter myRcycAdapter;
    private SwipeMenuListView listview;
    private ViewPager mPager;
    private LinearLayout linearLayout;
    private MyXiaoXiListviewAdapter myXiaoXiAdapter;

    private ArrayList<Fragment> fragmentArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xiaoxi_viewpager, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.layout_radio) ;
        button_left = (Button) view.findViewById(R.id.btn_nav_followed);
        button_right = (Button) view.findViewById(R.id.btn_nav_follows);

        mPager = (ViewPager) view.findViewById(R.id.view);
        fragmentArrayList = new ArrayList<Fragment>();
        MyXiaoXiFragment xiaoXiFragment = new MyXiaoXiFragment();
        MyFanYouFragment myFanYouFragment = new MyFanYouFragment();
        fragmentArrayList.add(xiaoXiFragment);
        fragmentArrayList.add(myFanYouFragment);

        mPager.setAdapter(new MyFragmentPagerAdapter(getFragmentManager(), fragmentArrayList));
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    linearLayout.setBackgroundResource(R.drawable.radio_right);
                    button_left.setTextColor(Color.parseColor("#ffffffff"));
                    button_right.setTextColor(Color.parseColor("#ff00a9fd"));
                }
                else {
                    linearLayout.setBackgroundResource(R.drawable.radio_left);
                    button_left.setTextColor(Color.parseColor("#ff00a9fd"));
                    button_right.setTextColor(Color.parseColor("#ffffffff"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.setBackgroundResource(R.drawable.radio_right);
                button_left.setTextColor(Color.parseColor("#ffffffff"));
                button_right.setTextColor(Color.parseColor("#ff00a9fd"));
                mPager.setCurrentItem(0);
            }
        });
        button_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.setBackgroundResource(R.drawable.radio_left);
                button_left.setTextColor(Color.parseColor("#ff00a9fd"));
                button_right.setTextColor(Color.parseColor("#ffffffff"));
                mPager.setCurrentItem(1);
            }
        });
        return view;
    }

}

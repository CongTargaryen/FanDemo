package com.example.congzhang.fanyou.xiaoxi_fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.congzhang.fanyou.R;

/**
 * Created by CongZhang on 2016/7/16.
 */
public class MyFanYouFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.myfanyou_fragment, container, false);
        return view;
    }
}

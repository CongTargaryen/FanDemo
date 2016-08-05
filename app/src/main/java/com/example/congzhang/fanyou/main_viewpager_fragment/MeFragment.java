package com.example.congzhang.fanyou.main_viewpager_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.congzhang.fanyou.MyApplication;
import com.example.congzhang.fanyou.R;
import com.example.congzhang.fanyou.fabu.FaBuActivity;

/**
 * Created by CongZhang on 2016/7/19.
 */
public class MeFragment extends Fragment{
    @Nullable


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newme_fragment, container,false);


        return view;
    }
}

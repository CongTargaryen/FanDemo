package com.example.congzhang.fanyou.xiaoxi_fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.congzhang.fanyou.MyApplication;
import com.example.congzhang.fanyou.R;
import com.example.congzhang.fanyou.xiaoxi_fragement.xiaoxi_listview.MyRcycAdapter;
import com.example.congzhang.fanyou.xiaoxi_fragement.xiaoxi_listview.XiaoXiItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CongZhang on 2016/7/16.
 */
public class MyXiaoXiFragment extends Fragment{

    private RecyclerView recyclerView;
    private List<XiaoXiItem> mlist;
    private MyRcycAdapter myRcycAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initData();
        View view = inflater.inflate(R.layout.myxiaoxi_fragement, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        myRcycAdapter = new MyRcycAdapter(MyApplication.getInstance(), mlist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getInstance(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myRcycAdapter);
        return view;
    }

    private void initData() {
        mlist = new ArrayList<XiaoXiItem>();
        XiaoXiItem item = new XiaoXiItem();
        item.setTitle("ssssss");
        item.setName("ddddd");
        for (int i = 0; i < 10; i++) {
            mlist.add(item);
        }
}
}

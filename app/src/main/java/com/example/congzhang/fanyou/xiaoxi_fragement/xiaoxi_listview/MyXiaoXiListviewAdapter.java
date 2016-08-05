package com.example.congzhang.fanyou.xiaoxi_fragement.xiaoxi_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.congzhang.fanyou.MyApplication;
import com.example.congzhang.fanyou.R;

import java.util.List;

/**
 * Created by CongZhang on 2016/7/16.
 */
public class MyXiaoXiListviewAdapter extends BaseAdapter{

    private LayoutInflater mInflater;
    private List<XiaoXiItem> mylist;


    public MyXiaoXiListviewAdapter(Context context, List<XiaoXiItem> mylist){
        System.out.println("ddddddddddddddddddddddddddddddddd");
        this.mInflater = LayoutInflater.from(context);
        System.out.println("sssssss");
        System.out.println("77777777");
        this.mylist = mylist;
        System.out.println("77777777");
    }

    public int getCount() {
        System.out.println("99999999999");
        return mylist.size();
    }

    @Override
    public Object getItem(int i) {
        System.out.println("888888888");
        return mylist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = null;
        if (view != null) {
            view1 = view;
        } else {
            view1 = View.inflate(MyApplication.getInstance(), R.layout.myfanyou_revcycview_item, null);
        }
        return view;
    }
}

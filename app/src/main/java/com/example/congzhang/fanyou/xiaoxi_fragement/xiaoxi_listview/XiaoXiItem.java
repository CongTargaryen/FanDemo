package com.example.congzhang.fanyou.xiaoxi_fragement.xiaoxi_listview;

import android.graphics.Bitmap;

/**
 * Created by CongZhang on 2016/7/16.
 */
public class XiaoXiItem {
    private String name;
    private String title;
//    private Bitmap n;

//    public Bitmap getN() {
//        return n;
//    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

//    public void setN(Bitmap n) {
//        this.n = n;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

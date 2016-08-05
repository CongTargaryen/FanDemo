package com.example.congzhang.fanyou.fabu;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by CongZhang on 2016/7/22.
 */
public class ChuanDi {
    public static String caiyao;
    public static String huodong;
    public static FaBuItem faBuItem;
    public static String name;//发起人姓名
    public static String title;//主题
    public static String describe;//描述
    public static String phone;//联系方式
    public static String time_s;//时间(分）
    public static String time_y;//月日
    public static String fangshi;//方式
    public static String money;//价格
    public static BmobFile imag;//图片

    public void chongZhi() {
        caiyao = null;
        huodong = null;
        name = null;
        title = null;
        describe = null;
        time_s = null;
        time_y = null;
        fangshi = null;
        money = null;
        imag = null;
    }

}

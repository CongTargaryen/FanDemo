package com.example.congzhang.fanyou.bmob;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by CongZhang on 2016/8/1.
 */
public class PutFanTuan extends BmobObject{
    private String name;//发起人姓名
    private String title;//主题
    private String describe;//描述
    private String phone;//联系方式
    private String time_s;//时间(分）
    private String time_y;//月日
    private String fangshi;//方式
    private String money;//价格
    private BmobFile imag;//图片
    public List<String> people;//参加的人
    public String getTime_y() {
        return time_y;
    }

    public void setTime_y(String time_y) {
        this.time_y = time_y;
    }

    public String getFangshi() {
        return fangshi;
    }

    public void setFangshi(String fangshi) {
        this.fangshi = fangshi;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public PutFanTuan() {
        people = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getPeople() {
        return people;
    }

    public String getDescribe() {
        return describe;
    }

    public String getPhone() {
        return phone;
    }

    public String getTime() {
        return time_s;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTime(String time) {
        this.time_s = time;
    }

}

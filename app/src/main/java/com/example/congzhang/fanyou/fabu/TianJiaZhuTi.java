package com.example.congzhang.fanyou.fabu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.congzhang.fanyou.MyApplication;
import com.example.congzhang.fanyou.R;
import com.example.congzhang.fanyou.bmob.PutFanTuan;
import com.example.congzhang.fanyou.sms.bean.User;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by CongZhang on 2016/7/23.
 * 生成饭局
 */
public class TianJiaZhuTi extends Activity{
    private EditText sc_zhuti;
    private ImageView sc_descirbe;
    private TextView sc_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scfanjuactivity);
        initData();
        sc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shangChuanShuJu();
                finish();
            }
        });
    }

    private void initData() {
        sc_zhuti = (EditText) findViewById(R.id.sc_zhuti);
        sc_button = (TextView) findViewById(R.id.sc_button);
        sc_descirbe = (ImageView) findViewById(R.id.sc_describe);
    }

//    上传数据
    private void shangChuanShuJu() {
        String a = sc_zhuti.getText().toString();
        PutFanTuan myFanTuan = new PutFanTuan();
        BmobUser user = BmobUser.getCurrentUser();
        myFanTuan.setName(user.getUsername());
        myFanTuan.setDescribe(ChuanDi.caiyao);
        myFanTuan.setFangshi(ChuanDi.fangshi);
        myFanTuan.setPhone(user.getMobilePhoneNumber());
        myFanTuan.setTime(ChuanDi.time_s);
        myFanTuan.setTime_y(ChuanDi.time_y);
        myFanTuan.setTitle(a);
        myFanTuan.setMoney(ChuanDi.money);
        myFanTuan.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){

                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }
}

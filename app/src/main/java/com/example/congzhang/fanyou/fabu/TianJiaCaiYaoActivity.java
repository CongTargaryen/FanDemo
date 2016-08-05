package com.example.congzhang.fanyou.fabu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.congzhang.fanyou.MyApplication;
import com.example.congzhang.fanyou.R;

/**
 * Created by CongZhang on 2016/7/22.
 */
public class TianJiaCaiYaoActivity extends Activity{
    private EditText caiyao;
    private EditText huodong;
    private TextView fanhui;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tianjia_fabu_activity);
        caiyao = (EditText) findViewById(R.id.caiyao);
        huodong = (EditText) findViewById(R.id.huodong);
        fanhui = (TextView) findViewById(R.id.fanhuifabu);
        ChuanDi.caiyao = caiyao.getText().toString();
        ChuanDi.huodong = caiyao.getText().toString();
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = View.inflate(MyApplication.getInstance(), R.layout.fabu_fragment, null);
                textView = (TextView) view1.findViewById(R.id.changedeskshuoming);
                textView.setText(caiyao.getText().toString());
                Intent intent = new Intent(MyApplication.getInstance(), FaBuActivity.class);
                startActivity(intent);
            }
        });

    }
}

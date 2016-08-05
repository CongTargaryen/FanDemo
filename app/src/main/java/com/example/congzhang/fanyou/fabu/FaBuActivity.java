package com.example.congzhang.fanyou.fabu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.congzhang.fanyou.MyApplication;
import com.example.congzhang.fanyou.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by CongZhang on 2016/7/20.
 */
public class FaBuActivity extends Activity implements View.OnClickListener{

    private TextView relativeLayout;
    private CardView cardView;
    private EditText editText;
    private FaBuItem faBuItem;
    private int selectedFruitIndex = 0;
    private static int tianjiarenshu = 0;//添加人数
    private TextView renShu;
    private TextView riQi;
    private TextView zaoWan;
    private TextView shiJian;
    private TextView fangShi;
    private TextView money;
    private TextView changedeskshuoming;
    private CircleImageView circleImageView1;
    private CircleImageView circleImageView2;
    private CircleImageView circleImageView3;
    private CircleImageView circleImageView4;
    private CircleImageView circleImageView5;
    private CircleImageView circleImageView6;
    private CircleImageView circleImageView7;
    private CircleImageView circleImageView8;
    private LinearLayout linearLayout;
    private LinearLayout linerjiage;
    private LinearLayout linerfangshi;
    private LinearLayout linershijian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fabu_fragment);
        faBuItem = new FaBuItem();
        initData();
        chongHuiTouXiang();
        if (ChuanDi.huodong != null||ChuanDi.caiyao != null) {
            faBuItem.caiyao = ChuanDi.caiyao;
            faBuItem.huodong = ChuanDi.huodong;
        }

    }
    //重绘两边小图
    private void chongHuiTouXiang() {
        ViewTreeObserver vto = circleImageView2.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                circleImageView2.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                circleImageView2.getHeight();
                circleImageView2.getWidth();
                ViewGroup.LayoutParams layoutParams = circleImageView5.getLayoutParams();
                layoutParams.height = circleImageView2.getMeasuredHeight();
                layoutParams.width = circleImageView2.getMeasuredWidth();
                circleImageView5.setLayoutParams(layoutParams);

            }
        });
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                circleImageView2.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                circleImageView2.getHeight();
                circleImageView2.getWidth();
                ViewGroup.LayoutParams layoutParams = circleImageView6.getLayoutParams();
                layoutParams.height = circleImageView2.getMeasuredHeight();
                layoutParams.width = circleImageView2.getMeasuredWidth();
                circleImageView6.setLayoutParams(layoutParams);

            }
        });
    }
    //初始化数据
    private void initData() {
        relativeLayout = (TextView) findViewById(R.id.xiaYiBu);
        relativeLayout.setOnClickListener(this);
        circleImageView1 = (CircleImageView) findViewById(R.id.cirleimg1);
        circleImageView2 = (CircleImageView) findViewById(R.id.cirleimg2);
        circleImageView3 = (CircleImageView) findViewById(R.id.cirleimg3);
        circleImageView4 = (CircleImageView) findViewById(R.id.cirleimg4);
        circleImageView5 = (CircleImageView) findViewById(R.id.cirleimg5);
        circleImageView6 = (CircleImageView) findViewById(R.id.cirleimg6);
        circleImageView7 = (CircleImageView) findViewById(R.id.cirleimg7);
        circleImageView8 = (CircleImageView) findViewById(R.id.cirleimg8);
        cardView = (CardView) findViewById(R.id.changedesk);
        linearLayout = (LinearLayout) findViewById(R.id.AAZ);
        changedeskshuoming = (TextView) findViewById(R.id.changedeskshuoming);
        changedeskshuoming.setOnClickListener(this);
        linerfangshi = (LinearLayout) findViewById(R.id.linercaiyao);
        linerjiage = (LinearLayout) findViewById(R.id.linerjiage);
        linershijian = (LinearLayout) findViewById(R.id.linerriqi);
        renShu = (TextView) findViewById(R.id.yaoYueRenShu);
        riQi = (TextView) findViewById(R.id.yaoYueJuTiShiJian);
        shiJian = (TextView) findViewById(R.id.yaoYueShiJian);
        zaoWan = (TextView) findViewById(R.id.yaoYueZaoWan);
        fangShi = (TextView) findViewById(R.id.yaoYueFangShi);
        money = (TextView) findViewById(R.id.yaoYueJiaGe);
        circleImageView1.setOnClickListener(this);
        circleImageView2.setOnClickListener(this);
        circleImageView3.setOnClickListener(this);
        circleImageView4.setOnClickListener(this);
        circleImageView5.setOnClickListener(this);
        circleImageView6.setOnClickListener(this);
        circleImageView7.setOnClickListener(this);
        circleImageView8.setOnClickListener(this);
        linershijian.setOnClickListener(this);
        linerjiage.setOnClickListener(this);
        linerfangshi.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        zaoWan.setOnClickListener(this);
        shiJian.setOnClickListener(this);
        cardView.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

         switch(view.getId()) {
             case R.id.cirleimg1 :
                 //TODO


                break;
             case R.id.cirleimg2 :
                 //TODO
                 // 改变头像
                 tianjiarenshu++;
                 faBuItem.yaoqingrenshu = tianjiarenshu;
                 renShu.setText(""+tianjiarenshu);
                 break;
             case R.id.cirleimg3 :
//TODO
                 tianjiarenshu++;
                 faBuItem.yaoqingrenshu = tianjiarenshu;
                 renShu.setText(""+tianjiarenshu);
                 break;
             case R.id.cirleimg4 :
//TODO
                 tianjiarenshu++;
                 faBuItem.yaoqingrenshu = tianjiarenshu;
                 renShu.setText(""+tianjiarenshu);
                 break;
             case R.id.cirleimg5 :
//TODO
                 tianjiarenshu++;
                 faBuItem.yaoqingrenshu = tianjiarenshu;
                 renShu.setText(""+tianjiarenshu);
                 break;
             case R.id.cirleimg6 :
//TODO
                 tianjiarenshu++;
                 faBuItem.yaoqingrenshu = tianjiarenshu;
                 renShu.setText(""+tianjiarenshu);
                 break;
             case R.id.cirleimg7 :
//TODO
                 tianjiarenshu++;
                 faBuItem.yaoqingrenshu = tianjiarenshu;
                 renShu.setText(""+tianjiarenshu);
                 break;
             case R.id.cirleimg8 :
//TODO
                 tianjiarenshu++;
                 faBuItem.yaoqingrenshu = tianjiarenshu;
                 renShu.setText(""+tianjiarenshu);
                 break;
             case R.id.AAZ :
                 AAZchange();
                 break;
             case R.id.changedeskshuoming :
                 Intent intent = new Intent(this, TianJiaCaiYaoActivity.class);
                 startActivity(intent);
                 break;
             case R.id.linerjiage :
                 jiagechange();

                 break;
             case R.id.linerriqi:
                 riqichange();
                 break;
             case R.id.yaoYueFangShi :

                 break;
             case R.id.yaoYueJiaGe :

                 break;
             case R.id.yaoYueJiDian :
                 //TODO 图片

                 break;
             case R.id.yaoYueJuTiShiJian :

                 break;
             case R.id.yaoYueRenShu :
//                 renShu.setText(tianjiarenshu);
                 break;
             case R.id.yaoYueShiJian :

                 shijianchange();
                 break;
             case R.id.yaoYueZaoWan :
                 zaowanchange();

                 break;
             case R.id.xiaYiBu :
                 Intent intent1 = new Intent(MyApplication.getInstance(), TianJiaZhuTi.class);
                 startActivity(intent1);

                 break;
             default:
                 break;
        }
    }
    private void zaowanchange() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View myLoginView = layoutInflater.inflate(R.layout.jiagechange, null);
        editText = (EditText) myLoginView.findViewById(R.id.changemoney);
        editText.setHint("时间");
        Dialog alertDialog = new AlertDialog.Builder(this).
                setView(myLoginView).
                setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        faBuItem.zaowan = editText.getText().toString();
                        zaoWan.setText(faBuItem.zaowan);
                    }
                }).
                setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                }).
                create();
        alertDialog.show();
    }
    private void shijianchange() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View myLoginView = layoutInflater.inflate(R.layout.jiagechange, null);
        editText = (EditText) myLoginView.findViewById(R.id.changemoney);
        editText.setHint("时间");
        Dialog alertDialog = new AlertDialog.Builder(this).
                setView(myLoginView).
                setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        faBuItem.shi = editText.getText().toString();
                        shiJian.setText(faBuItem.shi);
                        ChuanDi.time_s = faBuItem.shi;
                    }
                }).
                setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                }).
                create();
        alertDialog.show();
    }


    private void riqichange() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View myLoginView = layoutInflater.inflate(R.layout.jiagechange, null);
        editText = (EditText) myLoginView.findViewById(R.id.changemoney);
        editText.setHint("日期");
        Dialog alertDialog = new AlertDialog.Builder(this).
                setView(myLoginView).
                setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        faBuItem.ri = editText.getText().toString();
                        riQi.setText(faBuItem.ri);
                        ChuanDi.time_y = faBuItem.ri;
                    }
                }).
                setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                }).
                create();
        alertDialog.show();
    }
    private void jiagechange() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View myLoginView = layoutInflater.inflate(R.layout.jiagechange, null);
        editText = (EditText) myLoginView.findViewById(R.id.changemoney);

        Dialog alertDialog = new AlertDialog.Builder(this).
                setView(myLoginView).
                setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        faBuItem.money = editText.getText().toString();
                        money.setText(faBuItem.money);
                        ChuanDi.money = faBuItem.money;
                    }
                }).
                setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                }).
                create();
        alertDialog.show();
    }
    private void AAZchange() {
        final String[] arrayFruit = new String[] { "AA", "代工", "免费", "自定义" };

        Dialog alertDialog = new AlertDialog.Builder(this).
                setTitle("选择方式")
                .setSingleChoiceItems(arrayFruit, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedFruitIndex = which;
                    }
                }).
                        setPositiveButton("确认", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(FaBuActivity.this, arrayFruit[selectedFruitIndex], Toast.LENGTH_SHORT).show();
                                fangShi.setText(arrayFruit[selectedFruitIndex]);
                                faBuItem.fangshi = arrayFruit[selectedFruitIndex];
                                ChuanDi.fangshi = arrayFruit[selectedFruitIndex];
                            }
                        }).
                        setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                            }
                        }).
                        create();
        alertDialog.show();
    }

}

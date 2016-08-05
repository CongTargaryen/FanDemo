package com.example.congzhang.fanyou.sms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.congzhang.fanyou.AppMainActivity;
import com.example.congzhang.fanyou.MyApplication;
import com.example.congzhang.fanyou.R;
import com.example.congzhang.fanyou.sms.bean.User;

import cn.bmob.v3.BmobUser;



public class MainActivity extends Activity implements View.OnClickListener{


	private ImageView iv_left;
	private TextView tv_title;
	private TextView tv_user;
	private Button btn_bind;
	private Button btn_reset;
	private String from;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initData();
		from = getIntent().getStringExtra("from");
		if(from.equals("login")){
			btn_bind.setVisibility(View.VISIBLE);
		}else{
			btn_bind.setVisibility(View.GONE);
		}
		iv_left.setVisibility(View.VISIBLE);
		tv_title.setText("首页");
		iv_left.setOnClickListener(this);
		btn_reset.setOnClickListener(this);
		btn_bind.setOnClickListener(this);
	}

	private void UpdateUser(){
		User user = BmobUser.getCurrentUser(User.class);
		//用户只有绑定过手机号或者用手机号注册登录过就可以直接通过手机号码来重置用户密码了，所以加下这个判断
		if(user!=null && user.getMobilePhoneNumberVerified()!=null && user.getMobilePhoneNumberVerified()){
			btn_reset.setVisibility(View.VISIBLE);
		}else{
			btn_reset.setVisibility(View.INVISIBLE);
		}
		tv_user.setText(user.getUsername()+"-"+user.getAge()+"-"+user.getMobilePhoneNumberVerified()+"-"+user.getMobilePhoneNumber());
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		UpdateUser();
	}
	


	private void initData() {
		iv_left = (ImageView) findViewById(R.id.iv_left);
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_user = (TextView) findViewById(R.id.tv_user);
		btn_bind = (Button) findViewById(R.id.btn_bind);
		btn_reset = (Button) findViewById(R.id.btn_reset);
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()) {
			case R.id.iv_left:
				Intent intent = new Intent(MyApplication.getInstance(), AppMainActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_bind:
				startActivity(new Intent(this, UserBindPhoneActivity.class));
				break;
			case R.id.btn_reset:
				startActivity(new Intent(this, ResetPasswordActivity.class));
			default:
				break;
		}
	}
}

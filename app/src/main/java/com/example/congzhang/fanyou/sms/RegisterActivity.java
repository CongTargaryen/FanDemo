package com.example.congzhang.fanyou.sms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.congzhang.fanyou.R;
import com.example.congzhang.fanyou.sms.bean.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;



public class RegisterActivity extends BaseActivity{
	

	ImageView iv_left;

	TextView tv_title;

	EditText et_account;

	EditText et_password;

	EditText et_pwd_again;

	Button btn_register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initData();
		iv_left.setVisibility(View.VISIBLE);
		tv_title.setText("注册");
		iv_left.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
		btn_register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				registerUser();
			}
		});
	}

	/**
	 */

	private void initData() {
		iv_left = (ImageView) findViewById(R.id.iv_left);
		tv_title = (TextView) findViewById(R.id.tv_title);
		et_account = (EditText) findViewById(R.id.et_account);
		et_password = (EditText) findViewById(R.id.et_password);
		et_pwd_again = (EditText) findViewById(R.id.et_pwd_again);
		btn_register = (Button) findViewById(R.id.btn_register);
	}

	private void registerUser(){
		String account = et_account.getText().toString();
		String password = et_password.getText().toString();
		String pwd = et_pwd_again.getText().toString();
		if (TextUtils.isEmpty(account)) {
			showToast("用户名不能为空");
			return;
		}
		if (TextUtils.isEmpty(password)) {
			showToast("密码不能为空");
			return;
		}
		if (!password.equals(pwd)) {
			showToast("两次密码不一样");
			return;
		}
		final ProgressDialog progress = new ProgressDialog(RegisterActivity.this);
		progress.setMessage("正在登录中...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		final User user = new User();

		user.setUsername(account);
		user.setPassword(password);
		user.signUp( new SaveListener<User>() {

			@Override
			public void done(User user, BmobException e) {
				progress.dismiss();
				toast("注册成功---用户名："+user.getUsername());
				Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
				intent.putExtra("from", "login");
				startActivity(intent);
				finish();
			}

		});
	}
	
	
}

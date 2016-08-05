package com.example.congzhang.fanyou.sms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.congzhang.fanyou.R;
import com.example.congzhang.fanyou.sms.bean.User;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;



public class LoginActivity extends BaseActivity implements View.OnClickListener{

	
	private static final String BMOB_APP_KEY="7561556060a45627beff67b32eef5270";

	ImageView iv_left;
	EditText et_account;
	EditText et_password;
	Button btn_login;
	Button btn_onekey;
	Button btn_register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//初始化Bmob sdk
		Bmob.initialize(this, BMOB_APP_KEY);
		initData();
		iv_left.setOnClickListener(this);
		btn_register.setOnClickListener(this);
		btn_onekey.setOnClickListener(this);
		btn_login.setOnClickListener(this);
	}

	/** 登陆操作 
	 * @method login    
	 * @return void  
	 * @exception   
	 */
	private void login(){
		String account = et_account.getText().toString();
		String password = et_password.getText().toString();
		if (TextUtils.isEmpty(account)) {
			showToast("账号不能为空");
			return;
		}
		if (TextUtils.isEmpty(password)) {
			showToast("密码不能为空");
			return;
		}
		final ProgressDialog progress = new ProgressDialog(LoginActivity.this);
		progress.setMessage("正在登录中...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		//V3.3.9提供的新的登录方式，可传用户名/邮箱/手机号码
		BmobUser.loginByAccount( account, password, new LogInListener<User>() {

			public void done(User user, BmobException ex) {
				// TODO Auto-generated method stub
				progress.dismiss();
				if(ex==null){
					toast("登录成功---用户名："+user.getUsername()+"，年龄："+user.getAge());
					Intent intent = new Intent(LoginActivity.this,MainActivity.class);
					intent.putExtra("from", "login");
					startActivity(intent);
					finish();
				}else{
					toast("登录失败：code="+ex.getErrorCode()+"，错误描述："+ex.getLocalizedMessage());
				}
			}
		});
	}

	private void initData() {
		iv_left = (ImageView) findViewById(R.id.iv_left);
		et_account = (EditText) findViewById(R.id.et_account);
		et_password = (EditText) findViewById(R.id.et_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_onekey = (Button) findViewById(R.id.btn_onekey);
		btn_register = (Button) findViewById(R.id.btn_register);
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()) {
				case R.id.iv_left:
					finish();
					break;
				case R.id.btn_login:
					login();
					break;
			case R.id.btn_register:
				Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(intent);
			case R.id.btn_onekey:
				Intent intent1 = new Intent(LoginActivity.this,LoginOneKeyActivity.class);
				startActivity(intent1);
				break;
			default:
				break;
		}
	}
}

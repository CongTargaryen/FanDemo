package com.example.congzhang.fanyou.sms;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.congzhang.fanyou.R;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;


public class ResetPasswordActivity extends BaseActivity {

	MyCountTimer timer;

	ImageView iv_left;

	TextView tv_title;

	EditText et_phone;

	EditText et_code;

	Button btn_send;

	EditText et_pwd;

	Button btn_reset;

	private void initData() {
		iv_left = (ImageView) findViewById(R.id.iv_left);
		tv_title = (TextView) findViewById(R.id.tv_title);
		et_code = (EditText) findViewById(R.id.et_verify_code);
		et_phone = (EditText) findViewById(R.id.et_phone);
		btn_send = (Button) findViewById(R.id.btn_send);
		et_pwd = (EditText) findViewById(R.id.et_pwd);
		btn_reset = (Button) findViewById(R.id.btn_reset);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_pwd);
		initData();
		iv_left.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
		btn_reset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				resetPwd();
			}
		});
		btn_send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				requestSMSCode();
			}
		});
		iv_left.setVisibility(View.VISIBLE);
		tv_title.setText("重置密码");

	}

	class MyCountTimer extends CountDownTimer {
		  
        public MyCountTimer(long millisInFuture, long countDownInterval) {  
            super(millisInFuture, countDownInterval);  
        }  
		@Override
        public void onTick(long millisUntilFinished) {  
            btn_send.setText((millisUntilFinished / 1000) +"秒后重发");  
        }  
        @Override
        public void onFinish() {  
        	btn_send.setText("重新发送验证码");  
        }  
    }  


	private void requestSMSCode() {
		String number = et_phone.getText().toString();
		if (!TextUtils.isEmpty(number)) {
			timer = new MyCountTimer(60000, 1000);   
			timer.start();   
			BmobSMS.requestSMSCode(number, "重置密码模板",new QueryListener<Integer>() {
				@Override
				public void done(Integer integer, BmobException e) {
					if (e == null) {// 验证码发送成功
						toast("验证码发送成功");// 用于查询本次短信发送详情
					}else{//如果验证码发送错误，可停止计时
						timer.cancel();
					}
				}
			});
		} else {
			toast("请输入手机号码");
		}
	}

	/**
	 */
	private void resetPwd() {
		final String code = et_code.getText().toString();
		final String pwd = et_pwd.getText().toString();
		if (TextUtils.isEmpty(code)) {
			showToast("验证码不能为空");
			return;
		}
		if (TextUtils.isEmpty(pwd)) {
			showToast("密码不能为空");
			return;
		}
		final ProgressDialog progress = new ProgressDialog(ResetPasswordActivity.this);
		progress.setMessage("正在重置密码...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		// V3.3.9提供的重置密码功能，只需要输入验证码和新密码即可
		BmobUser.resetPasswordBySMSCode(code, pwd, new UpdateListener() {

			public void done(BmobException ex) {
				// TODO Auto-generated method stub
				progress.dismiss();
				if(ex==null){
					toast("密码重置成功");
					finish();
				}else{
					toast("密码重置失败：code="+ex.getErrorCode()+"，错误描述："+ex.getLocalizedMessage());
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(timer!=null){
			timer.cancel();
		}
	}
}

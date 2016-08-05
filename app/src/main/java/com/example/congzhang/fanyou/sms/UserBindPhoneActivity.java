package com.example.congzhang.fanyou.sms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.congzhang.fanyou.R;
import com.example.congzhang.fanyou.sms.bean.User;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;


public class UserBindPhoneActivity extends BaseActivity implements View.OnClickListener{
	

	ImageView iv_left;
	EditText et_number;
	EditText et_input;
	TextView tv_title;
	TextView tv_send;
	TextView tv_bind;
	
	MyCountTimer timer;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bind);
		initData();
		iv_left.setVisibility(View.VISIBLE);
		tv_title.setText("绑定手机号");
		iv_left.setOnClickListener(this);
		tv_send.setOnClickListener(this);
		tv_bind.setOnClickListener(this);
	}

	private void initData() {
		iv_left = (ImageView) findViewById(R.id.iv_left);
		et_input = (EditText) findViewById(R.id.et_input);
		et_number = (EditText) findViewById(R.id.et_number);
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_bind = (TextView) findViewById(R.id.tv_bind);
		tv_send = (TextView) findViewById(R.id.tv_send);
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()) {
			case R.id.iv_left:
				finish();
				break;
			case R.id.tv_send:
				requestSMSCode();
				break;
			case R.id.tv_bind:
				verifyOrBind();
			default:
				break;
		}
	}

	class MyCountTimer extends CountDownTimer {
		  
        public MyCountTimer(long millisInFuture, long countDownInterval) {  
            super(millisInFuture, countDownInterval);  
        }  
		@Override
        public void onTick(long millisUntilFinished) {  
			tv_send.setText((millisUntilFinished / 1000) +"秒后重发");  
        }  
        @Override
        public void onFinish() {  
        	tv_send.setText("重新发送验证码");  
        }  
    }  
	

		
	private void requestSMSCode() {
		String number = et_number.getText().toString();
		if (!TextUtils.isEmpty(number)) {
			timer = new MyCountTimer(60000, 1000);   
			timer.start();
			BmobSMS.requestSMSCode( number, "我的模板",new QueryListener<Integer>() {


				public void done(Integer smsId, BmobException ex) {
					// TODO Auto-generated method stub
					if (ex == null) {// 验证码发送成功
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
	
	private void verifyOrBind(){
		final String phone = et_number.getText().toString();
		String code = et_input.getText().toString();
		if (TextUtils.isEmpty(phone)) {
			showToast("手机号码不能为空");
			return;
		}

		if (TextUtils.isEmpty(code)) {
			showToast("验证码不能为空");
			return;
		}
		final ProgressDialog progress = new ProgressDialog(this);
		progress.setMessage("正在验证短信验证码...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();

		BmobUser.signOrLoginByMobilePhone(phone, code, new LogInListener<User>() {
			public void done(User user, BmobException e) {
				if(user!=null){
					Log.i("smile","用户登陆成功");
					bindMobilePhone(phone);
				}
			}
		});
	}
	
	private void bindMobilePhone(String phone){
		//开发者在给用户绑定手机号码的时候需要提交两个字段的值：mobilePhoneNumber、mobilePhoneNumberVerified
		User user =new User();
		user.setMobilePhoneNumber(phone);
		user.setMobilePhoneNumberVerified(true);
		User cur = BmobUser.getCurrentUser(User.class);
		user.update( cur.getObjectId(),new UpdateListener() {

			@Override
			public void done(BmobException e) {

				if(e==null){
					toast("手机号码绑定成功");
				}else{
					toast("失败:" + e.getMessage());
				}
			}
		});
	}
	
}

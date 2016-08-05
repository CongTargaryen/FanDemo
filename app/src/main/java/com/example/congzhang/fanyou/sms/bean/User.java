package com.example.congzhang.fanyou.sms.bean;

import com.example.congzhang.fanyou.sms.UserBindPhoneActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;

public class User extends BmobUser {

	/**  
	 *  
	 */  
	
	private static final long serialVersionUID = 1L;
	
	private Integer age;//年龄

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


}

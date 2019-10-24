package com.xinzhi.service.impl;

import com.xinzhi.dao.impl.LoginDaoimpl;
import com.xinzhi.pojo.User;
import com.xinzhi.service.ILoginService;

public class LoginServiceImpl implements ILoginService {
	/**
	 * shj
	 */
	@Override
	public String login(String phone, String password) {
		LoginDaoimpl ldi = new LoginDaoimpl();
		User login = ldi.login(phone,password);
		if(login != null){
			System.out.println(login.getUid());
			return "succeed";
		}else{
			return "defeated";
		}
	}

}

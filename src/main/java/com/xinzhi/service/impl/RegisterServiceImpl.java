package com.xinzhi.service.impl;

import com.xinzhi.dao.impl.RegisterDaoImpl;
import com.xinzhi.pojo.User;
import com.xinzhi.service.IRegisterService;
/**
 * 
 * @author kzx
 *
 */
public class RegisterServiceImpl implements IRegisterService {
	public String register(String uphone, String upwd) {
		RegisterDaoImpl rdi = new RegisterDaoImpl();
		String a = null;
		if(rdi.Selectxx(uphone) != null){
			a = "repetition";
		}else{
			int count = rdi.register(uphone, upwd);
			if(count>0){
				a = "succeed";
			}else{
				a = "defeated";
			}
		}
		return a;
	}
}

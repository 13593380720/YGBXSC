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
			a = "该用户已经注册";
		}else{
			int count = rdi.register(uphone, upwd);
			if(count>0){
				a = "数据成功添加";
			}else{
				a = "添加失败";
			}
		}
		return a;
	}
}


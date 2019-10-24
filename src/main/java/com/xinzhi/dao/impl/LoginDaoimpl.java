package com.xinzhi.dao.impl;

import java.rmi.server.UID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xinzhi.dao.BaseDao;
import com.xinzhi.dao.ILoginDao;
import com.xinzhi.pojo.User;

public class LoginDaoimpl implements ILoginDao {
	/**
	 * shj
	 */

	public User login(String phone, String password) {
		PreparedStatement pstat = null;
		ResultSet rs = null;
		User ui = null;
		try {
			Connection conn = BaseDao.getConn();
			String sql = "select Uid,Uphone from User where  Uphone=? and Upwd=?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, phone);
			pstat.setString(2, password);
			rs = pstat.executeQuery();
			if(rs.next()){
				ui = new User();
				ui.setUid(rs.getInt("Uid"));
				ui.setUphone(rs.getNString("Uphone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(ui);
		return ui;
	}
}

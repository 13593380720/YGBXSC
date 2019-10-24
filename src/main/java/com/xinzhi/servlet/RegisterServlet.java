package com.xinzhi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinzhi.service.impl.RegisterServiceImpl;
import com.xinzhi.uitl.SendSms;
import sun.security.mscapi.SunMSCAPI;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("src/main/text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String uphone = request.getParameter("uphone");
		String upwd = request.getParameter("upwd");
		String upwd1 = request.getParameter("upwd1");
		String yzm = request.getParameter("yzm");
		int i = 0;
		if(yzm != null){
			i = Integer.parseInt(yzm);
		}
		RegisterServiceImpl rsi = new RegisterServiceImpl();
		String page = rsi.register(uphone, upwd);
		int yz = SendSms.getSjs();
		if(yz == i&&page.equals("数据成功添加")&&upwd.equals(upwd1)){
			/* request.setAttribute("iphone", uphone);*/
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


}

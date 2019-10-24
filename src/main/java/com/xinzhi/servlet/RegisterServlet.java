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
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String uphone = request.getParameter("uphone");
		String upwd = request.getParameter("upwd");
		String yzm = request.getParameter("yzm");
		RegisterServiceImpl rsi = new RegisterServiceImpl();
		String page = rsi.register(uphone, upwd);
        int yz = SendSms.getSjs();
        System.out.println(yz);
		System.out.println(uphone + upwd + yzm);
        if (yzm.equals(yz)) {
            if (page.equals("succeed")) {
                request.setAttribute("iphone", uphone);
                request.getRequestDispatcher("Index.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("regist.jsp").forward(request, response);

            }
        }

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


}

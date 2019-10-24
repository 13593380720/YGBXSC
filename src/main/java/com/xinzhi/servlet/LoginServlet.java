package com.xinzhi.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xinzhi.service.impl.LoginServiceImpl;
@WebServlet(urlPatterns = "/YangGuangBaoXian/LoginServlet" )
public class LoginServlet extends HttpServlet {
	/**
	 * shj
	 */
//	private static final long serialVersionUID = 3398560501558431737L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		System.out.println(uname + upwd);
		if(uname == "" || upwd == ""){
         request.getRequestDispatcher("login.jsp").forward(request, response);
         }
         LoginServiceImpl lsi = new LoginServiceImpl();
         String result = lsi.login(uname, upwd);
//         System.out.println(result);
         request.setAttribute("loginResult", result);
         if(result.equals("succeed")){
//             request.setAttribute("iphone", uname);
             request.getRequestDispatcher("index.jsp").forward(request, response);
         }else{
             request.getRequestDispatcher("login.jsp").forward(request, response);
             System.out.println(result);
         }

         }

public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
        }
        }

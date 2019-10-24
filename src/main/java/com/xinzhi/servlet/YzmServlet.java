package com.xinzhi.servlet;

import com.xinzhi.uitl.SendSms;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "YzmServlet")
public class YzmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("src/main/text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String uphone = request.getParameter("uphone");
//        String yzm = request.getParameter("yzm");
//        int i = 0;
//        if(yzm != null){
//            i = Integer.parseInt(yzm);
//        }
//        System.out.println(1);
//        PrintWriter out = response.getWriter();
        int a = SendSms.SendCode(uphone);
//        int yz = SendSms.getSjs();
//        System.out.println(yz);
//        if(yz == i){
//            request.getRequestDispatcher("RegisterServlet").forward(request,response);
//        }else{
//            request.getRequestDispatcher("register.jsp").forward(request,response);
//        }

    }
}

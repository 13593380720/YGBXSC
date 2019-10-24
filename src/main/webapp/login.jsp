<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>登录注册</title>
        <link rel="stylesheet" href="css/common.css">
        <link rel="stylesheet" href="css/login.css">
        <script>
            var reg_phone = false;   //是否发送了验证码
            var reg_pwd = false;   //密码是否合格
            var reg_code = false;   //验证码是否合格
            var reg_opwd = false;   //第二次密码
            var reg_cphone = false;   //检查手机号是否通过
            var reg_only = false;   //用户唯一
            /**
             * 检查用户输入的手机号是否合法
             * @returns {boolean}
             */
            function checkPhone(){
                //1.获取用户手机号的值
                var tel = $(".userName").val();
                //2.定义手机号正则表达式
                var reg_tel = /^1[3456789]\d{9}$/;
                //3.判断用户输入是否满足正则
                var flag = reg_tel.test(tel);
                if (flag){
                    //此时,用户名合法
                    $(".userName").next().text("");
                }else{
                    //此时,用户名不合法
                    // $(".userName").next().text("手机号格式不正确,请重新输入").css("color","red");
                    alert("不正确");
                }
                return flag;
            }
            /**
             * 检查用户输入的密码是否合法
             */
            function checkPassword(){
                //1.获取密码的值
                var pwd = $("#pwd").val();
                //2.定义正则
                var reg_pwd = /^\w{8,20}$/;
                // 3.判断,给出提示信息
                var falg = reg_pwd.test(pwd);
                if (falg){
                    //此时,密码检查通过
                    $("#pwd").next().html("密码检查通过").css("color","green");
                    return true;
                }else{
                    $("#pwd").next().html("密码不符合规范,请重新输入").css("color","red");
                    return false;
                }
            }
            /**
             * 检查两次密码是否一致
             * @returns {boolean}
             */
            function validate() {
                var pwd = $("#pwd").val();
                var pwd1 = $("#repwd").val();
                if(pwd == pwd1){
                    $("#repwd").next().html("两次密码相同");
                    $("#repwd").next().css("color","green");
                    return true;
                }else {
                    $("#repwd").next().html("两次密码不相同");
                    $("#repwd").next().css("color","red")
                    return false;
                }
            }
            /**
             * 检查用户名是否唯一
             */
            function findUser(){
                var tel = $("#tel").val();
                $.post("RegFindUserServlet",{phoneNumber:tel},function (data) {
                    //alert(data.flag);
                    if (data.flag){
                        reg_only = true;
                        $("#tel").next().text("恭喜你,该用户名可以注册").css("color","green").addClass("success");
                    }else{
                        reg_only = false;
                        $("#tel").next().text("用户名已重复,请重新输入").css("color","red").removeClass("success");
                    }
                    enclick();
                });
            }
            /**
             * 发送短信倒计时
             */
            // function noSend(){
            //     var $button = $(".send");
            //     var number = 60;
            //     var countdown = function(){
            //         if (number == 0) {
            //             $button.attr("href","javascript:");
            //             $button.removeClass("hide");
            //             $button.next().addClass("hide");
            //             $button.html("发送验证码");
            //             number = 60;
            //             return;
            //         } else {
            //             $button.addClass("hide");
            //             $button.next().removeClass("hide");
            //             $button.next().html(number + "秒 重新发送");
            //             number--;
            //         }
            //         setTimeout(countdown,1000);
            //     }
            //     setTimeout(countdown,1000);
            // }
            /**
             * 启用注册按钮
             */
            // function enclick(){
            //     if (reg_phone && reg_pwd && reg_code && reg_opwd && reg_cphone && reg_only){
            //         $(".lang-btn").prop("disabled",false).css({"background":"#42a5f5"});
            //     }else{
            //         $(".lang-btn").prop("disabled",true).css({"background":"#aaa"});
            //     }
            // }
            $(function () {
                //验证用户手机号是否输入规范及是否重复
                $("body").delegate("#tel","propertychange input",function (){
                    if (checkPhone()){
                        reg_cphone = true;
                        //此时手机号格式校验通过,开始查询用户名是否重复
                        findUser();
                    }else {
                        reg_cphone = false;
                    }
                    enclick();
                });
                //验证用户输入的密码是否符合规范
                $("body").delegate("#pwd","propertychange input",function (){
                    if (checkPassword()){
                        reg_opwd = true;
                    }else {
                        reg_opwd = false;
                    }
                    if (validate()){
                        reg_pwd = true;
                    }else {
                        reg_pwd = false;
                    }
                    enclick();
                });
                //验证用户第二次的密码规范及是否和第一次输入的密码一致
                $("body").delegate("#repwd","propertychange input",function (){
                    if (validate()){
                        reg_pwd = true;
                    }else {
                        reg_pwd = false;
                    }
                    enclick();
                });
                //发送验证码按钮的点击事件
                $(".send").click(function () {
                    if ($("#tel").val() == ''){
                        alert("请输入手机号");
                        return ;
                    }
                    if (!checkPhone()){
                        alert("手机号格式错误");
                        return ;
                    }
                    if (!reg_only){
                        alert("手机号已注册,请直接登录");
                        $("#tel").next().text("用户名已重复,请重新输入").css("color","red");
                        return ;
                    }
                    $("#tel").next().text("恭喜你,该用户名可以注册").css("color","green");
                    var tel = $("#tel").val();
                    $.post("RegSendMessageServlet",{phoneNumber:tel},function (data) {
                        //alert(data);
                        if (data.flag){
                            //alert("发送成功");
                            reg_phone = true;
                            noSend();
                        }
                    });
                })
                $("body").delegate("#veri-code","propertychange input",function () {
                    if ($("#veri-code").val().length > 5 ){
                        reg_code = true;
                        //alert("reg_phone" + reg_phone + "reg_pwd" + reg_pwd + "reg_code" + reg_code + "reg_opwd" + reg_opwd)
                    }else {
                        reg_code = false;
                    }
                    enclick();
                });
                $(".lang-btn").click(function () {
                    //alert("可以注册了");
                    $.post("RegisteredServlet",$("#regform").serialize(),function (data) {
                        /*if (data.flag){
                            alert("注册成功,即将跳转到登录页面");
                            location.href="../JinTianFindHouse/Login/index.jsp";
                        }else{
                            alert(data.errorMsg);
                        }*/
                        alert(data);
                        if (data == '注册成功,即将跳转到登录页面'){
                            location.href="../index.jsp";
                        }
                    })
                })
            })
        </script>
    </head>
    <body>
    <div class="wrapper">
        <!--------------------------------------header_user begin------------------------------------->
        <div class="header_user">
            <div class="main_width">
                <span class="tel">客服电话：400-663-6600（周一至周六 9:00-18:00）</span>
                <span class="fr">
                <span class="my_insurance"><a href="center\mypolicy.jsp">我的保险<em>1</em></a></span>
                <span class="Backlog"><a href="book_detail.jsp">未完成订单<em>1</em></a></span>
                <span class="message"><a href="center\message.jsp">消息<em>1</em></a></span>
                <span class="register"><a href="register.jsp">注册</a></span>
                <span class="fg"><a href="#">|</a></span>
                <span><a href="login.jsp">登录</a></span>
            </span>
            </div>
        </div>
        <!---------------------------------------header_user end-------------------------------------->
        <!---------------------------------------header_nav begin------------------------------------->
        <div class="header_nav">
            <div class="main_width">
                <h1><a href="#" class="logo fl">大特保保险官网-互联网保险服务平台</a></h1>
                <!--<a href="#" class="my_policy fr">我的保单</a>-->
                <div class="menuBox">
                    <ul class="menu">
                        <li class="active"><a href="#">首页</a></li>
                        <li class="special"><a href="list2.jsp">保险产品</a></li>
                        <li><a href="#">理财中心</a></li>
                        <li><a href="#">关于我们</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <!----------------------------------------header_nav end-------------------------------------->
        <div class="container">
            <div class="login_bg">
                <div class="form">
                    <!--<div class="slogan"></div>-->
                    <form action="LoginServlet" method="post" name="loginForm" id="loginForm">
                        <h2>登录大特保网</h2>
                        <div class="user_box">
                            <span class="icon"></span>
                            <input type="text" class="userName" name="uname" placeholder="请输入十一位的手机号">
                        </div>
                        <div class="pwd_box">
                            <span class="icon"></span>
                            <input type="password" class="userPwd" name="upwd" placeholder="密码">
                        </div>
                        <div class="detail_box">
                            <input type="checkbox" id="login_check">
                            <label for="login_check">下次自动登录</label>
                            <a href="#" class="forgetPwd">忘记密码？</a>
                        </div>
                        <div>
                            <%--<input type="button" class="login_btn" id="loginBtn" value="登录">--%>
                                <input type="submit" class="login_btn" id="loginBtn" value="登录">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--footer begin-->
        <div class="copyright">
            <div class="main_width footer">
                <div class="footer_nav">
                    <p>
                        <a href="#">关于京东金融</a>
                        <span class="divider">|</span>
                        <a href="#">关于京东小金库</a>
                        <span class="divider">|</span>
                        <a href="#">关于京东钱包</a>
                        <span class="divider">|</span>
                        <a href="#">关于京东白条</a>
                        <span class="divider">|</span>
                        <a href="#">联系我们</a>
                        <span class="divider">|</span>
                        <a href="#">免责声明</a>
                    </p>
                    <p>Copyright © 2004-2016 京东JD.com 版权所有<span class="divider">|</span>投资有风险，购买需谨慎</p>
                </div>
                <div class="footer_contact">
                    <div class="f_contact_time">
                        联系我们<span>（09:00-22:00）</span>
                    </div>
                    <div class="f_contact_content">
                        <div class="f_cc_left">
                            <span class="f_cc_item">个人业务：400-098-8511</span>
                            <span class="f_cc_item">企业业务：400-088-8816</span>
                        </div>
                        <div class="f_cc_mid">
                            <a href="#" class="f_cc_link item_JIMI">咨询JIMI</a>
                            <a href="#" class="f_cc_link item_kefu">在线客服</a>
                        </div>
                        <div class="f_cc_right">
                            <a href="#" class="f_cc_link item_mail">客服邮箱</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--footer end-->
    </div>
    </body>
    </html>



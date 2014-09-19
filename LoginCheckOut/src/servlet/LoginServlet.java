package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 
import manager.Manager; 
public class LoginServlet extends HttpServlet {
     public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
         PrintWriter out = response.getWriter();
         
         String name = (String) request.getParameter("name");
         name = name.trim();            //去掉字符串首尾空格
         String pwd = (String) request.getParameter("pwd");        
         pwd = pwd.trim();
         
         boolean Check = Manager.CheckForm(name, pwd);        //验证输入的帐号密码是否符合规则
         System.out.println(Check);
         if (Check) {
             // 将密码转为MD5加密
             try {
                 // -------生成MessageDigest对象MD-------
                 MessageDigest MD = MessageDigest.getInstance("MD5");
                 // --------传入要计算的字符---------------
                 MD.update(pwd.getBytes("UTF8"));
                 // --------计算信息摘要----------------
                 byte[] pwdMD5Byte = MD.digest();
                 pwd = new String(pwdMD5Byte);
             } catch (Exception e) {
                 e.printStackTrace();
             }
             //得到验证码
             String certCode = request.getParameter("certCode");
             //查看输入的帐号密码是否在数据库里有记录
             boolean tag = Manager.CheckAccount(name, pwd);
             // 执行输出操作(弹出成功的对话框)
             response.setCharacterEncoding("gbk"); // 解决中文显示问题
             HttpSession session = request.getSession();
             if (certCode.equals((String) session.getAttribute("certCode"))) {
                 if (tag) {
                     session.setAttribute("name", name);            //验证成功的话设置session
                     out
                             .print("<script language=javascript>alert('Login Succeed!!');window.location.href='Success.jsp';</script>");
                 } else {
                     out
                             .print("<script language=javascript>alert('account or password error!!');window.location.href='index.jsp';</script>");
                 }
             } else {
                 out
                         .print("<script language=javascript>alert('certCode error!!');window.location.href='index.jsp';</script>");
             }
         } else {
             out
                     .print("<script language=javascript>alert('rule error!!');window.location.href='index.jsp';</script>");
         }
     }
 
     public void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         doGet(request, response);
     }
  }
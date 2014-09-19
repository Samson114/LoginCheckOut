package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.Manager;
import pojo.Account;
 
 public class RegisteServlet extends HttpServlet {
     public void doGet(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         Date currTime = new Date(); // 添加帐号的时间
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         String time = new String(formatter.format(currTime).getBytes(
                 "iso-8859-1")); // 时间格式化
         String name = (String) request.getParameter("name");
         name = name.trim(); // 去除字符串首尾空格
         String pwd = (String) request.getParameter("pwd");
         pwd = pwd.trim();
         boolean Check = Manager.CheckForm(name, pwd); // 验证输入的帐号密码是否符合规则
         System.out.println(Check);
         if (Check) {
             // 在写入数据库之前将密码转为MD5加密
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
             boolean tag = Manager.HasAccount(name); // 查看数据库里有没相同的用户名记录
             if (tag) {
                 response.setCharacterEncoding("gbk"); // 解决中文显示问题
                 PrintWriter out = response.getWriter();
                 out
                         .print("<script language=javascript>alert('用户名已被占用');window.location.href='registe.jsp';</script>");
             } else {
                 // new 一个Account对象,并将表单传过来的值填充它
                 Account account = new Account();
                 account.setName(request.getParameter("name"));
                 account.setPwd(pwd);
                 account.setAddtime(time);
                // 执行持久化操作,保存到数据库
                 Manager manager = new Manager();
                 manager.addAccount(account);
                 // 执行输出操作(弹出成功的对话框)
                 response.setCharacterEncoding("gbk"); // 解决中文显示问题
                 HttpSession session = request.getSession();
                 session.setAttribute("name", request.getParameter("name"));
                 PrintWriter out = response.getWriter();
                 out
                        .print("<script language=javascript>alert('注册成功!');window.location.href='Success.jsp';</script>");
             }
         } else {
             response.setCharacterEncoding("gbk"); // 解决中文显示问题
             PrintWriter out = response.getWriter();
             out
                     .print("<script language=javascript>alert('帐号或密码必须是四到八位的数字或字母!!');window.location.href='registe.jsp';</script>");
         }
    }
 
     public void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         doGet(request, response);
     }
 
 }
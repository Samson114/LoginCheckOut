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
         name = name.trim();            //ȥ���ַ�����β�ո�
         String pwd = (String) request.getParameter("pwd");        
         pwd = pwd.trim();
         
         boolean Check = Manager.CheckForm(name, pwd);        //��֤������ʺ������Ƿ���Ϲ���
         System.out.println(Check);
         if (Check) {
             // ������תΪMD5����
             try {
                 // -------����MessageDigest����MD-------
                 MessageDigest MD = MessageDigest.getInstance("MD5");
                 // --------����Ҫ������ַ�---------------
                 MD.update(pwd.getBytes("UTF8"));
                 // --------������ϢժҪ----------------
                 byte[] pwdMD5Byte = MD.digest();
                 pwd = new String(pwdMD5Byte);
             } catch (Exception e) {
                 e.printStackTrace();
             }
             //�õ���֤��
             String certCode = request.getParameter("certCode");
             //�鿴������ʺ������Ƿ������ݿ����м�¼
             boolean tag = Manager.CheckAccount(name, pwd);
             // ִ���������(�����ɹ��ĶԻ���)
             response.setCharacterEncoding("gbk"); // ���������ʾ����
             HttpSession session = request.getSession();
             if (certCode.equals((String) session.getAttribute("certCode"))) {
                 if (tag) {
                     session.setAttribute("name", name);            //��֤�ɹ��Ļ�����session
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
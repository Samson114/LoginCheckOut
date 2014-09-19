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
         Date currTime = new Date(); // ����ʺŵ�ʱ��
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         String time = new String(formatter.format(currTime).getBytes(
                 "iso-8859-1")); // ʱ���ʽ��
         String name = (String) request.getParameter("name");
         name = name.trim(); // ȥ���ַ�����β�ո�
         String pwd = (String) request.getParameter("pwd");
         pwd = pwd.trim();
         boolean Check = Manager.CheckForm(name, pwd); // ��֤������ʺ������Ƿ���Ϲ���
         System.out.println(Check);
         if (Check) {
             // ��д�����ݿ�֮ǰ������תΪMD5����
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
             boolean tag = Manager.HasAccount(name); // �鿴���ݿ�����û��ͬ���û�����¼
             if (tag) {
                 response.setCharacterEncoding("gbk"); // ���������ʾ����
                 PrintWriter out = response.getWriter();
                 out
                         .print("<script language=javascript>alert('�û����ѱ�ռ��');window.location.href='registe.jsp';</script>");
             } else {
                 // new һ��Account����,��������������ֵ�����
                 Account account = new Account();
                 account.setName(request.getParameter("name"));
                 account.setPwd(pwd);
                 account.setAddtime(time);
                // ִ�г־û�����,���浽���ݿ�
                 Manager manager = new Manager();
                 manager.addAccount(account);
                 // ִ���������(�����ɹ��ĶԻ���)
                 response.setCharacterEncoding("gbk"); // ���������ʾ����
                 HttpSession session = request.getSession();
                 session.setAttribute("name", request.getParameter("name"));
                 PrintWriter out = response.getWriter();
                 out
                        .print("<script language=javascript>alert('ע��ɹ�!');window.location.href='Success.jsp';</script>");
             }
         } else {
             response.setCharacterEncoding("gbk"); // ���������ʾ����
             PrintWriter out = response.getWriter();
             out
                     .print("<script language=javascript>alert('�ʺŻ�����������ĵ���λ�����ֻ���ĸ!!');window.location.href='registe.jsp';</script>");
         }
    }
 
     public void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         doGet(request, response);
     }
 
 }
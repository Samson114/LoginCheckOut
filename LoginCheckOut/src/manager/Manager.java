package manager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DB;
import pojo.Account;
 
 public class Manager {
 
     DB db = new DB();
 
     // ����ʺ�
     public void addAccount(Account account) {
         PreparedStatement pstmt = null;
         String sql = "insert into account(name,pwd,addtime) values(?,?,?)";
         pstmt = db.prepareStmt(sql);
         try {
             pstmt.setString(1, account.getName());
             pstmt.setString(2, account.getPwd());
             pstmt.setString(3, account.getAddtime());
             pstmt.executeUpdate();
 
         } catch (SQLException e) {
             System.out
                     .println("-----------------Manager.addAccount()�����쳣-------------------------");
             e.printStackTrace();
         } finally {
             db.closePstmt(pstmt);
         }
     }
 
     // �鿴���ݿ����Ƿ���ָ��name����ͬ��¼��
     public static boolean HasAccount(String name) {
         DB db = new DB();
         ResultSet rs = null;
         int count = 0;
         String sql = "select count(*) from account where name = '" + name + "'";
         rs = db.exeQuery(sql);
         try {
             while (rs.next()) {
                 count = rs.getInt(1);
             }
        } catch (SQLException e) {
             System.out
                     .println("------------Manager.HasAccount()�����쳣------------------------");
             e.printStackTrace();
         } finally {
             db.closeRs(rs);
         }
         if (count > 0) {
             return true;
         } else
             return false;
     }
 
     // �ж��ύ���ʺ������Ƿ�����Ӧ�ļ�¼�����ݿ��У�
     public static boolean CheckAccount(String name, String pwd) {
         DB db = new DB();
         ResultSet rs = null;
         int count = 0;
         String sql = "select count(*) from account where name = '" + name
                 + "' and pwd = '" + pwd + "' ";
         rs = db.exeQuery(sql);
         try {
            while (rs.next()) {
                count = rs.getInt(1);
             }
         } catch (SQLException e) {
             System.out
                     .println("-----------Manager.CheckAccount()�����쳣--------------");
         } finally {
             db.closeRs(rs);
         }
         if (count > 0) { // ����������0��
            return true; // ��ʾ���û����ڣ�
         } else
             return false; // ��ʾ���û������ڣ�
     }

     // �ж��ύ���ʺ������Ƿ����Ҫ��
    public static boolean CheckForm(String name, String pwd) {
         String regEx="[a-zA-Z0-9]{4,8}+";//������ʾ:�ĵ���λ�����ֻ���ĸ
         if(name.matches(regEx)&&pwd.matches(regEx)) return true;
         else return false;
     }
 
 }
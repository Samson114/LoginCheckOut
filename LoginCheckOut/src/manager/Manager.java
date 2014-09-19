package manager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DB;
import pojo.Account;
 
 public class Manager {
 
     DB db = new DB();
 
     // 添加帐号
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
                     .println("-----------------Manager.addAccount()方法异常-------------------------");
             e.printStackTrace();
         } finally {
             db.closePstmt(pstmt);
         }
     }
 
     // 查看数据库里是否有指定name的相同记录；
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
                     .println("------------Manager.HasAccount()方法异常------------------------");
             e.printStackTrace();
         } finally {
             db.closeRs(rs);
         }
         if (count > 0) {
             return true;
         } else
             return false;
     }
 
     // 判断提交的帐号密码是否有相应的记录在数据库中；
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
                     .println("-----------Manager.CheckAccount()方法异常--------------");
         } finally {
             db.closeRs(rs);
         }
         if (count > 0) { // 如果结果大于0；
            return true; // 表示此用户存在；
         } else
             return false; // 表示此用户不存在；
     }

     // 判断提交的帐号密码是否符合要求；
    public static boolean CheckForm(String name, String pwd) {
         String regEx="[a-zA-Z0-9]{4,8}+";//正则表达示:四到八位的数字或字母
         if(name.matches(regEx)&&pwd.matches(regEx)) return true;
         else return false;
     }
 
 }
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
 public class DB {
     
    private static DB db = null;
     private static Connection conn = null;
     private static Statement stmt = null;
     
     //单例模式,new DB类的时候,创建唯一对象,只初始化一次,
     //注意:不要显式交闭此static中的Connection和Statement对象,否则抛空指针异常
     static{
         try {
             String driverClass = "oracle.jdbc.driver.OracleDriver";
             String url = "jdbc:oracle:thin:@172.18.85.13:1521:orcl";
             String username = "scott";     
             String password = "tianyidong";    
             Class.forName(driverClass);
             conn = DriverManager.getConnection(url,username,password);
             stmt = conn.createStatement();
             System.out.println("--------初始化---------");
         } catch (ClassNotFoundException e) {
             System.out.println("---------- 加载数据库驱动类时发生异常: ----------");
             e.printStackTrace();
         } catch (SQLException e) {
             System.out.println("------------ getConnection()方法发生异常--------------");
         }
     }
     
    //创建PrepareStatement对象
     public PreparedStatement prepareStmt(String sql){
         PreparedStatement pstmt = null;
         try {
             pstmt = conn.prepareStatement(sql);
         } catch (SQLException e) {
             System.out.println("-------------prepareStmt()方法发生异常-------------------");
             e.printStackTrace();
         }
         return pstmt;
     }
     
     //执行查询所有记录操作
     public ResultSet exeQuery(String sql){
         ResultSet rs = null;
         try {
             rs = stmt.executeQuery(sql);
         } catch (SQLException e) {
             System.out.println("------------exeQuery()方法发生异常: --------------------");
             e.printStackTrace();
         }
         return rs;
     }
     
     //执行保存、更新、删除操作
     public void exeUpdate(String sql){
         try{
             stmt.executeUpdate(sql);
         } catch(SQLException e){
             System.out.println("------------- exeUpdate()方法发生异常------------------");
             e.printStackTrace();
         }
     }
     
     
     //关闭PreparedStatement对象
     public void closePstmt(PreparedStatement pstmt){
         try{
             pstmt.close();
             pstmt = null;
         }catch(SQLException e){
             System.out.println("-------------------- DB.closePstmt()方法发生异常 -------------------------");
             e.printStackTrace();
         }
     }
     
     //关闭ResultSet对象
     public void closeRs(ResultSet rs){
         try{
             rs.close();
             rs = null;
         }catch(SQLException e){
             System.out.println("-------------------- DB.closePstmt()方法发生异常 -------------------------");
             e.printStackTrace();
         }
     }
     
 
 }
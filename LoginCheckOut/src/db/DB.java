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
     
     //����ģʽ,new DB���ʱ��,����Ψһ����,ֻ��ʼ��һ��,
     //ע��:��Ҫ��ʽ���մ�static�е�Connection��Statement����,�����׿�ָ���쳣
     static{
         try {
             String driverClass = "oracle.jdbc.driver.OracleDriver";
             String url = "jdbc:oracle:thin:@172.18.85.13:1521:orcl";
             String username = "scott";     
             String password = "tianyidong";    
             Class.forName(driverClass);
             conn = DriverManager.getConnection(url,username,password);
             stmt = conn.createStatement();
             System.out.println("--------��ʼ��---------");
         } catch (ClassNotFoundException e) {
             System.out.println("---------- �������ݿ�������ʱ�����쳣: ----------");
             e.printStackTrace();
         } catch (SQLException e) {
             System.out.println("------------ getConnection()���������쳣--------------");
         }
     }
     
    //����PrepareStatement����
     public PreparedStatement prepareStmt(String sql){
         PreparedStatement pstmt = null;
         try {
             pstmt = conn.prepareStatement(sql);
         } catch (SQLException e) {
             System.out.println("-------------prepareStmt()���������쳣-------------------");
             e.printStackTrace();
         }
         return pstmt;
     }
     
     //ִ�в�ѯ���м�¼����
     public ResultSet exeQuery(String sql){
         ResultSet rs = null;
         try {
             rs = stmt.executeQuery(sql);
         } catch (SQLException e) {
             System.out.println("------------exeQuery()���������쳣: --------------------");
             e.printStackTrace();
         }
         return rs;
     }
     
     //ִ�б��桢���¡�ɾ������
     public void exeUpdate(String sql){
         try{
             stmt.executeUpdate(sql);
         } catch(SQLException e){
             System.out.println("------------- exeUpdate()���������쳣------------------");
             e.printStackTrace();
         }
     }
     
     
     //�ر�PreparedStatement����
     public void closePstmt(PreparedStatement pstmt){
         try{
             pstmt.close();
             pstmt = null;
         }catch(SQLException e){
             System.out.println("-------------------- DB.closePstmt()���������쳣 -------------------------");
             e.printStackTrace();
         }
     }
     
     //�ر�ResultSet����
     public void closeRs(ResultSet rs){
         try{
             rs.close();
             rs = null;
         }catch(SQLException e){
             System.out.println("-------------------- DB.closePstmt()���������쳣 -------------------------");
             e.printStackTrace();
         }
     }
     
 
 }
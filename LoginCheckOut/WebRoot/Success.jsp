<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="java.sql.*" %>
<html> 
<%request.setCharacterEncoding("UTF-8");%>  
<%response.setCharacterEncoding("UTF-8");%> 

    <head> 

        <title> 上下分割窗口 </title> 

    </head> 

    <frameset rows="5%,95%," frameborder="0"> 
              <frame src="http://localhost:8888/Login/SucceedLogin.jsp"> 
              <frame src="http://localhost:8888/my/putin.jsp">
       </frameset> 

</html>

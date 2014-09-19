<%@page contentType="image/jpeg" pageEncoding="gbk" %>
<jsp:useBean id="image" scope="page" class="bean.makeCertPic" />
<%
    String str = image.getCertPic(0,0,response.getOutputStream());
    // 避免getWriter()和responsegetOutputStream()冲突而产生异常
    out.clear();
    out = pageContext.pushBody();
    // CREATE SESSION
    session.setAttribute("certCode", str); 
%>
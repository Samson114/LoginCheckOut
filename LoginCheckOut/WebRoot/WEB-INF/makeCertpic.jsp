<%@page contentType="image/jpeg" pageEncoding="gbk" %>
<jsp:useBean id="image" scope="page" class="bean.makeCertPic" />
<%
    String str = image.getCertPic(0,0,response.getOutputStream());
    // ����getWriter()��responsegetOutputStream()��ͻ�������쳣
    out.clear();
    out = pageContext.pushBody();
    // CREATE SESSION
    session.setAttribute("certCode", str); 
%>
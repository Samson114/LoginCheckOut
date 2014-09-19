<div align="right"><% 
     if (session.getAttribute("name") == null 
             || session.getAttribute("name").toString().length() == 0) { 
         response.sendRedirect(request.getContextPath() + "/error.jsp"); 
    } 
%></div>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
    <link href="css\bootstrap.min.css" rel="stylesheet"  />
  </head>
      <script language="JavaScript">

    function check()
    {
       if( form.name.value=="" )
        {
           alert("\�����������û�������")
               form.name.focus();
           return false;
        }
    

    
       
       if( form.pwd.value==""  )
           {
               alert("\���������ĵ�½����!!")
                   form.pwd.focus();
               return false;
       }
    return true;
    }
	function reImg(){  
            var img = document.getElementById("Img");  
            img.src ="http://localhost:8080/login/makeCertpic.jsp";  
        }
    </script>
  
  
  <body>
    <h2><strong>�û���¼</strong></h2>
    <form name="form" id="form" onSubmit="check()" method="post" action="LoginServlet">
    	<div class="input-prepend ">
        	<span class="add-on"><i class="icon-user"></i>&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;�ţ�</span>
            <input type="text" id="inputWarning" class="input-small" name="name" />
        </div>
        <br />
        <div class="input-prepend ">
            <span class="add-on">
            <i class=" icon-qrcode"></i>&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;�룺</span>
            <input type="password" id="inputError" class="input-small" name="pwd" />
        </div>
        <br />
        <div class="input-prepend ">
            <span class="add-on">
            <i class="icon-barcode"></i> &nbsp;&nbsp;��֤�룺
            </span>
            <input type="text" id="inputInfo" class="input-small" name="certCode" />
            <span>
            <img src="http://localhost:8080/login/makeCertpic.jsp" id="Img" class="pic" />
            </span>
            <span class="add-on">
            <i class="  icon-refresh"></i><a href="#" onclick="reImg();">&nbsp;&nbsp;�����壬��һ��</a>
            </span> 
        </div>   	
        <div>
        	<button class="btn btn-primary" type="submit" name="submit">��¼</button>
        	<button class="btn btn-primary" type="submit" onClick="location='registe.jsp'" value="ע��">ע��</button>
        </div>
    </form>
  </body>
</html>
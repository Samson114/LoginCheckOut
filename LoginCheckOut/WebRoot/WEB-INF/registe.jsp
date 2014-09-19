<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>注册</title>
    <link href="css\bootstrap.min.css" rel="stylesheet"  />
  </head>
  
    <script language="JavaScript">

    function check()
    {
       if( form.name.value=="" )
        {
           alert("\请输入您的用户名！！")
               form.name.focus();
           return false;
        }
    

    
       
       if( form.pwd.value==""  )
           {
               alert("\请输入您的登陆密码!!")
                   form.pwd.focus();
               return false;
       }
       if(form.repwd.value=="")
           {
                   alert("\请输入您的第二次密码！！")
                       form.repwd.focus();
                   return false;
           }
       if(form.pwd.value!=form.repwd.value)
           {
                   alert("\两次输入的密码不一致!!")
                       form.repwd.focus();
                   return false;
       }
    return true;
    }

    </script>
  
  <body>
    <h2><strong>用户注册</strong></h2>
    <form name="form" id="form" onSubmit="check()" method="post" action="RegisteServlet">
    	<div class="input-prepend ">
        	<span class="add-on"><i class="icon-user"></i>&nbsp;&nbsp;账&nbsp;&nbsp;&nbsp;&nbsp;号：</span>
            <input type="text" id="inputWarning" class="input-small" name="name" />
        </div>
        <br />
        <div class="input-prepend ">
            <span class="add-on">
            <i class=" icon-qrcode"></i>&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
            <input type="password" id="inputError" class="input-small" name="pwd" />
        </div>
        <br />
        <div class="input-prepend ">
            <span class="add-on">
            <i class="icon-repeat"></i> &nbsp;&nbsp;再次输入密码：
            </span>
            <input type="password" id="inputInfo" class="input-small" name="repwd" />
        </div>
        <br />
        <div>
        	<button class="btn btn-inverse" type="submit">注册</button>
        	<button class="btn btn-warning" type="reset" >重置</button>
        </div>
   </form>
  </body>
</html>
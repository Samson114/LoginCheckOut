<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>ע��</title>
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
       if(form.repwd.value=="")
           {
                   alert("\���������ĵڶ������룡��")
                       form.repwd.focus();
                   return false;
           }
       if(form.pwd.value!=form.repwd.value)
           {
                   alert("\������������벻һ��!!")
                       form.repwd.focus();
                   return false;
       }
    return true;
    }

    </script>
  
  <body>
    <h2><strong>�û�ע��</strong></h2>
    <form name="form" id="form" onSubmit="check()" method="post" action="RegisteServlet">
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
            <i class="icon-repeat"></i> &nbsp;&nbsp;�ٴ��������룺
            </span>
            <input type="password" id="inputInfo" class="input-small" name="repwd" />
        </div>
        <br />
        <div>
        	<button class="btn btn-inverse" type="submit">ע��</button>
        	<button class="btn btn-warning" type="reset" >����</button>
        </div>
   </form>
  </body>
</html>
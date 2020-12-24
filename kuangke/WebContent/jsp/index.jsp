<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.ResultSet"  %>
<% String path = request.getContextPath(); %>
<% String base = "http://" + request.getServerName()+":"+request.getServerPort(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
 <style> .divTestStyle{width:400px;height:200px;border:3px solid #000} </style>
 <script type="text/javascript" src="<%=base + path %>/resource/js/jquery-1.7.2.js"></script>
 <script type="text/javascript">
	function logon1(){
		var userName=document.getElementById('uesrName').value;
		var passWord=document.getElementById('passWord').value;
		if(userName=='' || passWord==''){
			alert("账号和密码不能为空！");
			return false;
		}
		document.getElementById('indexform').submit();
	}
	
	function register(){
		var userName=document.getElementById('uesrName').value;
		var passWord=document.getElementById('passWord').value;
		if(userName=='' || passWord==''){
			alert("账号和密码不能为空！");
			return false;
		}
		window.location.href='<%=path%>/register?userName='+userName+'&passWord='+passWord;
	}
</script>
</head>
<body style="background:#F5FBEF">
    <c:if test="${!(empty param.msg_info) }"> <script> alert("${param.msg_info}");</script></c:if>
    <center text-align:center>
    	 <div class="divTestStyle" >
	        	<h1 style="color:red">登录</h1>
	            <form id="indexform" name="indexForm" action="<%=path %>/logon" method="post">
	                <table border="0">
	                    <tr>
	                        <td>账号：</td>
	                        <td><input type="text" id="uesrName" name="uesrName"></td>
	                    </tr>
	                    <tr>
	                        <td>密码：</td>
	                        <td><input type="password" id="passWord" value="" name="passWord">
	                        </td>
	                    </tr>
	                </table>
	            <br>
	                <input type="button" onclick="logon1()" value="登录" style="color:#BC8F8F">
	                <input type="button" onclick="register()" value="注册" style="color:#BC8F8F">
	            </form>
        </div>  
    </center>
</body>
</html>
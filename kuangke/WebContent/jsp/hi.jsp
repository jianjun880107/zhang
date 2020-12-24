<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath(); %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form name="from1" method="post" action="<%=path%>/save">
    姓名：<input name="name" type="text">
    年龄：<input name="age" type="text">  
    <input name="submit" type="submit" value="提交">
</form>

年龄：${myn.age}
</body>
</html>
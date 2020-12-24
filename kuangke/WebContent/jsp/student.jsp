<%@page import="com.kuangke.domain.Student"%>
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
<title>学生系统</title>
<style>
	/* body{background:#f6f6f6;} */
	.main{width:700px;margin: 0 auto;}
	.edit{width:700px;background:#FFF;border:#999 1px solid;}
	
	.edit form ul li{list-style:outside none;margin:5px auto;height:30px}
	.edit form ul li span {width: 68px; float: left; padding-right: 10px;
	color: #666; height: 26px; font: normal 14px/26px arial; text-align: right;}
	.edit form ul li span b{color: #f00; padding-left:3px;}
	.edit form ul li input{width: 234px;height: 24px;border: 1px #b6b6b6 solid;
	background: #fff;line-height: 24px;padding: 0px 5px;}
	.show table{width:700px;background:#FFF;border:#FF9 1px solid;margin-top:10px;border-collapse: collapse;}
	.show table tr{ border:1px #b6b6b6 dashed;margin:3px; padding-right: 10px;
	color: #666; height: 30px; font: normal 14px/26px arial; text-align:center;}
</style>
<script type="text/javascript" src="<%=base + path %>/resource/js/jquery-1.7.2.js"></script>
<script type="text/javascript" kind=''>
	//设置全部变量
	var id1;
</script>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){ 
			
			$.ajax({
				url:'<%=path%>/student/delete',
				data:$(this).attr("v"),
				type:"GET",
				dataType: "text",
				success:function(msg){
					alert('删除成功');
				},
				error:function(msg){
					alert('error');
				}
			});
			 
			$(this).parent().parent().remove();
			return false;
		});
		

	});
</script>
<script type="text/javascript">
	$(function(){
			$(".myclass").each(function(){ 
			    var tmp=$(this).children().eq(6); 
			    var btn=tmp.children(); 
			    btn.bind("click",function(){ 
			        //var id= btn.parent().parent().children("td").get(5).children("a").get(0).attr("v"); 
			        id1=$(".delete").attr("v")
			        var name=btn.parent().parent().children("td").get(1).innerHTML; //姓名
			        var sex=btn.parent().parent().children("td").get(2).innerHTML; //性别
			        var age=btn.parent().parent().children("td").get(3).innerHTML; //年龄
			        var memo=btn.parent().parent().children("td").get(4).innerHTML; //备注
			        //alert(id);
			        $("input[name='name']") .attr("value",name);
			        $("input:radio[value="+sex+"]").attr('checked','true');
			        $("input[name='age']") .attr("value",age);
			        $("input[name='memo']") .attr("value",memo);
			        }); 
			    }); 
	});
</script>
<script type="text/javascript">
	//修改
	function stModify(){
		    id1=$(".myclass").attr("id");
		    var name=$("input[name='name']") .attr("value");
			var sex=$("input[name='sex']:checked") .attr("value");
			var age=$("input[name='age']") .attr("value");
			var memo=$("input[name='memo']") .attr("value");
			
			var json = {"id":id1,"name":name,"sex":sex,"age":age,"memo":memo};

		$.ajax({
			url:'<%=path%>/student/modify',
			contentType : "application/json;charset=utf-8",
			data:JSON.stringify(json),
			type:"post",
			dataType: "text",
			success:function(msg){
				alert('修改成功');
				$("input[name='name']").attr("value",""); //姓名
				//$("input[name='sex']").attr("value",""); //性别
		        $("input[name='age']").attr("value",""); //年龄
		        $("input[name='memo']").attr("value",""); //备注
			},
			error:function(msg){
				alert('error');
			}
		});
	}
	//重置	 
	function reset1(){
		$("input[name='name']").attr("value",""); //姓名
		//$("input[name='sex']").attr("value",""); //性别
        $("input[name='age']").attr("value",""); //年龄
        $("input[name='memo']").attr("value",""); //备注
	}
	//查询
	function select1(){
		window.location.href='<%=path%>/student/query';
	}	
	//退出
	function logonOut(){
		window.location.href='<%=path%>';
	}
</script>
</head>
<body style="background-image:url('images/blue.jpg') no-repeat;">
<div align=right>
	<input type="button" onclick="logonOut()" value="退出" style="width:100px"/>
</div>

<div class="main">
<h2 align="center">管理系统</h2>
	<div class="edit">
    	<form action="<%=path %>/student/insert" method="post" name="stuForm">
        	<ul>
            	<li><span><b>*</b>姓名:</span><input name="name" /></li>
                <li><span><b>*</b>性别:</span>
                	<input name="sex" type="radio" value="男" style="width:20px">男</input>
                	<input name="sex" type="radio" value="女" style="width:20px">女</input>
                </li>
                <li><span><b>*</b>年龄:</span><input name="age"/></li>
                <li><span>备注:</span><input name="memo"></input></li>
                <li><span></span>
                	<input type="submit" value="保存" style="width:100px"/>
                    <input type="reset" onclick="reset1()" value="重置" style="width:100px"/>
                    <input type="button" onclick="stModify()" value="确认修改" style="width:100px"/>
                    <input type="button" onclick="select1()" value="查询" style="width:100px"/>
                </li>
            </ul>
        </form>
    </div>
    <div class="show">
    
    	<table>
        	<tr>
          		<td width="10%"><b>序号</b></td>
                <td width="15%"><b>姓名</b></td>
                <td width="10%"><b>性别</b></td>
                <td width="15%"><b>年龄</b></td>
                <td width="25%"><b>备注</b></td>
                <td width="15%"><b>操作</b></td>
                <td width="10%"><b>修改</b></td>
            </tr>
            <c:forEach var="s" items="${student}" varStatus="status">
            	 <tr id='${s.id}' class="myclass">
	            	 <td>${status.count}</td>
	            	 <td>${s.name}</td>
	            	 <td>${s.sex}</td>  
	            	 <td>${s.age}</td> 
	            	 <td>${s.memo}</td> 
	            	 <td><a class='delete' v='id=${s.id}' href="#">删除</a></td>
	            	 <td align="center"> 
                    	<button id="edit" >编辑</button> 
                	 </td> 
            	 </tr>
            </c:forEach>
        </table>
    </div>
</div>
<!-- <div align="center" style="margin-top: 50px;">
	<a href="tencent://message/?uin=3235097294&Site=www.kuangke8.com&Menu=yes">老彭聊聊</a> 
	<a href="http://kuangke8.com" target="new" style="padding-left: 40px;">更多资料</a>
</div> -->
</body>
</html>
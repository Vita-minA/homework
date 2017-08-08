<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录/注册</title>
<h1>欢迎进入我们的红包系统</h1>
<script type="text/javascript">
var patern1=new RegExp("^\[ \t]*$")//空白
function b1(){
	var temp=document.form1.username.value
	if(patern1.exec(temp)){
		document.getElementById("a1").innerHTML="昵称不能为空"
		return false
		}
	else{
		document.getElementById("a1").innerHTML=""
		return true
	}
}
function b2(){
	var temp=document.form1.itcode.value
	if(patern1.exec(temp)){
		document.getElementById("a2").innerHTML="账号不能为空"
		return false
		}
	else{
		document.getElementById("a2").innerHTML=""
		return true
	}
}
</script>
</head>
<body>
<form name="form1" action="login">
<table>
<tr>
<td>username:</td>
<td><input type="text" name="username" onblur="b1()"></input></td>
<td><div id="a1" style="color:#ff0000"></div></td>
</tr>
<tr>
<td>itcode:</td>
<td><input type="text" name="itcode" onblur="b2()"></input></td>
<td><div id="a2" style="color:#ff0000"></div></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="登录"></td>
</tr>
</table>
</form>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>红包发放记录</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/search1.css">
<link href="css/fonts.css" rel="stylesheet" type="text/css" media="all" />
<script src="js/jquery-1.7.1.min.js"></script>
<script src="js/pagination.js"></script>
<script>
	function initAjax() {
		var xmlHttp;
		try {
			// Firefox, Opera 8.0+, Safari
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			// Internet Explorer
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert("您的浏览器不支持AJAX！");
				}
			}
		}
		return xmlHttp;
	}
	function sendvalue(round) {
		var http_request = initAjax();
		var round = round.value;
		alert(round)
		http_request.open("get", "getrecordbyround?round=" + encodeURI(round),
				true);
		http_request.onreadystatechange = function() {
			if (http_request.readyState == 4) {
				if (http_request.status == 200) {
					alert("ok");
					document.getElementById("div1").innerHTML = http_request.responseText;
				}
			}
		}
		http_request.send(null);
	}
</script>
</head>
<body>
<div id="page" class="container">
	<div id="header">
		<div id="logo">
			<img src="img/pic03.jpg" alt="" />
			<h1><a href="#">Admin</a></h1>
		</div>
		<div id="menu" >
			<ul>
				<li><a href="gotoadminhome1" accesskey="1" title="">首页</a></li>
				<li><a href="gotoadminperinfo1" accesskey="2" title="">团队信息</a></li>
				<li><a href="gotoadminprogram1" accesskey="2" title="">节目列表管理</a></li>
				<li ><a href="gotoadminluckyset1" accesskey="3" title="">红包发放管理</a></li>
				<li  class="current_page_item"><a href="gotoadminsearch1" accesskey="4" title="">红包发放记录</a></li>
				<li><a href="gotoadminsearch2" accesskey="5" title="">用户交易记录</a></li>
                <li><a href="logout" accesskey="6" title="">退出登录</a></li>
			</ul>
		</div>
	</div>
	<div id="main" >
	<section class="demo">
	<div class="tableWrap clearfix">
	<form name="formcheck1" action="getrecordbyusernameandtimeandround">
		<table class="table" style="font-weight:500;">

			<tr>
				<td></td>
				<td>用户名</td>
				<td>起始时间</td>
				<td>终止时间</td>
				<td>红包发放轮次</td>
				<td > </td>
			</tr>
			<tr>
				<td >条件：</td>
				<td ><input type="text" name="username" value="username" class="b"
					onfocus="if(value=='username'){value=''}"></input></td>
				<td><input type="text" name="starttime" class="a"
					value="1970-01-01 00:00:00"
					onfocus="if(value=='starttime'){value=''}"></input></td>
				<td><input type="text" name="endtime" class="a"
					value="2025-01-01 00:00:00"
					onfocus="if(value=='endtime'){value=''}"></input></td>
				<td><input type="text" name="round" value="round" class="b"
					onfocus="if(value=='round'){value=''}"></input></td>
				<td><button type="submit"  id="inquiry">查询</button></td>
			</tr>
		</table>
		<hr>
		<div  id="div1">
			<table  style="font-weight:500;" class="table table-striped" >
				<tr>
					<td>交易记录ID &nbsp</td>
					<td>用户名 &nbsp</td>
					<td>发放红包金额（元） &nbsp</td>
					<td>交易记录时间</td>
					<td>红包发放轮次</td>
				</tr>
				<c:forEach var="a" items="${list}">
					<tr>
						<td><c:out value="${a.rid}"></c:out></td>
						<td><c:out value="${a.username}"></c:out></td>
						<td><c:out value="${a.lucky_number/100}"></c:out></td>
						<td><c:out value="${a.tradetime}"></c:out></td>
						<td><c:out value="${a.round}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="show" style="text-align: center" >
			<ul class="pagination">
			<%int i=0;%>
			<li id="head"><a href="javascript:void(0)">首页</a></li>
			<li><input type="hidden" value="00"></input></li>
			<li id="last"><a href="javascript:void(0)">&laquo;</a></li>
					
			<c:forEach var="a" begin="0" end="${pagenum}">
			<%i++; %>
			<li id=<%=i %>><a href="javascript:void(0)"><%=i %></a></li>
			</c:forEach>
			<li id="next"><a href="javascript:void(0)">&raquo;</a></li>
			<li>  </li>
			<li id="tail"><a href="javascript:void(0)">尾页</a></li>
			</ul>
		</div>
	</form>
	<input type="hidden" value="${pagenum}" id="ret" class="ret-class"/>
	</div>
	</section>
	</div>
	 
	</div>
</body>
</html>
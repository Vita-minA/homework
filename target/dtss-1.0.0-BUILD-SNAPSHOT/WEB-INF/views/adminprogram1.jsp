<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE  PUBLIC >
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>个人信息</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="stylesheet" href="css/bootstrap.min.css">
<link href="css/search1.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/fonts.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="css/pop.css" />
<script src="js/jquery-1.4.2.min.js"></script>
<script src="js/popup_layer.js"></script>
<!--[if IE 6]><link href="default_ie6.css" rel="stylesheet" type="text/css" /><![endif]-->
</head>
<body>
<div id="page" class="container">
	<div id="header">
		<div id="logo">
			<img src="img/pic03.jpg" alt="" />
			<h1><a href="#">Privy</a></h1>
		</div>
		<div id="menu">
			<ul>
				<li ><a href="gotoadminhome1" accesskey="1" title="">首页</a></li>
				<li><a href="gotoadminperinfo1" accesskey="2" title="">个人信息</a></li>
				<li class="current_page_item"><a href="gotoadminprogram1" accesskey="2" title="">节目列表管理</a></li>
				<li ><a href="gotoadminluckyset1" accesskey="3" title="">红包管理</a></li>
				<li><a href="gotoadminsearch1" accesskey="4" title="">红包发放记录</a></li>
				<li><a href="gotoadminsearch2" accesskey="5" title="">用户交易记录</a></li>
                <li><a href="logout" accesskey="6" title="">退出登录</a></li>
			</ul>
		</div>
	</div>
	<div id="main">
	<section class="demo">
	<div class="tableWrap clearfix">
	<h3>晚会节目清单</h3>
	<div id="ele1" class="tigger">
		<table class="table table-striped">
			<tr id="0">
				<td>序号</td>
				<td>节目名</td>
				<td>联系人</td>
				<td>部门</td>
			</tr>
			<%
				int i = 0;
			%>
			<c:forEach var="a" items="${list}">
				<%
					i++;
				%>
				<tr id="${a.pid }" >
					<td><c:out value="<%=i%>"></c:out></td>
					<td><c:out value="${a.proname}"></c:out></td>
					<td><c:out value="${a.name}"></c:out></td>
					<td><c:out value="${a.department}"></c:out></td>
				</tr>
			</c:forEach>
		</table>
		<input type="button" value="+" id="add" class="add"></input>
	</div>

	<div id="blk6" class="blk" style="display: none;">
		<div class="main" id="div1">
			<h2>节目信息为：</h2>
			<a href="javascript:void(0)" id="close6" class="closeBtn">关闭</a>
			<div id="div2"></div>
		</div>
	</div>

</div>
</section>
	</div>
	 
	</div>
</body>
</html>
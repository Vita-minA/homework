<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>节目信息</title>
<link rel="stylesheet" href="css/pop.css" />
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-1.7.1.min.js"></script>
<script src="js/popup_layer.js"></script>
</head>
<div style="text-align: center">
<h1>晚会节目清单</h1>
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
</html>
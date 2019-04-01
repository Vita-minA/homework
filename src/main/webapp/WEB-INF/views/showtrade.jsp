<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<script type="text/javascript">
function show2(){
	var username=document.getElementById("show2").value;
	var String=username+"的交易记录";
	if(username=="username"||username==""){
		document.getElementById("div2").innerHTML="所有人的交易记录";
	}
	else{
		document.getElementById("div2").innerHTML=String;
	}
}
</script>
</head>
<div id="div2" style="font: 18px/1.5 Tahoma, DFKai-SB, '宋体', sans-serif;color:rgba(30, 30, 30, 0.65);"></div>
<table class="table table-striped" style="text-align:center">
	<tr>
		<td>交易记录ID &nbsp</td>
		<td>用户名 &nbsp</td>
		<td>交易金额 &nbsp</td>
		<td>交易时间 &nbsp</td>
		<td>交易备注 &nbsp</td>
	</tr>
	<c:forEach var="a" items="${list}">
		<tr>
			<td><c:out value="${a.tid}"></c:out></td>
			<td><c:out value="${a.username}"></c:out></td>
			<td><c:out value="${a.volume/100}"></c:out></td>
			<td><c:out value="${a.tradetime}"></c:out></td>
			<td><c:out value="${a.memo}"></c:out></td>
		</tr>
	</c:forEach>
</table>
	<input type="hidden" value="${username}"id="show2"class="ret-class"/>
	<script type="text/javascript">
	show2();
	</script>
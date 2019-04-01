<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-striped">
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

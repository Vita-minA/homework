<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form>
<table>
<tr><td>  交易记录ID &nbsp </td><td>  用户名 &nbsp </td><td> 发放红包金额 &nbsp</td><td>  交易记录时间  </td><td>  红包发放轮次 </td></tr>
<c:forEach var="a" items="${list}">
<tr>
<td><c:out value="${a.rid}"></c:out></td>
<td><c:out value="${a.username}"></c:out></td>
<td><c:out value="${a.lucky_number}"></c:out></td>
<td><c:out value="${a.tradetime}"></c:out></td>
<td><c:out value="${a.round}"></c:out></td>
</tr>
</c:forEach>
</table>
</form>
</body>
</html>
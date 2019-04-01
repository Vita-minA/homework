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
<input type="button" value="返回" onclick="javascript:location.href='admin1toadmin2?round1number=0&round2number=0&round3number=0&number=0'">
<table>
<tr><td>  Rid &nbsp </td><td>  Wid &nbsp </td><td> Lucky_number &nbsp </td><td>  Tradetime &nbsp </td></tr>
<c:forEach var="a" items="${list}">
<tr>
<td><c:out value="${a.getRid()}"></c:out></td>
<td><c:out value="${a.getWid()}"></c:out></td>
<td><c:out value="${a.getLucky_number()}"></c:out></td>
<td><c:out value="${a.getTradetime()}"></c:out></td></tr>

</c:forEach>
</table>
</body>
</html>
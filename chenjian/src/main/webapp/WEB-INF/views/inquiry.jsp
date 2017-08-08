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
<c:forEach var="a" items="${trade}">
<table>
<tr><td>  Tid &nbsp </td><td>  Wid &nbsp </td><td> Volume &nbsp </td><td>  Tradetime &nbsp </td></tr>
<tr>
<td><c:out value="${a.getTid()}"></c:out></td>
<td><c:out value="${a.getWid()}"></c:out></td>
<td><c:out value="${a.getVolume()}"></c:out></td>
<td><c:out value="${a.getTradetime()}"></c:out></td></tr>
</table>
</c:forEach>
</body>
</html>
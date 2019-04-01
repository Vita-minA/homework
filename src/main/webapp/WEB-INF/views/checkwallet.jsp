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
<tr>
<td></td><td>钱包id  &nbsp</td><td>账户余额  &nbsp</td>
</tr>
<tr>
<td>用户${wallet.username}钱包信息：</td><td>${wallet.wid}</td><td>${wallet.amount}</td>
</tr>
</table>
</form>
</body>
</html>
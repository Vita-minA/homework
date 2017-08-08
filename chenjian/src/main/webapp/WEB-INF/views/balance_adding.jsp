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
<h1>充值结果</h1>
${result}
<hr>
<form name="form1" action="inquiry">
<h1>查询转账记录</h1>
from：<input type="text" name="from"></input> to:<input type="text" name="to"></input>
<input type="submit" value="查询">
请采用标准格式：xxxx-xx-xx xx:xx:xx
</form>
</body>
</html>
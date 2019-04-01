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
<table class="table" style="color:rgba(0,0,0,1)"> 
<tr><td>交易序号</td><td>交易时间</td><td>交易金额</td><td>交易备注</td></tr>
<c:forEach var="temp" items="${trade}">
<tr id="${temp.getTid()}">       
<td>${temp.getTid()}</td>  
<td>${temp.getTradetime()}</td>
<td>${temp.getVolume()/100 }</td>  
<td>${temp.getMemo() }</td>   
</tr> 
</c:forEach>   
</table> 
<script type="text/javascript">
 document.ready(
 );
</script>
</body>
</html>
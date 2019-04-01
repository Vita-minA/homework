<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#rank1{
font-size:200%;
font-weight:500;

}
#rank2{
font-size:160%;
}
#rank3{
font-size:130%
}

</style>
<script language="JavaScript">
function myrefresh()
{
   window.location.reload();
}
setTimeout('myrefresh()',1000); //指定1秒刷新一次
</script>


</head>
<body>  
<table class="table" style="text-align:center"> 
<tr><td>节目序号</td><td>节目名称</td><td>部门</td><td>总打赏金额</td></tr>
			<%
				int i = 0;
			%>
<c:forEach var="temp" items="${program_list}">
				<%
					i++;
				String id="rank"+i;
				%>
<tr id="<%=id%>">
<td ><c:out value="<%=i%>"></c:out></td>
<td><c:out value="${temp.proname}"></c:out></td>
<td><c:out value="${temp.department}"></c:out></td>
<td><c:out value="${temp.award/100}"></c:out></td>  
</tr>
</c:forEach>  
</table>
</body>
</html>
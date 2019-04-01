<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/JavaScript">
	function updatepro() {
		document.form1.action = "updatepro";
		document.form1.submit();
	}
	function deletepro() {
		document.form1.action = "deletepro";
		document.form1.submit();
	}
</script>
<form name="form1" id="form1">
	<ul>
		<li><input type="hidden" name="pid" value="${program.pid} "></input></li>
	</ul>
	<ul>
		<li></li>
		<li>节目名：</li>
		<li><input name="proname" value="${program.proname }"></input></li>
	</ul>
	<ul>
		<li></li>
		<li>联系人姓名：</li>
		<li><input name="name" value="${program.name }"></input></li>
	</ul>
	<ul>
		<li></li>
		<li>联系人电话：</li>
		<li><input name="phone" value="${program.phone} "></input></li>
	</ul>
	<ul>
		<li></li>
		<li>表演类型：</li>
		<li><input name="type" value="${program.type} "></input></li>
	</ul>
	<ul>
		<li></li>
		<li>大致时长：</li>
		<li><input name="time" value="${program.time} "></input></li>
	</ul>
	<ul>
		<li></li>
		<li>部门：</li>
		<li><input name="department" value="${program.department} "></input></li>
	</ul>
	<ul>
		<li></li>
		<li><input type="submit" value="提交" id="change"
			onclick="updatepro()"></input></li>
		<li></li>
		<li><input type="submit" value="删除" id="delete"
			onclick="deletepro()"></input></li>
	</ul>
</form>

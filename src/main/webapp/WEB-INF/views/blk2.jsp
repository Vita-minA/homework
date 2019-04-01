<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/JavaScript">
	function insertpro() {
		document.form1.action = "insertpro";
		document.form1.submit();
	}
</script>
<form name="form1" id="form1" method="get">
	<ul>
		<li></li>
		<li>节目名：</li>
		<li><input name="proname" value=""></input></li>
	</ul>
	<ul>
		<li></li>
		<li>联系人姓名：</li>
		<li><input name="name" value=""></input></li>
	</ul>
	<ul>
		<li></li>
		<li>联系人电话：</li>
		<li><input name="phone" value=""></input></li>
	</ul>
	<ul>
		<li></li>
		<li>表演类型：</li>
		<li><input name="type" value=""></input></li>
	</ul>
	<ul>
		<li></li>
		<li>大致时长：</li>
		<li><input name="time" value=""></input></li>
	</ul>
	<ul>
		<li></li>
		<li>部门：</li>
		<li><input name="department" value=""></input></li>
	</ul>
	<ul>
		<li></li>
		<li><input type="submit" value="添加" id="change" onclick="insertpro()"></input></li>
		<li></li>
		<li><input type="button" value="取消" id="cancel" onclick="javascript:window.location.href='gotoadminprogram1'" ></input></li>
	</ul>
</form>
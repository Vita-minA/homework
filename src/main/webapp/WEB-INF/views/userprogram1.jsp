<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>快来打赏</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/program2.css">
<link href="css/user.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/bgslideshow.js"></script>
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>





<script type="text/javascript">
/**
 * AjAX获取节目打赏结果，优秀节目列表
 */
	function initAjax() {
		var xmlHttp;
		try {
			// Firefox, Opera 8.0+, Safari
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			// Internet Explorer
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert("您的浏览器不支持AJAX！");
				}
			}
		}
		return xmlHttp;
	}
	
/**
 * test2函数请求打赏操作，并返回打赏结果
 */
			function test2() { 
				
				   var pid=document.getElementById("tr1").value;
				   var amount=document.getElementById("dmoney").value;
		var http_request = initAjax();   
		http_request.open("get", "reward?amount="+(amount*100)+"&pid="+pid, true);
		http_request.onreadystatechange = function() {
			if (http_request.readyState == 4) {
				if (http_request.status == 200) {
					  if(http_request.responseText==1){
							 alert("打赏成功");
						 }else if(http_request.responseText==-2){
							 
							 alert("请充值");
						 }else{
							 alert("打赏失败，请重新打赏");
							 
						 }
					  close();
				}
			}
		} 
		http_request.send(null);
		
	} 
	/**
	
	Headprogram函数向服务器请求返回优秀节目列表
	**/
	function Headprogram() { 
			
		var http_request = initAjax();   
		http_request.open("get", "PerfectProgram", true);
		http_request.onreadystatechange = function() {
			if (http_request.readyState == 4) {
				if (http_request.status == 200) {
					document.getElementById("headprogram").innerHTML = http_request.responseText;
				}
			}
		}
		http_request.send(null);
	} 
	/**
	
	findproname返回打赏节目名称
	**/
	function findproname(pid) {
		
		var http_request = initAjax();   
		http_request.open("get", "findproname?pid="+pid, true);
		http_request.onreadystatechange = function() {
			if (http_request.readyState == 4) {
				if (http_request.status == 200) {
					document.getElementById("poiuy").innerHTML = http_request.responseText;
				}
			}
		}
		http_request.send(null);
	} 
/**
 * show函数实现打赏弹框的弹出
 */
	function show(){
		
		if(document.getElementById("jiemu").style.display=="none"){
		document.getElementById("jiemu").style.display="";
		}
		else
			document.getElementById("jiemu").style.display="none";
	}
	function close(){
		back.style.display="none";
		aa.style.display="none"; 
	}
</script>

</head>
<body onload="Headprogram()">
<div id="page_content">
<div id="page" class="container">
	<div id="header">
		<div id="logo">
			<img  alt="${userface}"
							src="img/${userface}" width="80" height="80">
			<h1><a href="#">${username}</a></h1>
		</div>
		<div id="menu">
			<ul>
				<li ><a href="gotouserhome1" accesskey="1" title="">首页</a></li>
				<li><a href="gotouserperinfo1" accesskey="2" title="">个人信息</a></li>
				<li ><a href="gotouserchat1" accesskey="3" title="">来聊天啊</a></li>
				<li><a href="gotouserlucky1" accesskey="4" title="">抢红包了</a></li>
				<li class="current_page_item"><a href="gotouserprogram1" accesskey="5" title="">节目列表</a></li>
                <li><a href="logout" accesskey="6" title="">退出登录</a></li>
			</ul>
		</div>
	</div>
	<div id="main">
	<section class="demo">
	<div class="tableWrap clearfix">
	<div id="back">
	<div id="aa">
		<div><input  id="wrong" type="button" value="X" ></div>
			<table class="table"style="text-align: center">
				<tr>
					<td>当前节目：</td>
					<td><div id="poiuy"></div></td>
				</tr>
				<tr>
					<td>打赏金额：</td>
					<td><input type="text"id="dmoney" name="dmoney" value="" /></td>
				</tr>
				
			</table>
			<input type="hidden" name="select1" value="" id="tr1">
				<input type="button" id="dashang" name="dashang" class="award2"
					value="赏" onclick="Headprogram();test2()">
			 
		
	</div> 
	</div>
	<div id="headprogram" style="z-index: 0"></div>
	<div id="zz">
		<input type="button" id="pro" value="点击生成节目单" onclick="show()">
	</div>
	<div id="jiemu" style="display: none;">
		<form name="jj" action="ProgramList">
			<table class="table" style="text-align: center">
				<tr>
					<td>序号</td>
					<td>节目名称</td>
					<td>部门</td>
					<td>请为我加油！</td>
				</tr>
				<%
					int i = 0;
				%>
				<c:forEach var="temp" items="${program_list}">
					<%
						i++;
					%>
					<tr>
						<td><%=i%></td>
						<td>${temp.proname}</td>
						<td>${temp.department}</td>
						<td><input id="${temp.pid}" type="button" value="赏"class="award" onclick="sbbutton(this)"></td>
					</tr>
					
				</c:forEach>
			</table>
		</form>
	</div>
	</div>
	</div>
	</section>
	
	</div>
	</div>
	    <!-- The container for the background-image -->
<img id="bg" style="display:none" />
	<script>  
	//各种点击操作  
	function $(id) 
	{
		return document.getElementById(id); 
	}  
     function sbbutton(obj)
     {
     var pid=obj.id;  
     back.style.display="block"; 
     aa.style.display="block";
    
    
     document.getElementById("tr1").value = pid;
    
     findproname(pid);
     var tim=null;
     tim=setInterval(function()  
      	{ 
      	   var look=document.documentElement.clientWidth;
		       var oh=document.documentElement.clientHeight;
		       var tw=aa.offsetWidth;
		       var th=aa.offsetHeight;
		       var stop=(oh-th)/2;
		       var sleft=(look-tw)/2;
      	},1) 
      	} 
	 $('wrong').onclick=function(){
			back.style.display="none";
			aa.style.display="none"; 
	 }
	 
	 
	</script>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/program2.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript">
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
	function dashang(obj) { 
		var http_request = initAjax();   
		http_request.open("get", "dashang?pid=" + encodeURI(obj) , true);
		http_request.onreadystatechange = function() {
			if (http_request.readyState == 4) {
				if (http_request.status == 200) {
					document.getElementById("restmoney").innerHTML = http_request.responseText;
				}
			}
		}
		http_request.send(null);
	} 
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
<script type="text/javascript">
if(${result}==1) 
{
	window.location.reload();
	alert("打赏成功");

}  
else if(${result}==0){        
alert("打赏失败");  
}
else if(${result}==-2){
	alert("请充值");
	
}
</script>
</head>
<body onload="Headprogram()">
	<div id="back"></div>
	<div id="aa">
		<div><input  id="wrong" type="button" value="X" ></div>
		<form name="d_form" action="reward" method="post">
			<table class="table"style="text-align: center">
				<tr>
					<td>当前节目：</td>
					<td><div id="poiuy"></div></td>
				</tr>
				<tr>
					<td>打赏金额：</td>
					<td><input type="text" name="dmoney" value="" /></td>
				</tr>
				
			</table>
			<input type="hidden" name="select1" value="" id="tr1">
				<input type="submit" id="dashang" name="dashang" class="award2"
					value="赏" onclick="Headprogram();">
			
		</form>
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
						<td><input id="${temp.pid}" type="button" value="赏"
							class="award" onclick="sbbutton(this)"></td>
					</tr>
					
				</c:forEach>
			</table>
		</form>
	</div>
	<script>  
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
		       var tw=chongzhiing.offsetWidth;
		       var th=chongzhiing.offsetHeight;
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






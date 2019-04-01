<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>
function showhidediv(id){
	var sbtitle=document.getElementById(id);
	if(sbtitle){
	   if(sbtitle.style.display=='block'){
	   sbtitle.style.display='none';
	   }else{
	   sbtitle.style.display='block';
	   }
	}
	}

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
  function sendvalue(round) {

	var http_request = initAjax();
	var round=round.value;
	alert(round)
	http_request.open("get", "lucky_on?round=" +encodeURI(round), true);
	http_request.onreadystatechange = function() {
		if (http_request.readyState == 4) {
			if (http_request.status == 200) {
				if (http_request.responseText == '1') {
					  
					  alert("123");
				} 
			}
		}
	}
	http_request.send(null);
	alert("test");
  }
</script>
</head>
<body>
<form name="formstart" action="addadmin">
<table>
<tr>
<td></td>
<td><button type="button" id="round1" value="1" onclick="sendvalue(this)">开启第一轮红包雨</button></td>
<td><button type="button" id="round2" value="2" onclick="sendvalue(this)">开启第二轮红包雨</button></td>
<td><button type="button" id="round3" value="3" onclick="sendvalue(this)">开启第三轮红包雨</button></td>
<td><button type="button" id="round4" value="4" onclick="sendvalue(this)">开启抢红包</button></td>
</tr>
<tr>
<td>当前余额：</td>
<td>${get1/100}元</td>
<td>${get2/100}元</td>
<td>${get3/100}元</td>
<td>${get4/100}元</td>
</tr>
<tr>
<td>充值通道：</td>
<td><input type="text"  style="text-align: right" name="round1number" value="0" onfocus="if(value=='0'){value=''}" ></input>元 <input type="submit" id="round11" value=""></input></td>
<td><input type="text"  style="text-align: right" name="round2number" value="0" onfocus="if(value=='0'){value=''}"></input>元 <input type="submit" id="round22"></input></td>
<td><input type="text"  style="text-align: right" name="round3number" value="0" onfocus="if(value=='0'){value=''}"></input>元 <input type="submit" id="round33"></input></td>
<td><input type="text"  style="text-align: right" name="number" value="0" onfocus="if(value=='0'){value=''}"></input>元 <input type="submit" id="round44"></input></td>
</tr>
<tr>
<h1>当前admin账户钱包余额：${adminwallet/100}元</h1>
</tr>
</table>
</form>
<div id="msg" style="display:none;">出现显示的内容
<img alt="" src="img/pic03.jpg">
</div> <!--这里是MsgDiv-->
<button onclick='showhidediv("msg")'>点击显示</button>
</body>
</html>
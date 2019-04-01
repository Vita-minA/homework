<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
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
function sendvalue() {
	var http_request = initAjax();
	alert("go")
	http_request.open("get", "snatchred", true);
	http_request.onreadystatechange = function() {
		if (http_request.readyState == 4) {
			if (http_request.status == 200) {
				if(http_request.responseText>0)
					{
					alert("恭喜您，抢到红包"+http_request.responseText/100+"元");
				    window.location.href="gotosnatch";
					}
				
				else alert("no");
				
			}
		}
	}
	http_request.send(null);
  }
</script>
</head>
<body>
<td><button type="button" id="go" value="1" onclick="sendvalue()">抢红包</button></td>
</body>
</html>
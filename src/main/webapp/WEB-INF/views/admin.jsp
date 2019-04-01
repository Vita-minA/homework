<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
function show(url) {
      var xmlHttp=initAjax();
     xmlHttp.open("get", "firstpage?password=" +encodeURI(password)
				+ "&username=" +encodeURI(username)+"&code="+encodeURI(code), true);
      xmlHttp.onreadystatechange=function() {    
        if (xmlHttp.readyState == 4) {
          if (xmlHttp.status == 200) {   
            document.getElementById("right").innerHTML=xmlHttp.responseText;  //重设页面中id="content"的div里的内容
            executeScript(xmlHttp.responseText);  //执行从服务器返回的页面内容里包含的JavaScript函数
          }
          //错误状态处理
          else if (xmlHttp.status == 404){
            alert("出错了☹  （错误代码：404 Not Found），……！"); 
            /* 对404的处理 */
            return;
          }
          else if (xmlHttp.status == 403) { 
            alert("出错了☹  （错误代码：403 Forbidden），……"); 
            /* 对403的处理 */ 
            return;
          }
          else {
            alert("出错了☹  （错误代码：" + request.status + "），……"); 
            /* 对出现了其他错误代码所示错误的处理  */
            return;
          }  
        } 
       }
      //把请求发送到服务器上的指定文件（url指向的文件）进行处理
      xmlHttp.open("GET", url, true);    //true表示异步处理
      xmlHttp.send();
    }    
</script>
</head>
<body>
<div id="left">   
<ul id="firstpage">
  <li><a href="#index" onclick="show('firstpage.jsp')"> 首页 </a></li>
</ul>
<ul id="function">
  <li><a href="#luckyon" onclick="showAtRight('recordList.jsp')" >开启发红包功能</a></li>
</ul>
<ul id="tradelist">
  <li><a href="#tradelist" > 查询交易记录 </a></li>
</ul>
<ul id="performlist">
  <li><a href="#performlist" > 查询节目信息 </a></li>
</ul>
</div>  
<div id="right">
</div> 
</body>
</html>
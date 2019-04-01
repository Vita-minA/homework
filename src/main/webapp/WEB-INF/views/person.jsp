
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<style>
* {
	padding: 0;
	margin: 0; 
}
/* button的样式*/
button.btn { 
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
}
button.btn_lq{
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 7px 8px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 8px; 
    margin: 4px 2px;
    cursor: pointer;
}

#person_message{
    position: absolute;
	display: block;
	font-size: 50px;
	color: #6e6e6e;
	top: 100px;
	left: 300px; 
}
#back {
	width: 100%;
	height: 100%;
	opacity: 0.6;
	background: #d5d5d5;
	position: absolute;
	top: 0px;
	left: 0px;
	display: none;
}
/*当前余额*/
#restmoney_name {
	position: absolute;
	display: block;
	font-size: 18px;
	color: #6e6e6e;
	top: 350px;
	left: 250px;
}
 
#restmoney {
	position: absolute;
	diaplay: block;
	font-size: 18px;
	color: #6e6e6e;
	top: 370px;
	left: 450px;
}
.hform{
border: none;
	position: absolute;
	top: 170px;
	left: 700px;
	overflow: hidden;
	padding: 60px;
	background-color: #ffffff;
	display: none;
	word-spacing: 10px;

}
#money {
	position: absolute;
	display: block;
	font-size: 50px;
	color: #6e6e6e;
	top: 420px;
	left: 300px;
} 

#result1 {
	border: none;
	position: absolute;
	top: 170px;
	left: 700px;
	overflow: hidden;
	padding: 60px;
	background-color: #ffffff;
	display: none;
	word-spacing: 10px;
}

#wrong {
	font-size: 20px;
	color: #d5d5d5;
	position: absolute;
	top: 10px;
	right: 10px;
	cursor: pointer;
}

#wrong:hover {
	color: #6e6e6e;
}

input {
	border: none;
	background: #ffffff;
	color: #3c3c3c;
	border: 1px solid #d5d5d5;
	padding-left: 10px;
	padding-right: 10px;
	line-height: 30px;
}

#wrong1 {
	font-size: 20px;
	color: #d5d5d5;
	position: absolute;
	top: 10px;
	right: 10px;
	cursor: pointer;
}
 
#wrong1:hover {
	color: #6e6e6e;
}

#wrong3 {
	font-size: 20px; 
	color: red;
	position: absolute;
	top: 10px;
	right: 10px;  
	cursor: pointer; 
	display:block; 

}

#wrong3:hover {
	color: #6e6e6e; 
}


/*form中的提交和充值按钮*/
.zuse {
	line-height: 30px;
	color: #ffffff;
	border: none;
	background: #3F89EC;
	padding: 0px 15px;
	text-align: center;
	cursor: pointer;
	font-size: 18px;
	border-radius: 4px; 
	margin-left: 74px;
}
/*交易记录*/
#deal_records {
	position: absolute;
	display: block; 
	font-size: 10px;
	color: #6e6e6e;
	top: 10px;
	right: 30px;
}

#jiaoyi {
	border: none;
	position: absolute;
	top: 50px;
	left: 450px;   
	width:450px;   
	height:500px;
	overflow: hidden;
	padding: 30px; 
	background-color: #ffffff;
	display: none; 
	word-spacing: 30px;  
	margin:0 auto;  
}  
.hform{
    background-image:url("img/div1.jpg"); 
	background-size: cover;
}
body{ 
	background-image:url("img/body_background.jpg"); 
	background-size: cover;																									
}
</style>
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
	  function show1() {   
		var http_request = initAjax();
		http_request.open("get", "MyWallet", true);
		http_request.onreadystatechange = function() {
			if (http_request.readyState == 4) {
				if (http_request.status == 200) {
					document.getElementById("restmoney").innerHTML=http_request.responseText;
				}
			}
		}
		http_request.send(null);
	  }  
	  function show2() {   
			var http_request = initAjax();
			http_request.open("get", "jiaoyi_record", true);
			 
			http_request.onreadystatechange = function() {
				if (http_request.readyState == 4) {
					if (http_request.status == 200) {
						document.getElementById("jiaoyi").innerHTML=http_request.responseText;
					}
				}
			}
			http_request.send(null);
		  }  
	  function show3() {   
			var http_request = initAjax();
			http_request.open("get", "perperson", true);
			 
			http_request.onreadystatechange = function() {
				if (http_request.readyState == 4) {
					if (http_request.status == 200) {
						document.getElementById("person_message").innerHTML=http_request.responseText;
					}
				}
			}
			http_request.send(null);
		  }  
	  </script>

<script type="text/javascript">
if(${result}==1)  
{alert("充值成功");}    
else{        
alert("充值失败"); 
}       
</script>
</head>   
<body onload="show1();show3()">
<div id="person_message">
 
</div>      
	<div id="deal_records">  
	   <button id="btn_lq"class="btn_lq"type="button"onclick="show2()">零钱明细</button>
	</div> 
	<div id="restmoney_name">    
		<h1>当前余额:</h1> 
	</div>  
	<div id="restmoney">  
	//余额
		<%-- ${wallet.getAmount()} --%>
	</div>   
	<div id="money">   
	     <button id="btn_cz"class="btn"type="button"onclick="show1()">充值</button>
	     <button id="btn_tx"class="btn"type="button"onclick="show1()">提现</button> 
	</div>
	<div id="back">
	 <div id="wrong3">✖</div>
	</div>  
	<div id="chongzhiing"class="hform" >
		<div id="wrong">✖</div>
		<form name="c_form" action="balance_adding" method="post">
			充值金额：<input type="text" name="zmoney" value="" /><br />
			<br /> <input type="submit" id="zchongzhi" class="zuse"
				value="充值" onclick="show1()">
		</form>
	</div> 
	<div id="tixianing"class="hform">
		<div id="wrong1">✖</div>
		<form name="t_form" action="" method="get">
			提现金额：<input type="text" id="tmoney" value="" /><br />
			<br /> <input type="submit" class="zuse" value="提现" />
		</form>
	</div> 
    
	<div id="jiaoyi" style="overflow:auto;">
	  
		<%--交易的记录，返回对象的值 --%>
	</div>
	<script type="text/javascript">
   function $(id)
	{
		return document.getElementById(id);
	}
   var chongzhiing=document.getElementById('chongzhiing')
   $('btn_cz').onclick=function(){//设置充值框和提现框居中显示
	   back.style.display="block";
	   chongzhiing.style.display="block";
	   var tim=null; 
       tim=setInterval(function() 
        	{  
        	   var look=document.documentElement.clientWidth;
		       var oh=document.documentElement.clientHeight;
		       var tw=chongzhiing.offsetWidth; 
		       var th=chongzhiing.offsetHeight; 
		       var stop=(oh-th)/2;   
		       var sleft=(look-tw)/2;   
               chongzhiing.style.top=stop+'px';
               chongzhiing.style.left=sleft+'px';   
        	},1)      
   }  
   $('btn_tx').onclick=function(){  
	   back.style.display="block";
	   tixianing.style.display="block";
	   var tim=null;  
       tim=setInterval(function()  
        	{ 
        	   var look=document.documentElement.clientWidth;
		       var oh=document.documentElement.clientHeight;
		       var tw=tixianing.offsetWidth;
		       var th=tixianing.offsetHeight;
		       var stop=(oh-th)/2;
		       var sleft=(look-tw)/2;
               tixianing.style.top=stop+'px';
               tixianing.style.left=sleft+'px';
        	},1)  
   } 
 
   $('deal_records').onclick=function(){
	   
	   back.style.display="block";   
	   jiaoyi.style.display="block";
   }  
   $('wrong').onclick=function(){
		back.style.display="none";
		chongzhiing.style.display="none"; 
		
		// $('xs').style.display="none";
       // $('yc').style.display="block";
		}
   $('wrong1').onclick=function(){
	   back.style.display="none";    
       tixianing.style.display="none"; 
   }        
          
   $('wrong3').onclick=function(){  
	   back.style.display="none";
       jiaoyi.style.display="none";
       
   } 
            
   $('zchongzhi').onclick=function(){
	   result1.style.display="block"; 
	   back.style.display="block"; 
   }
   </script>
</body>
</html>
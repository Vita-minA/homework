<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页</title>
<link href="css/user.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/bgslideshow.js"></script>
<script type="text/javascript" src="js/custom.js"></script>


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
					if(http_request.responseText==1){
					
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
	  function show4() {   
			var http_request = initAjax();
			http_request.open("get", "balance_adding", true);
			 
			http_request.onreadystatechange = function() {
				if (http_request.readyState == 4) {
					if (http_request.status == 200) {
						if(http_request.responseText==1){
							alert("充值成功");}
						else{
							alert("充值失败");
						}
						}
					}
				}
			}
			http_request.send(null);
		  }  
	  </script>

 
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
</head>
<body onload="show1();show3()">
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
				<li><a href="gotouserhome1" accesskey="1" title="">首页</a></li>
				<li  class="current_page_item"><a href="gotouserperinfo1" accesskey="2" title="">个人信息</a></li>
				<li ><a href="http://10.10.15.249:8080/luckymoney/login?username=JoyFly&itcode=1001" accesskey="3" title="">来聊天啊</a></li>
				<li><a href="gotouserlucky1" accesskey="4" title="">抢红包了</a></li>
				<li><a href="gotouserprogram1" accesskey="5" title="">节目列表</a></li>
                <li><a href="#" accesskey="6" title="">退出登录</a></li>
			</ul>
		</div>
	</div>
	 <div id="main">
	 
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
		<form name="c_form">
			充值金额：<input type="text" name="zmoney" value="" /><br />
			<br /> <input type="button" id="zchongzhi" class="zuse"
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

	 
	 
	 </div>
	</div>
	</div>
	    <!-- The container for the background-image -->
<img id="bg" style="display:none" />
</body>
</html>
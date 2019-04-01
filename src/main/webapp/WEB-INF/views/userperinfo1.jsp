<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>个人信息</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link href="css/user.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/bgslideshow.js"></script>
<script type="text/javascript" src="js/custom.js"></script>


<script type="text/javascript">
/**
 * AJAX获取个人信息，交易记录，以及充值，提现的返回结果
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
	 /*  function show1() {   
		
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
	  } */
	  function show1() { 
			
		  
            var http_request = initAjax();   
             http_request.open("get", "MyWallet", true);
             http_request.onreadystatechange = function() {
	                  if (http_request.readyState == 4) {
	                   	if (http_request.status == 200) {
	                   		document.getElementById("restmoney").innerHTML = "当前余额为："+http_request.responseText/100+"元";
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
		    
	
			   function test2(){
				   var amount=document.getElementById("zmoney").value;
				   $.get("balance_adding?amount="+(amount*100),function(data,status){ 
					   if(data==1){
							 alert("充值成功");
							 javascript:location.reload();
						 }else{	 
							 alert("充值失败");
						 }
				});
				   } 
	  </script>
 

	<script type="text/javascript">
 
   function btn_cz(){//设置充值框和提现框居中显示
	   
	   backface.style.display="block";
	   chongzhiing.style.display="block";
	   var tim=null; 
       tim=setInterval(function() 
        	{   
        	},1)      
   }  
   function btn_tx(){  
	   backface.style.display="block";
	   tixianing.style.display="block";
	   var tim=null;  
       tim=setInterval(function()  
        	{ 

        	},1)  
   } 
 
   function wrong(){
		backface.style.display="none"; 
		chongzhiing.style.display="none"; 
		
		// $('xs').style.display="none";
       // $('yc').style.display="block";
		}
   function wrong1(){
	   backface.style.display="none";    
       tixianing.style.display="none"; 
   }        
          
   function wrong3(){  
	   back2.style.display="none";
       jiaoyi.style.display="none";
       
   } 
            
   function zchongzhi(){
	   result1.style.display="block"; 
	   backface.style.display="block"; 
   }
   function zuse(){
	   backface.style.display="none";
	   tixianing.style.display="none"; 
   }
   function a(){
	   chongzhiing.style.display="none"; 
	   backface.style.display="none"; 
   }
   </script>

</head>
<style>
body{
color:rgba(0,0,0,1);
}
#person_message{
font-family:Arial,Helvetica,sans-serif;
       font-size:1em;
       font-weight:normal;
     padding-top:50px;
    width:300px;
	display: block;
	font-size: 30px;
	color: #6e6e6e;
	 
}
#money{
font-family:Arial,Helvetica,sans-serif;
    font-size:1em;
    
    font-weight:normal;
    
    
	display: block;
	font-size: 30px;
	color: #6e6e6e;
	
}
#backface {

	width: 50%;
	height: 50%;
	opacity: 0.6;
	background: #d5d5d5;
	
	
	display: none;
}
#back2 {

	width: 40%;
	height: 50%;
	opacity: 0.6;
	background: #d5d5d5;
	
	display: none;
}

#chongzhiing {
	display: none;
}

#tixianing {
	display: none;
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
	font-size: 20px;
	color: #6e6e6e;
	position: absolute;
	top: 10px;
	right: 10px;
	cursor: pointer;
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
		font-size: 20px;
	position: absolute;
	top: 10px;
	right: 10px;
	cursor: pointer;
}

#wrong3 {
	font-size: 20px;
	color: red;
	position: absolute;
	top: 10px;
	right:10px;
	cursor: pointer;
	display: block;
}

#wrong3:hover {
	color: #6e6e6e;
	font-size: 20px;
	position: absolute;
	top: 10px;
	right:10px;
	cursor: pointer;
	display: block;
}
#jiaoyi{
	border: none;
   
	width:700px;   
	height:500px;
	overflow: hidden;
	padding: 30px; 
	background-color: #ffffff;
	font-color:rgba(0,0,0,1);
	word-spacing: 30px;  
	
}
#restmoney_name{
font-family:Arial,Helvetica,sans-serif;
    font-size:1em;
    margin-left:30%;
    font-weight:normal;
	display: block;
	font-size: 30px;
	font-color:rgba(0,0,0);
	color: #6e6e6e;
	padding-top:100px
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
</style>
<body onload="show1();show3();show2();">

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
				<li ><a href="gotouserchat1" accesskey="3" title="">来聊天啊</a></li>
				<li><a href="gotouserlucky1" accesskey="4" title="">抢红包了</a></li>
				<li><a href="gotouserprogram1" accesskey="5" title="">节目列表</a></li>
                <li><a href="logout" accesskey="6" title="">退出登录</a></li>
			</ul>
		</div>
	</div>
	<div id="main">
		<section class="demo">
	<div class="tableWrap clearfix">
	<div id="chongzhiing" class="hform"  style="z-index:999">
		<div id="wrong" onclick="wrong()">✖</div>
		<form name="c_form">
			充值金额：<input type="text"id="zmoney" name="zmoney" value="" /><br />
			<br /> <input type="button" id="zchongzhi" class="zuse"
				value="充值" onclick="a();show1();test2();">
		</form>
	</div> 
	<div id="tixianing" class="hform"  style="z-index:999">
		<div id="wrong1" onclick="wrong1()">✖</div>
		<form name="t_form" action="" method="get">
			提现金额：<input type="text" id="tmoney" value="" /><br />
			<br /> <input type="submit" class="zuse" value="提现" id="zuse" onclick="zuse()"/>
		</form>
	</div> 
	 
	 
	 <div style="float:left" style="top:100px" id="person_message">
	 </div>      
	<div style="width:700px"  style="float:left" style="top:100px" id="deal_records" >  
	
	<div id="restmoney_name">    
			<div id="restmoney">  
			<%-- ${wallet.getAmount()} --%>
	</div>  
	</div>  
 
	<div id="money">   
	     <button id="btn_cz" class="btn" type="button" onclick="btn_cz()">充值</button>
	     <button id="btn_tx" class="btn" type="button" onclick="btn_tx()">提现</button>  
	</div>
	<div id="backface">

	</div>  
	<div id="back2">
		 <div id="wrong3" onclick="wrong3()">✖</div>
	</div>

    </div>
	<div id="jiaoyi" style="overflow:auto;">
		<%--交易的记录，返回对象的值 --%>
	</div>

	 </div>
	 </section>
	</div>
	</div>
	</div>
	    <!-- The container for the background-image -->
<img id="bg" style="display:none" />
</body>
</html>
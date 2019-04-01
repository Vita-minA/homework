<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE   PUBLIC >
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>发红包了</title>
<link href="css/lucky2.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/fonts.css" rel="stylesheet" type="text/css" media="all" />
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
  function sendvalue(round) {
	var http_request = initAjax();
	var round=round.value;
	http_request.open("get", "lucky_on?round=" +encodeURI(round), true);
	http_request.onreadystatechange = function() {
		if (http_request.readyState == 4) {
			if (http_request.status == 200) {
				if (http_request.responseText == '1') {
					alert("红包雨已成功发放")
					window.location.href="gotolucky";
				} 
				else if (http_request.responseText == '2') {
					alert("成功开启抢红包功能")
					window.location.href="gotostart";
				} 
			}
		}
	}
	http_request.send(null);
	
  }
</script>

</head>
<body>
<div align="center">

</div>
<div id="page" class="container">
	<div id="header">
		<div id="logo">
			<img src="img/pic03.jpg" alt="" />
			<h1><a href="#">Admin</a></h1>
		</div>
		<div id="menu">
			<ul>
				<li ><a href="gotoadminhome1" accesskey="1" title="">首页</a></li>
				<li ><a href="gotoadminperinfo1" accesskey="2" title="">团队信息</a></li>
				<li><a href="gotoadminprogram1" accesskey="2" title="">节目列表管理</a></li>
				<li class="current_page_item"><a href="gotoadminluckyset1" accesskey="3" title="">红包发放管理</a></li>
				<li><a href="gotoadminsearch1" accesskey="4" title="">红包发放记录</a></li>
				<li><a href="gotoadminsearch2" accesskey="5" title="">用户交易记录</a></li>
                <li><a href="logout" accesskey="6" title="">退出登录</a></li>
			</ul>
		</div>
	</div> 
    <div id="main" >
    <section class="demo">
    <div class="tableWrap clearfix">
    <form name="formstart" action="addadmin" method="post">
			<div class="table left">
				<h2>第一轮红包雨发放控制</h2>
				<div class="price">
					<span>￥${get1/100}</span>
					<span>账户余额</span>
					<button type="button" id="round1" value="1" onclick="sendvalue(this)">发放</button>
				</div>
                <ul>
					<li>充值金额：<input type="text" style="text-align: right" name="round1number" value="0" onfocus="if(value=='0'){value=''}">   <button type="submit"  >充值</button>
                    </li>
				</ul>
                <div class="money">
                <span>￥${adminwallet/100}</span>
					<span>总金额</span>
                    </div>
				<p></p>
			</div>  

        		<div class="table left">
				<h2>第二轮红包雨发放控制</h2>
				<div class="price">
					<span>￥${get2/100}</span>
					<span>账户余额</span>
					<button type="button" id="round2" value="2" onclick="sendvalue(this)">发放</button>
				</div>
				<ul>
					<li>充值金额：<input    type="text" style="text-align: right" name="round2number" value="0" onfocus="if(value=='0'){value=''}">   <button type="submit"  >充值</button>
                    </li>
				</ul>
                <div class="money">
                <span>￥${adminwallet/100}</span>
					<span>总金额</span>
                </div>
				<p></p>
			</div> 
            		<div class="table left">
				<h2>第三轮红包雨发放控制</h2>
				<div class="price">
					<span>￥${get3/100}</span>
					<span>账户余额</span>
					<button type="button" id="round3" value="3" onclick="sendvalue(this)">发放</button>
				</div>
				<ul>
					<li>充值金额：<input    type="text" style="text-align: right" name="round3number" value="0" onfocus="if(value=='0'){value=''}">   <button type="submit"  >充值</button>
                    </li>
				</ul>
				
                <div class="money">
                <span>￥${adminwallet/100}</span>
					<span>总金额</span>
                </div>
				<p> </p>
			</div> 
        <div class="table fly">
				<h2>抢红包管理</h2>
				<div class="price">
					<span>￥${get4/100}</span>
					<span>账户余额</span>
					<button type="button"  id="round4" value="4" onclick="sendvalue(this)">开始</button>
				</div>      

				<ul>
					<li>充值金额：<input    type="text" style="text-align: right" name="number" value="0" onfocus="if(value=='0'){value=''}">   <button type="submit"  >充值</button>
                    </li>
				</ul>
                <div class="money">
                <span>￥${adminwallet/100}</span>
					<span>总金额</span>
                </div>
				<p> </p>
			</div>	
			</form>
            </div>	
	</section>
    </div>
    </div> 

</body>
</html>


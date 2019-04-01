<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>红包来了</title>
<link href="css/user.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/bgslideshow.js"></script>
<style type="text/css">
#main h1
{
color:#fff;
line-height:700px;
vertical-align:middle;

}

</style>
</head>
<body>
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
				<li><a href="gotouserperinfo1" accesskey="2" title="">个人信息</a></li>
				<li ><a href="gotouserchat1" accesskey="3" title="">来聊天啊</a></li>
				<li class="current_page_item"><a href="gotouserlucky1" accesskey="4" title="">抢红包了</a></li>
				<li><a href="gotouserprogram1" accesskey="5" title="">节目列表</a></li>
                <li><a href="logout" accesskey="6" title="">退出登录</a></li>
			</ul>
		</div>
	</div>
	 <div id="main"  style="text-align: center;" >
	
	<h1>抢红包还未开启,敬请期待</h1>
	
	
	
	</div>
	 
	</div>
	</div>
	    <!-- The container for the background-image -->
<img id="bg" style="display:none" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页</title>
<link href="css/user.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/fonts.css" rel="stylesheet" type="text/css" media="all" />
<!-- The JavaScript -->
<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/bgslideshow.js"></script>
<script type="text/javascript" src="js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="js/custom.js"></script>
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
				<li class="current_page_item"><a href="gotouserhome1" accesskey="1" title="">首页</a></li>
				<li><a href="gotouserperinfo1" accesskey="2" title="">个人信息</a></li>
				<li ><a href="http://10.10.15.249:8080/luckymoney/login?username=JoyFly&itcode=1001" accesskey="3" title="">来聊天啊</a></li>
				<li ><a href="gotouserlucky1" accesskey="4" title="">抢红包了</a></li>
				<li><a href="gotouserprogram1" accesskey="5" title="">节目列表</a></li>
                <li><a href="logout" accesskey="6" title="">退出登录</a></li>
			</ul>
		</div>
	</div>
	 <div id="main">
      <div class="punch-line">
                        欢迎参加公司年会活动
                    </div>
     <div class="banner-wrap">
     
                        <div class="flexslider-container">
                            <div class="flexslider">
                            <ul class="slides">
                                <li>
                                    <img src="img/slider_img2.png" />
                                    <p class="flex-caption">Modernize Ajax HTML Template</p>
                                </li>
                                <li>
                                    <img src="img/slider_img3.png" />
                                    <p class="flex-caption">Wescroll Single Page Template</p>
                                </li>
                                <li>
                                    <img src="img/slider_img.png" />
                                    <p class="flex-caption">Frames Horizontal Scrolling Template</p>
                                </li>
                            </ul>
                          </div>
                        </div>
                    </div>
                     <div class="home-services">
                        <div class="grid_9">
                            <h2>精彩节目</h2>
                            <p>Proin id dui vel orci aliquam laoreet vitae sed risus. Fusce diam lectus, malesuada in feugiat sed, iaculis ut augue.</p>
                        </div>
                        <div class="grid_9 alpha">
                            <h2>工作总结</h2>
                            <p>Proin id dui vel orci aliquam laoreet vitae sed risus. Fusce diam lectus, malesuada in feugiat sed, iaculis ut augue.</p>
                        </div>
                        <div class="grid_9 alpha">
                            <h2>巨额红包</h2>
                            <p>Proin id dui vel orci aliquam laoreet vitae sed risus. Fusce diam lectus, malesuada in feugiat sed, iaculis ut augue.</p>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    </div>
	</div>
    </div>
    <!-- The container for the background-image -->
<img id="bg" style="display:none" />
</body>
</html>
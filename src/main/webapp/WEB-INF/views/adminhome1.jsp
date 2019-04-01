<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>管理员首页</title>
<link href="css/search1.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/fonts.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="js/custom.js"></script>
</head>
<body>
<div id="page" class="container">
	<div id="header">
		<div id="logo">
			<img src="img/jj.jpg" alt="" />
			<h1><a href="#">Admin</a></h1>
		</div>
		<div id="menu">
			<ul>
				<li class="current_page_item"><a href="gotoadminhome1" accesskey="1" title="">首页</a></li>
				<li><a href="gotoadminperinfo1" accesskey="2" title="">团队信息</a></li>
				<li><a href="gotoadminprogram1" accesskey="2" title="">节目列表管理</a></li>
				<li ><a href="gotoadminluckyset1" accesskey="3" title="">红包发放管理</a></li>
				<li><a href="gotoadminsearch1" accesskey="4" title="">红包发放记录</a></li>
				<li><a href="gotoadminsearch2" accesskey="5" title="">用户交易记录</a></li>
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
                                    <img src="img/cd1.jpg" />
                                    <p class="flex-caption">Modernize Ajax HTML Template</p>
                                </li>
                                <li>
                                    <img src="img/cd2.jpg" />
                                    <p class="flex-caption">Wescroll Single Page Template</p>
                                </li>
                                <li>
                                    <img src="img/cd3.jpg" />
                                    <p class="flex-caption">Frames Horizontal Scrolling Template</p>
                                </li>
                            </ul>
                          </div>
                        </div>
                    </div>
                     <div class="home-services">
                        <div class="grid_9">
                            <h2>精彩节目</h2>
                            <p>Do you have a freestyle~</p>
                            <p>I wanna play a game with you</p>
                            <P>Lizhuo Liu is very cute</P>
                        </div>
                        <div class="grid_9 alpha">
                            <h2>工作总结</h2>
                            <p>ok! let's begin!</p>
                            <p>I love you~~~~</p>
                            <P>You love me!!!</P>
                            <p>We are Happy Family</p>
                        </div>
                        <div class="grid_9 alpha">
                            <h2>巨额红包</h2>
                            <p>hey DJ,drop the beat.</p>
                            <p>drop the database.</p>
                            <p>drop the table</p>
                            <P>drop me!!!!!!</P>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    </div>
	 
	</div>
</body>
</html>
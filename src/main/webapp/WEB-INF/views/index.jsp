<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="author" content="wyh">

  <meta content="yes" name="apple-mobile-web-app-capable">
  <!-- 网站开启对web app程序的支持，在web app应用下状态条（屏幕顶部条）的颜色，默认值为default（白色），可以定为black（黑色）和black-translucent（灰色半透明）-->
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <title>红包雨</title>
  <link href="css/minireset.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/index.css">
</head>

<body>
<div>
<a href="gotouserhome1">返回首页</a>
</div>
  <div class="pack_box">
    <!--开始动画框-->
    <div class="start_box">
      <span></span>
    </div>
    <!--红包框-->
    <ul class="redpack_box"></ul>
    <!--弹出框-->
    <div class="pop_box">
      <div id="outout" class="pop_con">
      <!--   <img src="img/gx.png" alt="">
        <h3>恭喜您，获得三元红包!${lucky}</h3>-->
      </div>
           <div  class="pop_con">
 <a href="javascript: void(0);"></a> 
      </div>
 
    </div>
  </div>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/snatch.js"></script>
</html>
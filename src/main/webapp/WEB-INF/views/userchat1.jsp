<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>评论弹幕区</title>
<link href="css/user.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/bgslideshow.js"></script>
<style type="text/css">
   
    .boxDom {
        width:100%;
        height: 100%; 
        overflow: hidden;
              background: none; 
    }
    .idDom { 
        width: 100%;
        height: 100%;
  

    }
    .content {
        display: inline-block;
        width:100%;
        height: 100px;
        margin-top:66%;
    }
    .title {
        display: inline;
        font-size: 4em;
        vertical-align:bottom;
        color:#fff;
    }
    .text {
        border:none;
        width:300px;
        height: 50px;
        border-radius: 5px;
        font-size: 2.4em;
        left:auto;
        right:auto;
    }
    .btn {
        width:110px; 
        height: 30px;
        background: #f90000;
        border:none;
        color:#fff;
        font-size: 1.4em;

        left:auto;
        right:auto;

    }
    .string {
        width:300px;
        height: 40px;
        position: absolute;
        overflow: hidden;
        color:#000;
        font-size: 2em;
        line-height: 1.5em;
        cursor: pointer;
        white-space:nowrap;
    }
</style>
<script type="text/javascript">
function doButton() { 
	 if(event.keyCode==13)  {
	  //btSubmit指对应的提交按扭的id名称
	  document.all.btn.click(); 
	 }
	}
</script>
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
				<li ><a href="gotouserhome1" accesskey="1" title="">首页</a></li>
				<li><a href="gotouserperinfo1" accesskey="2" title="">个人信息</a></li>
				<li class="current_page_item"><a href="gotouserchat1" accesskey="3" title="">来聊天啊</a></li>
				<li><a href="gotouserlucky1" accesskey="4" title="">抢红包了</a></li>
				<li><a href="gotouserprogram1" accesskey="5" title="">节目列表</a></li>
                <li><a href="logout" accesskey="6" title="">退出登录</a></li>
			</ul>
		</div>
	</div>
	 <div id="main">
	<div class="boxDom" id="boxDom"><!-- 外样式div -->
    <div class="idDom" id="idDom"><!-- 内样式div -->
    <h1 style="color:#fff">弹幕轰炸中</h1>
        <div class="content"><!--按钮div  -->
            
            <input type="text" class="text" id="text"  onKeyDown="doButton()"/> 
            <button type="button" class="btn" id="btn">sent:</button>
        </div>
    </div>
</div> 
	
	
	</div>
	 
	</div>
	</div>
	    <!-- The container for the background-image -->
<img id="bg" style="display:none" />
</body>
 <script >
   $(function(){
        var pageW=parseInt($(document).width());
        var pageH=parseInt($(document).height());
        var boxDom=$("#boxDom");
        var btnDom=$("#btn");
        var Top,Right;
        var width;
        width=pageW;
        var colorArr=["#cfaf12","#12af01","#981234","#adefsa","#db6be4","#f5264c","#d34a74"];
         btnDom.bind("click",test2).bind("click",test1)/*.bind("click",auto) */; 
       document.onkeydown=function(e){
            if(e.keyCode == 13){ 
                test1(); 
            } 
        } 
 /**
 test2函数向服务器请求存入评论
 **/
      function test2(){
    	   var dm=document.getElementById("text").value;
		   $.get("gooddanmu?dm="+dm,function(data,status){
			 
		});
		   }
 /**
 
 auto函数设置弹幕出现样式
 **/
      function auto(str){
        	
            var creSpan=$("<span class='string'></span>");
            var text=str;
            creSpan.text(text);
            $("#text").val("");
            Top=parseInt(pageH*(Math.random()));
            var num=parseInt(colorArr.length*(Math.random()));
            if(Top>pageH-90){
                Top=pageH-90;
            }
            //字幕的css样式
            creSpan.css({"top":Top,"right":0,"color":getRandomColor()});
            boxDom.append(creSpan);
            var spanDom=$("#boxDom>span:last-child");
            spanDom.stop().animate({"right":pageW+300},10000,"linear",function(){
                        $(this).remove();
                    });
        }
 /**
 
 test1函数向服务器请求返回弹幕
 **/
      function test1(){
    	  
		   $.get("Commons",function(data,status){
			
			   for(i=0;i<data.length;i++)
	   				{ 
				   
				   auto(data[i].message);}
			   /* setTimeout(test1,2000);   */    
			  
		}); 
		 
		   }
     //设置弹幕的颜色   
        function getRandomColor(){
            return '#' + (function(h){
                return new Array(7 - h.length).join("0") + h
            })((Math.random() * 0x1000000 << 0).toString(16));
        }
   }); 
      


</script> 
</html>
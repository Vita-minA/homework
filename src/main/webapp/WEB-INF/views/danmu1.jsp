<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script src="js/jquery-1.7.1.min.js" type="text/javascript" charset="utf-8"></script>
<style type="text/css">
    html,body{margin:0px;padding:0px;width: 100%;height:100%;font-family: "微软雅黑";font-size: 62.5%;background: #ccc;}
    .boxDom {
        width:100%;
        height: 100%; 
        position: relative;
        overflow: hidden;
    }
    .idDom { 
        width: 100%;
        height: 100px;
        background: #666;
        position: fixed; 
        bottom: 0px;
    }
    .content {
        display: inline-block;
        width:430px;
        height: 40px;
        position: absolute;
        left: 0px;
        right: 0px;
        top:0px;
        bottom:0px;
        margin:auto;
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
        height: 30px;
        border-radius: 5px;
        font-size: 2.4em;
    }
    .btn {
        margin: 4px 2px;
        width:410px; 
        height: 30px;
        background: #f90000;
        border:none;
        color:#fff;
        font-size: 2.4em;
    }
    .string {
        width:300px;
        height: 40px;
        position: absolute;
        overflow: hidden;
        color:#000;
        font-size: 4em;
        line-height: 1.5em;
        cursor: pointer;
        white-space:nowrap;
    }
</style>
</head>

<body>
<div class="boxDom" id="boxDom"><!-- 外样式div -->
    <div class="idDom" id="idDom"><!-- 内样式div -->
        <div class="content"><!--按钮div  -->
            <p class="title">message:</p>
            <input type="text" class="text" id="text" /> 
            <button type="button" class="btn" id="btn">sent:</button>
        </div>
    </div>
</div> 
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
 
      function test2(){
    	   var dm=document.getElementById("text").value;
		   $.get("gooddanmu?dm="+dm,function(data,status){
			 
		});
		   }
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
            creSpan.css({"top":Top,"right":-300,"color":getRandomColor()});
            boxDom.append(creSpan);
            var spanDom=$("#boxDom>span:last-child");
            spanDom.stop().animate({"right":pageW+300},10000,"linear",function(){
                        $(this).remove();
                    });
        }
      function test1(){
    	  
		   $.get("Commons",function(data,status){
			
			   for(i=0;i<data.length;i++)
	   				{ 
				   
				   auto(data[i].message);}
			   /* setTimeout(test1,2000);   */    
			  
		}); 
		 
		   }
        
        function getRandomColor(){
            return '#' + (function(h){
                return new Array(7 - h.length).join("0") + h
            })((Math.random() * 0x1000000 << 0).toString(16));
        }
   }); 
      


</script> 
</html>
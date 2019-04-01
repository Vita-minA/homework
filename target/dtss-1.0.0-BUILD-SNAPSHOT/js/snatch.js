/**
 * Created by Administrator on 2017/7/5.
 * 红包雨
 */
$(document).ready(function(){

  // 页面加载完毕之后设置的初始样式
  initPage();

  //开始动画
  startAnimation();

  //添加红包
  setTimeout(add,3000);

});
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


function initPage(){
  $(".start_box").css("height",$(document).height());
  $(".redpack_box").css("height",$(document).height());
}

function startAnimation(){
  var num =4;
  var startA =function(){
    num--;
    if(num > 0 ){
      $(".start_box span").html(num);
    }else{
      $(".start_box").remove();
    }
    setTimeout(startA,1000);
  }
  startA();
}

//循环随机数的方法
function randomNum(startNum,endNum){
  return parseInt(Math.random() * ((endNum+1) - startNum)+startNum);
}

var nums =0;
var wid =parseInt($(".redpack_box").width()) - 60;
function add(){
  var ranImg =randomNum(1,2); //此随机数用来切换图片用。
  var ranWidth =randomNum(20,60); //此随机数用来设置红包的宽度。
  var ranLeft =randomNum(0,wid); //用来设置红包的left值。
  var ranRotate =randomNum(-45,45); //设置红包的rotate值。

  nums++;

  var redpackHTML ="<li class='li"+nums+"'>" +
      "<a href='javascript: void(0);'>" +
      "<img src='img/hb_"+ranImg+".png'></a></li>";

  $(".redpack_box").append(redpackHTML);
  $(".li"+nums).css("left",ranLeft);
  $(".li"+nums+" img").css({
    "width": ranWidth+"px",
    "transform": "rotate("+ranRotate+"deg)",
    "-ms-transform": "rotate("+ranRotate+"deg)", /* Internet Explorer */
    "-moz-transform": "rotate("+ranRotate+"deg)", /* Firefox */
    "-webkit-transform": "rotate("+ranRotate+"deg)",/* Safari 和 Chrome */
    "-o-transform": "rotate("+ranRotate+"deg)" /* Opera */
  });

  $(".li"+nums).animate({"top":$(document).height()+20},5000,function(){
    //删除掉落的红包
    $(this).remove();
  });

  $(".li"+nums).click(function(){
	
			var http_request = initAjax();
	
			http_request.open("get", "snatchred", true);
			http_request.onreadystatechange = function() {
				if (http_request.readyState == 4) {
					if (http_request.status == 200) {
						
						    //window.location.href="gotouserlucky1";
							document.getElementById("outout").innerHTML=http_request.responseText;
							
					}
				}
			}
			http_request.send(null);
		  
    $(".pop_box").css("display","block");
  });

  setTimeout(add,500);
}

$(".pop_con a").click(function(){
  $(".pop_box").css("display", "none")
});


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<link rel="stylesheet" href="css/style1.css">
<script type="text/javascript">
function setCookie ( name, value, expdays )
{
    var expdate = new Date();
    //设置Cookie过期日期
    expdate.setDate(expdate.getDate() + expdays) ;
    //添加Cookie
    document.cookie = name + "=" + escape(value) + ";expires=" + expdate.toUTCString();
}
function getCookie ( name )
{
    //获取name在Cookie中起止位置
    var start = document.cookie.indexOf(name+"=") ;
    if ( start != -1 )
    {
        start = start + name.length + 1 ;
        //获取value的终止位置
        var end = document.cookie.indexOf(";", start) ;
        if ( end == -1 )
            end = document.cookie.length ;
        //截获cookie的value值,并返回
        return unescape(document.cookie.substring(start,end)) ;
    }
    return "" ;
}
function check ()
{
    //获取表单输入:用户名,密码,是否保存密码
    var username = document.getElementById("username").value.trim() ;
    var password = document.getElementById("password").value.trim() ;
    var isRmbPwd = document.getElementById("isRmbPwd").checked ;

    //判断用户名,密码是否为空(全空格也算空)
    if ( username.length != 0 && password.length != 0 )
    {
        //若复选框勾选,则添加Cookie,记录密码
        if ( isRmbPwd == true )
        {    
            setCookie ( "username", username, 7 ) ;
        }
        //否则清除Cookie
        else
        {
        	setCookie ( "username", "", -1 ) ;
        }
        return true ;
    }
    //非法输入提示
    else
    {
        alert('请输入必填字段!!!')
        return false ;
    }    
}
//将function函数赋值给onload对象
window.onload = function ()
{
    //从Cookie获取到用户名
    var username = getCookie("username") ;
    if ( username == "" )
    {
        document.getElementById("username").value="" ;
        document.getElementById("password").value="" ;
        document.getElementById("isRmbPwd").checked=false ;
    }
    else
    {
        document.getElementById("username").value = username ;
        document.getElementById("password").value = "" ;
        document.getElementById("isRmbPwd").checked = true ;
    }
}
</script>
</head>

<body>
	<!-- 
	<div class="menu">
		<ul class="mainmenu clearfix">
			<li class="menuitem">Well</li>
			<li class="menuitem">how</li>
			<li class="menuitem">about</li>
			<li class="menuitem">that?</li>
		</ul>
	</div>
	 -->
	<div class="form">
		<div class="forceColor"></div>
		<div class="topbar">
			<div class="spanColor"></div>
			<input name="username" class="input" id="username"
				placeholder="Username" />
		</div>
		<div class="topbar">
			<div class="spanColor"></div>
			<input name="itcode" class="input" id="itcode" placeholder="Itcode" />
		</div>
		<div class="topbar">
			<div class="spanColor"></div>
			<input type="password" class="input" id="password" name="password"
				placeholder="Password" />
		</div>
		<div class="checkbox">
			<input type="checkbox" id="isRmbPwd" name="isRmbPwd"
				checked="checked">forget me
		</div>
		<button class="submit" id="submit" onclick="check()">Login</button>
	</div>
	<script src="js/jquery-3.2.1.min.js" charset="utf-8"></script>

	<script src="js/index.js"></script>

</body>
</html>

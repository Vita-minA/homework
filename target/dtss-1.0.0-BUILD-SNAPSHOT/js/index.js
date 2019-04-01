
var form = $('.form');
var btn = $('#submit');
var topbar = $('.topbar');
var input = $('#password');
var input2 = $('#username');
var input3 = $('#itcode');
var tries = 0;
var flag = 0;
var h = input.height();
//控制用户选择注销时进入哪个界面
var Isadmin=false;
$(document).ready(
		function() {
			$('.spanColor').height(h + 23);
			$('#findpass').on('click', function() {
				$(this).text('memeda');
			});
			input.on('focus', function() {
				topbar.removeClass('error success');
				input.text('');
			});
			btn.on('click', function() {
				var item = input.val();
				var item2 = input2.val();
				var item3 = input3.val();
				$.get("login?password=" + encodeURI(item)
						+ "&username=" + encodeURI(item2) + "&itcode="
						+ encodeURI(item3), function(data, status) {
					flag = data;
					if (tries <= 2) {
						var pass = $('#password').val();
						console.log(pass);
						//登陆成功,进入管理员界面
						if (flag == 2) {
							setTimeout(function() {
								btn.text('Success!');
							}, 250);
							topbar.addClass('success');
							form.addClass('goAway');
							tries = 0;
							Isadmin=true;
							//在这里填写管理员界面的URL
							window.location.href = "gotoadminhome1"
						}
						//登陆成功，进入普通用户界面
						else if(flag==3){
							setTimeout(function() {
								btn.text('Success!');
							}, 250);
							topbar.addClass('success');
							form.addClass('goAway');
							tries = 0;
							Isadmin=false;
							//在这里填写普通用户界面的URL
							window.location.href = "gotouserhome1"
						} 
						else if(flag==4){
							alert("你已登录一个账号，请注销后重试");
							//在这里
							if(Isadmin){
								//在这里填写管理员界面的URL
								window.location.href = "gotoadminhome1"
							}
							else{
								//在这里填写普通用户界面的URL
								window.location.href = "gotouserhome1"
							}
						}
						else if(flag==5){
							alert("该账号已在别的地方登陆！如果是被盗的话，告诉我，我揍他");
						}
						//密码不对
						else {
							topbar.addClass('error');
							tries++;
							switch (tries) {
							case 0:
								btn.text('Login');
								break;
							case 1:
								setTimeout(function() {
									btn.text('You have 2 tries left');
								}, 300);
								break;
							case 2:
								setTimeout(function() {
									btn.text('Only 1 more');
								}, 300);
								break;
							case 3:
								setTimeout(function() {
									btn.text('Recover password?');
								}, 300);
								input.prop('disabled', true);
								topbar.removeClass('error');
								input.addClass('disabled');
								btn.addClass('recover');
								break;
							defaut: btn.text('Login');
							break;
						}
					}
				} else {
					topbar.addClass('disabled');
				}
				});


		}	);

			$('.form').keypress(function(e) {
				if (e.keyCode == 13)
					submit.click();
			});
		});
input.keypress(function() {
	topbar.removeClass('success error');
});
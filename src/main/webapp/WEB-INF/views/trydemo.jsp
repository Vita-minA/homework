<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>分页Demo</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-1.4.2.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/extendPagination.js"></script>
<script type="text/javascript">
	function callBackPagination() {
		var totalCount = Number($('#totalCount1').val()) || 252, showCount = $(
				'#showCount1').val(),
		limit = Number($('#limit').val()) || 10;
		createTable(1, limit, totalCount);
		$('#callBackPager').extendPagination({
			totalCount : 100,
			showCount : 10,
			limit : 4,
			callback : function(curr, limit, totalCount) {
				createTable(curr, limit, totalCount);
			}
		});
	}
	function createTable(currPage, limit, total) {
		var html = [], showNum = limit;
		if (total - (currPage * limit) < 0)
			showNum = total - ((currPage - 1) * limit);
		html.push(' <table class="table table-hover piece" style="margin-left: 0;">');
		html.push(' <caption>悬停表格(' + total + ')</caption>');
		html.push(' <thead><tr><th>名称</th><th>城市</th><th>密码</th></tr></thead><tbody>');
		for (var i = 1; i <= showNum; i++) {
			html.push('<tr><td>name' + currPage + '页_' + i + '</td>');
			html.push('<td>city' + currPage + '页_' + i + '</td>');
			html.push('<td>pwd' + currPage + '页_' + i + '</td>');
			html.push('</tr>');
		}
		html.push('</tbody></table>');
		var mainObj = $('#mainContent');
		mainObj.empty();
		mainObj.html(html.join(''));
		
	}
	
</script>
</head>
<body>
	<div id="callBackPagination" class="piece">
		<h2>
			<small>扩展分页带回调函数(表格带分页)</small>
		</h2>
		<div id="mainContent"></div>
		<div id="callBackPager"></div>
	</div>
	<script type="text/javascript">
	callBackPagination();
</script>
</body>


</html>
$(document).ready(function() {
	var lid=0;
	var maxpage=Number(document.getElementById('ret').value);
	maxpage++;
	$("li").click(function() {
		var cursor1=lid;
		cursor1="#"+cursor1;
		$(cursor1).removeClass("active");
		id = $(this).attr("id");
		if(id!="last"&&id!="next"&&id!="head"&&id!="tail"){
			lid=id;
		}
		$.get("gettradebypage?id=" + id, function(data) {
			$("#div1").html(data);
		});
		var cursor=lid;
		cursor="#"+cursor;
		$(cursor).addClass("active");
	});
	$("#last").click(function(){
		if(lid>1)
		{
			var cursor=lid;
			cursor="#"+cursor;
			$(cursor).removeClass("active");
			lid=lid-1;
			}
		$.get("gettradebypage?id="+lid,function(data){
			$("#div1").html(data);
		});
		var cursor1=lid;
		cursor1="#"+cursor1;
		$(cursor1).addClass("active");
	});
	$("#next").click(function(){
		if(lid==0){
			lid=lid+1;
		}
		if(lid<maxpage){
			var cursor=lid;
			cursor="#"+cursor;
			$(cursor).removeClass("active");
			lid++;
		}
		$.get("gettradebypage?id="+lid,function(data){
			$("#div1").html(data);
		});
		var cursor1=lid;
		cursor1="#"+cursor1;
		$(cursor1).addClass("active");
	});
	$("#head").click(function(){
		var cursor=lid;
		cursor="#"+cursor;
		$(cursor).removeClass("active");
		lid=1;
		$.get("gettradebypage?id="+lid,function(data){
			$("#div1").html(data);
		});
		var cursor1=lid;
		cursor1="#"+cursor1;
		$(cursor1).addClass("active");
	})
	$("#tail").click(function(){
		var cursor=lid;
		cursor="#"+cursor;
		$(cursor).removeClass("active");
		lid=maxpage;
		$.get("gettradebypage?id="+lid,function(data){
			$("#div1").html(data);
		});
		var cursor1=lid;
		cursor1="#"+cursor1;
		$(cursor1).addClass("active");
	})
});

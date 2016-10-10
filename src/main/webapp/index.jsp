<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主界面</title>
<script type="text/javascript" src="<%=path%>/public/script/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/public/script/jqueryPage.js"></script>
</head>
<style>
.pager{height:30px;line-height:30px;font-size: 12px;margin: 25px 0px;text-align: center;color: #2E6AB1;overflow: hidden;}
.pager a{border:1px solid #9AAFE5;color:#2E6AB1;margin:0px 5px;padding:5px 8px;text-decoration: none;}
.pager a.hover,.pager a.active{background-color:#2E6AB1;border-color:#000080;color:#FFF;}
.pager a.disabled{color:#C8CDD2;cursor:auto;}
</style>
<script type="text/javascript">
function doPost(){
	$.ajax({
		type:'post',
		url:'<%=path%>/userInfo/users.do',
		success:function(data){
			/*  for (var i in data.result) {
	            alert(i + ":" + data.result[i]);
	        }  */
		alert(data.result.html);
			/* for(var i = 0;i<data.length;i++){
				alert("ID:"+data[i]['openid']);
			} */
		},
		error: function(e) { 
			for (var i in e) {
	            alert(i + ":" + e[i]);
	        }
			} 
	});
}
$(function(){
	$("#jqueryPage").pagination({
		count: 1000, //总数
		size: 10, //每页数量
		index: 1,//当前页
		lrCount: 3,//当前页左右最多显示的数量
		lCount: 1,//最开始预留的数量
		rCount: 1,//最后预留的数量
		callback: function (options) {
			alert(options.index);           
			//options.count = 300;
			//return options;
		},
		beforeRender: function (jA) {
			//jA.attr("href","default.php?index="+jA.text());
		}
	});
});
</script>
<body>
<input type="button" onclick="javascript:doPost()" value="ajax获取数据"/>
 
 
 
<div class="jqueryPage" id="jqueryPage">
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no">
<title>点菜成功</title>
<link rel="stylesheet" href="<%=path%>/public/style/weui.css"/>
<script type="text/javascript" src="<%=path%>/public/script/jquery-3.0.0.js"></script>
<style type="text/css">
body{
	position:relative;
}
#footer {
	position:absolute; z-index:2;
	bottom:0; left:0;
	width:100%;
	height:40px;
	padding:0;
}
</style>
</head>
<script type="text/javascript">
$(function(){
	$("#content").height($(window).height());
})
</script>
<body style="background-color:#E9E9E9;">
<div id="content" >
	<div id="head" style="text-align:center;line-height:150px;">
		<img alt="" src="<%=path%>/public/images/success.png" width="138" height="138">
	</div>
	<div style="text-align:center;line-height:50px;height:100px;">
		<font color="#85B64B">下单成功</font>
	</div>
	<div style="text-align:center;height:200px;">
		<font>您的订单需要等待15分钟!<br>请耐心等候!</font>
	</div>
	<div id="footer" >
		 <div class="button-sp-area">
            <a href="javascript:;" style="width:150px;" class="weui-btn weui-btn_plain-default weui-btn_plain-disabled">查看订单详情</a>
        </div>
	</div>
</div>
</body>
</html>
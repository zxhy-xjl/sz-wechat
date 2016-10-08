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
<title>扫桌反馈</title>
<style type="text/css">
#score{
background-image:url("<%=path%>/public/images/feedbackscore.png");
background-repeat:no-repeat;
width:250px;
height:250px;
}
span{
text-align:center; 

}
</style>
</head>
<script type="text/javascript">
	function jumpTo(){
		window.location.href="<%=path%>/jsp/takingorders.jsp";
	}
</script>
<body>
<table border="0" align="center" width="100%">
	<tr>
		<td align="center" height="50px;"><a href="http://www.haoschoool.com/sz_wechattest/restaurantinfo.jsp" style="text-decoration:none;">海底捞(大行宫店)</a></td>
	</tr>
	<tr>
		<td align="center" height="80px;">
			<div id="score">
				<div style="height:90px;"></div>
				<span><font color="red" size="14">99分</font></span>
			</div>
		</td>
	</tr>
	<tr>
		<td align="center">
			<a href="#" style="color:#CCCCCC">点击查看详情</a>
		</td>
	</tr>
	<tr>
		<td align="center" height="80px;">
			<font color="#333333">您选择的店铺评分高于南京市<font color="red">80%</font>商家</font>
		</td>
	</tr>
	<tr>
		<td align="center" height="80px;">
			<input id="" type="button" value="继续点餐" onclick="javascript:jumpTo()">		
		</td>
	</tr>
</table>
</body>
</html>
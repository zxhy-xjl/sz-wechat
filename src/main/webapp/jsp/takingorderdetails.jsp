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
<title>订单详情页</title>
</head>
<script type="text/javascript">
	function jumpTo(){
		window.location.href="<%=path%>/jsp/takingordersa.jsp";
	}
</script>
<body>
<table border="0" align="center" width="100%">
	<tr>
		<td ><h2>凉菜</h2> </td>
		<td align="right"><a href="http://www.haoschoool.com/sz_wechattest/restaurantinfo.jsp" style="text-decoration:none;color:#C7A999">>>进入餐厅</a></td>
	</tr>
	<tr>
		<td width="100px" ><img alt="" src="<%=path%>/public/images/food.png" width="100px" height="100px"></td>
		<td>
			<div style="">红烧牛肉  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;55</div><br/>
			
			<div style="height:30px;">特色：唯美多汁，强烈推荐</div>
		</td>
	</tr>
	<tr><td></td></tr>
	<tr>
		<td width="100px" ><img alt="" src="<%=path%>/public/images/food.png" width="100px" height="100px"></td>
		<td>
			<div style="">红烧牛肉  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;55</div><br/>
			
			<div style="height:30px;">特色：唯美多汁，强烈推荐</div>
		</td>
	</tr>
	<tr>
		<td colspan="2"><h2>热菜</h2></td>
	</tr>
	<tr>
		<td width="100px" ><img alt="" src="<%=path%>/public/images/food.png" width="100px" height="100px"></td>
		<td>
			<div style="">红烧牛肉  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;55</div><br/>
			
			<div style="height:30px;">特色：唯美多汁，强烈推荐</div>
		</td>
	</tr>
	<tr><td></td></tr>
		<tr>
		<td width="100px" ><img alt="" src="<%=path%>/public/images/food.png" width="100px" height="100px"></td>
		<td>
			<div style="">红烧牛肉  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;55</div><br/>
			
			<div style="height:30px;">特色：唯美多汁，强烈推荐</div>
		</td>
	</tr>
</table>
<table border="0" align="center" width="100%" height="40px" style="border-collapse:collapse;">
	<tr>
		<td width="80%" style="background-color:#999999;border:#000000 1px solid;" align="center"><font color="red">合计112元</font><br/><font>共11道菜</font></td>
		<td align="center" style="border:#000000 1px solid;"><a href="javascript:jumpTo();" style="text-decoration:none;">结账</a></td>
	</tr>
</table>
</body>
</html>
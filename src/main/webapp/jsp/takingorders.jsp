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
<title>开始点菜</title>
</head>
<script type="text/javascript">
	function jumpTo(){
		window.location.href="<%=path%>/jsp/takingordersucceed.jsp";
	}
</script>
<body>
<table border="0" align="center" width="100%">
	<tr>
		<td colspan="3"><h2>凉菜</h2></td>
	</tr>
	<tr>
		<td width="100px" ><img alt="" src="<%=path%>/public/images/food.png" width="100px" height="100px"></td>
		<td>
			<div style="">红烧牛肉  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;55</div><br/>
			
			<div style="height:30px;">特色：唯美多汁，强烈推荐</div>
		</td>
		<td><img alt="" src="<%=path%>/public/images/order.png"></td>
	</tr>
	<tr><td colspan="3"></td></tr>
	<tr>
		<td width="100px" ><img alt="" src="<%=path%>/public/images/food.png" width="100px" height="100px"></td>
		<td>
			<div style="">红烧牛肉  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;55</div><br/>
			
			<div style="height:30px;">特色：唯美多汁，强烈推荐</div>
		</td>
		<td><img alt="" src="<%=path%>/public/images/order.png"></td>
	</tr>
	<tr>
		<td colspan="3"><h2>热菜</h2></td>
	</tr>
	<tr>
		<td width="100px" ><img alt="" src="<%=path%>/public/images/food.png" width="100px" height="100px"></td>
		<td>
			<div style="">红烧牛肉  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;55</div><br/>
			
			<div style="height:30px;">特色：唯美多汁，强烈推荐</div>
		</td>
		<td><img alt="" src="<%=path%>/public/images/order.png" width="88"></td>
	</tr>
	<tr><td colspan="3"></td></tr>
	<tr>
		<td width="100px" ><img alt="" src="<%=path%>/public/images/food.png" width="100px" height="100px"></td>
		<td>
			<div style="">红烧牛肉  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;55</div><br/>
			
			<div style="height:30px;">特色：唯美多汁，强烈推荐</div>
		</td>
		<td><img alt="" src="<%=path%>/public/images/order.png"></td>
	</tr>
</table>
<div style="height:40px;"></div>
<table border="0" align="center" width="100%" height="40px" style="border-collapse:collapse;">
	<tr>
		
		<td width="80%" style="background-color:#999999;border:#D6D6D6 1px solid;" align="center">
			<img alt="" src="<%=path%>/public/images/num.png" width="60" height="60" style="margin-left:-80px;margin-top:-30px;">
			<font color="red">合计112元</font>
		</td>
		<td align="center" style="border:#D6D6D6 1px solid;"><a href="javascript:jumpTo();" style="text-decoration:none;">下单</a></td>
	</tr>
</table>
</body>
</html>
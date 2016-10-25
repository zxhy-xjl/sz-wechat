<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<style type="text/css">

html{
font-family: SimHei;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>首页</title>

</head>
<body>
<div id="baseinfo" style="margin-top:50px">
	<table border="0" width="100%" style="border-collapse: collapse;">
		<tr style="">
			<td>
				<label >&nbsp;&nbsp;<font style="font-weight: bold;" size="4">关注“扫桌”公众账号</font></label><br /><br />
				<label >&nbsp;&nbsp;<font style="font-weight: bold;" size="4">1、搜索“扫桌”公众账号</font></label><br /><br />
				<label >&nbsp;&nbsp;<font style="font-weight: bold;" size="4">2、扫描“扫桌”公众账号二维码</font></label><br /><br />
			</td>
		</tr>
		<tr style="">
			<td>
				<img src="<%=path%>/public/images/.jpg"><br>
			</td>
		</tr>
		<tr style="">
			<td>
				<input type="button" value="政府入口" id="1" style="margin-left:50px;text-align: center;font-weight: bold;font-size:18px" ><br /><br />
				<input type="button" value="餐厅入口" id="2" style="margin-left:50px;text-align: center;font-weight: bold;font-size:18px"><br /><br />
				<input type="button" value="餐具入口" id="3" style="margin-left:50px;text-align: center;font-weight: bold;font-size:18px"><br /><br />
			</td>
		</tr>
	</table>
</div>
</body>
</html>
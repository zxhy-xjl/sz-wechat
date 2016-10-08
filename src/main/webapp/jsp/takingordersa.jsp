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
<title>结账</title>
<style type="text/css">
.dotline {
BORDER-BOTTOM-STYLE: dotted; BORDER-LEFT-STYLE: dotted; BORDER-RIGHT-STYLE: dotted; BORDER-TOP-STYLE: dotted
}
</style>
</head>
<body>
<table border="0" align="center" width="100%" height="100%" >
	<tr>
		<td colspan="2"><h2>订单概要:</h2></td>
	</tr>
	<tr>
		<td>红烧牛肉 &nbsp;&nbsp;&nbsp; *1</td>
		<td align="right">55</td>
	</tr>
	<tr>
		<td>红烧牛肉 &nbsp;&nbsp;&nbsp; *1</td>
		<td align="right">55</td>
	</tr>
	<tr>
		<td>红烧牛肉 &nbsp;&nbsp;&nbsp; *1</td>
		<td align="right">55</td>
	</tr>
	<tr>
		<td colspan="2">
			<hr class="dotline" color=#111111 size=1>
		</td>
	</tr>
	<tr>
		<td>合计:</td>
		<td align="right"><font color="red">118元</font></td>
	</tr>
	<tr>
		<td colspan="2">
			<hr size=1 color="#E1E1E1">
		</td>
	</tr>
	<tr>
		<td colspan="2"><h2>付款方式:</h2></td>
	</tr>
	<tr>
		<td><input type="radio" name="money" />现金付款</td>
	</tr>
	<tr>
		<td><input type="radio" name="money" />微信付款</td>
	</tr>
	<tr>
		<td><input type="radio" name="money" />支付宝付款</td>
	</tr>
	<tr>
		<td colspan="2">
			<hr size=1 color="#E1E1E1">
		</td>
	</tr>
	<tr>
		<td>
			<input type="checkbox" name="piaoju">我要开票
		</td>
	</tr>
	<tr>
		<td>
			开票单位： <input type="text">
		</td>
	</tr>
	<tr>
		<td align="center">
			 <input type="button" value="确认" width="450px">
		</td>
	</tr>
</table>
</body>
</html>
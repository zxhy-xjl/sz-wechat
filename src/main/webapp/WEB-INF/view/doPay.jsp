<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no">
<title>微支付</title>
</head>
<body style="background-color:#E9E9E9;">
<div id="content">
 <div>
 	<table border="0" width="100%">
 		<tr>
 			<td width="5%">
 				<img alt="" src="<%=path%>/public/images/success.png" width="70" height="70">
 			</td>
 			<td align="left">
 					<font color="#8BCD44">支付成功</font>
 			</td>
 		</tr>
 	</table>
 </div>
 <div style="background-color:white;">
 	<table border="0" width="100%" align="center">
 		<tr>
 			<td align="center" colspan="2" style="">
 				<font size="5">付费通</font>
 			</td>
 		</tr>
 		<tr>
 			<td align="center"  colspan="2"><font size="5">￥${allprice}</font></td>
 		</tr>
 		<tr>
 			<td  colspan="2">
 				<hr>
 			</td>
 		</tr>
 		<tr>
 			<td style="color:#8B8B8B" width="30%">商品：</td>
 			<td style="color:#8B8B8B">扫桌微支付</td>
 		</tr>
 		<tr>
 			<td style="color:#8B8B8B">交易单号：</td>
 			<td style="color:#8B8B8B">${oddNumber }</td>
 		</tr>
 		<tr>
 			<td style="color:#8B8B8B">交易时间：</td>
 			<td style="color:#8B8B8B">${paytimes}</td>
 		</tr>
 		<tr>
 			<td style="color:#8B8B8B">支付状态：</td>
 			<td style="color:#8B8B8B">支付成功</td>
 		</tr>
 		<tr>
 			<td style="color:#8B8B8B">支付方式：</td>
 			<td style="color:#8B8B8B">银行</td>
 		</tr>
 	</table>
 </div>
</div>
</body>
</html>
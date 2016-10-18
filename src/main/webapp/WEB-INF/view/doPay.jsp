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
<body style="backgroundp-color:#E9E9E9;">
<div id="content">
	<div>
		扫桌 -订单号:${oddNumber}
	</div>
	<div>
		￥${allprice}
	</div>
	<div>
		<hr>
	</div>
	<div>
		<span>收款方：扫桌</span>
		<span>商品：扫桌-订单编号${oddNumber}</span>
	</div>
	<div>
		<hr>
	</div>
	<div>
		
	</div>
</div>
</body>
</html>
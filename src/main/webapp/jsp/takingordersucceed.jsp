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
</head>
<script type="text/javascript">
	function jumpTo(){
		window.location.href="<%=path%>/jsp/takingorderdetails.jsp";
	}
</script>
<body>
<table border="0" align="center" width="100%" height="100%" style="background-color:#F7F7F7">
	<tr height="80px;">
		<td align="center">
			您的订单已经通知厨房<br>
			大概需要等待<font color="red">15</font>分钟！
			请耐心等候!
		</td>
	</tr>
	<tr>
		<td align="center">
			<input id="" type="button" value="查看订单详情" onclick="javascript:jumpTo()">
		</td>
	</tr>
</table>
</body>
</html>
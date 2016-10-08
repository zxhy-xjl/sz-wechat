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
<title>餐具信息</title>
</head>
<script type="text/javascript">
	function jumpTo(){
		window.location.href="http://www.haoschoool.com/sz_wechattest/complaintinfo.jsp?flag=1";
	}
	function jumpTo1(){
		window.location.href="<%=path%>/jsp/takingorders.jsp";
	}
	
</script>
<body>
<table border="0" width="100%" align="center">
	<tr >
		<td width="50%" align="right">企业名称:</td>
		<td>大白兔餐具清洗公司</td>
	</tr>
	<tr>
		<td align="right">联系方式:</td>
		<td>025-99285852</td>
	</tr>
	<tr>
		<td align="right">公司地址：</td>
		<td>江苏省南京市江宁区方正中路999</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<img alt="" src="<%=path%>/public/images/xing.png">
		</td>
	</tr>
</table>
<table border="0" width="100%" align="center">
	<tr>
		<td align="right" width="60%">
			<textarea rows="8" cols="30"></textarea>
		</td>
		<td>
			<img alt="" width="37" height="66" src="<%=path%>/public/images/file.png"><br>
			<input type="button" value="投诉" onclick="javascript:jumpTo()">
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input type="button" value="继续点餐" onclick="javascript:jumpTo1()">
		</td>
	</tr>
</table>
</body>
</html>
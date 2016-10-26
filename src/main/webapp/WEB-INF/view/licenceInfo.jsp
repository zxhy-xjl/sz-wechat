<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>许可证详细信息</title>
</head>
<body style="background-color:#E9E9E9">
<div id="content" > 
<table border="0" width="100%" align="center">
	<tr>
		<td align="right" width="30%">许可证编号：</td>
		<td>${RepastPublicInfo.repastlicence}</td>
	</tr>
	<tr>
		<td align="right">名称：</td>
		<td>${RepastPublicInfo.repastname}</td>
	</tr>
	<tr>
		<td align="right">经营场所：</td>
		<td>${RepastPublicInfo.address}</td>
	</tr>
	<tr>
		<td align="right">负责人：</td>
		<td>${RepastPublicInfo.principal}</td>
	</tr>
	<tr>
		<td align="right">主体类型：</td>
		<td>${RepastPublicInfo.subjecttype}</td>
	</tr>
	<tr>
		<td align="right">许可范围：</td>
		<td>${RepastPublicInfo.permissionscope}</td>
	</tr>
	<tr>
		<td align="right">有效期限：</td>
		<td>${RepastPublicInfo.validity}</td>
	</tr>
	<tr>
		<td align="right">发证机关：</td>
		<td>${RepastPublicInfo.certificateoffice}</td>
	</tr>
	<tr>
		<td align="right">发证日期：</td>
		<td>${RepastPublicInfo.certificatetime}</td>
	</tr>
</table>
</div>
</body>
</html>
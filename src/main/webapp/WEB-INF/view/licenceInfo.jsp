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
<style type="text/css">
.td{border:1px solid #EBEBEB;border-width:0px 1px 1px 0px}
</style>
</head>
<body>
<div id="content" > 
<table width="100%" align="center"  style="border-collapse:collapse;border:1px solid #EBEBEB" >
	<tr>
		<td class="td" align="left" width="30%" style="background-color:#E7E7E7">许可证编号</td>
		<td class="td" style="background-color:#FFFFFF">${RepastPublicInfo.repastlicence}</td>
	</tr>
	<tr>
		<td class="td" align="left" style="background-color:#E7E7E7"> 企业名称</td>
		<td class="td" style="background-color:#FFFFFF">${RepastPublicInfo.repastname}</td>
	</tr>
	<tr>
		<td class="td" align="left" style="background-color:#E7E7E7">经营场所</td>
		<td class="td" style="background-color:#FFFFFF">${RepastPublicInfo.address}</td>
	</tr>
	<tr>
		<td class="td" align="left" style="background-color:#E7E7E7">负责人</td>
		<td class="td" style="background-color:#FFFFFF">${RepastPublicInfo.principal}</td>
	</tr>
	<tr>
		<td class="td"align="left" style="background-color:#E7E7E7">主体类型</td>
		<td class="td"style="background-color:#FFFFFF">${RepastPublicInfo.subjecttype}</td>
	</tr>
	<tr>
		<td class="td"align="left" style="background-color:#E7E7E7">许可范围</td>
		<td class="td"style="background-color:#FFFFFF">${RepastPublicInfo.permissionscope}</td>
	</tr>
	<tr>
		<td class="td"align="left" style="background-color:#E7E7E7">有效期限</td>
		<td class="td"style="background-color:#FFFFFF">${RepastPublicInfo.validity}</td>
	</tr>
	<tr>
		<td class="td"align="left" style="background-color:#E7E7E7">发证机关</td>
		<td class="td" style="background-color:#FFFFFF">${RepastPublicInfo.certificateoffice}</td>
	</tr>
	<tr>
		<td class="td"align="left" style="background-color:#E7E7E7">发证日期</td>
		<td class="td" style="background-color:#FFFFFF">${RepastPublicInfo.certificatetime}</td>
	</tr>
</table>
</div>
</body>
</html>
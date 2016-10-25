<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no">
<title>行政处罚信息</title>
</head>
<script type="text/javascript" src="<%=path%>/public/script/jquery-3.0.0.js"></script>
<script type="text/javascript">
var unlawfulfact = "${SupervisePunish.unlawfulfact}";
$(function(){
	if(unlawfulfact.length>20){
		unlawfulfact = unlawfulfact.substring(0,50);
		$("#unlawfulfact").text(unlawfulfact+"...............");
	}
});
</script>
<body style="background-color:#E9E9E9">
<div id="content" >
<table border="0" width="100%" align="center">
	<tr>
		<td align="right">行政处罚决定书文号：</td>
		<td>${SupervisePunish.adminpenaltybook}</td>
	</tr>
	<tr>
		<td align="right">案件名称：</td>
		<td>${SupervisePunish.casename}</td>
	</tr>
	<tr>
		<td align="right">违法行为类型：</td>
		<td>${SupervisePunish.unlawfulact}</td>
	</tr>
	<tr>
		<td align="right">违法企业名称或违法自然人姓名：</td>
		<td>${SupervisePunish.nlawfulcompanyname}</td>
	</tr>
	<tr>
		<td align="right">法定代表人姓名：</td>
		<td>${SupervisePunish.legalperson}</td>
	</tr>
	<tr>
		<td align="right">主要违法事实：</td>
		<td id="unlawfulfact"></td>
	</tr>
	<tr>
		<td align="right">处罚种类和方式：</td>
		<td>${SupervisePunish.penaltytype}</td>
	</tr>
	<tr>
		<td align="right">处罚履行方式和期限：</td>
		<td>${SupervisePunish.adminpenaltytypeandtime}</td>
	</tr>
	<tr>
		<td align="right">作出处罚的机关名称：</td>
		<td>${SupervisePunish.penaltyoffice}</td>
	</tr>
	<tr>
		<td align="right">作出处罚的决定日期：</td>
		<td>${SupervisePunish.punishmentdate}</td>
	</tr>
</table>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
div{
white-space: nowrap;
}
table.hovertable {
	font-family: SimHei;
	font-size:15px;
	color:#333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}
table.hovertable th {
	background-color:#c3dde0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
	font-size: 20px;
	font-weight: bolder;
}
table.hovertable tr {
	background-color:#d4e3e5;
}
table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>扫桌政府端控制台</title>
<script type="text/javascript">

</script>
</head>
<body style="background-color: #e9e9e9;">
<table class="hovertable">
<tr>
	<th colspan="5" ><font style="font-weight: bolder">投诉处理</font></th>
</tr>
<tr>
	<th>日期</th><th>餐厅</th><th>投诉内容</th><th>状态</th><th>操作</th>
</tr>
<c:forEach items="${complainlist}" var="item" varStatus="status">
<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
	<%-- <td>${fn:substring(item.complaintime,0,4)}年${fn:substring(item.complaintime,4,6)}月${fn:substring(item.complaintime,6,8)}日 ${fn:substring(item.complaintime,8,10)}:${fn:substring(item.complaintime,10,12)}</td><td>${item.companyid }</td><td>${item.complaincontent }</td>
	<td>${item.disposestatus }</td><td>Item 1C</td> --%>
	<td>${fn:substring(item.complaintime,0,4)}年${fn:substring(item.complaintime,4,6)}月${fn:substring(item.complaintime,6,8)}日 ${fn:substring(item.complaintime,8,10)}:${fn:substring(item.complaintime,10,12)}</td>
	<td>${item.companyinfo.companyname}</td>
	<td>${item.complaincontent}</td>
	<td>
	<c:if test="${item.disposestatus == 1}">
	<label>等待受理</label>
	</c:if>
	<c:if test="${item.disposestatus == 2}">
	<label>等待处理</label>
	</c:if>
	<c:if test="${item.disposestatus == 3}">
	<label>处理中</label>
	</c:if>
	<c:if test="${item.disposestatus == 4}">
	<label>已反馈</label>
	</c:if>
	</td>
	<td><c:if test="${item.disposestatus == 1}">
	<input type="button" value="受理" onclick="">
	</c:if>
	<c:if test="${item.disposestatus == 2}">
	<input type="button" value="处理" onclick="">
	</c:if>
	<c:if test="${item.disposestatus == 3}">
	<input type="button" value="反馈" onclick="">
	</c:if>
	<c:if test="${item.disposestatus == 4}">
	
	</c:if>
	
	</td>
	
</tr>
</c:forEach>


</table>

</body>
</html>
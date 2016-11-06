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
html{
font-family: SimHei;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>用户详情</title>
<script type="text/javascript">

</script>
</head>
<body style="background-color: #e9e9e9;">
<table class="hovertable">
<tr>
	<th colspan="8" ><font style="font-weight: bolder">关注用户列表</font></th>
</tr>
<tr>
	<th>openid</th><th>昵称</th><th>性别</th><th>访问时间</th><th>访问页面地址</th><th>访问页面名称</th><th>访问餐厅名称</th><th>其他参数名称</th>
</tr>
<c:forEach items="${loginfolist}" var="item" varStatus="status">
<tr onclick="" onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
    <td>
    ${item.openid }
    </td>
	    <td>
    ${item.nickname }
    </td>
     <td>
	<c:if test="${item.sex == 1}">
    男
    </c:if>
    <c:if test="${item.sex == 2}">
    女
    </c:if>
    <c:if test="${item.sex == 0}">
    未知
    </c:if>
    </td>

   <td>
    ${item.inserttime }
    </td> 
      <td>
    ${item.visitpage }
    </td> 
      <td>
    ${item.chntitle }
    </td> 
      <td>
    ${item.companyname }
    </td> 
      <td>
    ${item.otherparam }
    </td> 
</tr>
</c:forEach>


</table>
</body>
</html>
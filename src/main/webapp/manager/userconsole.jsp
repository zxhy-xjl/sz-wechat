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
<title>用户列表</title>
<script type="text/javascript">
function getuserdetails(openid)
{
	window.location.href="<%=path%>/userdetail.do?openid="+openid;
	
	}
</script>
</head>
<body style="background-color: #e9e9e9;">
<table class="hovertable">
<tr>
	<th colspan="7" ><font style="font-weight: bolder">关注用户列表</font></th>
</tr>
<tr>
	<th>openid</th><th>昵称</th><th>性别</th><th>城市</th><th>省份</th><th>关注时间</th><th>头像</th>
</tr>
<c:forEach items="${userlist}" var="item" varStatus="status">
<tr onclick="getuserdetails('${item.openid }')" onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
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
    ${item.city }
    </td> 
   <td>
    ${item.province }
    </td> 
      <td>
    ${item.subscribe_time }
    </td> 
      <td>
   <img src="${item.headimgurl }" width="60px"> 
    </td> 
</tr>
</c:forEach>


</table>
</body>
</html>
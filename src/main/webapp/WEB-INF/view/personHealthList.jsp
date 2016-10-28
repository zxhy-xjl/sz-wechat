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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>健康证列表信息</title>
</head>
<script type="text/javascript" src="<%=path%>/public/script/jquery-3.0.0.js"></script>

<body style="background-color:#E9E9E9">
<div id="content" >
	<table border="0" align="center" width="100%" style="border-collapse: collapse;">
		<c:forEach items="${personHealthList}" var="item" varStatus="status">
			<tr style="background-color:white">
				<td colspan="2">
				<img src="http://123.57.4.104/sz-wechat/public/healthPhotos/${item.healthpersoncode}.jpg"/>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>
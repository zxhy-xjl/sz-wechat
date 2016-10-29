<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no">
<title>订单详情页</title>
<script type="text/javascript" src="<%=path%>/public/script/jquery-3.0.0.js"></script>
<style type="text/css">
.dotline {
BORDER-BOTTOM-STYLE: dotted; BORDER-LEFT-STYLE: dotted; BORDER-RIGHT-STYLE: dotted; BORDER-TOP-STYLE: dotted
}
html,body {
   overflow:hidden;
   margin:0px;
   width:100%;
   height:100%;
}
#content {
    width:100%;
    height:90%;
    overflow-y:scroll;
    overflow-x:auto;
}
#footer {
	position:absolute; z-index:2;
	bottom:0; left:0;
	width:100%;
	height:50px;
	padding:0;
}
</style>
</head>
<script type="text/javascript">
	function doSubmit(){
		$("#detailForm").submit();
	}
</script>
<body style="background-color:#E9E9E9;">
<div id="content">
<form id="detailForm" action="<%=path%>/toMenuOrder.do" method="post">
<input value="${oddNumber}" name="oddNumber"  type="hidden">
<input value="${allPrice}" name="allPrice" type="hidden">
<input value="${companyCode}" name="companyCode" type="hidden">
</form>
	<table border="0" align="center" width="100%" style="border-collapse:collapse;" >
		<c:forEach items="${dictList}" var="item" varStatus="statu">
			<c:set  value="" var="flag" scope="page"/>
			<c:forEach items="${menuList}" var="item1" varStatus="status">
				<c:if test="${item.code_id ==  item1.menutype && flag != item1.menutype}">
					<tr style="height:20px;line-height:5px;">
						<td colspan="5" ><h2>&nbsp;&nbsp;<c:if test="${item.code_id == '0'}">${item.codedescript}</c:if><c:if test="${item.code_id == '1'}">${item.codedescript}</c:if></h2></td>
					</tr>
					<c:set  value="${item1.menutype}" var="flag" scope="page"/>
				</c:if>
				<c:if test="${item.code_id ==  item1.menutype}">
					<tr style="background-color:white;">
						<td width="120px" ><img alt="" src="<%=path%>/${item1.path}" width="150px" height="100px"></td>
						<td>
							 <p style="line-height:20px;">${item1.menuname}</p>
						</td>
						<td>
							<p style="line-height:20px;">${item1.menunum}份</p>
						</td>
						<td align="right">
							<p style="line-height:20px;color:#E1A541">￥${item1.price}</p>
						</td>
						<td width="15px;"></td>
					</tr>
					<tr>
						<td></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:forEach>
	</table>
</div>
<table id="footer" border="0"  width="100%" height="50px" style="border-collapse:collapse;">
	<tr>
		<td width="70%" style="background-color:#545454;" align="center">
			 <span style="color:#FBEBED;">合计:${allPrice}元</span>
			 <span style="color:#FBEBED;">${buyNum}道</span>
		</td>
		
		<td align="center" style="background-color:#D0021B;">
		<c:if test="${needPlayFlag}">
		<a href="javascript:doSubmit();" style="text-decoration:none;color:#FBEBED;">结算</a>
		</c:if>
		<c:if test="${!needPlayFlag}">
		已结算
		</c:if>
		</td>
	</tr>
</table>
</body>
</html>
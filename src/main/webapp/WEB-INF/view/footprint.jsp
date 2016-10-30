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
<title>足迹</title>
<style type="text/css">

html{

font-family: SimHei;

}

</style>
<script type="text/javascript">
var xmlHttpRequest = null;
var xmlHttpRequestLog = null;
var code = null;
var text = null;
var json = null;

var openid ="<%=session.getAttribute("openid")%>";




function pagejump(company)
{
	console.log(company);
	window.location.href="<%=path%>/toRestaurant.do?companycode="+company;
}
</script>
<style type="text/css">
table tr:nth-child(2n){background-color: #e9e9e9}
</style>
</head>
<body style="background-color:#E9E9E9;">
<div id="footinfo">
		<table border="0" align="center" width="100%"
			style="border-collapse: collapse;">
			<c:if test="${companyListCount==0 }">
			<tr style="background-color: white;height:50px;">
				<td>您还没有任何足迹，请扫描餐桌二维码</td>
			</tr>
			</c:if>
			<c:forEach items="${companyList}" var="item" varStatus="status">
				<tr style="background-color: white;height:50px;" onclick="pagejump('${item.companyCode}')">
				<td><img src="<%=path%>/public/images/company/${item.companyCode }.png"  height="100px" width="100px" / ></td>
					<td><label><font size="4px">${item.companyName}</font></label>
					<br><br>
						<label style="color: #d4a351;">订单数量:${item.consumerecCount}</label>
						<label style="color: #d4a351;">投诉数量:${item.complaintCount}</label>
						
					</td>
					<td width="50px"><label style="color: red; font-size: 20px;">${item.score}分</label><br />
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
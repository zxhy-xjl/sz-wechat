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
<title>${companyName}</title>
</head>
<script type="text/javascript" src="<%=path%>/public/script/jquery-3.0.0.js"></script>
<script type="text/javascript">

function toCompaintInfo(pid){
	window.location.href="<%=path%>/lookuserinfo.do?pid="+pid+"&companyname=${companyName}";
}

</script>
<body style="font-family: SimHei;background-color: #e9e9e9;">
<div id="content" >
	<table border="0" align="center" width="100%" style="border-collapse: collapse;">
		<c:if test="${complaintList==null}">
			<tr><td>该企业暂无投诉信息（未经核实反馈的投诉不再此列）</td></tr>
		</c:if>
		<c:forEach items="${complaintList}" var="item" varStatus="status">
			<tr style="background-color:white">
				<td colspan="2"><label style="font-size: 15px;">&nbsp;&nbsp;${fn:substring(item.complaintime,0,4)}年${fn:substring(item.complaintime,4,6)}月${fn:substring(item.complaintime,6,8)}日 ${fn:substring(item.complaintime,8,10)}:${fn:substring(item.complaintime,10,12)}   </label></td>
			</tr>
			<tr style="background-color:white" onclick="javascript:toCompaintInfo('${item.pid}')">
				<td><label><font size="4px" style="font-weight: bold">&nbsp;&nbsp;${item.complaincontent}</font></label><br>
					<label style="color: #d4a351;"><c:if test="${item.disposestatus == '1'}">&nbsp;&nbsp;您有一个投诉正在处理中</c:if></label>
				</td>
				<td><c:if test="${item.disposestatus == '1'|| item.disposestatus == '2'}"><label style="color: red; font-size: 20px;">未处理</label></c:if>
				<c:if test="${item.disposestatus == '4'|| item.disposestatus == '3'}"><label style="color: red; font-size: 20px;">已处理</label></c:if>
				<br />
				</td>
			</tr>
			<tr>
				<td colspan="2"><hr color="#B9B9B9"></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>
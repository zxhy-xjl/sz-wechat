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
<title>投诉详情</title>
<style type="text/css">

html{

font-family: SimHei;

}

</style>
<script type="text/javascript">

</script>
<style type="text/css">

</style>
</head>
<body style="background-color:#c3dde0;">
<div id="basedetail"  style="text-align: center;position:absolute;left:40%" >
<label ><font size="5" style="">投诉详情</font></label><br><br>
<div style="text-align: left;">
<label>饭店名称：${companyname }</label> <br><br>
<label>投诉时间：${complaint.complaintime }</label> <br><br>
<label>投诉内容：${complaint.complaincontent}</label><br><br>
<label>投诉人：${complaint.openid }</label><br><br>
<label>评分：${complaint.evaluate }星</label><br><br>
	<c:if test="${complaint.disposestatus == 1}">
	<label>投诉状态：等待受理</label><br><br>
	</c:if>
	<c:if test="${complaint.disposestatus == 2}">
	<label>投诉状态：等待处理</label><br><br>
	</c:if>
	<c:if test="${complaint.disposestatus == 3}">
	<label>投诉状态：处理中</label><br><br>
	</c:if>
	<c:if test="${complaint.disposestatus == 4}">
	<label>投诉状态：已反馈</label><br><br>
	</c:if>
<label>处理时间：${complaint.disposetime }</label><br><br>
<c:if test="${complaint.disposestatus!=1 }">
<label>处理部门：市场监督管理局</label><br><br>
</c:if>
<c:if test="${complaint.disposestatus==1 }">
<label>处理部门：${complaint.disposedep }</label><br><br>
</c:if>
<c:if test="${complaint.complaintype==1 }">
<label>投诉类型：店铺</label>
</c:if>
<c:if test="${complaint.complaintype==2 }">
<label>投诉类型：卫生</label>
</c:if>
<c:if test="${complaint.complaintype==3 }">
<label>投诉类型：服务</label>
</c:if>
<br><br>
<label>政府处理结果：${complaint.disposeresult }</label><br><br>
<c:if test="${complaint.complainphoto==null}">
<label style="font-weight: bolder;color: red;">&nbsp;&nbsp;没有图片，投诉时附加图片可以增加审核通过率</label>
</c:if>
<c:if test="${complaint.complainphoto!=null}">
<label>投诉图片：
<img alt="0" src="<%=path%>/toLookImage.do?pid=${complaint.pid }" width="200px"   >
</label>
</c:if>
<%-- <c:if test="${complaint.feedback!=null}">
<br><br>
<label>客户反馈：${complaint.feedback}</label>
</c:if> --%>
</div>

</div>
</body>
</html>
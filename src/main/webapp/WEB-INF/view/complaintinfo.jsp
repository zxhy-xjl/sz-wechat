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
.max{width:100%;height:auto;}
.min{width:100px;height:auto;}
html{
font-family: SimHei;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>投诉详情</title>
<script type="text/javascript" src="<%=path%>/public/script/jquery-3.0.0.js"></script>
<script type="text/javascript">
var flag = null;
var text = null;
var xmlHttpRequest = null;

$(function(){
	$('#img').click(function(){

	$(this).toggleClass('min');
	$(this).toggleClass('max');
	});
	});

</script>
</head>
<body style="background-color: #e9e9e9;">
<div id="baseinfo" style="background-color: #e9e9e9;">

<div style="width: 100%">
<table border="0" 
			style="border-collapse: collapse;width: 100%">
<tr style="height:40px;">
<td>
<label style="font-size: 20px">&nbsp;&nbsp;<font style="font-weight: bold;" size="5">投诉内容</font></label>
</td>
</tr>

<tr style="height:40px;background-color: white;">
<td><br />
<label >&nbsp;&nbsp;<font style="" size="3">投诉餐厅：${companyname }</font></label><br /><br />
<label >&nbsp;&nbsp;<font style="" size="3">投诉时间：${fn:substring(complaint.complaintime,0,4)}年${fn:substring(complaint.complaintime,4,6)}月${fn:substring(complaint.complaintime,6,8)}日
</font></label><br /><br />
<label >&nbsp;&nbsp;<font style="" size="3">投诉描述：${complaint.complaincontent }</font></label><br /><br />
<c:if test="${complaint.complainphoto!=null}">
<img alt="0" src="toLookImage.do?pid=${complaint.pid }" id="img" class='min'>
</c:if>
<c:if test="${complaint.complainphoto==null}">
<label style="font-weight: bolder;color: red;">&nbsp;&nbsp;没有图片，投诉时附加图片可以增加审核通过率</label>
</c:if>
</td>
</tr>
</table>
</div>
<div >
<label style="font-size: 20px;">&nbsp;&nbsp;<font style="font-weight: bold;" size="5">处理流程</font></label>
<br>
</div>

<div style="background-color: white;">
<br>
<table><tr>
<td>
<img src="<%=path%>/public/images/bg_1_1.png"><br>
&nbsp;&nbsp;<div style="display: inline;position:relative;"><label >投诉</label> </div>
</td>
<td>--<br>&nbsp;</td>
<td>
<c:if test="${complaint.disposestatus>1}">
<img src="<%=path%>/public/images/bg_1_2.png"><br>
</c:if>
<c:if test="${!(complaint.disposestatus>1)}">
<img src="<%=path%>/public/images/bg_2_2.png"><br>
</c:if>

&nbsp;&nbsp;<div style="display: inline;position:relative;"><label >受理</label> </div>
</td>
<td>--<br>&nbsp;</td>
<td>
<c:if test="${complaint.disposestatus>2}">
<img src="<%=path%>/public/images/bg_1_3.png"><br>
</c:if>
<c:if test="${!(complaint.disposestatus>2)}">
<img src="<%=path%>/public/images/bg_2_3.png"><br>
</c:if>
&nbsp;&nbsp;<div style="display: inline;position:relative;"><label >处理</label> </div>
</td>
<td>--<br>&nbsp;</td>
<td>
<c:if test="${complaint.disposestatus>3}">
<img src="<%=path%>/public/images/bg_1_4.png"><br>
</c:if>
<c:if test="${!(complaint.disposestatus>3)}">
<img src="<%=path%>/public/images/bg_2_4.png"><br>
</c:if>
&nbsp;&nbsp;<div style="display: inline;position:relative;"><label >反馈</label> </div>
</td>
</tr></table>
<hr color="lightgrey" size="1" width="90%"/>

<table border="0" width="100%"
			style="border-collapse: collapse;">


<tr style="height:40px;background-color: white;">

<td><br />
<label >&nbsp;&nbsp;<font style="" size="3">处理时间：
<c:if test="${complaint.disposetime != null }">
${fn:substring(complaint.disposetime,0,4)}年${fn:substring(complaint.disposetime,4,6)}月${fn:substring(complaint.disposetime,6,8)}日
</c:if>
</font></label><br /><br />
<label >&nbsp;&nbsp;<font style="" size="3">处理部门：${complaint.disposedep }</font></label><br /><br />
<label >&nbsp;&nbsp;<font style="" size="3">处理结果：${complaint.disposeresult }</font></label><br /><br />
</td>
</tr>
</table>
</div>
</div>
</body>
</html>
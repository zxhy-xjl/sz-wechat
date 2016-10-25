<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>投诉成功</title>
<style type="text/css">
html{
font-family: SimHei;
}
</style>
</head>
<body style="background-color: #e9e9e9">
<br><br><br><br><br>
<div id="imgdiv" style="text-align: center;">
<img id="successimg" onclick="" alt="0" src="<%=path%>/public/images/success.png" width="120" height="120">
</div>
<div id="success" style="text-align: center;">
<h2>投诉已提交！</h2>
</div>
<div id="suctable">
<br>
<h2 style="font-size: 20px;">&nbsp;&nbsp;&nbsp;后续流程</h2>
<hr color="lightgrey" width="85%"/>
&nbsp;&nbsp;&nbsp;&nbsp;<img style="position: relative;top:5px; " id="successimg" onclick="" alt="0" src="<%=path%>/public/images/bg_1.png" width="20" height="20">
<label style="color: #8bcd44;">申请投诉</label>
<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;<img style="position: relative;top:5px; " id="successimg" onclick="" alt="0" src="<%=path%>/public/images/bg_2.png" width="20" height="20">
<label>投诉受理中</label>
<br>
<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成功将在扫桌公众号中显示，请及时关注</label>
<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;<img style="position: relative;top:5px; " id="successimg" onclick="" alt="0" src="<%=path%>/public/images/bg_2.png" width="20" height="20">
<label >投诉处理中</label>
<br>
<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成功将在扫桌公众号中显示，请及时关注</label>
<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;<img style="position: relative;top:5px; " id="successimg" onclick="" alt="0" src="<%=path%>/public/images/bg_2.png" width="20" height="20">
<label >投诉反馈</label>
<br>
<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成功将在扫桌公众号中显示，请及时关注</label>


</div>
<br><br>
<div id="detaildiv" style="text-align: center;">
<input type="button" value="查看投诉详情" style="background: #f3be67;width:120px;height:40px;font-family: SimHei;font-weight: bold;font-size: 15px">
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>投诉详情</title>
<script type="text/javascript">
<%-- var flag = null;
var text = null;
var xmlHttpRequest = null;
window.onload=function()//用window的onload事件，窗体加载完毕的时候
{   text = <%=request.getParameter("userinfo")%>;
     var json = JSON.stringify(text); 
	flag = "<%=request.getParameter("flag")%>";
	var newurl = window.location.href;
	if(flag==1)
		{document.getElementById("shadowdiv").style.display="block";
		document.getElementById("disposediv").style.display="block";
		
		}
		var url = "<%=path%>/insertLog.do?json="+json+"&url="+newurl;
		
	if(window.ActiveXObject) {   			//IE的
		xmlHttpRequest = new ActionXObject("Microsoft.XMLHTTP");
	}
	else if(window.XMLHttpRequest) {		//除IE外的
		xmlHttpRequest = new XMLHttpRequest();
	}
	if(xmlHttpRequest != null) {
		
		xmlHttpRequest.open("GET", url, true);
		//xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		//关联好ajax的回调函数
		xmlHttpRequest.onreadystatechange = ajaxCall;
		
		//真正向服务器发送请求
		xmlHttpRequest.send();
	}
}

function ajaxCall(){
	
	if(xmlHttpRequest.readyState == 4 ) {  		//完全得到服务器的响应
		if(xmlHttpRequest.status == 200) {	
			console.log('保存成功！'); 
		}else
			{				
			console.log('保存失败！'); 
			}
	
	}
	
} --%>
</script>
</head>
<body>
<div id="baseinfo" style="background-color: #e9e9e9;">
<table border="0" width="100%"
			style="border-collapse: collapse;">
<tr style="height:40px;">
<td>
<label style="font-size: 20px">&nbsp;&nbsp;<font style="font-weight: bold;" size="5">投诉内容</font></label>
</td>
</tr>

<tr style="height:40px;background-color: white;">
<td><br />
<label >&nbsp;&nbsp;<font style="" size="3">投诉餐厅：${companyname }</font></label><br /><br />
<label >&nbsp;&nbsp;<font style="" size="3">投诉时间：${complaint.complaintime }</font></label><br /><br />
<label >&nbsp;&nbsp;<font style="" size="3">投诉描述：${complaint.complaincontent }</font></label><br /><br />
</td>
</tr>
<tr style="height:50px;background-color: #e9e9e9;">
<td>
<label style="font-size: 20px">&nbsp;&nbsp;<font style="font-weight: bold;" size="5">处理流程</font></label>
<br>
</td>
</tr>
<tr style="height:40px;background-color: white;">
<td><br>
<label >&nbsp;&nbsp;<font style="" size="3">处理时间：${complaint.disposetime }</font></label><br /><br />
<label >&nbsp;&nbsp;<font style="" size="3">处理部门：${complaint.disposedep }</font></label><br /><br />
<label >&nbsp;&nbsp;<font style="" size="3">处理结果：${complaint.disposeresult }</font></label><br /><br />

</td>
</tr>

</table>
</div>
<%-- <div id="baseinfo">
<label>投诉餐厅：上海创洁</label><br><br>
<label>投诉时间： 2016年9月1日</label><br><br>
<label>投诉内容：</label><br><br>
<textarea rows="5" >服务态度非常恶劣，简直无法形容。</textarea><br>
</div>
<div id="disposediv" style="">
<label>处理流程：</label><br>
<img src="<%=path%>/public/images/complaintflow.jpg"   /> <br>
<label>处理时间：2016年9月29日</label><br>
<label>处理部门：XX市质监局</label><br>
<label>处理结果：</label><br>
<textarea rows="5" >已通知餐厅处理。</textarea>
</div>

<div id="shadowdiv" style="display: none;text-align: center;">
<input type="button" value="提交投诉">
</div> --%>
</body>
</html>
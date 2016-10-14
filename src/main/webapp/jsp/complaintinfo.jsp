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
<title>投诉详情</title>
<script type="text/javascript">
var flag = null;
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
	
}
</script>
</head>
<body>
<div id="baseinfo">
<label>投诉餐厅： 某餐厅（XXX店）</label><br><br>
<label>投诉时间： 2016年9月1日</label><br><br>
<label>投诉内容：</label><br><br>
<textarea rows="5" >服务态度非常恶劣，简直无法形容。</textarea><br>
</div>
<div id="disposediv" style="display: none;">
<label>处理流程：</label><br>
<img src="../public/images/complaintflow.jpg"   /> <br>
<label>处理时间：2016年9月29日</label><br>
<label>处理部门：XX市质监局</label><br>
<label>处理结果：</label><br>
<textarea rows="5" >已通知餐厅处理。</textarea>
</div>

<div id="shadowdiv" style="display: none;text-align: center;">
<input type="button" value="提交投诉">
</div>
</body>
</html>
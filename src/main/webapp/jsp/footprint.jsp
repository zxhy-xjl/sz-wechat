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
<title>足迹</title>
<script type="text/javascript">
var xmlHttpRequest = null;
var xmlHttpRequestLog = null;
var code = null;
var text = null;
window.onload=function()//用window的onload事件，窗体加载完毕的时候
{
	//var openid ="<%=session.getAttribute("openid")%>";
	
	//window.location.href = "<%=path%>/toFootprint.do?openid="+openid;
	
	 code = "<%=request.getParameter("code")%>";
	var url = "<%=path%>/Oauth2Servlet.do?code="+code;
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


 	function ajaxCall() {
		
		if(xmlHttpRequest.readyState == 4 ) {  		//完全得到服务器的响应
			if(xmlHttpRequest.status == 200) {		//没有异常
				//var tableformap = document.createElement("div");
			
				 text = xmlHttpRequest.responseText;
				var json = eval('(' + text + ')'); 
				//var newurl = window.location.href;
				window.location.href = "<%=path%>/toFootprint.do?openid="+json.openid;
				
	} 
	  }
	}	
 
	

</script>

</head>
<body>
 
</body>
</html>
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
				var newurl = window.location.href;
				var urllog = "<%=path%>/insertLog.do?json="+text+"&url="+newurl;
				if(window.ActiveXObject) {   			//IE的
					xmlHttpRequestLog = new ActionXObject("Microsoft.XMLHTTP");
				}
				else if(window.XMLHttpRequest) {		//除IE外的
					xmlHttpRequestLog = new XMLHttpRequest();
				}
				if(xmlHttpRequestLog != null) {
					
					xmlHttpRequestLog.open("GET", urllog, true);
					//xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
					//关联好ajax的回调函数
					xmlHttpRequestLog.onreadystatechange = ajaxCallLog;
					
					//真正向服务器发送请求
					xmlHttpRequestLog.send();
				}
				
				
			    document.getElementById("openid").value=json.openid;
				document.getElementById("nickname").innerText=json.nickname;
				if(json.sex==1)
					document.getElementById("sex").value='男';
				else
					document.getElementById("sex").value='女';
				document.getElementById("language").value=json.language;
				document.getElementById("city").value=json.city;
				document.getElementById("province").value=json.province;
				document.getElementById("country").value=json.country;
				
				//alert(text);
		   } else 
			   {
			   alert("请求异常!");
			   }
		
		
	} 
	  }
	
	function ajaxCallLog(){
		
		if(xmlHttpRequestLog.readyState == 4 ) {  		//完全得到服务器的响应
			if(xmlHttpRequestLog.status == 200) {	
				console.log('保存成功！'); 
			}else
				{				
				console.log('保存失败！'); 
				}
		
		}
		
	}
	
	function pagejump()
	{
		window.location.href="restaurantinfo.jsp?userinfo="+text;  		
	}
</script>
<style type="text/css">
table tr:nth-child(2n){background-color: #e9e9e9}

</style>
</head>
<body>
  <div id="printinfo" > 
  <table style="width:100%;border-collapse: collapse;border-spacing: 10px;">
  
  <tr onclick="pagejump()" >
  <td>
  <label style="font-size: 10px;">2016年9月29日 19:20</label>
  
  </td>
  <td>
  <label>上海</label><br>
  <label><font style="font-weight: bold">海底捞（大行宫店）</font></label><br>
  
  </td>  
  <td>
 <label style="color: red;font-size: 20px;">87分</label><br/>
  </td>
  </tr>
    
  <tr onclick="pagejump()">
  <td>
    <label style="font-size: 10px;">2016年9月29日 19:20</label><br>
 
  </td>
  <td>
 <label>河南 郑州</label><br>
  <label><font style="font-weight: bold">魏家米皮（文化一路店）</font></label><br>
   <label style="color: #d4a351;">您有一个投诉正在处理中</label>
  </td>  
  <td>
 <label style="color: red;font-size: 20px;">99分</label>
  </td>
  </tr>
  
  </table>
  </div>

<div id="infodiv" style="display: none;" >
<label>openid:</label><input id="openid" /><br>
<label>昵称:</label><input id="nickname" /><br>
<label>性别:</label><input id="sex" /><br>
<label>语言:</label><input id="language" /><br>
<label>城市:</label><input id="city" /><br>
<label>省份:</label><input id="province" /><br>
<label>国家:</label><input id="country" /><br>
</div>
</body>
</html>
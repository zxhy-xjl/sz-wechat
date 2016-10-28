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
<title>我的</title>
<style type="text/css">
html{
font-family: SimHei;
}

</style>
<script type="text/javascript">
<%--  var xmlHttpRequest = null;
var xmlHttpRequestLog = null;
var code = null;
var text = null;
var json = null;
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
				
				 json = eval('(' + text + ')'); 
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
	
	function changetable(id)
	{
		if(id=='myorder')
		{
			document.getElementById("ordertablediv").style.display = "block";
			document.getElementById("complainttablediv").style.display = "none";
      
			document.getElementById("myorder").style.background = "#48DD22";
			document.getElementById("mycomplaint").style.background = "#FFFFFF";
		}
		else if(id=='mycomplaint')
			{
			document.getElementById("ordertablediv").style.display = "none";
			document.getElementById("complainttablediv").style.display = "block";

			document.getElementById("myorder").style.background = "#FFFFFF";
			document.getElementById("mycomplaint").style.background = "#48DD22";
			}
		
	}
	
	function pagejump()
	{
		window.location.href="<%=path%>/jsp/complaintinfo.jsp?userinfo="+text;  		
	}  --%>
	
	window.onload=function()//用window的onload事件，窗体加载完毕的时候
	{
	    var	nickname = "<%=session.getAttribute("nickname")%>";
		document.getElementById("nickname").innerText = nickname;
	}
	
	function changetable(id)
	{
		if(id=='myorder')
		{
			document.getElementById("myorder").style.display = "block";
			document.getElementById("mycomplaint").style.display = "none";
      
			document.getElementById("myorderbutton").style.background = "#e9e9e9";
			document.getElementById("mycomplaintbutton").style.background = "#d4d3d3";
		}
		else if(id=='mycomplaint')
			{
			document.getElementById("myorder").style.display = "none";
			document.getElementById("mycomplaint").style.display = "block";

			document.getElementById("myorderbutton").style.background = "#d4d3d3";
			document.getElementById("mycomplaintbutton").style.background = "#e9e9e9";
			}
		
	}
	
	function detailsinfo(oddNumber){
		
		
		console.log(oddNumber);
		window.location.href="<%=path%>/toMenuView.do?oddNumber="+oddNumber+"&flag=1"
		
		
	}
	function detailsinfo1(oddNumber){
		
		
		console.log(oddNumber);
		window.location.href="<%=path%>/toMenuView.do?oddNumber="+oddNumber+"&flag=0"
		
		
	}
	
	
	function pagejump(pid,companyname)
	{
		
		window.location.href="<%=path%>/lookuserinfo.do?pid="+pid+"&companyname="+companyname;  		
	
	}
</script>
</head>
<body style="background-color: #e9e9e9;">
<div id="welcomediv">您好，<label id="nickname" style="color: red;"></label><br></div>
<div style="text-align: center;">
<input  id="myorderbutton" type="button" onclick="changetable('myorder')" style="background-color: #e9e9e9;font-size:20px;font-family: SimHei;border:none;width:40%;height:35px;" value="我的订单"><input id="mycomplaintbutton" type="button" onclick="changetable('mycomplaint')" style="background-color: #d4d3d3;font-size:20px;font-family: SimHei;border:none;width:40%;height:35px;" value="我的投诉">
</div>
<div id="myorder">
<table border="0" width="100%"
			style="border-collapse: collapse;">
			<c:forEach items="${consumereclist}" var="item" varStatus="status">
				
				<tr style="background-color: #E9E9E9;height:30px;">
					<td><label style="font-size: 15px;">&nbsp;&nbsp;${fn:substring(item.paytime,0,4)}年${fn:substring(item.paytime,4,6)}月${fn:substring(item.paytime,6,8)}日 ${fn:substring(item.paytime,8,10)}:${fn:substring(item.paytime,10,12)}  上海</label></td>
				</tr>
				<tr style="background-color: white;height:50px;" onclick="detailsinfo1('${item.oddnumber}')">
					<td>
						<label><font size="4px" style="font-weight: bold">&nbsp;&nbsp;${item.pid}</font></label><br />
					</td>
					<td></td>
				</tr>
				<tr style="background-color: white;height:50px;" >
					<td>
						<label style="">&nbsp;&nbsp;合计：${item.buynum}道菜，${item.billunit}元</label>
					</td>
					
					<td><c:if test="${item.paytype == null}"><input type="button" style="height:25px;width:60px;background-color: #f3be67;border:none;font-family: SimHei;font-size: 20px;-moz-border-radius: 20px;" value="买单" onclick="detailsinfo('${item.oddnumber}')"></c:if>
					<c:if test="${item.paytype != null}"><label  style="height:25px;width:60px;color: #a59171;font-family: SimHei;font-size: 20px" >已付款</label></c:if>
					<br />
					</td>
				</tr>
			<%-- 	<tr style="background-color: white;height:50px;" onclick="pagejump('${item.companycode}','${item.visittime}')">
					<td>
						<label style="">&nbsp;&nbsp;合计：8道菜，12元</label>
					</td>
					<td><input type="button" style="height:25px;width:60px;background-color: #f3be67;border:none;font-family: SimHei;font-size: 15px;-moz-border-radius: 20px;" value="买单"><br />
					</td>
				</tr> --%>
			</c:forEach>
		</table>
</div> 
<div id="mycomplaint" style="display: none;">
<table border="0" align="center" width="100%"
			style="border-collapse: collapse;">
			<c:forEach items="${complaintlist}" var="item" varStatus="status">
				<tr style="background-color: #E9E9E9;height:30px;">
					<td><label style="font-size: 15px;">&nbsp;&nbsp;${fn:substring(item.complaintime,0,4)}年${fn:substring(item.complaintime,4,6)}月${fn:substring(item.complaintime,6,8)}日 ${fn:substring(item.complaintime,8,10)}:${fn:substring(item.complaintime,10,12)}  上海</label></td>
				</tr>
				<tr style="background-color: white;height:50px;" onclick="pagejump('${item.pid}','${item.disposetime}')">
					<td><label><font size="4px" style="font-weight: bold">&nbsp;&nbsp;${item.disposetime}</font></label><br>
						<label style="color: #d4a351;"><c:if test="${item.disposestatus == '1'}">&nbsp;&nbsp;您有一个投诉正在处理中</c:if></label>
					</td>
					<td><c:if test="${item.disposestatus == '1'|| item.disposestatus == '2'}"><label style="color: red; font-size: 20px;">未处理</label></c:if>
					<c:if test="${item.disposestatus == '4'|| item.disposestatus == '3'}"><label style="color: green; font-size: 20px;">已处理</label></c:if>
					
					<br />
					</td>
				</tr>
			</c:forEach>
		</table>
</div>
 <!-- <div id="welcomediv">您好，<label id="nickname" style="color: red;"></label><br></div>
<br>
 <div id="mydiv" style="text-align: center;">
  <input id="myorder" type="button" value="我的订单" onclick="changetable(this.id)" style="background: #48DD22;height:35px;width:75px;">
  <input id="mycomplaint" type="button" value="我的投诉" onclick="changetable(this.id)" style="height:35px;width:75px;">
  </div> 
  <div id="ordertablediv">
  <table>
  <tr >
  <td>
  <label>上海</label><br>
  <img src="../public/images/food.jpg" height="60px" width="60px"  />
  </td>
  <td>
  <label>2016年9月29日 19:20</label><br>
  <label>海底捞（大行宫店）</label><br>
  <label>金额：888元</label><br>
  <label>合计：8道菜</label><br>
  </td>  
  <td>
 <label style="color: red">未付款</label>
  </td>
  </tr>
    <tr>
  <td>
  <label>江苏 南京</label><br>
<img src="../public/images/food.jpg" height="60px" width="60px"  />  
  </td>
  <td>
  <label>2016年9月29日 19:20</label><br>
  <label>海底捞（大行宫店）</label><br>
  <label>金额：888元</label><br>
  <label>合计：8道菜</label><br>
  </td>  
  <td>
 <label style="color: red">未付款</label>
  </td>
  </tr>
  
     <tr>
  <td>
  <label>河南 郑州</label><br>
<img src="../public/images/food.jpg" height="60px" width="60px"  />  
  </td>
  <td>
  <label>2016年9月29日 19:20</label><br>
  <label>魏家米皮（文化路店）</label><br>
  <label>金额：888元</label><br>
  <label>合计：8道菜</label><br>
  </td>  
  <td>
 <label >已付款</label>
  </td>
  </tr>
  
  </table>
  </div>
  <div id="complainttablediv" style="display: none;">
  <table>
  <tr onclick="pagejump()">
  <td >
  <label >北京</label><br>
<img src="../public/images/food.jpg" height="60px" width="60px"  />  
  </td>
  <td>
  <label>2013年1月29日 19:20</label><br>
  <label>海底捞（大行宫店）</label><br>
  <label>金额：888元</label><br>
  <label>合计：8道菜</label><br>
  </td>  
  <td>
 <label >已付款</label>
  </td>
  </tr>
    <tr onclick="pagejump()">
  <td>
  <label>江苏 无锡</label><br>
<img src="../public/images/food.jpg" height="60px" width="60px"  />  
  </td>
  <td>
  <label>2011年9月29日 19:20</label><br>
  <label>海底捞（大行宫店）</label><br>
  <label>金额：888元</label><br>
  <label>合计：8道菜</label><br>
  </td>  
  <td>
 <label style="color: red">未付款</label>
  </td>
  </tr>
  
     <tr onclick="pagejump()">
  <td>
  <label>河南 洛阳</label><br>
<img src="../public/images/food.jpg" height="60px" width="60px"  />  
  </td>
  <td>
  <label>2013年9月11日 19:20</label><br>
  <label>魏家米皮（文化路店）</label><br>
  <label>金额：888元</label><br>
  <label>合计：8道菜</label><br>
  </td>  
  <td>
 <label style="color: red">未付款</label>
  </td>
  </tr>
  
  </table>
  </div>
<div id="infodiv" style="display: none;" >
<label>openid:</label><input id="openid" /><br>
<label>性别:</label><input id="sex" /><br>
<label>语言:</label><input id="language" /><br>
<label>城市:</label><input id="city" /><br>
<label>省份:</label><input id="province" /><br>
<label>国家:</label><input id="country" /><br>
</div> -->

</body>
</html>
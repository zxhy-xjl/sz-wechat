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
<title>我的</title>
<script type="text/javascript">
var xmlHttpRequest = null;
var code = null;
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
			
				var text = xmlHttpRequest.responseText;
				
				var json = eval('(' + text + ')'); 
				
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
		window.location.href="complaintinfo.jsp";  		
	}
</script>
</head>
<body>
<div id="welcomediv">您好，<label id="nickname" style="color: red;"></label><br></div>
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
  <img src="../img/food.jpg" height="60px" width="60px"  />
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
<img src="../img/food.jpg" height="60px" width="60px"  />  
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
<img src="../img/food.jpg" height="60px" width="60px"  />  
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
<img src="../img/food.jpg" height="60px" width="60px"  />  
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
<img src="../img/food.jpg" height="60px" width="60px"  />  
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
<img src="../img/food.jpg" height="60px" width="60px"  />  
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
</div>

</body>
</html>
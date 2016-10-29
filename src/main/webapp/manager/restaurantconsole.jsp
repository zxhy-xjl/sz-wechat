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
.mesWindow {
	border: #666 1px solid;
	background: #fff;
}

.mesWindowTop {
	border-bottom: #eee 1px solid;
	margin-left: 4px;
	padding: 3px;
	font-weight: bold;
	text-align: left;
	font-size: 12px;
}

.mesWindowContent {
	margin: 4px;
	font-size: 12px;
}

.mesWindow .close {
	/* height: 15px;
	width: 28px; */
	border: none;
	cursor: pointer;
	/* text-decoration: underline; */
	background: #fff
}
html{
font-family: SimHei;
}
div{
white-space: nowrap;
}
table.hovertable {
	font-family: SimHei;
	font-size:15px;
	color:#333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}
table.hovertable th {
	background-color:#c3dde0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
	font-size: 20px;
	font-weight: bolder;
}
table.hovertable tr {
	background-color:#d4e3e5;
}
table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>扫桌餐厅端控制台</title>
<script type="text/javascript">
var isIe = (document.all) ? true : false;
function complaindetails(pid,companyname)
{
	
	window.location.href="<%=path%>/governmentdetails.do?pid="+pid+"&companyname="+companyname;
	
	
}
//阻止事件重复响应
function stopBubble(e){
	 if(e && e.stopPropagation){
	 e.stopPropagation(); //w3c
	 }else window.event.cancelBubble=true; //IE
	 }
	 
	function bizchange(event,button,newpid,status)
	{
		
		stopBubble(event);
		var tmp = confirm("确认"+button.value+"此投诉？")
		
     if(tmp==true)
    	 {
		button.value="已"+button.value;
		window.location.href="<%=path%>/updategovdetails.do?pid="+newpid+"&status="+status;
    	 }
     else
    	 return;
	}
	
	function feedfinish()
	{
		

		//window.location.href="<%=path%>/government.do";
	}
	
	//弹出方法
	function showMessageBox(wTitle, content, wWidth,feedpid)

	{

		closeWindow();

		var bWidth = parseInt(document.documentElement.scrollWidth);

		var bHeight = parseInt(document.documentElement.scrollHeight);

		if (isIe) {

			setSelectState('hidden');
		}

		var back = document.createElement("div");

		back.id = "back";

		var styleStr = "top:0px;left:0px;position:absolute;background:#666;width:"
				+ bWidth + "px;height:" + bHeight + "px;";

		styleStr += (isIe) ? "filter:alpha(opacity=0);" : "opacity:0;";

		back.style.cssText = styleStr;

		document.body.appendChild(back);

		showBackground(back, 50);

		var mesW = document.createElement("div");

		mesW.id = "mesWindow";

		mesW.className = "mesWindow";

		mesW.innerHTML = "<div class='mesWindowTop'><table width='100%' height='100%'><tr><td>"
				+ wTitle
				+ "</td><td style='width:1px;'><input type='button' onclick='closeWindow();' title='关闭窗口' class='close' value='关闭' /></td></tr></table></div><div class='mesWindowContent' id='mesWindowContent'>"
				+ content + "</div><div class='mesWindowBottom'></div>";

		styleStr = "left:500"
				
				+ "px;height:500px;top:100" +  "px;position:absolute;width:" + wWidth
				+ "px;";

		mesW.style.cssText = styleStr;

		document.body.appendChild(mesW);
        document.getElementById("feedpid").value = feedpid;
	}

	//让背景渐渐变暗
	function showBackground(obj, endInt)
	{
		if (isIe)

		{
			obj.filters.alpha.opacity += 1;

			if (obj.filters.alpha.opacity < endInt)
			{
				setTimeout(function() {
					showBackground(obj, endInt)
				}, 5);
			}
	} else {
			var al = parseFloat(obj.style.opacity);
			al += 0.01;
			obj.style.opacity = al;
			if (al < (endInt / 100))
			{
				setTimeout(function() {
					showBackground(obj, endInt)
				}, 5);
			}
		}
	}

	//关闭窗口
	function closeWindow()
	{
		if (document.getElementById('back') != null)
		{
		document.getElementById('back').parentNode.removeChild(document
					.getElementById('back'));
		}
		if (document.getElementById('mesWindow') != null)
		{
			document.getElementById('mesWindow').parentNode.removeChild(document.getElementById('mesWindow'));
		}
		if (isIe) {
			setSelectState('');
		}
	}

	//测试弹出
	function testMessageBox(event,feedpid)
	{
		stopBubble(event);
	messContent = "<div style='padding:30px 0 30px 120px;'><form id=\"feedbackForm\" action='<%=path%>/updategovdetails.do' method=\"post\"> <input type=\"hidden\" id='feedpid' name=\"pid\" value=\"\"><input type=\"hidden\" id='status' name=\"status\" value=\"4\"><label>反馈内容:</label><br/><textarea id='result' name='feedback' row='6' style='height: 300px;width: 300px;'></textarea><br/><br/><br/><input type='submit' style='width:48px;margin-left: 120px;' value='完成' onclick='feedfinish()'/></form></div>";
		showMessageBox('用户反馈', messContent, 500,feedpid);
	}
</script>
</head>
<body style="background-color: #e9e9e9;">
<table class="hovertable">
<tr>
	<th colspan="5" ><font style="font-weight: bolder">投诉处理</font></th>
</tr>
<tr>
	<th>日期</th><th>餐厅</th><th>投诉内容</th><th>状态</th><th>操作</th>
</tr>
<c:forEach items="${consumelist}" var="item" varStatus="status">
<tr onclick="" onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">

	<td>
	${item.tablenum }
	</td>
	
</tr>
</c:forEach>


</table>

</body>
</html>
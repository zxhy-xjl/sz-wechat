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
	
	window.onload=function()//用window的onload事件，窗体加载完毕的时候
	{
	    var	nickname = "<%=session.getAttribute("nickname")%>";
		document.getElementById("nickname").innerText = nickname;
		var flag = '${flag}';
		if(flag==1)
			{
			document.getElementById("myorderbutton").style.background = "";
			document.getElementById("mycomplaintbutton").style.background = "#c1a79d";
			document.getElementById("myorder").style.display = "none";
			document.getElementById("mycomplaint").style.display = "";
			
			}
		
	}
	
	function changetable(id)
	{
		if(id=='myorder')
		{
      
			document.getElementById("myorderbutton").style.background = "#c1a79d";
			document.getElementById("mycomplaintbutton").style.background = "";
			document.getElementById("myorder").style.display = "";
			document.getElementById("mycomplaint").style.display = "none";
		}
		else if(id=='mycomplaint')
			{

			document.getElementById("myorderbutton").style.background = "";
			document.getElementById("mycomplaintbutton").style.background = "#c1a79d";
			document.getElementById("myorder").style.display = "none";
			document.getElementById("mycomplaint").style.display = "";
			}
		
	}
	
	function detailsinfo(oddNumber){
		
		
		console.log(oddNumber);
		window.location.href="<%=path%>/toMenuView.do?oddNumber="+oddNumber+"&flag=1"
		
		
	}
	function detailsinfo1(oddNumber,orderStatus){
		
		var flag = orderStatus=="待支付";
		console.log(oddNumber);
		window.location.href="<%=path%>/toMenuView.do?oddNumber="+oddNumber+"&flag=" + flag;
		
		
	}
	
	
	function pagejump(pid,companyname,reflag)
	{
		
		window.location.href="<%=path%>/lookuserinfo.do?pid="+pid+"&companyname="+companyname+"&reflag="+reflag;  		
	
	}
</script>
</head>
<body style="background-color: #e9e9e9;">
<div id="welcomediv">您好，<label id="nickname" style="color: red;"></label><br></div>
<div style="text-align: center;">
<input  id="myorderbutton" type="button" onclick="changetable('myorder')" style="background-color: #c1a79d;font-size:20px;font-family: SimHei;border:none;width:40%;height:35px;" value="我的订单">
<input id="mycomplaintbutton" type="button" onclick="changetable('mycomplaint')" style="background-color: ;font-size:20px;font-family: SimHei;border:none;width:40%;height:35px;" value="我的投诉">
</div>
<div id="myorder">
<table border="0" width="100%"
			style="border-collapse: collapse;">
			<c:forEach items="${orderList}" var="item" varStatus="status">
				
				<tr style="background-color: #E9E9E9;height:30px;">
					<td><label style="font-size: 15px;">
					&nbsp;&nbsp;${fn:substring(item.orderDate,0,4)}年${fn:substring(item.orderDate,4,6)}月${fn:substring(item.orderDate,6,8)}日 </label></td>
				</tr>
				<tr style="background-color: white;height:50px;" onclick="detailsinfo1('${item.orderNo}','${item.orderStatus}')">
					<td>
						<label><font size="4px" style="font-weight: bold">&nbsp;&nbsp;${item.companyName}</font></label><br />
					</td>
					<td></td>
				</tr>
				<tr style="background-color: white;height:50px;" >
					<td>
						<label style="">&nbsp;&nbsp;合计：${item.orderCount}道菜，${item.orderTotalMoney}元</label>
					</td>
					
					<td><c:if test="${item.orderStatus == '待支付'}">
					<input type="button" value="去结算" onclick="detailsinfo('${item.orderNo}')"></c:if>
					<c:if test="${item.orderStatus == '已支付'}"><label  style="height:25px;width:60px;color: #a59171;font-family: SimHei;font-size: 20px" >已付款</label></c:if>
					<br />
					</td>
				</tr>
			</c:forEach>
		</table>
</div> 
<div id="mycomplaint" style="display: none;">
<table border="0" align="center" width="100%"
			style="border-collapse: collapse;">
			<c:forEach items="${complaintlist}" var="item" varStatus="status">
				<tr style="background-color: #E9E9E9;height:30px;">
					<td><label style="font-size: 15px;">
					${item.companyName} ${fn:substring(item.complaintime,4,6)}月${fn:substring(item.complaintime,6,8)}日 
					
					</label>
					</td>
				</tr>
				<tr style="background-color: white;height:50px;" onclick="pagejump('${item.pid}','${item.companyName}','1')">
					<td>
					<label><font size="4px">&nbsp;&nbsp;${item.complaincontent}</font></label>
					<c:if test="${item.disposestatus == '4'}">
					<br>
					<label  style="color: green; font-size: 16px;">&nbsp;&nbsp;${item.disposeresult}</label>
					</c:if>
					</td>
					<td>
					<c:if test="${item.disposestatus == '1'}"><label style="color: red; font-size: 20px;">待受理</label></c:if>
					<c:if test="${item.disposestatus == '2'}"><label style="color: red; font-size: 20px;">已受理<br>待处理</label></c:if>
					<c:if test="${item.disposestatus == '3'}"><label style="color: red; font-size: 20px;">已处理<br>待反馈</label></c:if>
					<c:if test="${item.disposestatus == '4'}"><label style="color: green; font-size: 20px;">已反馈</label></c:if>
					
					<br />
					</td>
				</tr>
			</c:forEach>
		</table>
</div>

</body>
</html>
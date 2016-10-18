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
<title>餐厅详情</title>
<script type="text/javascript">



function pagejump()
{
	window.location.href="<%=path%>/jsp/complaintinfo.jsp?userinfo="+json+"&flag=1";  		
}

</script>
<style type="text/css"> 
.vote-star{ 
    display:inline-block;/*内联元素转换成块元素，并不换行*/ 
    margin-right:6px; 
    width:85px;/*5个星星的宽度 */ 
    height:17px;/*1个星星的高度 */ 
    overflow:hidden; 
    vertical-align:middle; 
    background:url(<%=path%>/public/images/star.gif) repeat-x 0 -17px;} 
.vote-star i{ 
    display:inline-block;/*内联元素转换成块元素，并不换行 */ 
    height:17px;/*1个星星的高度*/ 
    background:url(<%=path%>/public/images/star.gif) repeat-x 0 0;} 
</style> 
</head>
<body>
<div id="baseinfo">
<table>
<tr>
<td><img src="<%=path%>/public/images/food.jpg" height="100px" width="100px"/></td>
<td><label style="font-size: 26px;font-weight:bold">${CompanyInfo.companyname}</label><br><br>
<label style="font-size: 26px;font-weight:bold">综合得分：<font color="red">${CompanyInfo.score}</font>分</label><br>
</td>
</tr>
</table>
<hr color="lightgrey"/>
<label style="color: lightgrey;font-weight:bold">联系方式：025-99999999</label><br><br>
<label style="color: lightgrey;font-weight:bold">联系地址：${CompanyInfo.companyaddress}</label>
<hr color="lightgrey"/>
<label style="font-weight:bold">商家介绍：</label><br/><br/>
<label>${CompanyInfo.companyintro}</label>
<hr color="lightgrey"/>
<label style="font-weight:bold">消费记录：</label><label style="float: right;">【合计】<font color="red"> 11123 </font>元</label><br>
<label>2016年9月29日  16:00 消费<font color="red">882</font>元           【查看详情】</label>
</div>
<br>
<div class="star" style="text-align: center;"> 
<span class="vote-star"><i style="width:100%"></i></span> 
<br/><br/> 
</div> 
<div id="buttondiv" style="text-align: center;">
<input id="wannacomplain" type="button" value="我要投诉" style="width:100px;height:32px" onclick="pagejump()"/>
</div>

</body>
</html>
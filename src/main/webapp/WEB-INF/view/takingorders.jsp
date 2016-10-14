<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no">
<title>开始点菜</title>
<style type="text/css">
.dotline {
BORDER-BOTTOM-STYLE: dotted; BORDER-LEFT-STYLE: dotted; BORDER-RIGHT-STYLE: dotted; BORDER-TOP-STYLE: dotted
}
html,body {
   overflow:hidden;
   margin:0px;
   width:100%;
   height:100%;
}
#content {
    width:100%;
    height:92%;
    overflow-y:scroll;
    overflow-x:auto;
}
#footer {
	position:absolute; z-index:0;
	bottom:0; left:0;
	width:100%;
	height:40px;
	padding:0;
}

#group{
	background-image:url("<%=path%>/public/images/group.png");
	background-size:100%;
	width:60px;
	height:60px;
	margin-top:-20px;
	border:none;
	margin-right:150px;
	
}
#redShow{
	background-image:url("<%=path%>/public/images/show.png");
	background-repeat:no-repeat;
	background-size:100%;
	width:70px;
	height:70px;
	margin-left:5px;
	
}
</style>
<script type="text/javascript" src="<%=path%>/public/script/jquery-3.0.0.js"></script>
</head>
<script type="text/javascript">
$(function(){
	 
});
/**
 * 减
 */
function doSubtract(id){
	var numObj = $("#"+id+"num");
	var imgSubObj = $("#"+id+"imgSub");
	if(numObj.val()==1){
		numObj.css("display","none");
		imgSubObj.css("display","none");
	}
	numObj.val(parseInt(numObj.val())-1)
	selCount(1,id);
}
/**
 *增加
 */
function doAddOrder(id){
	var numObj = $("#"+id+"num");
	var imgSubObj = $("#"+id+"imgSub");
	if(numObj.css("display")=='none'){
		numObj.css("display","block");
	}
	if(imgSubObj.css("display")=='none'){
		imgSubObj.css("display","block");
	}
	numObj.val(parseInt(numObj.val())+1)
	selCount(0,id);
}
/**
 * 统计点菜个数
 */
var count = 0;
function selCount(flag,id){
	if(0 == flag){
		count = parseInt(count)+1;
		$("#redShow").css("display","block");
		selPrice(flag,id);
	}else{
		count = parseInt(count)-1;
		selPrice(flag,id);
		if(count == 0){
			$("#redShow").css("display","none");
		}
	}
	$("#shownum").text(count);
}
/**
 * 统计总价
 */
var allPrice = 0;
function selPrice(flag,id){
	$("#prices").css("display","block");
	var price = $("#"+id+"price").val();
	if(0 == flag){
		allPrice = parseFloat(allPrice) + parseFloat(price); 
	}else{
		allPrice = parseFloat(allPrice) - parseFloat(price); 
	}
	if(allPrice == 0){
		$("#prices").css("display","none");
	}
	$("#prices").text("合计"+Number(allPrice)+"元");
}
</script>
<body style="background-color:#E9E9E9;">
<div id="content">
	<table border="0" align="center" width="100%" style="border-collapse:collapse;" >
		<c:forEach items="${menuTypeList}" var="item" varStatus="status">
			<tr>
				<td colspan="5"><h2><c:if test="${item.menutype == '0'}">凉菜</c:if><c:if test="${item.menutype == '1'}">热菜</c:if></h2></td>
			</tr>
			<c:forEach items="${menuList}" var="item1" varStatus="status">
				<c:if test="${item.menutype ==  item1.menutype}">
					<tr style="background-color:white;">
						<td width="100px" ><img alt="" src="<%=path%>/public/images/food.png" width="150px" height="100px"></td>
						<td style="line-height:25px;">
							 <p style="line-height:20px;">${item1.menuname}</p>
							 <p style="line-height:20px;"><font style="color:#E09D2F">￥${item1.price}</font></p>
							 <p style="line-height:20px;">${item1.feature}</p>
						</td>
						<td  align="right">
							<input type="hidden" id="${item1.menuid}price" value="${item1.price}">
							<img id="${item1.menuid}imgSub" alt="减" style="display:none;"  src="<%=path%>/public/images/subtract.png"  height="32px" width="32px" onclick="javascript:doSubtract('${item1.menuid}');"/>
						</td>
						<td  align="center">
							<input id="${item1.menuid}num"  value="0" style="width:20px;border:none;text-align:center;display:none;"  readonly="readonly">
						</td>
						<td>
							<img alt="增" src="<%=path%>/public/images/add.png" height="32px" width="32px" onclick="javascript:doAddOrder('${item1.menuid}');"/>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</c:forEach>
	</table>
</div>
<div style="height:20px;"></div>
<table id="footer" border="0"  width="100%" height="35px" style="border-collapse:collapse;">
	<tr>
		<td width="80%" style="background-color:#545454;" align="center">
			<div id="group">
				<div id="redShow" style="display:none;">
					<div id="shownum"  style="font-size:25px;color:white;">0</div>
				</div>
			</div>
			<font color="#EAE9E9" id="prices" style="margin-top:-30px;width:100px;display:none;"></font>
		</td>
		<td align="center" style="background-color:#F3BE67;"><a href="javascript:jumpTo();" style="text-decoration:none;color:#654107;">下单</a></td>
	</tr>
</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    height:90%;
    overflow-y:scroll;
    overflow-x:auto;
}
#footer {
	position:absolute; z-index:2;
	bottom:0; left:0;
	width:100%;
	height:50px;
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
	countMenu(1,id);
}
/**
 *增加
 */
var json = '';
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
	countMenu(0,id);
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

/**
 * 统计菜单数量和对应ID
 */
var jsonMenu = "";
function countMenu(flag,id){
	var num = $("#"+id+"num").val();
	var text = id+":";
	if("" == jsonMenu || jsonMenu.indexOf(text)==-1){
		jsonMenu += text+num+",";
	}else{
		if(0==flag){
			var ntext = text+num;
			jsonMenu = jsonMenu.replace(text+(num-1),ntext);
		}else{
			if(num == 0){
				jsonMenu = jsonMenu.replace(text+(parseFloat(num)+1)+",","");
			}
			if(num > 0){
				var ntext = text+num;
				jsonMenu = jsonMenu.replace(text+(parseFloat(num)+1),ntext);
			}
		}
	}
	return jsonMenu;
}
/**
 * 下单
 */
function doSubmit(){
	if("" == jsonMenu){
		alert("请选择菜单!");
		return;
	}
	$("#menuJson").val(jsonMenu);
	$("#menuForm").submit();
}
</script>
<body style="background-color:#E9E9E9;">
<div id="content">
<form id="menuForm" action="<%=path%>/doAddMenu.do" method="post">
<input id="menuJson" name="menuJson" type="hidden">
<input type="hidden" name="companyCode" value="${companyCode}">
	<table border="0" align="center" width="100%"  style="border-collapse:collapse;" >
		<c:forEach items="${dictList}" var="item" varStatus="status">
				<c:set  value="" var="flag" scope="page"/>
			<c:forEach items="${menuList}" var="item1" varStatus="status">
				<c:if test="${item.code_id ==  item1.menutype && flag != item1.menutype}">
					<tr style="height:20px;line-height:5px;">
						<td colspan="5" ><h2>&nbsp;&nbsp;<c:if test="${item.code_id == '0'}">${item.codedescript}</c:if><c:if test="${item.code_id == '1'}">${item.codedescript}</c:if><c:if test="${item.code_id == '2'}">${item.codedescript}</c:if></h2></td>
					</tr> 
					<c:set  value="${item1.menutype}" var="flag" scope="page"/>
				</c:if>
				<c:if test="${item.code_id ==  item1.menutype}">
					<tr  style="background-color:white;">
						<td width="120px" ><img alt="" src="<%=path%>/${item1.path}" width="150px" height="100px"></td>
						<td style="line-height:25px;" width="80%">
							 <p style="line-height:20px;">${item1.menuname}</p>
							 <p style="line-height:20px;"><font style="color:#E09D2F">￥${item1.price}</font></p>
							 <p style="line-height:20px;" title="${item1.feature}">
							 	<c:if test="${fn:length(item1.feature)>9}">
							 		${fn:substring(item1.feature, 0, 9)}...
							 	</c:if>
							 	<c:if test="${fn:length(item1.feature)<=9}">
							 		${item1.feature}
							 	</c:if> 
							 </p>
						</td>
						<td  align="right" width="5%">
							<input type="hidden" id="${item1.menuid}price" value="${item1.price}">
							<img id="${item1.menuid}imgSub" alt="减" style="display:none;"  src="<%=path%>/public/images/subtract.png"  height="34px" width="34px" onclick="javascript:doSubtract('${item1.menuid}');"/>
						</td>
						<td  align="center"  width="5%">
							<input id="${item1.menuid}num" name="num" value="0" style="width:20px;border:none;text-align:center;display:none;"  readonly="readonly">
							<input name="menuid" type="hidden" value="item1.menuid">
						</td>
						<td align="right" width="5%">
							<img alt="增" src="<%=path%>/public/images/add.png" height="34px" width="34px"  onclick="javascript:doAddOrder('${item1.menuid}');"/>
						</td>
						<td width="15px;"></td>
					</tr>
					<tr>
						<td></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:forEach>
	</table>
</form>
</div>

<table id="footer" border="0"  width="100%" height="50px" style="border-collapse:collapse;">
	<tr>
		<td width="70%" style="background-color:#545454;" align="center">
			<div id="group">
				<div id="redShow" style="display:none;">
					<div id="shownum"  style="font-size:18px;color:white;padding-top:9%;">
					</div>
				</div>
			</div>
			<div id="prices" style="width:100px;display:none;color:#EAE9E9;margin-top:-5%;"></div>
		</td>
		<td align="center" style="background-color:#F3BE67;"><a href="javascript:doSubmit();" style="text-decoration:none;color:#654107;">下单</a></td>
	</tr>
</table>
</body>
</html>
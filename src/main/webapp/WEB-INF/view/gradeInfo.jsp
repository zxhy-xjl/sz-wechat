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
<title>${companyname}</title>
<script type="text/javascript" src="<%=path%>/public/script/jquery-3.0.0.js"></script>
<link rel="stylesheet" href="<%=path%>/public/style/lightbox.min.css" type="text/css" media="screen" />
<style type="text/css">
#head{
width:100px;
height:100px;
background:url("<%=path%>/public/images/feedbackscore2.png") no-repeat center top;
}
</style>
</head>
<script type="text/javascript">
var score = ${score}
var ratio = ${ratio}
$(function(){
	getScore();
	getPercentage();
});
/**
 * 获取分数
 */
function getScore(){
	var scoreHtml ='';
	if(score<65){
		scoreHtml = '<font size="6px" color="red">'+score+'</font><font size="4px" color="red">分</font>';
	} 
	if(score>65 && score<80){
		scoreHtml = '<font size="6px" color="#629527">'+score+'</font><font size="4px" color="#629527">分</font>';
	}
	if(score>=80){
		scoreHtml = '<font size="6px" color="#63B109">'+score+'</font><font size="4px" color="#63B109">分</font>';
	}
	$("#head").html(scoreHtml);
}
/**
 * 得到百分比
 */
function getPercentage(){
	 var msgHtml ='<font color="#333333">领先于全市<font color="red">'+ratio+'%</font>的餐厅</font>';
	$("#ratio").html(msgHtml);
}
</script>
<body style="background-color:#E9E9E9">
<div id="content">
	<div id="head" style="float:left;text-align:center;line-height:100px;margin-left:3%;">
	</div>
	<div id="ratio" style="width:65%;float:right;margin-top:10%;">
	</div>
	<div id="scoreStandard" style="width:30%;float:right;margin-top:10%;">
		<a href="<%=path%>/toScoreStandar.do" style="text-decoration:none;">评分规则</a>
	</div>
	<div id="hr" style="padding-top:35%;">
		<hr color="#B9B9B9">
	</div>
	<div id="showMsg"style="font-family:YouYuan;width:100%">
		<table border="0" align="center" width="100%" style="border-collapse:collapse;" >
			<c:forEach items="${allgradelist}"  var="item" varStatus="status">
				<tr style="padding:25px;">
					<td style="padding-left:3%">${item.allscore}</td>
				</tr>
				 <c:set var="exitId" value="0"></c:set>
				 <c:forEach  items="${gradeList}" var="item2" varStatus="status">
				 	<c:if test="${item.type eq item2.type && exitId ne 1}">
				 	 <c:set var="exitId" value="1"></c:set>
				 	</c:if>
				 </c:forEach>
				<c:forEach items="${gradeList}" var="item1" varStatus="status">
				<c:choose>
					<c:when test="${item.type == item1.type}">
						<tr style="background-color:white;height:10px;"><td colspan="3"></td></tr>
						<tr style="background-color:white">
							<td style="padding-left:3%"><c:if test="${item1.name != ''}">${item1.name}</c:if><c:if test="${item1.error != ''}">${item1.error}</c:if></td>
							<td>-${item1.score}</td>
							<td align="right">${item1.result}</td>
						</tr>
						<tr style="background-color:white;height:10px;"><td colspan="3"></td></tr>
					</c:when>
				</c:choose>
				</c:forEach>
				<tr height="10px"><td></td></tr>
				<c:if test="${exitId eq 0}">
					<tr style="background-color:white;"height="10px"><td colspan="3" style="padding-left:3%">该企业暂无相关数据</td></tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
</div>
</body>
<script src="<%=path%>/public/script/lightbox-plus-jquery.min.js" type="text/javascript"></script>
</html>
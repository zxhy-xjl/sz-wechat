<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
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
		scoreHtml = '<font size="6px" color="red">'+score+'分</font>';
	} 
	if(score>65 && score<80){
		scoreHtml = '<font size="6px" color="#629527">'+score+'分</font>';
	}
	if(score>80){
		scoreHtml = '<font size="6px" color="#63B109">'+score+'分</font>';
	}
	$("#head").html(scoreHtml);
}
/**
 * 得到百分比
 */
function getPercentage(){
	var msgHtml = '';
	msgHtml ='<font color="#333333">领先于全市<font color="red">'+ratio+'%</font>的餐厅</font>';
	$("#ratio").html(msgHtml);
}
</script>
<body style="background-color:#E9E9E9">
<div id="content">
	<div id="head" style="float:left;text-align:center;line-height:100px;margin-left:3%">
	</div>
	<div id="ratio" style="width:500px;float:right;margin-right:50%;margin-top:3%;">
	</div>
	<div id="hr" style="padding-top:8%;">
		<hr color="#B9B9B9">
	</div>
</div>
</body>
</html>
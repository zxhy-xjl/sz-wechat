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
<title>${CompanyInfo.companyname}</title>
<link rel="stylesheet" href="<%=path%>/public/style/weui.css"/>
<script type="text/javascript" src="<%=path%>/public/script/jquery-3.0.0.js"></script>
<style type="text/css">
#score{
width:250px;
height:250px;
margin:0px auto;
background:url("<%=path%>/public/images/feedbackscore.png") no-repeat center top;
}
span{
text-align:center; 

}
</style>
</head>
<script type="text/javascript">
/**
 * 初始化
 */
$(function(){
	getPercentage();
	getScore();

});

/**
 * 得到占比
 */
 var ratio = 0;
function getPercentage(){
	var msgHtml = '';
	$.ajax({
		type:'post',
		url:'<%=path%>/proportionCompany.do?companyCode=${CompanyInfo.companycode}',
		success:function(data){
			if(data){
				ratio = data.result;
				msgHtml ='<font color="#333333">领先于全市<font color="red">'+ratio+'%</font>的餐厅</font>';
				$("#showMsg").html(msgHtml);
				$("#ratio").val(ratio);
			}
		}
	});
}
/**
 * 获取积分
 */
var score = 0;
var listObj;
function getScore(){
	var scoreHtml ='';
	$.ajax({
		type:'post',
		url:'<%=path%>/superviseScore.do?companyCode=${CompanyInfo.companycode}',
		success:function(data){
			if(data){
				score = data.result;
				if(score<65){
					scoreHtml = '<font style="font-size:78px" color="red">'+score+'分</font>';
				}
				if(score>65 && score<80){
					scoreHtml = '<font style="font-size:78px" color="#629527">'+score+'分</font>';
				}
				if(score>80){
					scoreHtml = '<font style="font-size:78px" color="#63B109">'+score+'分</font>';
				}
				$("#score").html(scoreHtml);
				$("#scoreinput").val(score);
			}
		}
	});
}
/**
 * 跳转至点菜页面
 */
function toTakingOrder(){
	window.location.href="<%=path%>/toTakingOrder.do?companyCode=${CompanyInfo.companycode}";
}
/**
 * 跳转至评分详细页面
 */
function toGradeInfo(){
	$("#scoreForm").submit();
}
</script>
<body style="background-color:#E9E9E9">
<form id="scoreForm" action="<%=path%>/toGradeInfo.do" method="post" >
<input id="scoreinput" name="score" type="hidden">
<input id="companyname" name="companyname" type="hidden" value="${CompanyInfo.companyname}">
<input id="ratio" name="ratio" type="hidden">
<input id="listObj" name="listObj"  type="hidden">
</form>
<div id="content" style="height: 100%">
	<div style="height:50px"></div>
	<div id="score" style="text-align:center;line-height:230px;">
	</div>
	<div id="info" style="text-align:center;line-height:50px;">
		<a href="javascript:toGradeInfo()" style="font-family:'楷体','楷体_GB2312';font-size:20px;text-decoration:none;color:#4B4B4B">点击查看详情</a>
	</div> 
	<div id="showMsg" style="text-align:center;line-height:150px;">
	</div>
	<div class="button-sp-area" style="text-align:center;"> 
         <a href="javascript:toTakingOrder();" class="weui-btn weui-btn_mini weui-btn_warn" style="background-color:#F3BE67;width:150px;height:30px">去点餐</a> 
    </div> 
</div>
</body>
</html>
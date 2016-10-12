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
	getScore();
});

var score = 0;
function getScore(){
	var scoreHtml ='';
	var msgHtml = '';
	$.ajax({
		type:'post',
		url:'<%=path%>/superviseScore.do?companyCode=${CompanyInfo.companycode}',
		success:function(data){
			if(data){
				var score = data.result;
				if(score<65){
					scoreHtml = '<font size="14px" color="red">'+data.result+'分</font>';
					msgHtml ='<font color="#333333">您选择的店铺评分高于南京市<font color="red">20%</font>商家</font>';
				}
				if(score>65 && score<80){
					scoreHtml = '<font size="14px" color="#629527">'+data.result+'分</font>';
					msgHtml ='<font color="#333333">您选择的店铺评分高于南京市<font color="red">70%</font>商家</font>';
				}
				if(score>80){
					scoreHtml = '<font size="14px" color="#63B109">'+data.result+'分</font>';
					msgHtml ='<font color="#333333">您选择的店铺评分高于南京市<font color="red">90%</font>商家</font>';
				}
				$("#score").html(scoreHtml);
				$("#showMsg").html(msgHtml);
			}
		}
	});
}
</script>
<body style="background-color:#E9E9E9;">
<div id="content" style="height: 100%">
	<div id="heard" style="text-align:center;font-family:'楷体','楷体_GB2312';font-size:20px;line-height:100px;">
		此餐厅卫生得分
	</div>
	<div id="score" style="text-align:center;line-height:230px;">
	</div>
	<div id="info" style="text-align:center;line-height:50px;">
		<a href="#" style="font-family:'楷体','楷体_GB2312';font-size:24px;text-decoration:none;color:#4B4B4B">点击查看详情</a>
	</div>
	<div id="showMsg" style="text-align:center;line-height:150px;">
	</div>
	<div id="btn" style="text-align:center;line-height:150px;">
		<input id="" type="button" value="去点餐" style="background-color:#F5A623;border:0;width:100px;height:30px;" onclick="javascript:jumpTo()">	
	</div>
</div>
</body>
</html>
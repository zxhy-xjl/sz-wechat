<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>投诉</title>

<style type="text/css">

html{
font-family: SimHei;
}
textarea{
font-family: SimHei;
}
</style>

</head>
<body style="background-color:#E9E9E9;">
<div id="baseinfo">
<label>投诉餐厅： ${companyname}</label><br><br>
<label>投诉内容：</label>

<%-- <img onclick="takephoto();" src="<%=path%>/public/images/camera.jpg" style="width:40px;height:30px;float: right;"/> --%><br>
<textarea id="complaintcontent" rows="10" cols="40" style="width:100%;margin:0 auto;">服务态度非常恶劣，简直无法形容。</textarea><br>
<input type="file" accept="image/*"  capture="camera" ><br>
<br>
</div>
<div id="shadowdiv" style="text-align: center;">

<input type="button" value="投诉" onclick="finishcomplaint(' ${companyname}','${companycode}')" style="background: #f3be67;width:90px;height:40px;font-family: SimHei;font-weight: bold;font-size: 15px">
</div>
<script type="text/javascript">
var flag = null;
window.onload=function()//用window的onload事件，窗体加载完毕的时候
{
	flag = "<%=request.getParameter("flag")%>";
	if(flag==1)
		document.getElementById("shadowdiv").style.display="block";
}


var video=document.getElementById("video");
var context= null; 
var errocb=function(){
	console.log("sth srong");
}



function takephoto(){
	context = canvas.getContext("2d");
	if(navigator.getUserMedia){
		navigator.getUserMedia({"video":true},function(stream){
			video.src=stream;
			video.play();
		},errocb);
	}else if(navigator.webkitGetUserMedia){
		navigator.webkitGetUserMedia({"video":true},function(stream){
			video.src=window.webkitURL.createObjectURL(stream);
			video.play();
		},errocb);
	}
	
}

function photo()
{
	context.drawImage(video,0,0,64,48);

	}
function finishcomplaint(companyname,companycode)
{
	var complaintcontent = document.getElementById("complaintcontent").value;
	
	window.location.href="<%=path%>/doInsertComplaint.do?complaintcontent="+complaintcontent+"&companyname="+companyname+"&companycode="+companycode;
	
	}

</script>
</body>
</html>
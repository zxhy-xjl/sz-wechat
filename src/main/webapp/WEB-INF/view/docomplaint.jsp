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
<form id="complainForm" action="<%=path%>/doInsertComplaint.do" method="post" enctype="multipart/form-data">
<input type="hidden" name="companyname" value="${companyname}">
<input type="hidden" name="companycode" value="${companycode}">
<input type="hidden" name="evaluate" value="${evaluate}">
<input type="hidden" name="footprintpid" value="${footprintpid}">

<%-- <img onclick="takephoto();" src="<%=path%>/public/images/camera.jpg" style="width:40px;height:30px;float: right;"/> --%><br>
<textarea id="complaintcontent" name="complaintcontent" rows="10" cols="40" style="width:100%;margin:0 auto;" placeholder="请输入投诉信息"></textarea><br>
<div id="photodiv">
<input onchange="read();" type="file" id="photofile" name="camera" accept="image/*"  capture="camera" ><br>
</div>
<br>
<div style="text-align: center;">
<input  type="submit" value="投诉"  style="background: #f3be67;width:90px;height:40px;font-family: SimHei;font-weight: bold;font-size: 15px">
</div>
</form>


</div>



<script type="text/javascript">
var flag = null;
var execflag = null;
window.onload=function()//用window的onload事件，窗体加载完毕的时候
{
/* 	if(""!="${complaintcontent}"){
		document.getElementById("complaintcontent").value="${complaintcontent}";
	} */
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
	
	window.location.href="<%=path%>/doInsertComplaint.do?complaintcontent="+complaintcontent+"&companyname="+companyname+"&companycode="+companycode+"&evaluate=${evaluate}";
	
	}

function read(){
    var fs=document.getElementById('photofile').files[0];
    if(fs){
        var reader=new FileReader();
        reader.readAsDataURL(fs);
        function li(str){
            var obj=document.createElement('li');
            obj.innerHTML=str;
            document.getElementById("photodiv").appendChild(obj);
        }
        reader.onloadstart=function(){
           // li('开始读取')
        }
        reader.onprogress=function(){
           // li('正在读取.....')
        }
        reader.onload=function(e){
        	if(execflag==null)
        	{ var img=document.createElement('img');
            img.src=this.result;
            img.style.width = "50%";
            document.getElementById("photodiv").appendChild(img);
            execflag="true";
        	}
        	else
        		{
        		document.getElementById("photodiv").children[2].src = this.result;
        		document.getElementById("photodiv").children[2].style.width = "50%";
        		
        		}
        }
        reader.onabort=function(){
            li('读取中断！！')     
        }
        reader.onerror=function(){
            li('读取出现错误！！')
        }
        reader.onloadend=function(){
          //  li('FileReader读取步骤执行完毕')
        }
    }
}


</script>
</body>
</html>
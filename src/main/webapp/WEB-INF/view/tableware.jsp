<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	HttpSession session = request.getSession(); 
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no">
<title>餐具信息</title>
<link rel="stylesheet" href="<%=path%>/public/style/weui.css"/>
<script type="text/javascript" src="<%=path%>/public/script/jweixin-1.1.0.js"></script>
<script type="text/javascript" src="<%=path%>/public/script/jquery-3.0.0.js"></script>
<link rel="stylesheet" href="<%=path%>/public/style/lightbox.min.css" type="text/css" media="screen" />
</head>
<script type="text/javascript">
	var xmlHttpRequest = null;
	$(function(){
		<%
		String code = (String)session.getAttribute("code");
		String open_code = (String)session.getAttribute("open_code");
		%>
		setOpenid();
	});
	/**
	 * 初始化加载
	 */
	function onloadScore(){
		var score ="${score}";
		$("img").each(function(){
			var id = $(this).attr("id");
			if(typeof(id) != "undefined" &&id.substring(id.length-1,id.length) <=score){
				$(this).attr('src','<%=path%>/public/images/star_on.png');
				$(this).attr('alt','1');
			}
		});
	}
	/**
	 * 打分
	 */
	function doScore(obj){
		var id = obj.id;
		var size = id.substring(id.length-1,id.length);
		$("#star").val(size);
		if(obj.alt == 0){
			$("img").each(function(){
				if($(this).attr("id").substring(id.length-1,id.length) <=size){
					$(this).attr('src','<%=path%>/public/images/star_on.png');
					$(this).attr('alt','1');
				}
			});
		}else{
			$("img").each(function(){
				 if($(this).attr("id").substring(id.length-1,id.length) >= size){
					 $(this).attr('src','<%=path%>/public/images/star1.png');
					$(this).attr('alt','0');
				 }
			});
		}
		 
	}
	function doSubmit(){
		window.location.href="<%=path%>/toTakingOrder.do";
	}
	
	function tots(){
		var codeObj = $("#codei");
		if(codeObj.val() == "null" ){
			$("#complaintForm").submit();
		}else{
			window.location.href="<%=path%>/toComplain.do?company="+codeObj.val()+"&companyname=${CompanyInfo.companyname}&complaintcontent="+$("#textare").val()+"&evaluate="+$("#star").val()+"&flag=2";
		}
	}
	/**
	 * 回掉方法
	 */
	function backHttp(){
		if(xmlHttpRequest.readyState == 4 ) {  		//完全得到服务器的响应
			if(xmlHttpRequest.status == 200) {		//没有异常
				text = xmlHttpRequest.responseText;
				var json = eval('(' + text + ')'); 
				$.ajax({
					type:'post',
					url:'<%=path%>/setOpenId.do?openid='+json.openid,
					success:function(){}
				});
				onloadScore();
			} 
  		}
	}
	/**
	 *  存入openid
	 */
	function setOpenid(){
		var url="<%=path%>/Oauth2Servlet.do?code=<%=open_code%>";
		if(window.ActiveXObject) {   			//IE的
			xmlHttpRequest = new ActionXObject("Microsoft.XMLHTTP");
		}
		else if(window.XMLHttpRequest) {		//除IE外的
			xmlHttpRequest = new XMLHttpRequest();
		}
		if(xmlHttpRequest != null) {
			xmlHttpRequest.open("GET", url, true);
			//xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			//关联好ajax的回调函数
			xmlHttpRequest.onreadystatechange = backHttp;
			//真正向服务器发送请求
			xmlHttpRequest.send();
		} 
	}
</script>
<body style="background-color:#E9E9E9">
<form action="<%=path%>/doInsertComplaint.do" method="post" id="complaintForm">
<input id="codei" type="hidden" value="<%=code%>">
<input id="star" name="evaluate" type="hidden" value="0"/>
<input name="companyname" value="${CompanyInfo.companyname}" type="hidden">
<input name="companycode" value="${CompanyInfo.companycode}" type="hidden">
<div id="content">
	<div>
		<table border="0" width="100%" align="center">
			<tr>
				<td align="right" width="20%">企业名称：</td>
				<td>${CompanyInfo.companyname}</td>
			</tr>
			<tr>
				<td align="right">联系方式：</td>
				<td>${CompanyInfo.lxfs}</td>
			</tr>
			<tr>
				<td align="right" >公司地址：</td>
				<td>${CompanyInfo.companyaddress}</td>
			</tr>
			<tr>
				<td id="stars" colspan="2" align="center" style="line-height:150px;bottom:0px;">
					<img id="star1" onclick="javascript:doScore(this)" alt="0" src="<%=path%>/public/images/star1.png" width="56" height="53">
					<img id="star2" onclick="javascript:doScore(this)" alt="0" src="<%=path%>/public/images/star1.png" width="56" height="53">
					<img id="star3" onclick="javascript:doScore(this)" alt="0" src="<%=path%>/public/images/star1.png" width="56" height="53">
					<img id="star4" onclick="javascript:doScore(this)" alt="0" src="<%=path%>/public/images/star1.png" width="56" height="53">
					<img id="star5" onclick="javascript:doScore(this)" alt="0" src="<%=path%>/public/images/star1.png" width="56" height="53">
				</td>
			</tr>
			<tr>
				<td colspan="2" >
					<div style="margin-left:5%">
						<p>
						<a class="example-image-link" href="http://218.242.124.22:8081/businessCheck/viewLicence_view_20151215.do?attribute13=${CompanyInfo.companyrecode}" data-lightbox="example-set" data-title="${CompanyInfo.companyname}" style="text-decoration:none;" >1、营业执照</a>
						</p>
						<p><a href="<%=path%>/getPublicInfo.do?repastlicence=${CompanyInfo.licence}" style="text-decoration:none;">2、餐饮许可证</a></p>
						<p><a href="<%=path%>/getSuperviseByCompanyName.do?companyname=${CompanyInfo.companyname}">3、行政处罚单</a></p>
						<p><a href="<%=path%>/getComplaintByOpenid.do?companyCode=${CompanyInfo.companycode}&companyName=${CompanyInfo.companyname}">4、投诉</a></p>
						<!-- <p>5、评论</p> -->	
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2"  align="center">
					<div>
						<textarea id="textare" name="complaintcontent" rows="6" cols="55" placeholder="你想说点啥" ></textarea>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<input type="file" accept="image/*"  capture="camera" style="width:100%" >
					<%-- <img alt="" src="<%=path%>/public/images/photo.png" onclick="javascript:text()" width="43" height="42" style="margin-left:-25%" > --%>
				</td>
				<td align="right">
					<img alt="" src="<%=path%>/public/images/sub.png" onclick="javascript:tots();" width="85" height="42" style="margin-right:5%">
				</td>
			</tr>
		</table>
	</div>
	<div class="button-sp-area" style="text-align:center;margin-top:5%"> 
         <a href="javascript:doSubmit();" class="weui-btn weui-btn_mini weui-btn_warn" style="background-color:#F3BE67;width:150px;height:30px;">继续点餐</a> 
    </div> 
</div>
</form>
</body>
<script src="<%=path%>/public/script/lightbox-plus-jquery.min.js" type="text/javascript"></script>
</html>
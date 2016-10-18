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
<title>餐具信息</title>
<link rel="stylesheet" href="<%=path%>/public/style/weui.css"/>
<script type="text/javascript" src="<%=path%>/public/script/jquery-3.0.0.js"></script>
</head>
<script type="text/javascript">
	function doScore(obj){
		var id = obj.id;
		var size = id.substring(id.length-1,id.length);
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
		window.location.href="<%=path%>/scanCode.jsp?flag=1";
	}
</script>
<body style="background-color:#E9E9E9">
<div id="content">
	<div>
		<table border="0" width="100%" align="center">
			<tr>
				<td align="right">企业名称：</td>
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
						<p>1、工商营业执照</p>
						<p>2、卫生许可证cc
						<p>3、行政处罚单</p>
						<p>4、投诉</p>
						<p>5、评论</p>	
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2"  align="center">
					<div>
						<textarea rows="6" cols="55" placeholder="你想说点啥" ></textarea>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<img alt="" src="<%=path%>/public/images/photo.png" width="43" height="42" style="margin-left:-25%" >
				</td>
				<td align="right">
					<img alt="" src="<%=path%>/public/images/sub.png" width="85" height="42" style="margin-right:5%">
				</td>
			</tr>
		</table>
	</div>
	<div class="button-sp-area" style="text-align:center;margin-top:5%"> 
         <a href="javascript:doSubmit();" class="weui-btn weui-btn_mini weui-btn_warn" style="background-color:#F3BE67;width:150px;height:30px;">继续点餐</a> 
    </div> 
</div>
</body>
</html>
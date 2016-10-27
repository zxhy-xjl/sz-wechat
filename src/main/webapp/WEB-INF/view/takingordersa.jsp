<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no">
<title>结账</title>
<!--标准mui.css-->
<link rel="stylesheet" href="<%=path%>/public/style/mui.min.css">
<link rel="stylesheet" href="<%=path%>/public/style/weui.css"/>
<script type="text/javascript" src="<%=path%>/public/script/jquery-3.0.0.js"></script>
<style type="text/css">
.dotline {
BORDER-BOTTOM-STYLE: dotted; BORDER-LEFT-STYLE: dotted; BORDER-RIGHT-STYLE: dotted; BORDER-TOP-STYLE: dotted
}
</style>
</head>
<script type="text/javascript">
	$(function(){
		if(""!="${billunit}"){
			$("#invoicetype").attr("checked",true);
		}
	});
	function doSubmit(){
		var cashpay = $("#cashpay").val();
		var wechatpay = $("#wechatpay").val();
		var allpay = $("#allpay").val();
		if("" == cashpay && "" ==wechatpay && ""==allpay){
			alert("请选择支付方式!");
			return;
		}
		 $("#payForm").submit();
	}
</script>
<body style="background-color:#E9E9E9;">
<form action="<%=path%>/doPay.do" id="payForm" method="post">
<input type="hidden" name="oddNumber" value="${oddNumber}">
<input type="hidden" name="allprice" value="${allPrice}">
<div id="content">
	<div style="width:200px;margin-left:20px;"><h2>订单概要:</h2></div>
	<div id="dd" style="background-color:white;text-align:center;">
		<table border="0" align="center" width="100%"  style="border-collapse:collapse;">
			<c:forEach items="${menuList}" var="item" varStatus="status">
				<tr>
					<td width="30%">${item.menuname}</td>
					<td>*${item.menunum}</td>
					<td>${item.price}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="3"><hr width="95%" color="#C8C7CC" size="1"></td>
			</tr>
			<tr>
				<td colspan="3" align="right">合计:${allPrice}元</td>
			</tr>
		</table>
	</div>
	<div style="width:200px;margin-left:20px;"><h2>付款方式:</h2></div>
	<div>
		<div class="mui-card" style="width:100%;">
			<form class="mui-input-group">
				<div class="mui-input-row mui-radio">
					<label>现金</label>
					<input id="cashpay" name="paytype" type="radio" value="0">
				</div>
				<div class="mui-input-row mui-radio">
					<label>微信</label>
					<input id="wechatpay" name="paytype" type="radio" value="1" checked>
				</div>
				<!-- <div class="mui-input-row mui-radio ">
					<label>支付宝</label>
					<input id="allpay" name="paytype" type="radio" value="2">
				</div> -->
			</form>
		</div>
	</div>
	<div style="width:200px;margin-left:20px;"><h2>开发票:</h2></div>
	<div style="background-color:white;line-height:17px;color:#333;text-align:center;">
		<table border="0" align="center" width="98%"  style="border-collapse:collapse;">
			<tr style="line-height:50px;">
				<td width="80%" align="left">
				<div style="margin-left:7%">
					开票单位：<input id="invoice" name="invoice" value="${billunit}" type="text" style="width:60%;" >				
				</div>
				</td>
				<td width="48%"></td>
				<td colspan="1"></td>
			</tr>
			<tr>
				<td align="left">
					<div style="margin-left:5%">
						<label>默认添加</label>
						<input id="invoicetype" name="invoicetype" value="1" type="checkbox">
					</div>
				</td>
				<td width="60%"></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
	<div class="button-sp-area" style="text-align:center;margin-top:10%"> 
         <a href="javascript:doSubmit();" class="weui-btn weui-btn_mini weui-btn_warn" style="background-color:#F3BE67;width:150px;height:30px;">确定</a> 
    </div> 
</div>
</form>
</body>
</html>
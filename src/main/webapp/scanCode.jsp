<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>扫一扫</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<link rel="stylesheet" href="<%=path%>/style/mui.min.css">
<script type="text/javascript">
function getCode(){
	  $.ajax({
		type:'post',
		url:'<%=path%>/scanCode/sign.do',
		success:function(data){
			if(data.result){
				var obj = data.result;
				$("#timestamp").val(obj.timestamp);
				$("#nonceStr").val(obj.nonce);
				$("#signature").val(obj.signature);
				getConfig();
			}
		}
	});  
}

function getConfig(){
	wx.config({
		debug:false,
		appId:'wx4203dd1ae2c80664',
		timestamp:$("#timestamp").val(),
		nonceStr:$("#nonceStr").val(),
		signature:$("#signature").val(),
	    jsApiList: ['scanQRCode']
	}); 
}
wx.ready(function(){
	getCode();
	  wx.scanQRCode({
	        // 默认为0，扫描结果由微信处理，1则直接返回扫描结果
	        needResult : 1,
	        desc : 'scanQRCode desc',
	        success : function(res) {
	            //扫码后获取结果参数赋值给Input
	            var url = res.resultStr;
	            //商品条形码，取","后面的
	            if(url.indexOf(",")>=0){
	                var tempArray = url.split(',');
	                var tempNum = tempArray[1];
	                $("#id_securityCode_input").val(tempNum);
	            }else{
	                $("#id_securityCode_input").val(url);
	            }
	        }
	    });
});
wx.error(function(res){
	alert(res.errMsg)
})
</script>
</head>
<body>
<input type="hidden" id="timestamp">
<input type="hidden" id="nonceStr">
<input type="hidden" id="signature">
<div class="mui-input-row">
	<input id='id_securityCode_input' type="text" class="mui-input-clear mui-input">
</div>
</body>
</html>
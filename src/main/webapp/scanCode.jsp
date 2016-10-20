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
<meta name=”viewport” content=”width=device-width, initial-scale=1″ />
<title>扫一扫</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
var flag = 0 ;
$(function(){
	flag = GetRequest();
	getCode();
});
function GetRequest() { 
	var url = location.search; //获取url中"?"符后的字串 
	var theRequest = new Object(); 
	if (url.indexOf("?") != -1) { 
		var str = url.substr(1); 
		strs = str.split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
		theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
		} 
	} 
	return theRequest; 
} 
/**
 * 获取到扫一扫参数
 */
function getCode(){ 
	  $.ajax({
		type:'post',
		url:'<%=path%>/scanCode/sign.do?flag='+flag['flag'],
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

/**
 *设置扫一扫参数
 */
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
/**
 * 调用扫一扫接口
 */
wx.ready(function(){
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
	            
	            if(3 == flag['flag']){
	            	window.location.href="<%=path%>/toComplain.do?companycode="+$('#id_securityCode_input').val()+"&flag=2";
	            	return;
	            }
	            if(1 == flag['flag']){
	            	 window.location.href="<%=path%>/checkScanCode.do?company="+$('#id_securityCode_input').val();
	            	 return;
	            }
	            if(2 == flag['flag']){}{
	            	 window.location.href="<%=path%>/checkScanCodeTableWare.do?company="+$('#id_securityCode_input').val();
	            	 return;
	            }
	        }
	    });
});
/**
 * 弹出异常
 */
wx.error(function(res){
	alert(res.errMsg)
})
</script>
</head>
<body>
<input type="hidden" id="timestamp">
<input type="hidden" id="nonceStr">
<input type="hidden" id="signature">
<input id='id_securityCode_input' type="hidden" >
</body>
</html>
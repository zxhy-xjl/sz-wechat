<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	HttpSession session = request.getSession(); 
	String code = (String)session.getAttribute("code");
	String open_code = (String)session.getAttribute("open_code");
%>
<html>
<head>
    <title></title>
  
    <script src="<%=path%>/public/script/qrcode.js"></script>
    <style>
        #qrcode{
            /*text-align: center;*/
            /*display: table-cell;*/
            /*width: 96px;*/
            /*height: 96px;*/
            /*vertical-align:middle;*/
            /*position: relative;*/
        }
    </style>
</head>
<body>
<div id="qrcode">
</div>

<input type="text" id="getval"/> <button id="send">点击更换验证码</button>
<script>
    window.onload =function(){
        var qrcode = new QRCode(document.getElementById("qrcode"), {
            width : 96,//设置宽高
            height : 96
        });
        qrcode.makeCode("http://www.baidu.com");
        document.getElementById("send").onclick =function(){
            qrcode.makeCode(document.getElementById("getval").value);
        }
    }


</script>
</body>
</html>
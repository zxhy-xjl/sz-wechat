<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>评分标准</title>
<style type="text/css">
#content p{
     width:auto;
     margin: 5px;
     padding: 5px;
     word-wrap: break-word;
}
</style>
</head>
<body>
<div id="content">
<p>
评分采用百分制，由资质、处罚、
投诉、评价（餐饮安全等级和百姓评星）四部分构成。<br/>
缺少信用代码证（营业执照）、食品经营许可证（餐饮服务许可证）一项扣30分，每一条处罚（警告1分、罚款2分、没收财物3分、停产停业4分、暂扣或吊销执照5分）
分别扣除相应分数，每条投诉扣2分，餐饮食品安全等级为C（哭脸）扣1分，大众评星少于3颗星扣1到2分。
</p>
</div>
</body>
</html>
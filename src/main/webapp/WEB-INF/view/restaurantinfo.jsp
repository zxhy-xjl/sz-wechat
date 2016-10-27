<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>餐厅详情</title>
<link rel="stylesheet" href="<%=path%>/public/style/weui.css"/>

<script type="text/javascript" src="<%=path%>/public/script/jquery-3.0.0.js"></script>
</head>
<script type="text/javascript">

function doScore(obj){
	var id = obj.id;
	var size = id.substring(id.length-1,id.length);
	if(obj.alt == 0){
		$('#stars img').each(function(){
			if($(this).attr("id").substring(id.length-1,id.length) <=size){
				$(this).attr('src','<%=path%>/public/images/star_on.png');
				$(this).attr('alt','1');
			}
		});
	}else{
		$('#stars img').each(function(){
			 if($(this).attr("id").substring(id.length-1,id.length) >= size){
				 $(this).attr('src','<%=path%>/public/images/star1.png');
				$(this).attr('alt','0');
			 }
		});
	}
	 
}

function pagejump(companyname,companycode,footprintpid)
{
	console.log(companyname);
	
	window.location.href="<%=path%>/toComplain.do?companyname="+companyname+"&companycode="+companycode+"&footprintpid"+footprintpid;  		
}


function detailsinfo(oddNumber){
	
	
	console.log(oddNumber);
	window.location.href="<%=path%>/toMenuView.do?oddNumber="+oddNumber+"&flag=0";
	
	
}

function lookcomplaint(pid,companyname)
{
	
	window.location.href="<%=path%>/lookuserinfo.do?pid="+pid+"&companyname="+companyname; 
	
	}

</script>


<body style="font-family: SimHei">
<div id="baseinfo">
<table>
<tr>
<td><img src="<%=path%>/public/images/food.jpg" height="100px" width="100px"/> </td>
<td><label style="font-size: 26px;font-weight:bold">${CompanyInfo.companyname}</label><br><br>
<label style="font-size: 26px;font-weight:bold">综合得分：<font color="red">${CompanyInfo.score}</font>分</label><br>
</td>
</tr>
</table>
<hr color="lightgrey"/>
<label style="color: lightgrey;font-weight:bold">联系方式：025-99999999</label><br><br>
<label style="color: lightgrey;font-weight:bold">联系地址：${CompanyInfo.companyaddress}</label>
<hr color="lightgrey"/>
<label style="font-weight:bold">商家介绍：</label><br/><br/>
<label>${CompanyInfo.companyintro}</label>
<hr color="lightgrey"/>
<label style="font-weight:bold">消费记录：</label><label style="float: right;">【合计】<font color="red"> ${totalprice} </font>元</label><br>
<label>${fn:substring(paytime,0,4)}年${fn:substring(paytime,4,6)}月${fn:substring(paytime,6,8)}日  ${fn:substring(paytime,8,10)}:${fn:substring(paytime,10,12)} 消费<font color="red">${price}</font>元    </label><label onclick="detailsinfo('${oddnumber}')"><font color="red" style="font-weight: bolder;" ><u>查看详情</u></font></label>
</div>
<br>
<div id="stars" style="text-align: center;"> 

<img id="star1" onclick="doScore(this)" alt="0" src="<%=path%>/public/images/star1.png" width="56" height="53">
<img id="star2" onclick="doScore(this)" alt="0" src="<%=path%>/public/images/star1.png" width="56" height="53">
<img id="star3" onclick="doScore(this)" alt="0" src="<%=path%>/public/images/star1.png" width="56" height="53">
<img id="star4" onclick="doScore(this)" alt="0" src="<%=path%>/public/images/star1.png" width="56" height="53">
<img id="star5" onclick="doScore(this)" alt="0" src="<%=path%>/public/images/star1.png" width="56" height="53">

</div> 
<div id="buttondiv" style="text-align: center;">
 <c:if test="${complainflag== '1'}"> 
<input id="wannacomplain" type="button" value="我要投诉" style="background: #f3be67;width:90px;height:40px;font-family: SimHei;font-weight: bold;font-size: 15px" onclick="pagejump('${CompanyInfo.companyname}','${CompanyInfo.companycode}','${pid}')"/>
</c:if>
 <c:if test="${complainflag== '0'}"> 
<input id="wannacomplain" type="button" value="我要投诉" style="background: #f3be67;width:90px;height:40px;font-family: SimHei;font-weight: bold;font-size: 15px" onclick="pagejump('${CompanyInfo.companyname}','${CompanyInfo.companycode}','${pid}')"/>
</c:if>
<c:if test="${complainflag== '2' }">
<input id="historycomplain" type="button" value="查看投诉详情" style="background: #f3be67;width:100px;height:40px;font-family: SimHei;font-weight: bold;font-size: 15px" onclick="lookcomplaint('${complaintpid}','${CompanyInfo.companyname}')"/>
</c:if>
</div>

</body>
</html>
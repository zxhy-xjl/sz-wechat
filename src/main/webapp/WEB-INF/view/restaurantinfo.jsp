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

<style type="text/css">
#score{
width:150px;
height:85px;
margin:0px auto;
background:url("<%=path%>/public/images/feedbackscore.png") no-repeat center top;
background-size:85px;
}
span{
text-align:center; 

}
</style>
<script type="text/javascript" src="<%=path%>/public/script/jquery-3.0.0.js"></script>
</head>
<script type="text/javascript">

 
/**
 * 星星
 */
function doStar(obj){
	if (!obj){
		return;
	}
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


<body style="font-family: SimHei;background-color: #e9e9e9;">
<label style="font-size: 36px;font-weight:bold">${companyInfo.companyname}</label>
<div id="baseinfo">
<table>
<tr>
<td><img src="<%=path%>/public/images/food.jpg" height="100px" width="100px"/></td>
<td width="30%">
<div id="score" style="text-align:center;line-height:220px;position:relative;top:0px;">
		<font style="font-size:40px;position: relative;bottom: 65px;" color="red">&nbsp;${score}</font>
		<font style="font-size:18px;position: relative;bottom: 65px;" color="red">分</font>
	</div>
 </td>
 <td width="80%">
<table width="100%"><tr><td>
 <label style="font-weight:bold">扫桌次数：${scanCount}次</label>
</td></tr><tr><td>
<div id="stars"> 
<img id="star1" onclick="doStar(this)" alt="0" src="<%=path%>/public/images/star1.png" width="14" height="13">
<img id="star2" onclick="doStar(this)" alt="0" src="<%=path%>/public/images/star1.png" width="14" height="13">
<img id="star3" onclick="doStar(this)" alt="0" src="<%=path%>/public/images/star1.png" width="14" height="13">
<img id="star4" onclick="doStar(this)" alt="0" src="<%=path%>/public/images/star1.png" width="14" height="13">
<img id="star5" onclick="doStar(this)" alt="0" src="<%=path%>/public/images/star1.png" width="14" height="13">

</div> 
</td></tr></table>


 </td>
</tr>
</table>
<label style="font-weight:bold">${companyInfo.companyaddress}  &nbsp;<a href="tel:${companyInfo.lxfs}">${companyInfo.lxfs}</a></label>
<hr color="lightgrey"/>
<label style="font-weight:bold;font-size: 20px;">
<a href="http://218.242.124.22:8081/businessCheck/viewLicence_view_20151215.do?attribute13=${companyInfo.companyrecode}" data-lightbox="example-set" data-title="${companyInfo.companyname}" style="text-decoration:none;" >营业执照</a>
</label>
<img align="top" onclick="" alt="0" src="<%=path%>/public/images/success.png" width="28" height="26">

<c:if test="${companyInfo.licence==null}">
<label style="font-weight:bold;font-size: 20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
餐饮许可证
</label>
<img align="top" onclick="" alt="0" src="<%=path%>/public/images/close.png" width="28" height="26">
</c:if>
<c:if test="${companyInfo.licence!=null}">
<label style="font-weight:bold;font-size: 20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<%=path%>/getPublicInfo.do?repastlicence=${companyInfo.licence}" style="text-decoration:none;">餐饮许可证</a>
</label>
<img align="top" onclick="" alt="0" src="<%=path%>/public/images/success.png" width="28" height="26">
</c:if>

<br>
<label style="font-weight:bold;font-size: 20px;">&nbsp;&nbsp;
<c:if test="${personHealthCount > 0}">
<a href="<%=path%>/personHealthList.do?companyCode=${companyInfo.companycode}">健康证</a>
</c:if>
<c:if test="${personHealthCount  == 0}">
健康证
</c:if>
&nbsp; ${personHealthCount }个</label><label style="font-weight:bold;font-size: 20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;食品安全等级</label>
&nbsp;
<c:if test="${face=='良好（笑脸）' }">
<img align="top" onclick="" alt="0" src="<%=path%>/public/images/food_smile.png" width="28" height="26">
</c:if>
<c:if test="${face=='一般（平脸）' }">
<img align="top" onclick="" alt="0" src="<%=path%>/public/images/food_normal.png" width="28" height="26">
</c:if>
<c:if test="${face=='很差（哭脸）' }">
<img align="top" onclick="" alt="0" src="<%=path%>/public/images/food_cry.png" width="28" height="26">
</c:if>

<br>
<label style="font-weight:bold;font-size: 20px;">&nbsp;&nbsp;行政处罚    ${superviseCount }个</label>
<label style="font-weight:bold;font-size: 20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投诉举报    &nbsp; 0个</label>
<hr color="lightgrey"/>
<label style="font-weight:bold">商家介绍：</label><br/><br/>
<label>${companyInfo.companyintro}</label>
<hr color="lightgrey"/>
<label style="font-weight:bold">消费记录：</label><label style="float: right;">【合计】<font color="red"> ${totalprice} </font>元</label><br>
<%-- <label>${fn:substring(paytime,0,4)}年${fn:substring(paytime,4,6)}月${fn:substring(paytime,6,8)}日  ${fn:substring(paytime,8,10)}:${fn:substring(paytime,10,12)} 消费<font color="red">${price}</font>元    </label><label onclick="detailsinfo('${oddnumber}')"><font color="red" style="font-weight: bolder;" ><u>查看详情</u></font></label>
 --%>
  <c:forEach items="${helperlist}" var="item" varStatus="status">
 
 <label>${fn:substring(item.paytime,0,4)}年${fn:substring(item.paytime,4,6)}月${fn:substring(item.paytime,6,8)}日  ${fn:substring(item.paytime,8,10)}:${fn:substring(item.paytime,10,12)} 消费<font color="red">${item.price}</font>元    </label><label onclick="detailsinfo('${item.oddnumber}')"><font color="red" style="font-weight: bolder;" ><u>查看详情</u></font></label><br>
 <c:if test="${item.complainttype == '2'}"><label><font color="red" style="font-weight: bolder;" >&nbsp;&nbsp;<u>投诉受理中</u></font></label><br></c:if>
  <c:if test="${item.complainttype == '0'}"><label><font color="red" style="font-weight: bolder;" >&nbsp;&nbsp;<u>投诉处理中</u></font></label><br></c:if>
 </c:forEach> 
</div>
<br>

<div id="buttondiv" style="text-align: center;">
<br>
<input id="wannacomplain" type="button" value="我要投诉" style="background: #f3be67;width:90px;height:40px;font-family: SimHei;font-weight: bold;font-size: 15px" onclick="pagejump('${companyInfo.companyname}','${companyInfo.companycode}','${pid}')"/>
<%--  <c:if test="${complainflag== '1'}"> 
<input id="wannacomplain" type="button" value="我要投诉" style="background: #f3be67;width:90px;height:40px;font-family: SimHei;font-weight: bold;font-size: 15px" onclick="pagejump('${companyInfo.companyname}','${companyInfo.companycode}','${pid}')"/>
</c:if>
 <c:if test="${complainflag== '0'}"> 
<input id="wannacomplain" type="button" value="我要投诉" style="background: #f3be67;width:90px;height:40px;font-family: SimHei;font-weight: bold;font-size: 15px" onclick="pagejump('${companyInfo.companyname}','${companyInfo.companycode}','${pid}')"/>
</c:if>
<c:if test="${complainflag== '2' }">
<input id="historycomplain" type="button" value="查看投诉详情" style="background: #f3be67;width:100px;height:40px;font-family: SimHei;font-weight: bold;font-size: 15px" onclick="lookcomplaint('${complaintpid}','${companyInfo.companyname}')"/>
</c:if> --%>
</div>

</body>
<script type="text/javascript">

doStar(document.getElementById("star" + ${star}));
</script>
</html>
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
<title>主界面</title>
<link rel="stylesheet" href="<%=path%>/public/style/mui.min.css">
</head>
<style>
.pager{height:30px;line-height:30px;font-size: 12px;margin: 25px 0px;text-align: center;color: #2E6AB1;overflow: hidden;}
.pager a{border:1px solid #9AAFE5;color:#2E6AB1;margin:0px 5px;padding:5px 8px;text-decoration: none;}
.pager a.hover,.pager a.active{background-color:#2E6AB1;border-color:#000080;color:#FFF;}
.pager a.disabled{color:#C8CDD2;cursor:auto;}
</style>
<body>
<form action="<%=path%>/doInsertMenu.do" method="post" enctype="multipart/form-data">
<input type="file" name="cover">
<input type="submit">
</form>

<!-- <div id="pullrefresh" class="mui-content mui-scroll-wrapper">
			Slider
			<div class="mui-scroll">
				List
				<h4 class="list-title">用户</h4>
				<ul id="myList" class="mui-table-view">
				</ul>
			</div>
		</div> -->
</body>
<script type="text/javascript" src="<%=path%>/public/script/mui.min.js"></script>
<script type="text/javascript" src="<%=path%>/public/script/app.js"></script>
<script type="text/javascript" src="<%=path%>/public/script/app.template.js"></script>
<%-- <script type="text/javascript">
var listurl="<%=path%>/userInfo/users.do";
(function(mui, doc) {
	//mui初始化
	mui.init({
		pullRefresh: {
			container: '#pullrefresh',
			down: {
				callback: pulldownRefresh
			},
			up: {
				contentrefresh: '正在加载...',
				callback: pullupLoad
			}
		}
	});
	mui.plusReady(function() {
		plus.webview.currentWebview().setStyle({
			scrollIndicator: 'none'
		});
		app.initTemplates();
	});
	// 在刚方法内添加页面第一次被打开时需要执行的方法
	app.initPage = function() {
		setTimeout(function() {
			// 触发下拉加载时间
			mui('#pullrefresh').pullRefresh().pullupLoading();
		}, 0);
	};
	var myListBox = document.getElementById('myList');
	//当前页数
	var page = 1;
	//每页显示大小
	var pageSize = 10;
	//总页数
	var total = 0;
	function pulldownRefresh() {
		console.log("pulldownRefresh action");
		console.log("往下拉，每次都强制获取数据");
		mui('#pullrefresh').pullRefresh().refresh(true);
		// 初始化数据
		mui.ajax(listurl, {
			dataType: 'json', //服务器返回json格式数据
			type: 'get', //HTTP请求类型
			data:{page:1,pageSize:pageSize},
			timeout: 10000, //超时时间设置为10秒；
			success: function(data, textStatus, xhr) {
				//服务器返回响应，根据响应结果，分析是否登录成功；
				if (data) {
					// 清空原有数据
					myList.innerHTML = "";
					console.log("下拉成功后，设置page为2");
					total = data.result.total;
					page = 2;
					myList.innerHTML = myList.innerHTML + data.result.html;
				}
				mui('#pullrefresh').pullRefresh().endPulldownToRefresh();
				var over = (page-1)*pageSize >= total;
				console.log("show over:" + over);
				mui('#pullrefresh').pullRefresh().endPullupToRefresh(over);  
			}
		});
	}
	function pullupLoad(){
		console.log("pullupLoad 往上推");
		// 初始化数据
		mui.ajax(listurl, {
			dataType: 'json', //服务器返回json格式数据
			data:{page:page,pageSize:pageSize},
			type: 'get', //HTTP请求类型
			timeout: 10000, //超时时间设置为10秒；
			success: function(data, textStatus, xhr) {
				//服务器返回响应，根据响应结果，分析是否登录成功；
				if (data) {
					console.log("获取成功之后，设置page+1:");
					total = data.result.total;
					page++;
					myList.innerHTML = myList.innerHTML + data.result.html;
				}
				//所有条数显示完了，就不在支持往上推获取新数据了
				var over = (page-1)*pageSize >= total;
				console.log("show over:" + over);
				mui('#pullrefresh').pullRefresh().endPullupToRefresh(over); 
				
			}
		});
	}
	function renderChild(rows) {
		if (rows && rows.length > 0) {
			for (var i = 0; i < rows.length; i++) {
				var item = rows[i];
				var li = document.createElement('li');
				li.className = 'mui-table-view-cell mui-media';
				li.id = item.id;
				var inner = ['<a href="information-detail.html?id=', item.id, '">'];
				//inner.push('<img class="mui-media-object mui-pull-left" src="', app.conf.host, '/', item.filePath, '">');
				inner.push('<div class="mui-media-body">', item.tile);
				inner.push('<p class = "mui-ellipsis"> 【新闻】', item.pubTime.substr(0, 10));
				inner.push('<i class="mui-icon mui-icon-star"></i>', item.viewTimes);
				inner.push('</p></div></a>');
				li.innerHTML = inner.join('');
				myListBox.appendChild(li)
			}
		}
	}
}(mui, document));
</script> --%>
</html>
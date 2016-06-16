<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
String uri = request.getRequestURI();
String  url  =  uri.substring(uri.lastIndexOf("/")+1);
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>数据采集系统-<sitemesh:write property='title'/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link type="text/css" href="${pageContext.request.contextPath}/source/css/main.css" rel="stylesheet"/> 
<script src="${pageContext.request.contextPath}/source/js/jquery-1.11.2.min.js"></script> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/source/js/easyUI/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/source/js/easyUI/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/source/js/easyUI/jquery.easyui.min.js"></script>
<script src="${pageContext.request.contextPath}/source/js/easyUI/easyui-lang-zh_CN.js"></script>
<script src="${pageContext.request.contextPath}/source/js/validate.js"></script> 
<!-- 导入页面引用的特殊js和css文件 -->
<sitemesh:write property='head' />
<script type="text/javascript"> 
	$(function(){
		//计算网页高度
		setHei();
		$(window).resize(function(){
			setHei();
		});
	});
	
	function setHei(){
		$("#content").removeAttr("style");
		var h = $(window).height();
		var hh = document.body.offsetHeight;
		var t = $("#content").offset().top;
		if(hh>h)h = hh+20;
		var v = h-t-1;
		var rh = $("#conRight").height();
		if(rh>v){
			v = rh+50;
		}
		$("#content").css("height",v-30);
	}
</script>
</head>

<body>
	<div id="main">
		<div id="header"><jsp:include page="/page/decorators/header.jsp"></jsp:include></div>
		<div id="content" style="width:100%;">
			 <div class="container">
				<jsp:include page="/page/decorators/left.jsp"></jsp:include>
				<sitemesh:write property='body'/>
				<div class="cl"></div>
			</div>
		</div>
		<%-- <div id="footer"><jsp:include page="/page/decorators/footer.jsp"></jsp:include> --%></div>
	</div>
</body>
</html>
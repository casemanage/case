<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>采集信息概况</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1  ,maximum-scale=1, user-scalable=no" /> 
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->  
	<script src="${pageContext.request.contextPath}/source/js/fusionChart/FusionCharts.js"></script>
	<link href="${pageContext.request.contextPath}/source/js/fusionChart/FusionCharts.css" rel="stylesheet" />
	<script type="text/javascript">
		$(document).ready(function(){
			 loadstationStatistic();
			 loadtypeStatistic();
			 loadstationTypeStatistic();
			 loadlastMonthStatistic(); 
			 loadlastWeekStatistic();
			 loadlatestStatistic();
		});
		function loadstationStatistic(){
		
		};
		function loadtypeStatistic(){
		
		};
		function loadstationTypeStatistic(){
		
		};
		function loadlastMonthStatistic(){
		
		};
		function loadlastWeekStatistic(){
		
		};
		function loadlatestStatistic(){
		
		};
	</script>
  </head>
  
 <body style="background:#fff;">
	 <div style="width:99%" >
	 	<div id="stationStatistic" align="center">
	 	</div>
	 </div>
	 <div style="width:50%" >
	 	<div id="typeStatistic" align="center"></div>
	 </div>
	 <div style="width:49%" >
	 	<div id="stationTypeStatistic" align="center"></div>
	 </div>
	 <div style="width:30%" >
	 	<div id="lastMonthStatistic" align="center"></div>
	 </div>
	 <div style="width:30%" >
	 	<div id="lastWeekStatistic" align="center"></div>
	 </div>
	 <div style="width:30%" >
	 	<div id="latestStatistic" align="center"></div>
	 </div>
</body>
</html>

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
	
	<script src="<%=basePath %>source/js/fusionChart/fusioncharts.js"></script>  
    <link href="<%=basePath %>source/js/fusionChart/assets/css/style.css" rel="stylesheet" />
	
<%--     <script src="${pageContext.request.contextPath}/source/js/jqplot/jquery.jqplot.min.js"></script>
    <link href="${pageContext.request.contextPath}/source/js/jqplot/jquery.jqplot.min.css" rel="stylesheet" />
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/source/js/jqplot/excanvas.js"></script>
    <![endif]-->
    <script src="${pageContext.request.contextPath}/source/js/jqplot/jqplot.pieRenderer.js"></script>
    <script src="${pageContext.request.contextPath}/source/js/jqplot/jqplot.donutRenderer.js"></script> --%>
	<script type="text/javascript">
		$(document).ready(function(){
			 loadStatisticInfo(); 
		});
		function loadStatisticInfo(){
			$.ajax({
	            url: "<%=basePath%>Home/jsonLoadStatisticXML.do",
				type : "post",  
				dataType:"json",
				success : function(req) { 
		  			if(req.code == 0){   
		  				 var perTotalCount = getJsonData(req,1);
		  				 var perTotalchart = new FusionCharts("<%=basePath %>source/chart/Column2D.swf", "ChartId1", "650", "350", "0", "0");
						 perTotalchart.setJSONData({
						 	"chart":{      
			                    "caption":"重点人员分类统计图",     
			                    "xaxisname":"类型",     
			                    "yaxisname":"数量",    
			                    "showvalues":"0",      
			                    "decimals":"2",
			                    "useRoundEdges":"1",
			                    "theme": "fint",
			                    "formatNumberScale":"0"  },
			                   "data":perTotalCount
						 }); 
   						 perTotalchart.render("person_totalStatistic"); 
		  				 var assoTotalCount = getJsonData(req,0);
		  				 var assoTotalchart = new FusionCharts("<%=basePath %>source/chart/Column2D.swf", "ChartId2", "650", "350", "0", "0");
						 assoTotalchart.setJSONData({
						 	"chart":{      
			                    "caption":"社会机构分类统计图",     
			                    "xaxisname":"类型",     
			                    "yaxisname":"数量",    
			                    "showvalues":"0",      
			                    "decimals":"2",
			                    "theme": "fint",
			                    "useRoundEdges":"1",
			                    "formatNumberScale":"0"  }, 
			                   "data":assoTotalCount
						 });  
   						 assoTotalchart.render("asso_totalStatistic");  
   						 
		  				 var assostationCount = getJsonData(req,2);
		  				 var assostationchart = new FusionCharts("<%=basePath %>source/chart/Column2D.swf", "ChartId3", "650", "350", "0", "0");
						 assostationchart.setJSONData({
						 	"chart":{      
			                    "caption":"社会机构单位采集统计图",     
			                    "xaxisname":"单位",     
			                    "yaxisname":"数量",    
			                    "showvalues":"0",      
			                    "decimals":"2",
			                    "useRoundEdges":"1",
			                    "theme": "fint",
			                    "formatNumberScale":"0"  },
			                   "data":assostationCount
						 });  
   						 assostationchart.render("asso_stationTypeStatistic");  
   						 
		  				 var personstationCount = getJsonData(req,3);
		  				 var personstationchart = new FusionCharts("<%=basePath %>source/chart/Column2D.swf", "ChartId4", "650", "350", "0", "0");
						 personstationchart.setJSONData({
						 	"chart":{      
			                    "caption":"重点人员单位采集统计图",     
			                    "xaxisname":"单位",     
			                    "yaxisname":"数量",    
			                    "showvalues":"0",      
			                    "decimals":"2",
			                    "useRoundEdges":"1",
			                    "theme": "fint",
			                    "formatNumberScale":"0"  },
			                   "data":personstationCount
						 });  
   						 personstationchart.render("person_stationTypeStatistic");  
   						 
		  			}else{
						$.messager.alert('错误信息',data.message,'error');
		  			} 
				}
			}); 
		}; 
		function getJsonData(req,i){
			var arry = req.obj[i].list;
			var arryList = [];
			$.each(arry,function(index,item){
				var per = {};
				per.label = item.typeName;
				per.value = item.totalCount;
				arryList.push(per);
			});
			return arryList;
		}
	</script>
  </head>
  
 <body style="background:#fff;"> 
		    
  <div id="contentRight" class="contentRight">
     <div style="float:left" >
 		<div id="asso_totalStatistic"></div> 
 	 </div>
	 <div style="float:left" >
	 	<div id="person_totalStatistic"></div>
	 </div>
	 <div style="float:left" >
	 	<div id="asso_stationTypeStatistic"></div>
	 </div>
	 <div style="float:left" >
	 	<div id="person_stationTypeStatistic"></div>
	 </div>
	 <div style="float:left" >
	 	<div id="asso_lastMonthStatistic"></div>
	 </div>
	 <div style="float:left" >
	 	<div id="person_lastMonthStatistic"></div>
	 </div> 
	 <div style="float:left" >
	 	<div id="asso_lastWeekStatistic"></div>
	 </div>
	 <div style="float:left" >
	 	<div id="person_lastWeekStatistic"></div>
	 </div> 
	 </div>
</body>
</html>

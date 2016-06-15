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
	
    <script src="${pageContext.request.contextPath}/source/js/jqplot/jquery.jqplot.min.js"></script>
    <link href="${pageContext.request.contextPath}/source/js/jqplot/jquery.jqplot.min.css" rel="stylesheet" />
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/source/js/jqplot/excanvas.js"></script>
    <![endif]-->
    <script src="${pageContext.request.contextPath}/source/js/jqplot/jqplot.pieRenderer.js"></script>
    <script src="${pageContext.request.contextPath}/source/js/jqplot/jqplot.donutRenderer.js"></script>
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
		  				 var asototal = req.obj[0];
		  				 var asototalary = []; 
		  				 $.each(asototal.list,function(index,ary){
		  				 	var temp = [ary.typeName, ary.totalCount];
		  				 	asototalary.push(temp);
		  				 });
		  				 $.jqplot('asso_totalStatistic', [asototalary], {
				            title: {
				                text: '采集信息汇总', //设置当前图的标题
				                show: true//设置当前图的标题是否显示
				            },
				            grid: {
				                drawGridLines: false, // wether to draw lines across the grid or not.
				                background: '#fff', //设置整个图标区域的背景色
				                borderWidth: 0, //设置图表的(最外侧)边框宽度
				                shadow: false, //为整个图标（最外侧）边框设置阴影，以突出其立体效果
				                renderer: jQuery.jqplot.CanvasGridRenderer, // renderer to use to draw the grid.
				                rendererOptions: {} // options to pass to the renderer. Note, the default CanvasGridRenderer takes no additional options.
				            },
				            seriesDefaults: {
				                renderer: $.jqplot.PieRenderer,
				                rendererOptions: {
				                    showDataLabels: true,
				                    dataLabels: 'value',
				                    startAngle: -90,
				                    shadow: false
				                }
				            },
				            legend: {
				                show: true,
				                location: "e"
				            }
				        });
				        var pertotal = req.obj[1];
		  				 var pertotalary = []; 
		  				 $.each(pertotal.list,function(index,ary){
		  				 	var temp = [ary.typeName, ary.totalCount];
		  				 	pertotalary.push(temp);
		  				 });
		  				 $.jqplot('person_totalStatistic', [pertotalary], {
				            title: {
				                text: '采集信息汇总', //设置当前图的标题
				                show: true//设置当前图的标题是否显示
				            },
				            grid: {
				                drawGridLines: false, // wether to draw lines across the grid or not.
				                background: '#fff', //设置整个图标区域的背景色
				                borderWidth: 0, //设置图表的(最外侧)边框宽度
				                shadow: false, //为整个图标（最外侧）边框设置阴影，以突出其立体效果
				                renderer: jQuery.jqplot.CanvasGridRenderer, // renderer to use to draw the grid.
				                rendererOptions: {} // options to pass to the renderer. Note, the default CanvasGridRenderer takes no additional options.
				            },
				            seriesDefaults: {
				                renderer: $.jqplot.PieRenderer,
				                rendererOptions: {
				                    showDataLabels: true,
				                    dataLabels: 'value',
				                    startAngle: -90,
				                    shadow: false
				                }
				            },
				            legend: {
				                show: true,
				                location: "e"
				            }
				        });
				        var stationtotal = req.obj[2];
		  				 var stationtotalary = []; 
		  				 $.each(stationtotal.list,function(index,ary){
		  				 	var temp = [ary.typeName, ary.totalCount];
		  				 	stationtotalary.push(temp);
		  				 });
		  				 $.jqplot('asso_stationTypeStatistic', [stationtotalary], {
				            title: {
				                text: '采集信息汇总', //设置当前图的标题
				                show: true//设置当前图的标题是否显示
				            },
				            grid: {
				                drawGridLines: false, // wether to draw lines across the grid or not.
				                background: '#fff', //设置整个图标区域的背景色
				                borderWidth: 0, //设置图表的(最外侧)边框宽度
				                shadow: false, //为整个图标（最外侧）边框设置阴影，以突出其立体效果
				                renderer: jQuery.jqplot.CanvasGridRenderer, // renderer to use to draw the grid.
				                rendererOptions: {} // options to pass to the renderer. Note, the default CanvasGridRenderer takes no additional options.
				            },
				            seriesDefaults: {
				                renderer: $.jqplot.PieRenderer,
				                rendererOptions: {
				                    showDataLabels: true,
				                    dataLabels: 'value',
				                    startAngle: -90,
				                    shadow: false
				                }
				            },
				            legend: {
				                show: true,
				                location: "e"
				            }
				        });
				        var perstationtotal = req.obj[3];
		  				 var perstationtotalary = []; 
		  				 $.each(perstationtotal.list,function(index,ary){
		  				 	var temp = [ary.typeName, ary.totalCount];
		  				 	perstationtotalary.push(temp);
		  				 });
		  				 $.jqplot('person_stationTypeStatistic', [perstationtotalary], {
				            title: {
				                text: '采集信息汇总', //设置当前图的标题
				                show: true//设置当前图的标题是否显示
				            },
				            grid: {
				                drawGridLines: false, // wether to draw lines across the grid or not.
				                background: '#fff', //设置整个图标区域的背景色
				                borderWidth: 0, //设置图表的(最外侧)边框宽度
				                shadow: false, //为整个图标（最外侧）边框设置阴影，以突出其立体效果
				                renderer: jQuery.jqplot.CanvasGridRenderer, // renderer to use to draw the grid.
				                rendererOptions: {} // options to pass to the renderer. Note, the default CanvasGridRenderer takes no additional options.
				            },
				            seriesDefaults: {
				                renderer: $.jqplot.PieRenderer,
				                rendererOptions: {
				                    showDataLabels: true,
				                    dataLabels: 'value',
				                    startAngle: -90,
				                    shadow: false
				                }
				            },
				            legend: {
				                show: true,
				                location: "e"
				            }
				        });
		  			}else{
						$.messager.alert('错误信息',data.message,'error');
		  			} 
				}
			}); 
		}; 
	</script>
  </head>
  
 <body style="background:#fff;"> 
		    
     <div style="float:left" >
 		<div id="asso_totalStatistic" style="width:400px;height:300px;float:left;padding:5px;margin-top: 20px;" >
 		</div> 
 	 </div>
	 <div style="float:left" >
	 	<div id="person_totalStatistic" style="width:400px;height:300px;float:left;padding:5px;margin-top: 20px;" ></div>
	 </div>
	 <div style="float:left" >
	 	<div id="asso_stationTypeStatistic" style="width:400px;height:300px;float:left;padding:5px;margin-top: 20px;" ></div>
	 </div>
	 <div style="float:left" >
	 	<div id="person_stationTypeStatistic" style="width:400px;height:300px;float:left;padding:5px;margin-top: 20px;" ></div>
	 </div>
	 <div style="float:left" >
	 	<div id="asso_lastMonthStatistic" style="width:400px;height:300px;float:left;padding:5px;margin-top: 20px;" ></div>
	 </div>
	 <div style="float:left" >
	 	<div id="person_lastMonthStatistic" style="width:400px;height:300px;float:left;padding:5px;margin-top: 20px;" ></div>
	 </div> 
	 <div style="float:left" >
	 	<div id="asso_lastWeekStatistic" style="width:400px;height:300px;float:left;padding:5px;margin-top: 20px;" ></div>
	 </div>
	 <div style="float:left" >
	 	<div id="person_lastWeekStatistic" style="width:400px;height:300px;float:left;padding:5px;margin-top: 20px;" ></div>
	 </div> 
</body>
</html>

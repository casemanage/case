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
    <title>区域管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 
	<script
	src="${pageContext.request.contextPath}/source/js/pager/jquery.pager.js"></script>
	<link
	href="${pageContext.request.contextPath}/source/js/pager/Pager.css"
	rel="stylesheet" />
<script type="text/javascript">
		$(document).ready(function(){
			$("#pager").pager({
			    pagenumber:'${area.pageNo}',                         /* 表示初始页数 */
			    pagecount:'${area.pageCount}',                      /* 表示总页数 */
			    totalCount:'${area.totalCount}',
			    buttonClickCallback:PageClick                     /* 表示点击分页数按钮调用的方法 */                  
			}); 
			$("#treeList").tree({
				 url: '<%=basePath%>area/jsonLoadAreaTreeList.do',   
   				 onClick:function(node){
   				 	getAreaListById(node.id);
   				 },
   				 onBeforeExpand:function(node){
   				 	$('#treeList').tree('options').url = '<%=basePath%>area/jsonLoadAreaTreeList.do?pid='+ node.id;
   				 }
   				 /* onLoadSuccess:function(){
					showProcess(false); 
   				    var aId = $.trim($("#hid_areaId").val());
   				 	if(aId.length>0){
   				 		var node = $("#treeList").tree("find",aId); 
						$('#treeList').tree("select", node.target);
   				 	} 
   				 } */
			});  
		});
function getAreaListById(id){
	  var pageNumber = $("#pageNumber").val();
	  $.ajax({
		url : "<%=basePath%>area/jsonLoadAreaListById.do?areaId="+id+"&&pageNumber="+pageNumber,
		type : "post",  
		dataType:"json",
		success : function(data) { 
  			if(data.code == 0){ 
  				 /* $("#pageNumber").val(1);  */
  				 $("#pager").pager({
				    pagenumber:data.obj.pageNo,                         /* 表示初始页数 */
				    pagecount:data.obj.pageCount,                      /* 表示总页数 */
				    totalCount:data.obj.totalCount,
				    buttonClickCallback:PageClick2                     /* 表示点击分页数按钮调用的方法 */                  
				});
				$("#areainfoList").html("");
				$("#pageNumber").val("");
				$("#areaId").val(data.obj.id);
				fillAreaList(data.list);
  			}else{
				$.messager.alert('错误信息',data.message,'error');
  			} 
		}
	});
};		
PageClick = function(pageclickednumber) {
	$("#pager").pager({
	    pagenumber:pageclickednumber,                 /* 表示启示页 */
	    pagecount:'${area.pageCount}',                  /* 表示最大页数pagecount */
	    buttonClickCallback:PageClick                 /* 表示点击页数时的调用的方法就可实现javascript分页功能 */            
	});
	
	$("#pageNumber").val(pageclickednumber);          /* 给pageNumber从新赋值 */
	/* 执行Action */
	pagesearch();
}
PageClick2 = function(pageclickednumber) {
	$("#pager").pager({
	    pagenumber:pageclickednumber,                 /* 表示启示页 */
	    pagecount:'${Area.pageCount}',                  /* 表示最大页数pagecount */
	    buttonClickCallback:PageClick2                 /* 表示点击页数时的调用的方法就可实现javascript分页功能 */            
	});
	
	$("#pageNumber").val(pageclickednumber);          /* 给pageNumber从新赋值 */
	/* 执行Action */
	pagesearch2();
}
function search(){
	$("#pageNumber").val("1");
	pagesearch();
}

function pagesearch(){
	AreaForm.submit();
}
function pagesearch2(){
	var areaId = $("#areaId").val();
	getAreaListById(areaId);	
	$("#areaId").val("");
}
function fillAreaList(lst){
	var html = "";
	for(var i = 0; i<lst.length;i++){
		html += "<tr>";
		html += "<td align='center' style='display:none'>"+lst[i].id+"</td><td	align='center'>"+(lst[i].name == null ? "":lst[i].name)+"</td><td align='center'>"+(lst[i].parentName == null ? "":lst[i].parentName)+"</td><td align='center'>"+(lst[i].latitude == null || lst[i].longtiude == null ? "":(lst[i].latitude,lst[i].longtiude))+"</td>";
		html += "<td align='center'>"+(lst[i].description == null ? "":lst[i].description)+"</td>";
		html += "<td><a href='javascript:void(0);' onclick=window.location.href='<%=basePath%>area/areaInfo.do?areaId="+lst[i].id+"'>"+ "编辑" + "</a></td>";
		html += "<td><a href='javascript:void(0);' onclick='deleteAreaById("+lst[i].associateid+","+lst[i].id+");'>"+"删除"+"</a></td>";
		html += "</tr>";
	}
	$("#areainfoList").html(html);
} 
function deleteAreaById(id){
	$.messager.confirm("删除确认","确认删除该区域?",function(r){  
		    if (r){  
			$.ajax({
				url : "<%=basePath%>area/jsonDeleteArea.do?areaId="+id,
				type : "post",  
				dataType:"json",
				success : function(data) { 
		  			if(data.code==0){ 
		  				$.messager.alert('删除信息',data.message,'info',function(){ 
							history.go(0);
		       			});
		  			}else{
						$.messager.alert('错误信息',data.message,'error');
		  			} 
				}
			});
	    }  
	});
}
</script>
  </head>
  
  <body style="background:#fff;">
  
  <div id="contentRight"  class="contentRight"	>
	<div class="containner-fluid">
		<div class="pannel-header"></i><span>区域管理</span>
		</div>
		<div class="Panel-content">
			<form id="AreaForm" name="AreaForm"
				action="area/areaList.do" method="get">
				<div style="width:100%;text-align:right;">
					<input type="text" name="searchName" validType="SpecialWord" class="easyui-validatebox" placeholder="按区域名称搜索" value="${area.searchName}" />
					<input type="button" class="btn-add" style="margin-left:10px;"  onclick="search();" value="搜索">  
				    <input type="hidden" id="pageNumber" name="pageNo" value="${area.pageNo}" />
					<input type="button" class="btn-add" style="margin-left:25px;"  onclick="window.location.href='<%=basePath%>area/areaInfo.do?areaId=0'" value="新建区域">
					<input type="hidden" id="areaId"/>
					<input type="hidden" id="parentid" name="parentid" value="${Area.parentid}"/>
				</div> 
			</form> 
		</div>
		
	</div>
		<div class="containner-fluid">
			<div class="fl yw-lump wid250 mt10">
				<div class="yw-cm-title">
					<span class="ml26">区域列表</span>
				</div>
				<div class="yw-tree-list" style="height: 639px;">
					<ul id="treeList"></ul>
				</div>
			</div>
			<div class="yw-lump wid-atuo ml260 mt10">
				<div class="yw-cm-title">
					<span class="ml26">全部区域</span>
				</div>
				<table cellpadding="10" cellspacing="0" width="100%" class="list-info">
					<thead>
						<tr style="background-color:#D6D3D3;font-weight: bold;">
							<th width="4%" style="display:none"></th>
							<th>区域名称</th>
							<th>所属区域</th>
							<th>经纬度</th>
							<th>备注</th>
							<th>详情</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="areainfoList">
					<c:forEach var="item" items="${areaAllList}">
						<tr>
							<td align="center" style="display:none">${item.id}</td>
							<td align="center">${item.name}</td>
							<td align="center">${item.parentName}</td>
							<c:if test="${item.latitude !='' &&  item.latitude != null && item.longtiude != '' && item.longtiude != null}">
								<td>${item.latitude}，${item.longtiude}</td>
							</c:if>
							<c:if test="${item.latitude =='' || item.latitude == null || item.longtiude == '' || item.longtiude == null}">
								<td></td>
							</c:if>
							<td>${item.description}</td>
							<td><a href="javascript:void(0);"
								onclick="window.location.href='<%=basePath%>area/areaInfo.do?areaId=${item.id}'">编辑</a></td>
							<td><a href="javascript:void(0);"
								onclick="deleteAreaById(${item.id});">删除</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<div class="page" id="pager"></div>
			</div>
		</div>
	</div>
</body>
</html>

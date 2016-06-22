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
    <title>社会机构管理</title>
    
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
			    pagenumber:'${associate.pageNo}',                         /* 表示初始页数 */
			    pagecount:'${associate.pageCount}',                      /* 表示总页数 */
			    totalCount:'${associate.totalCount}',
			    buttonClickCallback:PageClick                     /* 表示点击分页数按钮调用的方法 */                  
			});  
			/* $("#deviceList tr").each(function(i){
				if(i>0){
					$(this).bind("click",function(){
						var pointId = $(this).find("td").first().text();
						 window.location.href="device/deviceInfo.do?pointId="+pointId;
					});
				}
			}); */
			  
		});
		
PageClick = function(pageclickednumber) {
	$("#pager").pager({
	    pagenumber:pageclickednumber,                 /* 表示启示页 */
	    pagecount:'${associate.pageCount}',                  /* 表示最大页数pagecount */
	    buttonClickCallback:PageClick                 /* 表示点击页数时的调用的方法就可实现javascript分页功能 */            
	});
	
	$("#pageNumber").val(pageclickednumber);          /* 给pageNumber从新赋值 */
	/* 执行Action */
	pagesearch();
}
function search(){
	$("#pageNumber").val("1");
	pagesearch();
}

function pagesearch(){
	if ($('#AssociateForm').form('validate')) {
		AssociateForm.submit();
	}  
}
function showdialog(itemId){
	$("#hid_associateid").val(itemId);
	var wz = getDialogPosition($('#planwindow').get(0),100);
	$('#planwindow').window({
		  	top: 100,
		    left: wz[1]
	});
	$('#planwindow').window('open');
}
 function uploadPlan(obj){ 
	$(obj).attr("onclick", "");
	$('#uploadForm').form('submit',{
			success : function(data) {
				data = $.parseJSON(data);
				if (data.code == 0) {
					$.messager.alert('保存信息',data.message,'info',
									function() {
										$('#planwindow input[type=file]').val('');
										$('#planwindow').window('close');
									});
				} else {
					$.messager.alert('错误信息',
							data.message, 'error',
							function() {
							});
					$(obj).attr("onclick",
							"uploadPlan(this);");
				}
			}
		}); 
 }
 function showName(obj){
	 if(!(/(?:jpg)$/i.test(obj.value))&&!(/(?:jpeg)$/i.test(obj.value))&&!(/(?:png)$/i.test(obj.value))) {
         $.messager.alert('错误', "选择平面图文件格式错误", 'error');
         if(window.ActiveXObject) {//for IE
             obj.select();//lect the file ,and clear selection
             document.selection.clear();
         } else if(window.opera) {//for opera
             obj.type="text";
             obj.type="file";
         } else obj.value="";//for FF,Chrome,Safari
     } 
}
function deleteByAssociateId(id){
			$.messager.confirm("删除确认","确认删除该机构?",function(r){  
		    if (r){  
			$.ajax({
				url : "<%=basePath%>associate/jsonDeleteAssociate.do?associateId="+id,
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
  <div id="contentRight" class="contentRight">
	<div class="containner-fluid">
		<div class="pannel-header">社会机构管理</div>
		<div class="Panel-content">
			<form id="AssociateForm" name="AssociateForm" action="<%=basePath%>associate/associateList.do" method="get">
				<div style="width:100%;text-align:right;">
					<input type="text" name="searchName" validType="SpecialWord" class="easyui-validatebox" placeholder="搜索" value="${associate.searchName}" /> 
					<input type="button" class="btn-add" style="margin-left:10px;"  onclick="search();" value="搜索">  
					<input type="hidden" id="pageNumber" name="pageNo" value="${associate.pageNo}" /> 
					<input type="button" class="btn-add" style="margin-left:25px;" onclick="window.location.href='<%=basePath%>associate/associateInfo.do?associateId=0'" value="新建社会机构"> 
					<input type="button" class="btn-add" style="margin-left:25px;" onclick="window.location.href='<%=basePath%>fileUpload/downfile.do?filepath=source/excel/AssociateDataModel.xls'" value="下载导入模板"/>
				</div>
			</form>
		</div>
	</div>
	<div class="containner-fluid">
		<table cellpadding="10" cellspacing="0" width="100%" class="list-info">
			<thead>
				<tr style="background-color:#D6D3D3;font-weight: bold;">
					<th width="4%" style="display:none">&nbsp;</th>
					<th>编号</th>
					<th>名称</th>
					<th>类型</th>
					<th>经纬度</th>
					<th>地址</th>
					<th>手机号码</th>
					<!-- <th>采集单位</th>
					<th>采集人</th> -->
					<th>采集时间</th>
					<th>描述</th>
					<th>详情</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${associatelist}">
					<tr>
						<td align="center" style="display:none">${item.id}</td>
						<td align="center">${item.serialno}</td>
						<td>${item.name}</td>
						<td>${item.typename}</td>
						<c:if test="${item.latitude !='' && item.longitude != ''}">
							<td>${item.latitude}，${item.longitude}</td>
						</c:if>
						<c:if test="${item.latitude =='' || item.longitude == ''}">
							<td></td>
						</c:if>
						<td>${item.address}</td>
						<td>${item.telephone}</td>
						<%--<td	ondblclick="window.location.href='<%=basePath%>associate/associateInfo.do?associateId=${item.id}'">${item.organname}</td>
						<td	ondblclick="window.location.href='<%=basePath%>associate/associateInfo.do?associateId=${item.id}'">${item.creatorname}</td> --%>
						<td>${item.createtimes}</td>
						<td>${item.description}</td>
						<td><a href="javascript:void(0);" onclick="window.location.href='<%=basePath%>associate/associateInfo.do?associateId=${item.id}'">编辑</a></td>
						<%-- <td><a href="javascript:void(0);" onclick="showdialog(${item.id});">上传平面图</a></td> --%>
						<td><a href="javascript:void(0);" onclick="deleteByAssociateId(${item.id});">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	  <div class="page" id="pager"></div>
	</div> 
	</div>
	 <div id="planwindow" class="easyui-window" title="上传平面图" style="width:480px;height:300px;overflow:hidden;padding:10px;text-align:center;" iconCls="icon-info" closed="true" modal="true"   resizable="false" collapsible="false" minimizable="false" maximizable="false">
		<form id="uploadForm" name ="uploadForm" action="<%=basePath%>associate/jsonupdateplan.do"  method="post" enctype="multipart/form-data"  style="text-align:left;"> 
		<p style="display:none">
        	<span >id：</span><input name="associateid" id="hid_associateid" type="text"  class="easyui-validatebox"/> 
        </p>
    	<div style="margin-top:15px;width:100%;">
    		<span class="from-style">平面图1:</span> 
    		<input type="file" name="file1" id="jfile1" onchange="showName(this)" />
    	</div>
    	<div style="margin-top:15px;width:100%;">
    		<span class="from-style">平面图2:</span> 
    		<input type="file" name="file2" id="jfile2" onchange="showName(this)" />
    	</div>
    	<div style="margin-top:15px;width:100%;">
    		<span class="from-style">平面图3:</span> 
    		<input type="file" name="file3" id="jfile3" onchange="showName(this)" />
    	</div>
    	<div style="margin-top:15px;width:100%;">
    		<span class="from-style">平面图4:</span> 
    		<input type="file" name="file4" id="jfile4" onchange="showName(this)" />
    	</div>
    	<div style="margin-top:15px;width:100%;">
    		<span class="from-style">平面图5:</span> 
    		<input type="file" name="file5" id="jfile5" onchange="showName(this)" />
    	</div> 
		<div style="margin-top:15px;width:100%;"> 
	        <input type="button" class="btn-back" value="返回" style="float:right;margin-left:25px;margin-right:25px;"  onclick="$('#planwindow input[type=file]').val('');$('#planwindow').window('close');"> 
	         <input type="button" class="btn-sm" value="保存" style="float:right;margin-left:25px;" onclick="uploadPlan(this);"> 
		</div>
        </form>
	</div>
</body>
</html>

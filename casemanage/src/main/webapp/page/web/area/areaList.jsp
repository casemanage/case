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
    <title>重点人员管理</title>
    
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
			    pagenumber:'${Person.pageNo}',                         /* 表示初始页数 */
			    pagecount:'${Person.pageCount}',                      /* 表示总页数 */
			    totalCount:'${Person.totalCount}',
			    buttonClickCallback:PageClick                     /* 表示点击分页数按钮调用的方法 */                  
			});  
		});
		
PageClick = function(pageclickednumber) {
	$("#pager").pager({
	    pagenumber:pageclickednumber,                 /* 表示启示页 */
	    pagecount:'${Person.pageCount}',                  /* 表示最大页数pagecount */
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
	if ($('#PersonForm').form('validate')) {
		PersonForm.submit();
	}  
}
function deleteByPersonId(id){
	$.messager.confirm("删除确认","确认删除该重点人员?",function(r){  
		    if (r){  
			$.ajax({
				url : "<%=basePath%>person/jsonDeletePerson.do?personId="+id,
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
function excelChange(file){
	  if(!(/(?:xls)$/i.test(file.value)) && !(/(?:xlsx)$/i.test(file.value)) ) {
	        $.messager.alert('错误', "只允许上传xl或xlsx的文档", 'error'); 
	        if(window.ActiveXObject) {//for IE
	            file.select();//select the file ,and clear selection
	            document.selection.clear();
	        } else if(window.opera) {//for opera
	            file.type="text";file.type="file";
	        } else file.value="";//for FF,Chrome,Safari
	    } else {	
			showProcess(true, '温馨提示', '正在提交数据...'); 
	   		fileForms.submit();
	    	/* $('#fileForms').form('submit',{
				success : function(data) {
					data = $.parseJSON(data);
					if (data.code == 0) {
						$.messager.alert('保存信息', data.message, 'info',function(){
							search();
						});
						
					} else {
						$.messager.alert('错误信息', data.message, 'error');
					}  
				}
			});	  */
	    }
}
</script>
  </head>
  
  <body style="background:#fff;">
  
  <div id="contentRight"  class="contentRight"	>
	<div class="containner-fluid">
		<div class="pannel-header"></i><span>重点人员管理</span>
			<%-- <form id="fileForms" name="fileForms" action="<%=basePath%>fileUpload/uploadPersonExcel.do"  enctype="multipart/form-data" method="post" style="margin:0;padding:0;">
		       	<input type="file" name="file" id="jfile" onChange="excelChange(this);">
			</form>
			<input type="button" class="btn-add" style="margin-left:25px;" value="导入重点人员"> --%>
		</div>
		<div class="Panel-content">
			<form id="PersonForm" name="PersonForm"
				action="person/personList.do" method="get">
				<div style="width:100%;text-align:right;">
					<input type="text" name="searchName" validType="SpecialWord" class="easyui-validatebox" placeholder="按姓名或地址搜索" value="${Person.searchName}" />
					<input type="button" class="btn-add" style="margin-left:10px;"  onclick="search();" value="搜索">  
				    <input type="hidden" id="pageNumber" name="pageNo" value="${Person.pageNo}" />
					<input type="button" class="btn-add" style="margin-left:25px;"  onclick="window.location.href='<%=basePath%>person/personInfo.do?personId=0'" value="新建重点人员">
					<%-- <input type="button" class="btn-add" style="margin-left:25px;" onclick="window.location.href='<%=basePath%>fileUpload/downfile.do?filepath=source/excel/t1.xls'" value="下载导入模板"/> --%>
				</div> 
			</form> 
		</div>
		
	</div>
	<div class="containner-fluid">
		<table cellpadding="10" cellspacing="0" width="100%" class="list-info">
			<thead>
				<tr style="background-color:#D6D3D3;font-weight: bold;">
					<th width="4%" style="display:none">&nbsp;</th>
					<th>照片</th>
					<!-- <th>编号</th> -->
					<th>姓名</th>
					<th>人员类型</th>
					<th>人员级别</th>
					<th>性别</th>
					<!-- <th>出生年月</th> -->
					<!-- <th>照片</th> -->
					<th>身份证号</th>
					<th>手机号码</th>
					<th>mac地址</th>
					<th>地址</th>
					<th>事由</th>
					<th>描述</th>
					<!-- <th>警官名称</th>
					<th>看守单位</th>
					<th>警官电话</th> -->
					<!-- <th>采集人</th> -->
					<th>采集时间</th>
					<th>详情</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${personList}">
					<tr>
						<td align="center" style="display:none">${item.id}</td>
						<td	align="center">
							<img alt="头像" src="<%=basePath %>${item.photourl}" style="width:35px;height:35px">
						</td>
						<%-- <td>${item.serialno}</td> --%>
						<td	align="center">${item.name}</td>
						<td>${item.typeName}</td>
						<td>${item.levelName}</td>
						<c:if test="${item.sex == 1}">
							<td>男</td>
						</c:if>
						<c:if test="${item.sex == 0}">
							<td>女</td>
						</c:if>
						<%-- <td>${item.birth}</td> --%>
						<%-- <td>${item.photourl}</td> --%>
						<td>${item.idcard}</td>
						<td>${item.telephone}</td>
						<td>${item.macaddress}</td>
						<td>${item.address}</td>
						<td>${item.casecomment}</td>
						<td>${item.description}</td>
						<%-- <td>${item.policename}</td>
						<td>${item.policesector}</td>
						<td>${item.policephone}</td> --%>
						<%-- <td>${item.creatorname}</td> --%>
						<td>${item.createtimes}</td>
						<td><a href="javascript:void(0);" onclick="window.location.href='<%=basePath%>person/personInfo.do?personId=${item.id}'">编辑</a></td>
						<td><a href="javascript:void(0);" onclick="deleteByPersonId(${item.id});">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	  <div class="page" id="pager"></div>
	</div>
	</div>
</body>
</html>

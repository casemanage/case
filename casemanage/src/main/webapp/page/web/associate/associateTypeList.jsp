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
    <title>社会机构类型管理</title>
    
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
			    pagenumber:'${AssociateType.pageNo}',                         /* 表示初始页数 */
			    pagecount:'${AssociateType.pageCount}',                      /* 表示总页数 */
			    totalCount:'${AssociateType.totalCount}',
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
	    pagecount:'${AssociateType.pageCount}',                  /* 表示最大页数pagecount */
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
	if ($('#AssociateTypeForm').form('validate')) {
		AssociateTypeForm.submit();
	}  
}
function deleteByAssociateTypeId(id){
	$.messager.confirm("删除确认","确认删除该机构类型?",function(r){  
		    if (r){  
			$.ajax({
				url : "<%=basePath%>associate/jsonDeleteAssociateType.do?associateTypeId="+id,
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
            	<div class="pannel-header">社会机构类型管理</div>
                <div class="Panel-content">
					<form id="AssociateTypeForm" name="AssociateTypeForm"
						action="<%=basePath%>associate/associateTypeList.do" method="get"> 
							<div style="width:100%;text-align:right;">
								<input type="text" name="searchName" validType="SpecialWord" class="easyui-validatebox" placeholder="按关键字或类型搜索" value="${AssociateType.searchName}" /> 
								<input type="button" class="btn-add" style="margin-left:10px;"  onclick="search();" value="搜索">  
								<input type="hidden" id="pageNumber" name="pageNo" value="${AssociateType.pageNo}" />
								<input type="button" class="btn-add"  style="margin-left:25px;" onclick="window.location.href='<%=basePath%>associate/associateTypeInfo.do?associateTypeId=0'" value="新建社会机构类型">
							</div> 
						</form> 
                </div>
            </div>
            <div class="containner-fluid">
                <table cellpadding="10" cellspacing="0" width="100%" class="list-info">
                    <thead>
						<tr style="background-color:#D6D3D3;font-weight: bold;">
							<th width="4%" style="display:none">&nbsp;</th>							
							<th>关键字</th>
							<th>类型</th>							
							<th>描述</th>
							<th>详情</th>
							<th>操作</th> 
						</tr>
                    </thead>
                    <tbody>
                       <c:forEach var="item" items="${AssociateTypelist}">
						<tr>
							<td align="center" style="display:none">${item.id}</td>
							<td align="center">${item.keyword}</td>
							<td align="center">${item.name}</td>
							<td align="center">${item.description}</td>
							<td><a href="javascript:void(0);" onclick="window.location.href='<%=basePath%>associate/associateTypeInfo.do?associateTypeId=${item.id}'">编辑</a></td>
							<td><a href="javascript:void(0);" onclick="deleteByAssociateTypeId(${item.id});">删除</a></td>
						</tr>
					</c:forEach>
                    </tbody>
                </table>
				<div class="page" id="pager"></div> 
            </div> 
            </div>
</body>
</html>

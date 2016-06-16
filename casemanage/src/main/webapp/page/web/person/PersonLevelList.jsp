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
    <title>重点人员类型管理</title>
    
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
			    pagenumber:'${PersonLevel.pageNo}',                         /* 表示初始页数 */
			    pagecount:'${PersonLevel.pageCount}',                      /* 表示总页数 */
			    totalCount:'${PersonLevel.totalCount}',
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
	    pagecount:'${PersonLevel.pageCount}',                  /* 表示最大页数pagecount */
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
	if ($('#PersonLevelForm').form('validate')) {
		PersonLevelForm.submit();
	}  
}
 
</script>
  </head>
  
  <body style="background:#fff;">
  
  <div id="contentRight" style="width:83%;height:99%;float:right;background:#fff;"	>
        	<div class="containner-fluid">
            	<div class="pannel-header">重点人员级别管理</div>
                <div class="Panel-content">
					<form id="PersonLevelForm" name="PersonLevelForm"
						action="person/PersonLevelList.do" method="get"> 
							 <div style="width:100%;text-align:right;">
								<input type="text" name="searchName"   validType="SpecialWord" class="easyui-validatebox"  placeholder="搜索" value="${PersonLevel.searchName}" /> 
								<input type="button" class="btn-add" style="margin-left:10px;"  onclick="search();" value="搜索">  
								<input type="hidden" id="pageNumber" name="pageNo" value="${PersonLevel.pageNo}" />
			        			<input type="button" class="btn-add"  style="margin-left:25px;" onclick="window.location.href='person/PersonLevelInfo.do?personLevelId=0'" value="新建重点人员级别">
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
							<th>描述</th> 
						</tr>
                    </thead>
                    <tbody>
                       <c:forEach var="item" items="${PersonLevellist}">
						<tr>
							<td align="center" style="display:none">${item.id}</td>
							<td align="center" ondblclick="window.location.href='person/PersonLevelInfo.do?personLevelId=${item.id}'">${item.id}</td>
							<td align="center" ondblclick="window.location.href='person/PersonLevelInfo.do?personLevelId=${item.id}'">${item.name}</td>
							<td align="center" ondblclick="window.location.href='person/PersonLevelInfo.do?personLevelId=${item.id}'">${item.description}</td>
							
						</tr>
					</c:forEach>
                    </tbody>
                </table>
				<div class="page" id="pager"></div> 
            </div> 
            </div>
</body>
</html>
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
    <title>社会机构管理管理</title>
    
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
 
</script>
  </head>
  
  <body style="background:#fff;">

        	<div class="containner-fluid">
            	<div class="pannel-header">社会机构管理</div>
                <div class="Panel-content">
					<form id="AssociateForm" name="AssociateForm"
						action="associate/associateList.do" method="get"> 
							<div> 
								<input type="text" name="searchName"   validType="SpecialWord" class="easyui-validatebox" 
									placeholder="搜索" value="${associate.searchName}" /> 
								<span onclick="search();">搜索</span>
							</div> 
		
						<input type="hidden" id="pageNumber" name="pageNo"
							value="${associate.pageNo}" />
					</form> 
	        <div style="margin-top:25px;"><input type="button" class="btn-sm" onclick="window.location.href='associate/associateInfo.do?associateId=0'" value="新建社会机构"></div>
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
							<th>采集单位</th> 
							<th>采集人</th>
							<th>采集时间</th> 
							<th>描述</th> 
						</tr>
                    </thead>
                    <tbody>
                       <c:forEach var="item" items="${associatelist}">
						<tr>
							<td align="center" style="display:none">${item.id}</td>
							<td align="left" ondblclick="window.location.href='associate/associateInfo.do?associateId=${item.id}'" >${item.serialno}</td>
							<td ondblclick="window.location.href='associate/associateInfo.do?associateId=${item.id}'">${item.name}</td>
							<td ondblclick="window.location.href='associate/associateInfo.do?associateId=${item.id}'">${item.typename}</td>
							<td ondblclick="window.location.href='associate/associateInfo.do?associateId=${item.id}'">${item.latitude}，${item.longitude}</td>
							<td ondblclick="window.location.href='associate/associateInfo.do?associateId=${item.id}'">${item.address}</td>
							<td ondblclick="window.location.href='associate/associateInfo.do?associateId=${item.id}'">${item.organname}</td>
							<td ondblclick="window.location.href='associate/associateInfo.do?associateId=${item.id}'">${item.organname}</td> 
							<td ondblclick="window.location.href='associate/associateInfo.do?associateId=${item.id}'">${item.createdate}</td> 
							<td ondblclick="window.location.href='associate/associateInfo.do?associateId=${item.id}'">${item.description}</td>
							<td> 
						</tr>
					</c:forEach>
                    </tbody>
                </table>
				<div class="page" id="pager"></div> 
            </div> 
</body>
</html>

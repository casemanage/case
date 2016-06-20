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
 
</script>
  </head>
  
  <body style="background:#fff;">
  
  <div id="contentRight"  class="contentRight"	>
	<div class="containner-fluid">
		<div class="pannel-header">重点人员管理</div>
		<div class="Panel-content">
			<form id="PersonForm" name="PersonForm"
				action="person/personList.do" method="get">
				<div style="width:100%;text-align:right;">
					<input type="text" name="searchName" validType="SpecialWord" class="easyui-validatebox" placeholder="搜索" value="${Person.searchName}" />
					<input type="button" class="btn-add" style="margin-left:10px;"  onclick="search();" value="搜索">  
				    <input type="hidden" id="pageNumber" name="pageNo" value="${Person.pageNo}" />
					<input type="button" class="btn-add" style="margin-left:25px;"  onclick="window.location.href='person/personInfo.do?personId=0'" value="新建重点人员">
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
					<th>联系方式</th>
					<th>mac地址</th>
					<th>地址</th>
					<th>事由</th>
					<th>描述</th>
					<!-- <th>警官名称</th>
					<th>看守单位</th>
					<th>警官电话</th> -->
					<!-- <th>采集人</th> -->
					<th>采集时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${personList}">
					<tr>
						<td align="center" style="display:none">${item.id}</td>
						<td	align="center">
							<img alt="头像" src="<%=basePath %>${item.photourl}" style="width:35px;height:35px">
						</td>
						<%-- <td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.serialno}</td> --%>
						<td	align="center" ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.name}</td>
						<td ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.typeName}</td>
						<td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.levelName}</td>
						<c:if test="${item.sex == 1}">
							<td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">男</td>
						</c:if>
						<c:if test="${item.sex == 0}">
							<td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">女</td>
						</c:if>
						<%-- <td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.birth}</td> --%>
						<%-- <td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.photourl}</td> --%>
						<td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.idcard}</td>
						<td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.telephone}</td>
						<td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.macaddress}</td>
						<td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.address}</td>
						<td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.casecomment}</td>
						<td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.description}</td>
						<%-- <td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.policename}</td>
						<td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.policesector}</td>
						<td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.policephone}</td> --%>
						<%-- <td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.creatorname}</td> --%>
						<td	ondblclick="window.location.href='person/personInfo.do?personId=${item.id}'">${item.createtimes}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	  <div class="page" id="pager"></div>
	</div>
	</div>
</body>
</html>

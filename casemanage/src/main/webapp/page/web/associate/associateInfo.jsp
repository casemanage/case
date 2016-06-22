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
    
    <title>社会机构信息</title>
    
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
	<script
	src="${pageContext.request.contextPath}/source/js/pager/jquery.pager.js"></script>
	<link
	href="${pageContext.request.contextPath}/source/js/pager/Pager.css"
	rel="stylesheet" />
	<script type="text/javascript">
		$(document).ready(function() {
			$("#pager").pager({
			    pagenumber:'${associatePerson.pageNo}',                         /* 表示初始页数 */
			    pagecount:'${associatePerson.pageCount}',                      /* 表示总页数 */
			    totalCount:'${associatePerson.totalCount}',
			    buttonClickCallback:PageClick                     /* 表示点击分页数按钮调用的方法 */                  
			}); 
			var aId = Number($("#hid_assoId").val());
			if(aId > 0){
				var typeid = $("#typeid").val();
				$("#cmb_type").combobox("setValue",typeid);
			}
		});
		PageClick = function(pageclickednumber) {
			$("#pager").pager({
			    pagenumber:pageclickednumber,                 /* 表示启示页 */
			    pagecount:'${associatePerson.pageCount}',                  /* 表示最大页数pagecount */
			    buttonClickCallback:PageClick                 /* 表示点击页数时的调用的方法就可实现javascript分页功能 */            
			});
			
			$("#pageNumber").val(pageclickednumber);          /* 给pageNumber从新赋值 */
			/* 执行Action */
			pagesearch();
		}
		function pagesearch(){
			getAssociatePersonList();
		}
		function getAssociatePersonList(){
			var associateId = $("#hid_assoId").val();
			var pageNumber = $("#pageNumber").val();
			$.ajax({
				url : "<%=basePath%>associate/jsonLoadAssociatePersonList.do?associateId="+associateId+"&&pageNumber="+pageNumber,
				type : "post",  
				dataType:"json",
				success : function(data) { 
		  			if(data.code == 0){ 
		  				 $("#pager").pager({
						    pagenumber:data.obj.pageNo,                         /* 表示初始页数 */
						    pagecount:data.obj.pageCount,                      /* 表示总页数 */
						    totalCount:data.obj.totalCount,
						    buttonClickCallback:PageClick                     /* 表示点击分页数按钮调用的方法 */                  
						});
						$("#MemberList").html("");
						fillMemberList(data.list);
		  			}else{
						$.messager.alert('错误信息',data.message,'error');
		  			} 
				}
			});
		}
		function saveAssociste(obj){
		
			var typeId = $("#typeid").val();
			if(typeId.length == 0 || typeId ==""){
				$.messager.alert("操作提示","请选择机构类型","error");
				return;
			}
		
			if ($('#associsteForm').form('validate')) {
				$(obj).attr("onclick", "");
				$('#associsteForm').form('submit',{
									success : function(data) {
										data = $.parseJSON(data);
										if (data.code == 0) {
											$.messager.alert('保存信息',data.message,'info',
															function() {
																if(data.obj !=undefined ||data.obj != null){
																	window.location.href = "<%=basePath%>associate/associateInfo.do?associateId="+data.obj;
																}else{
																	window.location.href = "<%=basePath%>associate/associateInfo.do?associateId="+0;
																}
															});
										} else {
											$.messager.alert('错误信息',
													data.message, 'error',
													function() {
													});
											$(obj).attr("onclick",
													"saveAssociste(this);");
										}
									}
								});
			}
		}
		function deleteByAssociateId(associateId,Id){
			$.messager.confirm("删除确认","确认删除该成员?",function(r){  
		    if (r){  
			$.ajax({
				url : "<%=basePath%>associate/jsonDeleteMember.do?associateId="+associateId+"&&Id="+Id,
				type : "post",  
				dataType:"json",
				success : function(data) { 
		  			if(data.code==0){ 
		  				$.messager.alert('删除信息',data.message,'info',function(){ 
							getAssociatePersonList();
		       			});
		  			}else{
						$.messager.alert('错误信息',data.message,'error');
		  			} 
				}
			});
	    }  
	});
}
function fillMemberList(lst){
	var html = "";
	for(var i = 0; i<lst.length;i++){
		html += "<tr>";
		html += "<td align='center' style='display:none'>"+lst[i].id+"</td><td	align='center'><img alt='头像' src='<%=basePath %>"+(lst[i].photourl == null ? "":lst[i].photourl)+"style='width:35px;height:35px'></td><td align='center'>"+(lst[i].name == null ? "":lst[i].name)+"<td align='center'>"+(lst[i].sex == 0 ? "女":"男")+"</td>";
		html += "<td align='center'>"+(lst[i].birth == null ? "":lst[i].birth)+"</td><td align='center'>"+(lst[i].idcard == null ? "":lst[i].idcard)+"</td>"+"<td align='center'>"+(lst[i].address == null ? "" : lst[i].address)+"</td><td align='center'>"+(lst[i].description == null ? "" : lst[i].description)+"</td>"+"<td align='center'>"+(lst[i].isleader == 0 ?"普通人员":"负责人")+"</td>";
		html += "<td align='center'>"+(lst[i].creatorname == null ? "":lst[i].creatorname)+"</td><td align='center'>"+(lst[i].organname == null ? "":lst[i].organname)+"</td>"+"<td><a href='javascript:void(0);' onclick='deleteByAssociateId("+lst[i].associateid+","+lst[i].id+");'>"+"删除"+"</a>";
		html += "</tr>";
	}
	$("#MemberList").html(html);
} 
function back(){
	window.location.href = "<%=basePath%>associate/associateList.do";
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
     } else{
				$("#filename").val(obj.value);  
			}
}
	</script>
  </head>
  
 <body style="background:#fff;">
	
  <div id="contentRight" class="contentRight">
     	<div class="containner-fluid">
         	<div class="pannel-header">社会机构信息</div> 
               <div class="Panel-content">社会机构信息：${Associate.id == 0?"新建社会机构信息":Associate.name}</div>
      </div>
      <div id="tabInfo" class="easyui-tabs" style="width:100%;border:0;">  
    <div title="基础信息" style="padding:20px;" >  
         <div class="containner-fluid text-center"> 
			<form id="associsteForm" name="associsteForm" action="<%=basePath%>associate/jsonSaveOrUpdateAssociate.do" method="post"  enctype="multipart/form-data"  style="text-align:left;">
			<table style="width:100%;">
				<tr style="height:40px"> 
					<td rowspan="2" style="width:40%"> 
					    	<div style="margin-top:15px;width:100%;">
					    		<span class="from-style">机构名称:</span>
					    		<input type="hidden" id="hid_assoId" name="id" value="${Associate.id}" />
					    		<input type="text" required="true"  validType="Length[1,150]" class="easyui-validatebox"  style="width:354px;height:32px;" placeholder="请输入机构名称" value="${Associate.name}" name="name"/>
					    	</div>
					        <div style="margin-top:15px;">
					        	<span class="from-style">机构类型:</span>
								<input id="typeid" type="hidden"  name="typeid"   value="${Associate.typeid}" />
								<select id="cmb_type" class="easyui-combobox"  data-options="required:true,editable:false,onSelect:function(record){$('#typeid').val(record.value);}"  style="width:354px;height:35px;">  
									 <option value="">=请选择机构类型=</option>
									 <c:forEach var="item" items="${assoType }">
									 	<option value="${item.id}">${item.name }</option> 
									 </c:forEach>
								</select>  
					    	</div>
					        <div style="margin-top:15px;">
					        	<span class="from-style">机构地址:</span>
					    		<input type="text" required="true"  validType="Length[1,150]" class="easyui-validatebox"  style="width:354px;height:32px;"  placeholder="请输入地址" value="${Associate.address}" name="address"/>
					    	</div>
					    	<div style="margin-top:15px;">
					        	<span class="from-style">联系方式:</span>
					    		<input type="text"  class="easyui-validatebox"  style="width:354px;height:32px;"  placeholder="请输入联系方式" value="${Associate.telephone}" name="telephone"/>
				    		</div>
					        <div style="margin-top:15px;">
					        	<span class="from-style">坐标经度:</span>
					    		<input type="text"   style="width:354px;height:32px;" class="easyui-validatebox" placeholder="请输入经度" value="${Associate.latitude}" name="latitude"/>
					    	</div>
					        <div style="margin-top:15px;">
					        	<span class="from-style">坐标纬度:</span>
					    		<input type="text"  style="width:354px;height:32px;"  class="easyui-validatebox" placeholder="请输入纬度" value="${Associate.longitude}" name="longitude"/>
					    	</div>
					    	<div style="margin-top:15px;width:100%;">
					    		<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;平面图:</span> 
					        	<c:if test="${Associate.id >0}">
					        		<input type="text"  class="easyui-validatebox" style="width:354px;height:32px;" readonly="readonly" id="filename" value="${AssociateUrl}" />
					        	</c:if>
					        	<c:if test="${Associate.id == 0}">
					        		<input type="text"  class="easyui-validatebox" style="width:354px;height:32px;" readonly="readonly" id="filename" />
					        	</c:if> 
					    		<input type="file" name="file1" id="jfile1" onchange="showName(this)" />
					    	</div>
					        <div style="margin-top:15px;">
					        	<span class="from-style">备注描述:</span>
					        	<textarea rows="4" cols="3"   style="width:354px;"  value="${Associate.description}" name="description" ></textarea>
					    		<!-- <input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入描述信息"/> -->
					    	</div> 
						</td>
						<c:if test="${Associate.id >0}">
							<td rowspan="2" style="vertical-align: top;"> 
							<c:forEach var="item" items="${associateplanList}">
								<img alt="平面图" title="平面图" src="<%=basePath %>${item.planurl}" style="width:400px;height:120px">
							</c:forEach>
							</td> 
						</c:if>
						<td style="width:20%">
							<div style="margin-top:15px;width:100%;"> 
						        <input type="button" class="btn-back" value="返回" style="float:right;margin-left:25px;margin-right:25px;"  onclick="back();"> 
						         <input type="button" class="btn-sm" value="保存" style="float:right;margin-left:25px;" onclick="saveAssociste(this);"> 
							</div>
						</td>
					</tr> 
					<tr>
						<td></td>
					</tr>
				</table>
			</form>
	    </div>
    </div> 
    <c:if test="${Associate.id>0}">
	    <div title="相关人员" style="padding:20px;" >
	    <div class="containner-fluid">
	    	<div class="Panel-content">
		    	<div style="width:100%;text-align:right">
					<input type="button" class="btn-add"	onclick="window.location.href='<%=basePath%>associate/associateMember.do?associateId=${Associate.id}'" value="新增相关人员">
					<input type="button" class="btn-back" style="margin-left:25px;margin-right:25px"	onclick="javascript:history.back();" value="返回">
				</div>
			</div>
			</div>   
	    	<div class="containner-fluid">
				<table cellpadding="10" cellspacing="0" width="100%" class="list-info">
					<thead>
						<tr style="background-color:#D6D3D3;font-weight: bold;">
							<th width="4%" style="display:none">&nbsp;</th>
							<th align="center">照片</th>
							<th align="center">姓名</th>
							<th align="center">性别</th>
							<th align="center">出生年月</th>
							<!-- <th>照片</th> -->
							<th align="center">身份证号</th>
							<th align="center">地址</th>
							<th align="center">备注</th>
							<th align="center">人员职位</th>
							<th align="center">采集人</th>
							<th align="center">采集单位</th>
							<th align="center">操作</th>
						</tr>
					</thead>
					<tbody id="MemberList">
						<c:forEach var="item" items="${associateList}">
							<tr>
								<td align="center" style="display:none">${item.id}</td>
								<td	align="center">
									<img alt="头像" src="<%=basePath %>${item.photourl}" style="width:35px;height:35px">
								</td>
								<td	align="center">${item.name}</td>
								<c:if test="${item.sex == 1}">
									<td>男</td>
								</c:if>
								<c:if test="${item.sex == 0}">
									<td>女</td>
								</c:if>
								<td>${item.birth}</td>
								<%-- <td>${item.photourl}</td> --%>
								<td>${item.idcard}</td>
								<td>${item.address}</td>
								<td>${item.description}</td>
								<c:if test="${item.isleader == 0}">
									<td>普通人员</td>
								</c:if>
								<c:if test="${item.isleader == 1}">
									<td>负责人</td>
								</c:if>
								<td>${item.creatorname}</td>
								<td>${item.organname}</td>
								<td><a href="javascript:void(0);" onclick="deleteByAssociateId(${Associate.id},${item.id});">删除</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input type="hidden" id="pageNumber" name="pageNo" value="${associatePerson.pageNo}" /> 
			 	<div class="page" id="pager"></div>
			</div>
	    </div> 
    </c:if>  
</div> 
   
</div> 

</body>
</html>

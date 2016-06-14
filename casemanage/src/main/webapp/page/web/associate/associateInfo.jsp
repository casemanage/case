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
	<script type="text/javascript">
		$(document).ready(function() {
			var aId = Number($("#hid_assoId").val());
			if(aId > 0){
				var typeid = $("#typeid").val();
				$("#cmb_type").combobox("setValue",typeid);
			}
		});
		function saveAssociste(obj){
			if ($('#associsteForm').form('validate')) {
				$(obj).attr("onclick", "");
				$('#associsteForm').form('submit',{
									success : function(data) {
										data = $.parseJSON(data);
										if (data.code == 0) {
											$.messager.alert('保存信息',data.message,'info',
															function() {
																window.location.href = "associate/associateList.do";
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
		function getAssociateList(){
			var groupId = $("#groupId").val();
			var areaIds = $("#treeList").tree("getChecked");
			var s = "";
			for(var i=0;i<areaIds.length;i++){
				s += areaIds[i].id+",";
			}
			var deviceName = $("#deviceName").val();
			var deviceNumber = $("#deviceNumber").val();
			var pageNumber = $("#pageNumber").val();
			$.ajax({
				url : "deviceGroup/jsonLoadDeviceList.do?areaIds="+s+"&&deviceName="+deviceName+"&&deviceNumber="+deviceNumber+"&&pageNumber="+pageNumber,
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
						$("#deviceMemberList").html("");
						fillDeviceList(data.list);
		  			}else{
						$.messager.alert('错误信息',data.message,'error');
		  			} 
				}
			});
		}
		function fillDeviceList(lst){
			var html = "<tbody>";
			html += "<tr><th width='4%' style='display:none'>&nbsp;</th><th>选择成员</th><th>设备ID</th><th>设备编号</th><th>设备名称</th><th>Naming</th><th>RTSP</th><th>设备类型</th><th>设备地址</th><th>IP地址</th><th>所属区域</th></tr>";
			for(var i = 0; i<lst.length;i++){
				html += "<tr>";
				html += "<td style='display:none'>"+lst[i].id+"</td><td><input id='checkbox' type='checkbox'/>"+"</td><td align='left'>"+(lst[i].pointId == null ? "" : lst[i].pointId)+"</td><td  align='left'>"+(lst[i].pointNumber == null ? "":lst[i].pointNumber)+"</td>";
				html += "<td>"+(lst[i].pointName == null ? "":lst[i].pointName)+"</td><td>"+(lst[i].pointNaming == null ? "":lst[i].pointNaming)+"</td>"+"<td>"+(lst[i].rtspUrl == null ? "" : lst[i].rtspUrl)+"</td><td>"+(lst[i].type == null ? "" : lst[i].type)+"</td>"+"<td>"+(lst[i].address == null ? "" : lst[i].address)+"</td><td>"+(lst[i].ipAddress == null ? "" : lst[i].ipAddress)+"</td>"+"<td>"+(lst[i].areaName == null ? "" : lst[i].areaName)+"</td>";
				html += "</tr>";
			}
			html += "</tbody>";
			$("#deviceMemberList").html(html);
		}
	</script>
  </head>
  
 <body style="background:#fff;">
	
     	<div class="containner-fluid">
         	<div class="pannel-header">社会机构信息</div> 
               <div class="Panel-content">社会机构信息：${Associate.id == 0?"新建社会机构信息":Associate.name}</div>
      </div>
      <div id="tabInfo" class="easyui-tabs" style="width:99%;height:95%;border:0;">  
    <div title="基础信息" >  
         <div class="containner-fluid text-center" style="margin-top:120px;">
			<form id="associsteForm" name="associsteForm" action="associate/jsonSaveOrUpdateAssociate.do" method="post">
		    	<div style="margin-top:15px;">
		    		<span class="from-style">名称</span>
		    		<input type="hidden" id="hid_assoId" name="id" value="${Associate.id}" />
		    		<input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入机构名称" value="${Associate.name}" name="name"/>
		    	</div>
		        <div style="margin-top:15px;">
		        	<span class="from-style">类型</span>
					<input id="typeid" type="hidden"  name="typeid"   value="${Associate.typeid}" />
					<select id="cmb_type" class="easyui-combobox"  data-options="editable:false,required:true,onSelect:function(record){$('#typeid').val(record.value);}"  style="width:254px;height:32px;">  
						 <option value="">=请选择机构类型=</option>
						 <c:forEach var="item" items="${assoType }">
						 	<option value="${item.id}">${item.name }</option> 
						 </c:forEach>
					</select>  
		    	</div>
		        <div style="margin-top:15px;">
		        	<span class="from-style">经度</span>
		    		<input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入经度" value="${Associate.latitude}" name="latitude"/>
		    	</div>
		        <div style="margin-top:15px;">
		        	<span class="from-style">维度</span>
		    		<input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入维度" value="${Associate.longitude}" name="longitude"/>
		    	</div>
		        <div style="margin-top:15px;">
		        	<span class="from-style">地址</span>
		    		<input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入地址" value="${Associate.address}" name="address"/>
		    	</div>
		        <div style="margin-top:15px;">
		        	<span class="from-style">描述</span>
		    		<input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入描述信息" value="${Associate.description}" name="description"/>
		    	</div> 
		        <div style="margin-top:15px;">
		        	<span class="from-style">采集单位</span>
		    		<input type="text"  disabled="disabled"  class="easyui-validatebox" placeholder="请输入采集单位" value="${Associate.organname}" name="organname"/>
		    	</div> 
		        <div style="margin-top:15px;">
		        	<span class="from-style">采集人</span>
		    		<input type="text" disabled="disabled" class="easyui-validatebox" placeholder="请输入采集人" value="${Associate.creatorname}" name="creatorname"/>
		    	</div> 
		        <div style="margin-top:25px;"><input type="button" class="btn-sm" value="保存" onclick="saveAssociste(this);"></div>
		        <div style="margin-top:25px;"><input type="button" class="btn-sm" value="返回" onclick="javascript:history.back();"></div>
			</form>
	    </div>
    </div> 
    <c:if test="${Associate.id>0}">
	    <div title="相关人员">
	    <div class="containner-fluid">
	    	<div class="Panel-content">
		    	<div style="margin-top:25px;">
					<input type="button" class="btn-sm"	onclick="window.location.href='associate/associateMember.do?associateId=${Associate.id}'" value="新增相关人员">
					<input type="button" class="btn-sm"	onclick="javascript:history.back();" value="返回">
				</div>
			</div>
			</div>   
	    	<div class="containner-fluid">
				<table cellpadding="10" cellspacing="0" width="100%" class="list-info">
					<thead>
						<tr style="background-color:#D6D3D3;font-weight: bold;">
							<th width="4%" style="display:none">&nbsp;</th>
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
					<tbody>
						<c:forEach var="item" items="${associateList}">
							<tr>
								<td align="center" style="display:none">${item.id}</td>
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
									<td>普通员工</td>
								</c:if>
								<c:if test="${item.isleader == 1}">
									<td>负责人</td>
								</c:if>
								<td>${item.creatorname}</td>
								<td>${item.organname}</td>
								<td><input type="button" onclick="deleteByAssociateId(${Associate.id});" value="X"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			 <!--  <div class="page" id="pager"></div> -->
			</div>
	    </div> 
    </c:if>  
</div> 
   

</body>
</html>

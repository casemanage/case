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
					<form id="devicePointForm" name="devicePointForm" action="device/jsonSaveOrUpdatePoint.do" method="post">
				    	<div style="margin-top:15px;">
				    		<span class="from-style">名称</span>
				    		<input type="hidden" id="hid_assoId" value="${Associate.id}" />
				    		<input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入机构名称" value="${Associate.name}" />
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
				    		<input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入经度" value="${Associate.latitude}" />
				    	</div>
				        <div style="margin-top:15px;">
				        	<span class="from-style">维度</span>
				    		<input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入维度" value="${Associate.longitude}" />
				    	</div>
				        <div style="margin-top:15px;">
				        	<span class="from-style">地址</span>
				    		<input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入地址" value="${Associate.address}" />
				    	</div>
				        <div style="margin-top:15px;">
				        	<span class="from-style">描述</span>
				    		<input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入描述信息" value="${Associate.description}" />
				    	</div> 
				        <div style="margin-top:15px;">
				        	<span class="from-style">采集单位</span>
				    		<input type="text"  disabled="disabled"  class="easyui-validatebox" placeholder="请输入采集单位" value="${Associate.organname}" />
				    	</div> 
				        <div style="margin-top:15px;">
				        	<span class="from-style">采集人</span>
				    		<input type="text" disabled="disabled" class="easyui-validatebox" placeholder="请输入采集人" value="${Associate.creatorname}" />
				    	</div> 
				        <div style="margin-top:25px;"><input type="button" class="btn-sm" value="保存"></div>
				        <div style="margin-top:25px;"><input type="button" class="btn-sm" value="返回" onclick="javascript:history.back();"></div>
					</form>
			    </div>
		    </div>  
		    <div title="相关人员" >   
		    	
		    </div>  
		</div> 
   

</body>
</html>

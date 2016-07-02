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
    
    <title>区域信息</title>
    
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
$(document).ready(function(){ 
		//setShowStates();
		var de = $("#description").val();
		$("#textarea").val(de);
		 $("#cmbParentArea").combotree({
			 url: '<%=basePath%>area/jsonLoadAreaTreeList.do',  
  				 required: false,
  				 onBeforeExpand:function(node){
  				 	$('#cmbParentArea').combotree('tree').tree('options').url = '<%=basePath%>area/jsonLoadAreaTreeList.do?pid='+ node.id;
  				 },
  				 onSelect:function(record){
  				 	 var areaId = $("#areaId").val();
  				 	 if(areaId != undefined && areaId != 0 && areaId !="" && areaId != null){
  				 	 if(record!=null){
	   				 	 if(areaId == record.id){
	   				 	 	$.messager.alert('错误信息',"不能选择当前区域为所属区域",'error',function(){
		        			});
	   				 	 	$("#cmbParentArea").combotree("clear");
   				 	 		$("#cmbParentArea").combotree("reload",'<%=basePath%>area/jsonLoadAreaTreeList.do?pid='+0);
	   				 	 	$("#cmbParentArea").combotree("setText","=请选择所属区域=");
	   				 	 }else{
		   				 	 var parId = $("#parentId").val();
		   				 	 if(record.id  == parId){
		   				 	 	//$("#cmbParentCompany").combotree("setValue",0);
		   				 	 	$("#cmbParentArea").combotree("clear");
		   				 	 	$("#parentId").val(0);
	   				 	 		$("#cmbParentArea").combotree("reload",'<%=basePath%>area/jsonLoadAreaTreeList.do?pid='+0);
		   				 	 	$("#cmbParentArea").combotree("setText","=请选择所属区域=");
		   				 	 }else{  
		   				 	 	$("#parentId").val(record.id);
		   				 	 }   
	   				 	 }
   				 } else{
  				 	 	$("#cmbParentArea").combotree("clear");
  				 	 	$("#parentId").val(0);
 				 	 	$("#cmbParentArea").combotree("reload",'<%=basePath%>area/jsonLoadAreaTreeList.do?pid='+0);
  				 	 	$("#cmbParentArea").combotree("setText","=请选择所属区域=");
   				 } 
				 //appendParentNode();
				 }else{
   				 	 var parId = $("#parentId").val();
   				 	 if(record.id  == parId){
   				 	 	//$("#cmbParentCompany").combotree("setValue",0);
   				 	 	$("#cmbParentArea").combotree("clear");
   				 	 	$("#parentId").val(0);
  				 	 		$("#cmbParentArea").combotree("reload",'<%=basePath%>area/jsonLoadAreaTreeList.do?pid='+0);
   				 	 	$("#cmbParentArea").combotree("setText","=请选择所属区域=");
   				 	 }else{  
   				 	 	$("#parentId").val(record.id);
   				 	 }   
  				 	 }
  				 },
  				 onLoadSuccess:function(){
				//$("#cmbParentArea").combotree("disable",true);
				var parentId = $("#parentId").val();
				var parentName = $("#parentName").val();
				if(parentId==0){
  				 		$("#cmbParentArea").combotree("setText","=请选择所属区域=");
				}else{
					//appendParentNode();
					$("#cmbParentArea").combotree("setValue",parentId);
					$("#cmbParentArea").combotree("setText",parentName);
				}
  				 }
		}); 
	});	

function saveArea(obj){
if ($('#areaInfoForm').form('validate')) {
	showProcess(true, '温馨提示', '正在提交数据...'); 
	 $('#areaInfoForm').form('submit',{
	  		success:function(data){ 
				showProcess(false);
	  			data = $.parseJSON(data);
	  			if(data.code==0){	  					
	  				$.messager.alert('保存信息',data.message,'info',function(){
  						window.location.href="<%=basePath%>area/areaList.do";
	        			});
		  			}else{
						$.messager.alert('错误信息',data.message,'error',function(){
	        			});
		  			}
		  		}
		  	 });  
	}
}  
function getKeyword(){
	var keyword = $("#key").val();
	$("#keyword").val(keyword);
}

	</script> 
  </head>
  
 <body style="background:#fff;">
	
  <div id="contentRight" class="contentRight">
       	<div class="containner-fluid">
           	<div class="pannel-header">区域信息</div>          
                 <div class="Panel-content">区域信息：${area.id == 0?"新建社会机构类型信息":area.name}</div>                       
        	</div>
       
    <div class="containner-fluid text-center">
		<form id="areaInfoForm" name="areaInfoForm" action="<%=basePath%>area/jsonSaveOrUpdateArea.do" method="post" style="text-align:left;">
			<div style="margin-top:15px;width:100%;"> 
		        <input type="button" class="btn-back" value="返回" style="float:right;margin-left:25px;margin-right:25px;"  onclick="javascript:history.back();"> 
		        <input type="button" class="btn-sm" value="保存" style="float:right;margin-left:25px;" onclick="saveArea(this);">
				<input name="id" value="${area.id}" type="hidden" />
				<input id="parentId" name="parentid" value="${area.parentid}" type="hidden"/>
				<input id="parentName" name="parentName" value="${area.parentName}" type="hidden" />
			</div> 
		    <div style="margin-top:15px;width:100%;">
	        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;区域名称:</span>
	    		<input type="text" required="true"  validType="Length[1,20]" class="easyui-validatebox"  style="width:354px;height:32px;"  placeholder="请输入区域名称" value="${area.name}" name="name"/>
	    	</div>
		    <div style="margin-top:15px;width:100%;">
	        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;所属区域:</span>
	    		<input id="cmbParentArea" class="easyui-combotree"  style="width:354px;height:32px;" />
	    	</div>
		    <div style="margin-top:15px;width:100%;">
	        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;经&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度:</span>
	    		<input type="text" style="width:354px;height:32px;" class="easyui-numberbox" precision="1" min="0.0" placeholder="请输入经度" value="${area.latitude}" name="latitude"/>
	    	</div>
		    <div style="margin-top:15px;width:100%;">
	        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;纬&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度:</span>
	    		<input type="text"  style="width:354px;height:32px;"  class="easyui-numberbox" precision="1" min="0.0" placeholder="请输入纬度" value="${area.longtiude}" name="longtiude"/>
	    	</div>
	    	<div style="margin-top:15px;">
	        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;备注描述:</span>
	        	<input type="hidden" id="description" value="${area.description}"/>
	        	<textarea id="textarea" rows="4" cols="3"  class="textarea easyui-validatebox" placeholder="请输入备注描述" style="width:350px;height:100px;"   name="description" ></textarea>
	    	</div>    
		</form>
    </div>
</div>
</body>
</html>

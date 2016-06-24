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
	
	function saveAssociateType(obj){
	if ($('#associateTypeInfoForm').form('validate')) {
		showProcess(true, '温馨提示', '正在提交数据...'); 
		 $('#associateTypeInfoForm').form('submit',{
		  		success:function(data){ 
					showProcess(false);
		  			data = $.parseJSON(data);
		  			if(data.code==0){	  					
		  				$.messager.alert('保存信息',data.message,'info',function(){
	  						window.location.href="<%=basePath%>associate/associateTypeList.do";
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
                 <div class="Panel-content">区域信息：${area.id == 0?"新建社会机构类型信息":AssociateType.name}</div>                       
        </div>
       
    <div class="containner-fluid text-center">
		<form id="associateTypeInfoForm" name="associateTypeInfoForm" action="<%=basePath%>associate/jsonSaveOrUpdateAssociateType.do" method="post" style="text-align:left;">
			<div style="margin-top:15px;width:100%;"> 
		        <input type="button" class="btn-back" value="返回" style="float:right;margin-left:25px;margin-right:25px;"  onclick="javascript:history.back();"> 
		         <input type="button" class="btn-sm" value="保存" style="float:right;margin-left:25px;" onclick="saveAssociateType(this);">
		         <input id="keyword" name="keyword" value="${AssociateType.keyword}" type="hidden" /> 
			</div> 
			<c:if test="${AssociateType.id > 0 }">
		    <div style="margin-top:15px;width:100%;">
	        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;关键字:</span>
	    		<input name="id" value="${AssociateType.id}" type="hidden" />
	    		<input  type="text" disabled="disabled" required="true"  validType="Length[1,20]" class="easyui-validatebox"  style="width:354px;height:32px;"  placeholder="请输入关键字" value="${AssociateType.keyword}" />
	    	</div>
	    	</c:if>
	    	<c:if test="${AssociateType.id == 0 }">
		    <div style="margin-top:15px;width:100%;">
	        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;关键字:</span>
	    		<input name="id" value="${AssociateType.id}" type="hidden" />
	    		<input id="key" type="text" onchange="getKeyword();" required="true"  validType="Length[1,20]" class="easyui-validatebox"  style="width:354px;height:32px;"  placeholder="请输入关键字"/>
	    	</div>
	    	</c:if>
		    	<div style="margin-top:15px;width:100%;">
	        	<span class="from-style">类型名称:</span>
	    		<input type="text" name="name" required="true"  validType="Length[1,20]" class="easyui-validatebox"  style="width:354px;height:32px;"  placeholder="请输入类型" value="${AssociateType.name}" />
	    	</div>
		    	<div style="margin-top:15px;width:100%;">
	        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;描述:</span>
	    		<input type="text" name="description"  validType="Length[1,100]" class="easyui-validatebox"  style="width:354px;height:32px;"  placeholder="请输入描述信息" value="${AssociateType.description}" />
	    	</div>   
		</form>
    </div>
</div>
</body>
</html>

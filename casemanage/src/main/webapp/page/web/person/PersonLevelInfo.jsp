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
    
    <title>重点人员级别信息</title>
    
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
	 		var aId = Number($("#hid_levelId").val());
	 		if(aId > 0){
	 			var levelid = $("#levelid").val();
	 			$("#cmb_level").combobox("setValue",levelid);
	 		}
	 	});
	 	
	function savePersonLevel(obj){
	if ($('#personLevelInfoForm').form('validate')) {
		 $(obj).attr("onclick", ""); 
		showProcess(true, '温馨提示', '正在提交数据...'); 
		 $('#personLevelInfoForm').form('submit',{
		  		success:function(data){ 
		  			data = $.parseJSON(data);
		  			if(data.code==0){	  					
		  				$.messager.alert('保存信息',data.message,'info',function(){
	  						window.location.href="person/PersonLevelList.do";
	        			});
		  			}else{
						$.messager.alert('错误信息',data.message,'error',function(){
	        			});
						$(obj).attr("onclick", "savePersonLevel(this);"); 						
		  			}
		  		}
		  	 });  
	}
}     
	</script> 
  </head>
  
 <body style="background:#fff;">
	
       	<div class="containner-fluid">
           	<div class="pannel-header">重点人员级别信息</div> 
           	<div class="fl">
                 <div class="Panel-content">重点人员级别信息：${PersonLevel.id == 0?"新建重点人员级别信息":PersonLevel.name}</div>
               </div>  
                 <div class="fr">					
					<div style="margin-top:25px;"><input type="button" class="btn-sm" value="保存" onclick="savePersonLevel(this);"></div>
	                <div style="margin-top:25px;"><input type="button" class="btn-sm" value="返回" onclick="javascript:history.back();"></div>
				</div>
        </div>
       
    <div class="containner-fluid text-center" style="margin-top:120px;">
		<form id="personLevelInfoForm" name="personLevelInfoForm" action="person/jsonSaveOrUpdatePersonLevel.do" method="post">
	    	
	        <div style="margin-top:15px;display:none">
	        	<span class="from-style">编号</span>
	    		<input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入关键字" value="${PersonLevel.id}" />
	    	</div>
	        <div style="margin-top:15px;">
	        	<span class="from-style">名称</span>
	        	<input type="hidden" id="hid_levelId" name="id" value="${PersonLevel.id}" />
	    		<input type="text" validType="Length[1,30]" class="easyui-validatebox" placeholder="请输入姓名" value="${PersonLevel.name}" name="name"/>
	    	</div>
	        <div style="margin-top:15px;">
	        	<span class="from-style">描述</span>
	    		<input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入描述信息" value="${PersonLevel.description}" name="description" />
	    	</div> 
	        
		</form>
    </div>

</body>
</html>
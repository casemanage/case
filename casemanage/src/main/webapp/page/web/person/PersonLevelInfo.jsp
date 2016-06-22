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
	   
	function savePersonLevel(obj){
	if ($('#personLevelInfoForm').form('validate')) {
		 $('#personLevelInfoForm').form('submit',{
		  		success:function(data){ 
		  			data = $.parseJSON(data);
		  			if(data.code==0){	  					
		  				$.messager.alert('保存信息',data.message,'info',function(){
	  						window.location.href="<%=basePath%>person/PersonLevelList.do";
	        			});
		  			}else{
						$.messager.alert('错误信息',data.message,'error',function(){
	        			});
		  			}
		  		}
		  	 });  
	}
}     
	</script> 
  </head>
  
 <body style="background:#fff;">
	
  <div id="contentRight" class="contentRight">
       	<div class="containner-fluid">
           	<div class="pannel-header">重点人员级别信息</div> 
           	<div class="fl">
                 <div class="Panel-content">重点人员级别信息：${PersonLevel.id == 0?"新建重点人员级别信息":PersonLevel.name}</div>
               </div>   
        </div>
       
    <div class="containner-fluid text-center">
		<form id="personLevelInfoForm" name="personLevelInfoForm" action="<%=basePath%>person/jsonSaveOrUpdatePersonLevel.do" method="post" style="text-align:left">
    		<div style="margin-top:15px;width:100%;"> 
		        <input type="button" class="btn-back" value="返回" style="float:right;margin-left:25px;margin-right:25px;"  onclick="javascript:history.back();"> 
		         <input type="button" class="btn-sm" value="保存" style="float:right;margin-left:25px;" onclick="savePersonLevel(this);"> 
			</div> 
	        <div style="margin-top:15px;">
	        	<span class="from-style">人员级别:</span>
	        	<input type="hidden" id="hid_levelId" name="id" value="${PersonLevel.id}" />
	    		<input type="text" required="true" validType="Length[1,30]" class="easyui-validatebox" placeholder="请输入级别" style="width:354px;height:32px;" value="${PersonLevel.name}" name="name"/>
	    	</div>
	        <div style="margin-top:15px;">
	        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;描述:</span>
	    		<input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入描述信息" style="width:354px;height:32px;" value="${PersonLevel.description}" name="description" />
	    	</div> 
	        
		</form>
    </div>
</div>
</body>
</html>
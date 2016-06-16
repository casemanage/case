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
    
    <title>重点人员车辆信息</title>
    
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
	 	
	function savePersonCar(obj){
	if ($('#personCarInfoForm').form('validate')) {
		 $(obj).attr("onclick", ""); 
		showProcess(true, '温馨提示', '正在提交数据...'); 
		 $('#personCarInfoForm').form('submit',{
		  		success:function(data){ 
					showProcess(false);
		  			data = $.parseJSON(data);
		  			if(data.code==0){	  					
		  				$.messager.alert('保存信息',data.message,'info',function(){
	        			});
	  					window.location.href="person/personCarList.do";
		  			}else{
						$.messager.alert('错误信息',data.message,'error',function(){
	        			});
						$(obj).attr("onclick", "savePersonCar(this);"); 						
		  			}
		  		}
		  	 });  
	}
}     
	</script> 
  </head>
  
 <body style="background:#fff;">	
 
  <div id="contentRight" style="width:83%;height:99%;float:right;background:#fff;"	>
       	<div class="containner-fluid">
           	<div class="pannel-header">重点人员车辆信息</div>         
                 <div class="Panel-content">重点人员车辆：${PersonCar.id == 0?"新建重点人员车辆信息":PersonCar.name}</div>              											
        </div>       
    <div class="containner-fluid text-center" >
		<form id="personCarInfoForm" name="personCarInfoForm" action="person/jsonSaveOrUpdatePersonCar.do" method="post" style="text-align:left">
    		<div style="margin-top:15px;width:100%;"> 
		        <input type="button" class="btn-back" value="返回" style="float:right;margin-left:25px;margin-right:25px;"  onclick="javascript:history.back();"> 
		         <input type="button" class="btn-sm" value="保存" style="float:right;margin-left:25px;" onclick="savePersonCar(this);"> 
			</div> 
	        <div style="margin-top:15px;">
	        	<span class="from-style">车牌号码:</span><input name="id" value="${PersonCar.id}" type="hidden" />
	    		<input type="text" name="number"  required="true"  validType="Length[1,30]" class="easyui-validatebox" placeholder="请输入车牌号" style="width:354px;height:32px;"  value="${PersonCar.number}" />
	    	</div>
	        <div style="margin-top:15px;">
	        	<span class="from-style">车主姓名:</span>
	    		<input type="text" name="name"  required="true"  validType="Length[1,30]" class="easyui-validatebox" placeholder="请输入车主姓名" style="width:354px;height:32px;"  value="${PersonCar.name}" />
	    	</div>
	    	 <div style="margin-top:15px;">
	        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;描述:</span>
	    		<input type="text" name="description" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入详细描述" style="width:354px;height:32px;"  value="${PersonCar.description}" />
	    	</div>	               
		</form>
    </div>
</div>
</body>
</html>
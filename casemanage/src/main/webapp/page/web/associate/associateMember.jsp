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
    
    <title>新增社会成员</title>
    
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
		function setSex(){
			var value = $("input[name='sex1']:checked").val();
			$("#sex").val(value);
		}  
		function setLeader(){
			var value = $("input[name='leader']:checked").val();
			$("#isleader").val(value);
		} 
		function savePerson(obj){
			var associateId = $("#associateId").val();
			if ($('#personForm').form('validate')) {
				$(obj).attr("onclick", "");
				$('#personForm').form('submit',{
									success : function(data) {
										data = $.parseJSON(data);
										if (data.code == 0) {
											$.messager.alert('保存信息',data.message,'info',
															function() {
																window.location.href = "associate/associateInfo.do?associateId="+associateId;
															});
										} else {
											$.messager.alert('错误信息',
													data.message, 'error',
													function() {
													});
											$(obj).attr("onclick",
													"savePerson(this);");
										}
									}
								});
			}
		}
	</script>
  </head>
  
 <body style="background:#fff;">
	
       	<div class="containner-fluid">
           	<div class="pannel-header">机构人员信息</div> 
                 <div class="Panel-content">机构名称：${Associate.name}</div>
        	</div>
       
    <div class="containner-fluid text-center" style="margin-top:120px;">
		<form id="personForm" name="personForm" action="associate/jsonUpdateMember.do" method="post">
	    	<div style="margin-top:15px;">
	    		<span class="from-style">姓名</span>
	    		<input type="hidden" id="associateId" name="associateid" value="${Associate.id}" />
	    		<input type="text" validType="Length[1,30]" required="true" class="easyui-validatebox" placeholder="请输入人员姓名" name="name"/>
	    	</div>
	    	<div style="margin-top:15px;">
	        	<span class="from-style">性别</span>
	        	<input id="sex" type="hidden"  name="sex" id="sex"  value="${person.sex}" />
	    		<input type="radio" name="sex1" id="radio1" value="1" onchange='setSex();'>男
				<input type="radio" name="sex1" id="radio2" value="0" onchange='setSex();'>女 
	    	</div>
	    	<div style="margin-top:15px;">
	        	<span class="from-style">出生年月</span>
	    		<input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入出生年月" value="${person.birth}" name="birth"/>
	    	</div>
	    	<div style="margin-top:15px;">
	        	<span class="from-style">身份证号</span>
	    		<input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入身份证号" value="${person.idcard}" name="idcard"/>
	    	</div>
	    	<div style="margin-top:15px;">
	        	<span class="from-style">地址</span>
	    		<input type="text"  class="easyui-validatebox" placeholder="请输入地址" value="${person.address}" name="address"/>
	    	</div> 
	    	<div style="margin-top:15px;">
	        	<span class="from-style">描述</span>
	    		<input type="text"  class="easyui-validatebox" placeholder="请输入描述" value="${person.description}" name="description"/>
	    	</div>
	    	<div style="margin-top:15px;">
	        	<span class="from-style">是否为负责人</span>
	        	<input id="isleader" type="hidden"  name="isleader" id="isleader"/>
	    		<input type="radio" name="leader" id="radio3" value="1" onchange='setLeader();'>是
				<input type="radio" name="leader" id="radio4" value="0" onchange='setLeader();'>否 
	    	</div>
	        <div style="margin-top:25px;"><input type="button" class="btn-sm" value="保存" onclick="savePerson(this);"></div>
	        <div style="margin-top:25px;"><input type="button" class="btn-sm" value="返回" onclick="javascript:history.back();"></div>
		</form>
    </div>
</body>
</html>

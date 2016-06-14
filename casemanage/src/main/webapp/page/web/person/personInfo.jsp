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
    
    <title>重要人员信息</title>
    
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
				var levelid = $("#levelid").val();
				$("#cmb_level").combobox("setValue",levelid);
			}
			var sex = $("#sex").val();
			if(sex == 1){
				$("#radio1").attr("checked",'checked');
			}else if(sex ==0){
				$("#radio2").attr("checked",'checked');
			}else{
			}
		});
		function setSex(){
			var value = $('input:radio:checked').val();
			$("#sex").val(value);
		}   
		function savePerson(obj){
			if ($('#personForm').form('validate')) {
				$(obj).attr("onclick", "");
				$('#personForm').form('submit',{
									success : function(data) {
										data = $.parseJSON(data);
										if (data.code == 0) {
											$.messager.alert('保存信息',data.message,'info',
															function() {
																window.location.href = "person/personList.do";
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
           	<div class="pannel-header">重要人员信息</div> 
                 <div class="Panel-content">重要人员信息：${person.id == 0?"新建重要人员信息":person.name}</div>
        	</div>
       
    <div class="containner-fluid text-center" style="margin-top:120px;">
		<form id="personForm" name="personForm" action="person/jsonSaveOrUpdatePerson.do" method="post">
	    	<div style="margin-top:15px;">
	    		<span class="from-style">姓名</span>
	    		<input type="hidden" id="hid_assoId" name="id" value="${person.id}" />
	    		<input type="text" validType="Length[1,30]" required="true" class="easyui-validatebox" placeholder="请输入人员姓名" value="${person.name}" name="name"/>
	    	</div>
	        <div style="margin-top:15px;">
	        	<span class="from-style">人员类型</span>
				<input id="typeid" type="hidden"  name="typeid"   value="${person.typeid}" />
				<select id="cmb_type" class="easyui-combobox" data-options="editable:false,required:true,onSelect:function(record){$('#typeid').val(record.value);}"  style="width:254px;height:32px;">  
					 <option value="">=请选择人员类型=</option>
					 <c:forEach var="item" items="${personType}">
					 	<option value="${item.id}">${item.name }</option> 
					 </c:forEach>
				</select>  
	    	</div>
	    	<div style="margin-top:15px;">
	        	<span class="from-style">人员级别</span>
				<input id="levelid" type="hidden"  name="levelid"   value="${person.levelid}" />
				<select id="cmb_level" class="easyui-combobox" required="true" data-options="editable:false,required:true,onSelect:function(record){$('#levelid').val(record.value);}"  style="width:254px;height:32px;">  
					 <option value="">=请选择人员级别=</option>
					 <c:forEach var="item" items="${personLevel}">
					 	<option value="${item.id}">${item.name }</option> 
					 </c:forEach>
				</select>  
	    	</div>
	        <div style="margin-top:15px;">
	        	<span class="from-style">性别</span>
	        	<input id="sex" type="hidden"  name="sex" id="sex"  value="${person.sex}" />
	    		<input type="radio" name="radio" id="radio1" value="1" onchange='setSex();'>男
				<input type="radio" name="radio" id="radio2" value="0" onchange='setSex();'>女 
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
	    	<c:if test="${person.id>0}">
		        <div style="margin-top:15px;">
		        	<span class="from-style">编号</span>
		    		<input type="text" disabled="disabled" class="easyui-validatebox"  value="${person.serialno}" name="serialno"/>
		    	</div> 
	    	</c:if>
	    	<div style="margin-top:15px;">
	        	<span class="from-style">事由</span>
	    		<input type="text" validType="SpecialWord" class="easyui-validatebox" placeholder="请输入是由" value="${person.casecomment}" name="casecomment"/>
	    	</div> 
	        <div style="margin-top:15px;">
	        	<span class="from-style">描述</span>
	    		<input type="text"  class="easyui-validatebox" placeholder="请输入描述" value="${person.description}" name="description"/>
	    	</div>
	    	<div style="margin-top:15px;">
	        	<span class="from-style">mac地址</span>
	    		<input type="text" class="easyui-validatebox" placeholder="请输入mac地址" value="${person.macaddress}" name="macaddress"/>
	    	</div>
	    	<c:if test="${person.id>0}">
		    	<%-- <div style="margin-top:15px;">
		        	<span class="from-style">采集人Id</span>
		    		<input type="text" disabled="disabled" class="easyui-validatebox" value="${person.creator}" name="creator"/>
		    	</div> --%>
		    	<div style="margin-top:15px;">
		        	<span class="from-style">采集人</span>
		    		<input type="text" disabled="disabled" class="easyui-validatebox" value="${person.creatorname}" name="creatorname"/>
		    	</div>
		    	<div style="margin-top:15px;">
		        	<span class="from-style">采集单位</span>
		    		<input type="text" disabled="disabled" class="easyui-validatebox" value="${person.organname}" name="organname"/>
		    	</div>
	    	</c:if>
	        <div style="margin-top:25px;"><input type="button" class="btn-sm" value="保存" onclick="savePerson(this);"></div>
	        <div style="margin-top:25px;"><input type="button" class="btn-sm" value="返回" onclick="javascript:history.back();"></div>
		</form>
    </div>
</body>
</html>

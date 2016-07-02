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
		 $(document).ready(function() {
			var aId = Number($("#hid_assoId").val());
			if(aId > 0){ 
				var birth = $("#hid_birth").val();
				$("#dtb_birth").datebox("setValue",birth); 
			}
			var sex = $("#sex").val();
			if(sex == 1 && sex != ""){
				$("#radio1").attr("checked",'checked');
			}else if(sex == 0 && sex != ""){
				$("#radio2").attr("checked",'checked');
			}else{
			}
			var isleader = $("#isleader").val();
			if(isleader == 1 && isleader != ""){
				$("#radio3").attr("checked",'checked');
			}else if(isleader == 0 && isleader != ""){
				$("#radio4").attr("checked",'checked');
			}else{
			}
		});
		function setSex(){
			var value = $("input[name='sex1']:checked").val();
			$("#sex").val(value);
		}  
		function setLeader(){
			var value = $("input[name='leader']:checked").val();
			$("#isleader").val(value);
		} 
		function sDateSelect(date){
			var month = date.getMonth() + 1;
			if(month<10){
				month = "0"+ month;
			}
			var day = date.getDate();
			if(day<10){
				day = "0"+ day;
			}
			$("#hid_birth").val(date.getFullYear()+"-"+month+"-"+day);
		}
		function savePerson(obj){
			var idcard = $("#txt_idCard").val();
			if(idcard.length>0){
				if(idcard.length != 18 && idcard.length != 15 ){
					$.messger.alert("操作提示","请输入正确的15位或18位身份证号码","error");
					return;
				}
			}
			var associateId = $("#associateId").val();
			if ($('#personForm').form('validate')) {
				$(obj).attr("onclick", "");
				$('#personForm').form('submit',{
									success : function(data) {
										data = $.parseJSON(data);
										if (data.code == 0) {
											$.messager.alert('保存信息',data.message,'info',
															function() {
																window.location.href = "<%=basePath%>associate/associateInfo.do?typeKey=1&associateId="+associateId;
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
		function showName(obj){
			 if(!(/(?:jpg)$/i.test(obj.value))&&!(/(?:jpeg)$/i.test(obj.value))&&!(/(?:png)$/i.test(obj.value))) {
                $.messager.alert('错误', "选择头像文件格式错误", 'error');
                if(window.ActiveXObject) {//for IE
                    obj.select();//lect the file ,and clear selection
                    document.selection.clear();
                } else if(window.opera) {//for opera
                    obj.type="text";
                    obj.type="file";
                } else obj.value="";//for FF,Chrome,Safari
            }else{
				$("#filename").val(obj.value); 
			}
		}
	</script>
  </head>
  
 <body style="background:#fff;">
	
  <div id="contentRight" class="contentRight">
       	<div class="containner-fluid">
           	<div class="pannel-header">机构人员信息</div> 
                 <div class="Panel-content" style="float:left;">机构名称：${Associate.name}</div>
                 
						<div style="float;right; margin-top:5px;">  
					        <input type="button" class="btn-back" value="返回" style="float:right;margin-left:25px;margin-right:25px;"  onclick="javascript:history.back();"> 
					         <input type="button" class="btn-sm" value="保存" style="float:right;margin-left:25px;" onclick="savePerson(this);">  
						</div>
        	</div>
       
    <div class="containner-fluid text-center">
		<form id="personForm" name="personForm" action="<%=basePath%>associate/jsonUpdateMember.do" method="post" enctype="multipart/form-data"   style="text-align:left">
			<table style="width:100%;">
				<tr style="height:40px"> 
					<td rowspan="2" style="width:70%"> 
				    	<div style="margin-top:15px;">
				    		<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:</span>
				    		<input type="hidden" id="associateId" name="associateid" value="${Associate.id}" />
				    		<input type="hidden" id="hid_assoId" name="id" value="${Person.id}" />
				    		<input type="text" required="true"  validType="Length[1,30]" required="true" style="width:354px;height:32px;"  value="${Person.name}"  class="easyui-validatebox" placeholder="请输入人员姓名" name="name"/>
				    	</div>
				    	<div style="margin-top:15px;">
				        	<span class="from-style">身份证号:</span>
				    		<input type="text" id="txt_idCard" required="true"  onblur="validatationBirth(this);"  validType="Length[1,18]"    class="easyui-validatebox" style="width:354px;height:32px;"  placeholder="请输入身份证号" value="${Person.idcard}" name="idcard"/>
				    	</div> 
				    	<div style="margin-top:15px;">
				        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别:</span>
				        	<input id="sex" type="hidden"  name="sex" id="sex"  value="${Person.sex == null ?1:Person.sex}"  />
				    		<input type="radio" name="sex1" id="radio1" checked="checked"  value="1" onchange='setSex();'>男
							<input type="radio" name="sex1" id="radio2" value="0" onchange='setSex();'>女 
				    	</div>
				    	<div style="margin-top:15px;">
				        	<span class="from-style">出生年月:</span>
				        	<input type="text" id="dtb_birth" class="easyui-datebox" data-options="editable:false,panelWidth:354,require:true,onSelect:sDateSelect" style="width:354px;height:32px;" /> 
				    		<input type="hidden" id="hid_birth" value="${Person.birth}" name="birth"/> 
				    	</div> 
				    	<div style="margin-top:15px;">
				        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址:</span>
				    		<input type="text"  class="easyui-validatebox" placeholder="请输入地址"  style="width:354px;height:32px;" value="${Person.address}" name="address"/>
				    	</div> 
				    	<div style="margin-top:15px;">
				        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;描述:</span>
				    		<input type="text"  class="easyui-validatebox" placeholder="请输入描述" style="width:354px;height:32px;"  value="${Person.description}" name="description"/>
				    	</div>
				    	<div style="margin-top:15px;">
				        	<span class="from-style">是否负责人:</span>
				        	<input id="isleader" type="hidden"  name="isleader" id="isleader" value="1"/>
				    		<input type="radio" name="leader" id="radio3" value="1" checked="checked" onchange='setLeader();'>是
							<input type="radio" name="leader" id="radio4" value="0" onchange='setLeader();'>否 
				    	</div>
				        <div style="margin-top:15px;">
				        	<span class="from-style">上传头像:</span>
				        	<c:if test="${Person.id >0}">
				        		<input type="text"  class="easyui-validatebox" style="width:354px;height:32px;" readonly="readonly" id="filename" value="${Person.photourl}" />
				        	</c:if>
				        	<c:if test="${Person.id == 0}">
				        		<input type="text"  class="easyui-validatebox" style="width:354px;height:32px;" readonly="readonly" id="filename" placeholder="请选择头像文件进行上传"  />
				        	</c:if> 
				        	<input type="file" name="file" id="jfile" onchange="showName(this)" />
				    	</div>
					</td>
					<c:if test="${Person.id >0}">
						<td rowspan="2" style="vertical-align: top;"> 
							<img alt="头像" src="<%=basePath %>${Person.photourl}" style="width:300px;height:300px">
						</td> 
					</c:if>
					<td>
					</td>
				</tr> 
				<tr>
					<td></td>
				</tr>
			</table>
		</form>
    </div>
   </div>
</body>
</html>

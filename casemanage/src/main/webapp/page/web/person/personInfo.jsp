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
		});
		function setSex(){
			var value = $('input:radio:checked').val();
			$("#sex").val(value);
		} 
		
		function savePerson(obj){	
		var idcard = $("#idcard").val();
			if(idcard != null && idcard != ""){
				if(idcard.length != 18){
					if(idcard.length != 15){
						$.messager.alert("操作提示","身份证格式不正确","error");
						return;
					}
				}
			}							   
			if ($('#personForm').form('validate')) {
				    $('#personForm').form('submit',{
								success : function(data) {
									data = $.parseJSON(data);
									if (data.code == 0) {
										$.messager.alert('保存信息',data.message,'info',
														function() {
															window.location.href = "<%=basePath%>person/personList.do";
														});
									} else {
										$.messager.alert('错误信息',
												data.message, 'error',
												function() {
												});
									}
								}
							});			
			}
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
			$("#id_birth").val(date.getFullYear()+"-"+month+"-"+day);
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
				$("#photourl").val(obj.value);
			}
		}	
/* 	function birthCheck(){
		var idCardNo = $("#idcard").val();		
		var birthStr = "";
	var sexStr = "";
	if(idCardNo.length>0){
		 if(idCardNo.length == 15){ 
			
			tmpStr = idCardNo.substring(6, 12);
		    tmpStr = "19" + tmpStr;
		    tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6);
		    $("#hid_birth").val(tmpStr);
		   
		}else if(idCardNo.length == 18){
		
			tmpStr = idCardNo.substring(6, 14);
	        tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6);
	      
		    $("#hid_birth").val(tmpStr);
		    
		}
	}
}

}	  */
	</script>
  </head>
  
 <body style="background:#fff;">
	
  <div id="contentRight" class="contentRight">
       	<div class="containner-fluid">
           	<div class="pannel-header">重要人员信息</div> 
                 <div class="Panel-content">重要人员信息：${person.id == 0?"新建重要人员信息":person.name}</div>
        	</div>
       
    <div class="containner-fluid text-center"  >
		<form id="personForm" name="personForm" action="<%=basePath%>person/jsonSaveOrUpdatePerson.do" method="post"  enctype="multipart/form-data"  style="text-align:left;"> 
		<table style="width:100%;">
			<tr style="height:40px"> 
				<td rowspan="2" style="width:50%">
				    <div style="margin-top:15px;width:100%;"> 
			    		<span class="from-style">人员姓名:</span>
			    		<input type="hidden" id="hid_assoId" name="id" value="${person.id}" />
			    		<input type="hidden"  name="serialno" value="${person.serialno}" />
			    		<input type="text" required="true" validType="Length[1,30]" required="true" class="easyui-validatebox" style="width:354px;height:32px;"  placeholder="请输入人员姓名" value="${person.name}" name="name"/>
			    	</div>
				    <div style="margin-top:15px;width:100%;">
			        	<span class="from-style">人员类型:</span>
						<input id="typeid" type="hidden"  name="typeid"   value="${person.typeid}" />
						<select id="cmb_type" class="easyui-combobox" data-options="editable:false,required:true,onSelect:function(record){$('#typeid').val(record.value);}"  style="width:354px;height:35px;" >  
							 <option value="">=请选择人员类型=</option>
							 <c:forEach var="item" items="${personType}">
							 	<option value="${item.id}">${item.name }</option> 
							 </c:forEach>
						</select>  
			    	</div>
				    	<div style="margin-top:15px;width:100%;">
			        	<span class="from-style">人员级别:</span>
						<input id="levelid" type="hidden"  name="levelid"   value="${person.levelid}" />
						<select id="cmb_level" class="easyui-combobox" required="true" data-options="editable:false,required:true,onSelect:function(record){$('#levelid').val(record.value);}"  style="width:354px;height:35px;" >  
							 <option value="">=请选择人员级别=</option>
							 <c:forEach var="item" items="${personLevel}">
							 	<option value="${item.id}">${item.name }</option> 
							 </c:forEach>
						</select>  
			    	</div>
				    <div style="margin-top:15px;width:100%;">
			        	<span class="from-style">身份证号:</span>
			    		<input id="idcard" type="text" validType="number" onchange="validatationBirth(this);"  class="easyui-validatebox"
			    		  style="width:354px;height:32px;"  placeholder="请输入身份证号" value="${person.idcard}" name="idcard" />
			    	</div>  
					<div style="margin-top:15px;width:100%;">
				        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别:</span>
				        	<input id="sex" type="hidden"  name="sex" id="sex"  value="${person.sex == null ?1:person.sex}" />
				    		<input type="radio" name="sex1" id="radio1" value="1" checked="checked" onchange='setSex();' />男
							<input type="radio" name="sex1" id="radio2" value="0" onchange='setSex();' />女 
				   </div>
				   <div style="margin-top:15px;width:100%;">
				        	<span class="from-style">出生年月:</span>
				        	<input type="text" id="dtb_birth" class="easyui-datebox"  data-options="editable:false,panelWidth:354,require:true,onSelect:sDateSelect" style="width:354px;height:32px;" /> 
				    		<input type="hidden" id="hid_birth" value="${person.birth}" name="birth"/> 
				    		<%-- <input type="text" validType="SpecialWord" class="easyui-validatebox" style="width:354px;height:32px;"  placeholder="请输入出生年月" value="${person.birth}" name="birth"/> --%>
				    	</div> 
			    	<div style="margin-top:15px;">
			        	<span class="from-style">mac地址:</span>
			    		<input type="text" validType="macAddress" class="easyui-validatebox"  style="width:354px;height:32px;" placeholder="请输入mac地址" value="${person.macaddress}" name="macaddress"/>
			    	</div>
			    	 <div style="margin-top:15px;">
			        	<span class="from-style">手机号码:</span>
			    		<input  type="text" style="width:354px;height:32px;" placeholder="请输入联系方式" value="${person.telephone}" name="telephone"/>
			    	</div> 
				    	<div style="margin-top:15px;width:100%;">
			        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址:</span>
			        	<%-- <textarea rows="4" cols="3" name="address"  style="width:354px;height:32px;"  value="${person.address}" >${person.address}</textarea> --%>
			    		<input type="text"  class="easyui-validatebox" placeholder="请输入地址"  style="width:354px;height:32px;" value="${person.address}" name="address"/>
			    	</div> 
			    	<div style="margin-top:15px;">
			    		<!-- <input class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;事由:</input> -->
			        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;事由:</span>
			        	<textarea rows="7" cols="3" name="casecomment"  style="width:354px;height:50px;"  value="${person.casecomment}" >${person.casecomment}</textarea>
			    		<%-- <input type="text" style="width:300px"  validType="SpecialWord" class="easyui-validatebox" placeholder="请输入是由" value="${person.casecomment}" name="casecomment"/> --%>
			    	</div> 
			        <div style="margin-top:15px;">
			        	<span class="from-style">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;描述:</span>
			        	<textarea rows="7" cols="3" name="description"  style="width:354px;height:50px;"  value="${person.description}" >${person.description}</textarea>
			    		<%-- <input type="text"  class="easyui-validatebox" placeholder="请输入描述" value="${person.description}" name="description"/> --%>
			    	</div>
			    	<div style="margin-top:15px;">
			        	<span class="from-style">警官名称:</span>
			    		<input type="text"  class="easyui-validatebox" style="width:354px;height:32px;"  placeholder="请输入警官名称" value="${person.policename}" name="policename"/>
			    	</div>
			    	<div style="margin-top:15px;">
			        	<span class="from-style">看守单位:</span>
			    		<input type="text"  class="easyui-validatebox" style="width:354px;height:32px;"  placeholder="请输入看守单位" value="${person.policesector}" name="policesector"/>
			    	</div>
			    	<div style="margin-top:15px;">
			        	<span class="from-style">警官电话:</span>
			    		<input type="text"  class="easyui-validatebox" style="width:354px;height:32px;"  placeholder="请输入警官电话" value="${person.policephone}" name="policephone"/>
			    	</div>
			        <div style="margin-top:15px;">
			        	<span class="from-style">上传头像:</span>
			        	<c:if test="${person.id >0}">
			        		<input type="text"  class="easyui-validatebox" style="width:354px;height:32px;" readonly="readonly" id="filename" value="${person.photourl}"/>
			        	</c:if>
			        	<c:if test="${person.id == 0}">
			        		<input type="text"  class="easyui-validatebox" style="width:354px;height:32px;" readonly="readonly" id="filename"/>
			        	</c:if> 
			        	<input type="file" name="file" id="jfile" onchange="showName(this)" />
			    	</div>
				</td>
				<c:if test="${person.id >0}">
					<td rowspan="2" style="vertical-align: top;"> 
						<img alt="头像" src="<%=basePath %>${person.photourl}" style="width:300px;height:300px">
					</td> 
				</c:if>
				<td>
					<div style="margin-top:15px;width:100%;">  
				        <input type="button" class="btn-back" value="返回" style="float:right;margin-left:25px;margin-right:25px;"  onclick="javascript:history.back();"> 
				         <input type="button" class="btn-sm" value="保存" style="float:right;margin-left:25px;" onclick="savePerson(this);"> 
					</div>
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

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
 <div class="container">
 	<div class="Nav">
         <ul class="list-group">
             <li class="list-head"><div class="containner-fluid">导航菜单</div></li>
             <li><a  A="frame-1" class="link-primary"><div class="containner-fluid" onclick="window.location.href='${pageContext.request.contextPath}/associate/associateList.do'">社会机构管理</div></a></li>
             <li><a  A="frame-2" class="link-primary"><div class="containner-fluid" onclick="window.location.href='${pageContext.request.contextPath}/associate/associateList.do'">重点人员管理</div></a></li>
             <li><a  A="frame-3" class="link-primary"><div class="containner-fluid" onclick="window.location.href='${pageContext.request.contextPath}/associate/associateTypeList.do'">社会机构类型管理</div></a></li>
             <li><a  A="frame-4" class="link-primary"><div class="containner-fluid" onclick="window.location.href='${pageContext.request.contextPath}/person/personTypeList.do'">重点人员类型管理</div></a></li>
             <li><a  A="frame-4" class="link-primary"><div class="containner-fluid" onclick="window.location.href='${pageContext.request.contextPath}/person/personTypeList.do'">重点人员级别管理</div></a></li>
             <li><a  A="frame-4" class="link-primary"><div class="containner-fluid" onclick="window.location.href='${pageContext.request.contextPath}/person/personTypeList.do'">重点人员车辆管理</div></a></li>
         </ul>
     </div>
 </div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.admin.bean.*"  %>
<%@ page import="com.lpmas.admin.business.*"  %>
<%@ page import="com.lpmas.admin.config.*"  %>
<%@ page import="com.lpmas.file.config.*"  %>
<%@ page import="com.lpmas.file.bean.*"  %>
<%
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
	List<FileInfoBean> list = (List<FileInfoBean>)request.getAttribute("FileInfoList");
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
%>

<%@ include file="../include/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件管理</title>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<script type='text/javascript' src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/common.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/ui.js"></script>
</head>
<body class="article_bg">
<p class="article_tit">文件列表</p>
<form name="formSearch" method="post" action="FileInfoList.do">
  <div class="search_form">
  <em class="em1">文件ID：</em>
    <input type="text" name="fileId" id="fileId" value="<%=ParamKit.getParameter(request, "fileId", "") %>" size="20"/>
  	<em class="em1">应用ID：</em>
    <input type="text" name="appId" id="appId" value="<%=ParamKit.getParameter(request, "appId", "") %>" size="20"/>
    <em class="em1">文件名称：</em>
    <input type="text" name="fileName" id="fileName" value="<%=ParamKit.getParameter(request, "fileName", "") %>" size="20"/>
    <em class="em1">文件状态：</em>
    <select name="status" id="status">
    	<%
    	int status = ParamKit.getIntParameter(request, "status", Constants.STATUS_VALID);
    	for(StatusBean<Integer, String> statusBean:Constants.STATUS_LIST){ %>
          <option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus()==status)?"selected":"" %>><%=statusBean.getValue() %></option>
       <%} %>
    </select>
    <%if(adminUserHelper.hasPermission(FileResource.FILE_INFO, OperationConfig.SEARCH)){ %>
    <input name="" type="submit" class="search_btn_sub" value="查询"/>
    <%} %>
  </div>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_style">
    <tr>
      <th>文件ID</th>
      <th>应用ID</th>
      <th>文件名称</th>
      <th>信息类型</th>
      <th>信息ID</th>
      <th>文件类型</th>
      <th>操作</th>
    </tr>
    <%
    for(FileInfoBean bean:list){%> 
    <tr>
      <td><%=bean.getFileId()%></td>
      <td><%=bean.getAppId() %></td>
      <td><%=bean.getFileName()%></td>
      <td><%=bean.getInfoType1() %></td>
      <td><%=bean.getInfoId1() %></td>
      <td><%=bean.getFileType()%></td>
      <td align="center">
      	<%if(adminUserHelper.hasPermission(FileResource.FILE_INFO, OperationConfig.SEARCH)){ %>
      	<a href="/file/FileInfoManage.do?fileId=<%=bean.getFileId()%>">查看</a>
      	<%} %>
      </td>
    </tr>	
    <%} %>
  </table>
</form>
<ul class="page_info">
<li class="page_left_btn">
  <!-- <input type="button" name="button" id="button" value="新建" onclick="javascript:location.href='BrandInfoManage.do'"> -->
</li>
<%@ include file="../include/page.jsp" %>
</ul>
</body>
</html>
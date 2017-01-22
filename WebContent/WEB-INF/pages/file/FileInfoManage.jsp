<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.StatusBean" %>
<%@ page import="com.lpmas.admin.bean.*"  %>
<%@ page import="com.lpmas.admin.business.*"  %>
<%@ page import="com.lpmas.file.bean.*"  %>
<%@ page import="com.lpmas.file.config.*"  %>
<% 
	FileInfoBean bean = (FileInfoBean)request.getAttribute("FileInfoBean");
	AdminUserHelper adminHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
	List<AdminGroupInfoBean> groupList = adminHelper.getUserGroupList();
%>
<%@ include file="../include/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>文件管理</title>
	<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="<%=STATIC_URL %>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=STATIC_URL %>/js/common.js"></script>
</head>
<body class="article_bg">
	<p class="article_tit">
		<a href="FileInfoList.do"><img src="<%=STATIC_URL %>/images/return.png"/></a> 文件列表 > 
			修改文件信息
	</p>
	<form id="formData" name="formData" method="post" action="FileInfoManage.do" onsubmit="javascript:return checkForm('formData');">
	  <input type="hidden" name="fileId" id="fileId" value="<%=bean.getFileId() %>"/>
	  <input type="hidden" name="appId" id="appId" value="<%=bean.getFileId() %>"/>
	  <input type="hidden" name="infoType1" id="infoType1" value="<%=bean.getInfoType1() %>"/>
	  <input type="hidden" name="infoId1" id="infoId1" value="<%=bean.getInfoId1() %>"/>
	  <input type="hidden" name="fileType" id="fileType" value="<%=bean.getFileType() %>"/>
	  <input type="hidden" name="fileFormat" id="fileFormat" value="<%=bean.getFileFormat() %>"/>
	  <input type="hidden" name="filePath" id="filePath" value="<%=bean.getFilePath() %>"/>
	  <div class="modify_form">
	    <p>
	      <em class="int_label">文件ID：</em>
	      <em><%=bean.getFileId()%></em>
	    </p>
	    <p>
	      <em class="int_label">应用ID：</em>
	      <em><%=bean.getAppId() %></em>
	    </p>
	    <p>
	      <em class="int_label">信息类型：</em>
	      <em><%=bean.getInfoType1() %></em>
	    </p>
	     <p>
	      <em class="int_label">信息ID：</em>
	      <em><%=bean.getInfoId1() %></em>
	    </p>
	    <p>
	      <em class="int_label">文件名称：</em>
	      <input type="text" name="fileName" id="fileName" size="30" maxlength="100" value="<%=bean.getFileName()%>" checkStr="文件名称;txt;true;;100"/>
	    </p>
	    <p>
	      <em class="int_label">文件类型：</em>
	      <em><%=bean.getFileType()%></em>
	    </p>
	    <p>
	      <em class="int_label">文件格式：</em>
	      <em><%=bean.getFileFormat()%></em>
	    </p>
	    <p>
	      <em class="int_label">文件路径：</em>
	      <em><%=bean.getFilePath()%></em>
	    </p>
	    <p>
	      <em class="int_label">状态：</em>
	      <input type="checkbox" name="status" id="status" value="<%=Constants.STATUS_VALID %>" <%=(bean.getStatus()==Constants.STATUS_VALID)?"checked":"" %>/>
	    </p>
	    <%if (bean.getFileType().equals(FileInfoConfig.FILE_TYPE_PIC)) { %>
	    	<p>
	    	  <em><img src="<%=FileInfoConfig.FILE_UPLOAD_FOLDER + bean.getFilePath()%>"/></em>
	    </p>
	    <%} %>
	     <%if (bean.getFileType().equals(FileInfoConfig.FILE_TYPE_DOC)) { %>
	    	<p>
	    	  <em class="int_label"><a href="<%=FileInfoConfig.FILE_UPLOAD_FOLDER + bean.getFilePath()%>" >点击下载</a></em>
	    </p>
	    <%} %>
	    <p class="p_top_border">
	      <em class="int_label">备注：</em>
	      <textarea name="memo" id="memo" cols="60" rows="3" checkStr="备注;txt;false;;1000"><%=bean.getMemo() %></textarea>
	    </p>
	  </div>
	  <div class="div_center">
	  	<input type="button" name="button" id="button" value="取消" onclick="javascript:history.back()">
	  </div>
	</form>
</body>
</html>
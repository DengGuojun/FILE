<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.file.bean.*"  %>
<%
	ReturnMessageBean result = (ReturnMessageBean) request.getAttribute("MessageBean");
	FileInfoBean fileInfoBean = (FileInfoBean)result.getContent();
%>
<%@ include file="../include/header.jsp"%>
<html>
<script type="text/javascript">
        document.domain='<%=DOMAIN%>'; //解决与iframe之间的跨域问题
</script>
<script type="text/javascript" src="<%=STATIC_URL%>/js/jquery.js"></script>
<body>
	<input id="result" value="<%=result.getCode() %>"/>
	<input id="message" value="<%=result.getMessage() %>"/>
	<input id="fileId" value="<%=fileInfoBean!= null ? fileInfoBean.getFileId() : ""%>"/>
</body>
<script>
	var result = $("#result").val();
	var message = $("#message").val();
	if(result == 1){
		var fileId = $("#fileId").val();
		$('#fileId', parent.document).val(fileId);
		$('#formData', parent.document).submit();
	} 
	alert(message);
	
</script>
</html>
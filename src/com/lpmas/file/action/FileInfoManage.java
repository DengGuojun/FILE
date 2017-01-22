package com.lpmas.file.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.file.bean.FileInfoBean;
import com.lpmas.file.business.FileInfoBusiness;
import com.lpmas.file.config.FileConfig;
import com.lpmas.file.config.FileResource;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class FileInfoManage
 */
@WebServlet("/file/FileInfoManage.do")
public class FileInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileInfoManage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		String fileId = ParamKit.getParameter(request, "fileId", "");
		FileInfoBean bean = new FileInfoBean();
		if (StringKit.isValid(fileId)) {
			if (!adminUserHelper.checkPermission(FileResource.FILE_INFO, OperationConfig.SEARCH)) {
				return;
			}
			FileInfoBusiness business = new FileInfoBusiness();
			bean = business.getFileInfoByKey(fileId);
		} else {
			HttpResponseKit.alertMessage(response, "FileId缺失", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		request.setAttribute("FileInfoBean", bean);
		request.setAttribute("AdminUserHelper", adminUserHelper);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				FileConfig.PAGE_PATH + "FileInfoManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
//		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
//
//		FileInfoBean bean = new FileInfoBean();
//		try {
//			bean = BeanKit.request2Bean(request, FileInfoBean.class);
//			FileInfoBusiness business = new FileInfoBusiness();
//
//			int result = 0;
//			if (StringKit.isValid(bean.getFileId())) {
//				if (!adminUserHelper.checkPermission(FileResource.FILE_INFO, OperationConfig.UPDATE)) {
//					return;
//				}
//				ReturnMessageBean messageBean = business.verifyFileInfoProperty(bean);
//				if (StringKit.isValid(messageBean.getMessage())) {
//					HttpResponseKit.alertMessage(response, messageBean.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
//					return;
//				}
//				bean.setModifyUser(adminUserHelper.getAdminUserId());
//				result = business.updateFileInfo(bean);
//			}
//			if (result > 0) {
//				HttpResponseKit.alertMessage(response, "处理成功", "/file/FileInfoList.do");
//			} else {
//				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
//			}
//		} catch (Exception e) {
//			log.error("", e);
//		}
	}
}

package com.lpmas.file.action;

import java.io.IOException;
import java.util.HashMap;

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
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class FileInfoList
 */
@WebServlet("/file/FileInfoList.do")
public class FileInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileInfoList() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(FileResource.FILE_INFO, OperationConfig.SEARCH)) {
			return;
		}
		
		int pageNum = ParamKit.getIntParameter(request, "pageNum", FileConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", FileConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);

		HashMap<String, String> condMap = new HashMap<String, String>();
		String fileId = ParamKit.getParameter(request, "fileId", "").trim();
		if (StringKit.isValid(fileId)) {
			condMap.put("fileId", fileId);
		}
		String appId = ParamKit.getParameter(request, "appId", "").trim();
		if (StringKit.isValid(appId)) {
			condMap.put("appId", appId);
		}
		String fileName = ParamKit.getParameter(request, "fileName", "").trim();
		if (StringKit.isValid(fileName)) {
			condMap.put("fileName", fileName);
		}

		FileInfoBusiness business = new FileInfoBusiness();
		PageResultBean<FileInfoBean> result = business.getFileInfoPageListByMap(condMap, pageBean);
		request.setAttribute("FileInfoList", result.getRecordList());
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("AdminUserHelper", adminUserHelper);
		RequestDispatcher rd = request.getRequestDispatcher(FileConfig.PAGE_PATH + "FileInfoList.jsp");
		rd.forward(request, response);
	}

}

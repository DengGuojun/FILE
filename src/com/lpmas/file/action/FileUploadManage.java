package com.lpmas.file.action;

import java.io.IOException;
import java.util.List;

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
import com.lpmas.file.config.FileInfoConfig;
import com.lpmas.file.config.FileResource;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.transfer.FileUploadResultBean;
import com.lpmas.framework.transfer.FileUploadResultBean.FileUploadItem;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.UuidKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.system.client.cache.SysApplicationInfoClientCache;

/**
 * 目前仅支持单个文件上传 Servlet implementation class FileUploadManage
 */
@WebServlet("/file/FileUploadManage.do")
public class FileUploadManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUploadManage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		int result = -1;
		ReturnMessageBean messageBean = new ReturnMessageBean();
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		int userId = adminUserHelper.getAdminUserId();
		String appCode = ParamKit.getParameter(request, "appCode", "");
		String fileId = ParamKit.getParameter(request, "fileId", "");
		SysApplicationInfoClientCache sysApplicationInfoClientCache = new SysApplicationInfoClientCache();
		int appId = sysApplicationInfoClientCache.getSysApplicationIdByCode(appCode);
		if (appId == 0) {
			messageBean.setMessage("应用ID不合法");
		} else {
			if (!adminUserHelper.checkPermission(FileResource.FILE_UPLOAD, OperationConfig.CREATE)) {
				return;
			}
			FileInfoBusiness business = new FileInfoBusiness();
			// 保存文件
			String fileName = UuidKit.getUuid();
			String filePath = DateKit.getCurrentDateTime("yyyy" + Constants.FILE_SEPARATOR + "MM"
					+ Constants.FILE_SEPARATOR + "dd");
			String uploadPath = FileInfoConfig.FILE_PATH + FileInfoConfig.FILE_UPLOAD_FOLDER + filePath;
			FileUploadResultBean resultBean = business.saveFile(request, uploadPath, fileName);
			// 获取上传结果
			if (resultBean.getResult()) {
				List<FileUploadItem> list = resultBean.getFileItemList();
				for (FileUploadItem item : list) {
					if (item.getResult()) {
						// 构建实体Bean，保存数据信息在数据库
						FileInfoBean bean = new FileInfoBean();
						bean = BeanKit.request2Bean(request, FileInfoBean.class);
						bean.setAppId(appId);
						bean.setFileName(fileName);
						bean.setFileFormat(item.getExtensionFileName());
						bean.setFileType(FileInfoConfig.FILE_TYPE_MAP.get(item.getExtensionFileName()));
						bean.setFilePath(filePath + Constants.FILE_SEPARATOR + fileName + "."
								+ item.getExtensionFileName());
						bean.setStatus(Constants.STATUS_VALID);

						// 生成图片的缩略图
						business.generateThumbnail(bean);
						// 保存数据模型，如果已存在旧文件，需要删除旧文件
						FileInfoBean existsFileInfoBean = business.getFileInfoByKey(fileId);
						if (existsFileInfoBean != null) {
							bean.setFileId(existsFileInfoBean.getFileId());
							bean.setModifyUser(userId);
							result = business.updateFileInfo(bean, appCode);
							business.deleteFile(existsFileInfoBean);
						} else {
							bean.setFileId(UuidKit.getUuid());
							bean.setCreateUser(userId);
							result = business.addFileInfo(bean, appCode);
						}
						if (result >= 0) {
							messageBean.setMessage("文件上传成功");
							messageBean.setContent(bean);
							messageBean.setCode(Constants.STATUS_VALID);
						} else {
							messageBean.setMessage("文件上传失败");
						}
					} else {
						messageBean.setMessage(item.getResultContent());
					}
				}
			} else {
				messageBean.setMessage(resultBean.getResultContent());
			}
		}
		request.setAttribute("MessageBean", messageBean);
		RequestDispatcher rd = request.getRequestDispatcher(FileConfig.PAGE_PATH + "FileUploadResult.jsp");
		rd.forward(request, response);
	}

}

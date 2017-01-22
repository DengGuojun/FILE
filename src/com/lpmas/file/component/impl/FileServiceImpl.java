package com.lpmas.file.component.impl;

import java.util.HashMap;

import Ice.Current;

import com.lpmas.file.bean.FileInfoBean;
import com.lpmas.file.business.FileInfoBusiness;
import com.lpmas.file.cache.FileInfoCache;
import com.lpmas.file.component._FileServiceDisp;
import com.lpmas.file.config.FileClientConfig;
import com.lpmas.file.config.FileInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.JsonKit;

public class FileServiceImpl extends _FileServiceDisp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6666365353883587126L;

	@Override
	public String rpc(String method, String params, Current __current) {
		String result = "";
		if (method.equals(FileClientConfig.GET_FILE_URL_BY_KEY)) {
			result = getFileUrlByKey(params);
		} else if (method.equals(FileClientConfig.GET_IMAGE_THUMBNAIl_URL_BY_KEY)) {
			result = getImageThumbnailUrlByKey(params);
		} 
		return result;
	}

	public String getFileUrlByKey(String params) {
		String result = "";
		HashMap<String, String> paramMap = JsonKit.toBean(params, HashMap.class);
		FileInfoCache cache = new FileInfoCache();
		FileInfoBean fileInfoBean = cache.getFileInfoByKey(paramMap.get("fileId"));
		if (fileInfoBean != null && fileInfoBean.getStatus() == Constants.STATUS_VALID) {
			result = FileInfoConfig.FILE_UPLOAD_FOLDER + fileInfoBean.getFilePath();
		}
		return result;
	}

	public String getImageThumbnailUrlByKey(String params) {
		String result = "";
		FileInfoCache cache = new FileInfoCache();
		FileInfoBusiness business= new FileInfoBusiness();
		HashMap<String, String> paramMap = JsonKit.toBean(params, HashMap.class);
		FileInfoBean fileInfoBean = cache.getFileInfoByKey(paramMap.get("fileId"));
		if (fileInfoBean != null && fileInfoBean.getStatus() == Constants.STATUS_VALID) {
			result = FileInfoConfig.FILE_UPLOAD_FOLDER + business.getThumbnailPicPath(fileInfoBean.getFilePath());
		}
		return result;
	}

}

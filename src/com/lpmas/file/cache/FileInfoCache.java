package com.lpmas.file.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.file.bean.FileInfoBean;
import com.lpmas.file.business.FileInfoBusiness;
import com.lpmas.file.config.FileCacheConfig;
import com.lpmas.file.config.FileConfig;
import com.lpmas.framework.cache.RemoteCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.JsonKit;

public class FileInfoCache {
	private static Logger log = LoggerFactory.getLogger(FileInfoCache.class);

	public FileInfoBean getFileInfoByKey(String fileId) {
		FileInfoBean bean = null;
		String key = FileCacheConfig.getFileInfoKey(fileId);

		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(FileConfig.APP_ID, key);
		if (obj != null) {
			log.info("get FileInfoBean from remote cache");
			bean = JsonKit.toBean((String) obj, FileInfoBean.class);
		} else {
			log.info("set FileInfoBean to remote cache");
			FileInfoBusiness business = new FileInfoBusiness();
			bean = business.getFileInfoByKey(fileId);
			if (bean != null) {
				remoteCache.set(FileConfig.APP_ID, key, JsonKit.toJson(bean), Constants.CACHE_TIME_2_HOUR);
			}
		}
		return bean;
	}

	public boolean refreshFileInfoByKey(String fileId) {
		String key = FileCacheConfig.getFileInfoKey(fileId);
		RemoteCache remoteCache = RemoteCache.getInstance();
		return remoteCache.delete(FileConfig.APP_ID, key);
	}
}

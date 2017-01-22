package com.lpmas.file.client;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.file.component.FileServicePrx;
import com.lpmas.file.config.FileClientConfig;
import com.lpmas.file.config.FileConfig;
import com.lpmas.framework.component.ComponentClient;
import com.lpmas.framework.util.JsonKit;

public class FileServiceClient {

	private static Logger log = LoggerFactory.getLogger(FileServiceClient.class);

	public String getFileUrlByKey(String fileId) {
		ComponentClient client = new ComponentClient();
		FileServicePrx fileService = (FileServicePrx) client.getProxy(FileConfig.APP_ID, FileServicePrx.class);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("fileId", fileId);
		String result = fileService.rpc(FileClientConfig.GET_FILE_URL_BY_KEY, JsonKit.toJson(params));
		log.info("result : {}", result);
		return result;
	}

	public String getImageThumbnailUrlByKey(String fileId) {
		ComponentClient client = new ComponentClient();
		FileServicePrx fileService = (FileServicePrx) client.getProxy(FileConfig.APP_ID, FileServicePrx.class);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("fileId", fileId);
		String result = fileService.rpc(FileClientConfig.GET_IMAGE_THUMBNAIl_URL_BY_KEY, JsonKit.toJson(params));
		log.info("result : {}", result);
		return result;
	}

}

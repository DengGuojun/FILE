package com.lpmas.file.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class LogInfoConfig {

	// 文件操作日志项
	public static String LOG_FILE_INFO = "FILE_INFO";
	public static List<StatusBean<String, String>> LOG_FILE_INFO_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> LOG_FILE_INFO_MAP = new HashMap<String, String>();

	static {
		initLogFileInfoList();
		initLogFileInfoMap();
	}

	private static void initLogFileInfoList() {
		LOG_FILE_INFO_LIST = new ArrayList<StatusBean<String, String>>();
		LOG_FILE_INFO_LIST.add(new StatusBean<String, String>(LOG_FILE_INFO, "文件信息"));
	}

	private static void initLogFileInfoMap() {
		LOG_FILE_INFO_MAP = StatusKit.toMap(LOG_FILE_INFO_LIST);
	}

}

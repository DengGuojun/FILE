package com.lpmas.file.config;

import com.lpmas.framework.util.PropertiesKit;

public class FileDBConfig {

	public static String DB_LINK_FILE_W = PropertiesKit.getBundleProperties(FileConfig.FILE_PROP_FILE_NAME,
			"DB_LINK_FILE_W");

	public static String DB_LINK_FILE_R = PropertiesKit.getBundleProperties(FileConfig.FILE_PROP_FILE_NAME,
			"DB_LINK_FILE_R");
}

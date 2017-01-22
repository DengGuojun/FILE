package com.lpmas.file.config;

public class FileCacheConfig {
	public static final String FILE_INFO_KEY = "FILE_INFO_";
	
	public static String getFileInfoKey(String fileId) {
		return FILE_INFO_KEY + fileId;
	}
}

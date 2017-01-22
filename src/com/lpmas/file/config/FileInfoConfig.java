package com.lpmas.file.config;

import java.util.HashMap;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.PropertiesKit;

public class FileInfoConfig {

	public static final int MAX_SIZE = 512 * 1024; // 设置上传文件最大为512K
	public static final String ALLOWED_FILE_TYPE = "jpg,jpeg,gif,png,txt,doc,docx,xls,pdf,ppt,pptx";// 设置允许上传的文件类型
	public static final String THUMBNAIL_NAME = "_thumbnail";// 设置缩略图后缀
	//设置缩略图压缩宽度
	public static Double THUMBNAIL_WIDTH = Double
			.valueOf(PropertiesKit.getBundleProperties(FileConfig.FILE_PROP_FILE_NAME, "FILE_THUMBNAIL_WIDTH"));
	//设置默认图片路径
	public static String DEFAULT_IMG_URL = PropertiesKit.getBundleProperties(FileConfig.FILE_PROP_FILE_NAME,
			"FILE_DEFAULT_IMG_URL");

	// 设置文件保存路径
	public static final String FILE_PROP_FILE_NAME = Constants.PROP_FILE_PATH + "/file_config";
	public static final String FILE_PATH = PropertiesKit.getBundleProperties(FILE_PROP_FILE_NAME, "FILE_PATH");
	public static final String FILE_UPLOAD_FOLDER = PropertiesKit.getBundleProperties(FILE_PROP_FILE_NAME,
			"FILE_UPLOAD_FOLDER");
	public static final String FILE_BACKUP_FOLDER = PropertiesKit.getBundleProperties(FILE_PROP_FILE_NAME,
			"FILE_BACKUP_FOLDER");

	public static final String FILE_TYPE_PIC = "PIC";
	public static final String FILE_TYPE_AUDIO = "AUDIO";
	public static final String FILE_TYPE_VIDIO = "VIDIO";
	public static final String FILE_TYPE_DOC = "DOC";

	public static HashMap<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

	static {
		initFileTypeMap();
	}

	private static void initFileTypeMap() {
		FILE_TYPE_MAP.put("jpg", FILE_TYPE_PIC);
		FILE_TYPE_MAP.put("jpeg", FILE_TYPE_PIC);
		FILE_TYPE_MAP.put("gif", FILE_TYPE_PIC);
		FILE_TYPE_MAP.put("png", FILE_TYPE_PIC);
		FILE_TYPE_MAP.put("txt", FILE_TYPE_DOC);
		FILE_TYPE_MAP.put("doc", FILE_TYPE_DOC);
		FILE_TYPE_MAP.put("docx", FILE_TYPE_DOC);
		FILE_TYPE_MAP.put("xls", FILE_TYPE_DOC);
		FILE_TYPE_MAP.put("xlsx", FILE_TYPE_DOC);
		FILE_TYPE_MAP.put("pdf", FILE_TYPE_DOC);
		FILE_TYPE_MAP.put("ppt", FILE_TYPE_DOC);
		FILE_TYPE_MAP.put("pptx", FILE_TYPE_DOC);

	}
}

package com.lpmas.file.business;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import com.lpmas.file.bean.FileInfoBean;
import com.lpmas.file.cache.FileInfoCache;
import com.lpmas.file.config.FileInfoConfig;
import com.lpmas.file.config.LogInfoConfig;
import com.lpmas.file.dao.FileInfoDao;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.transfer.FileUploadKit;
import com.lpmas.framework.transfer.FileUploadResultBean;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.FileKit;
import com.lpmas.framework.util.NumeralOperationKit;

public class FileInfoBusiness {
	public int addFileInfo(FileInfoBean bean, String appCode) {
		FileInfoDao dao = new FileInfoDao();
		int result = dao.insertFileInfo(bean);
		if (result >= 0) {
			FileLogSendHelper helper = new FileLogSendHelper();
			helper.sendAddLog(bean, bean.getInfoType1(), bean.getInfoId1(), LogInfoConfig.LOG_FILE_INFO, appCode);
		}
		return result;
	}

	public int updateFileInfo(FileInfoBean bean, String appCode) {
		FileInfoDao dao = new FileInfoDao();
		FileInfoBean orgBean = dao.getFileInfoByKey(bean.getFileId());
		int result = dao.updateFileInfo(bean);
		if (result >= 0) {
			FileInfoCache cache = new FileInfoCache();
			cache.refreshFileInfoByKey(bean.getFileId());
			FileLogSendHelper helper = new FileLogSendHelper();
			helper.sendUpdateLog(orgBean, bean, bean.getInfoType1(), bean.getInfoId1(), LogInfoConfig.LOG_FILE_INFO, appCode);
		}
		return result;
	}

	public FileInfoBean getFileInfoByKey(String fileId) {
		FileInfoDao dao = new FileInfoDao();
		return dao.getFileInfoByKey(fileId);
	}

	public PageResultBean<FileInfoBean> getFileInfoPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		FileInfoDao dao = new FileInfoDao();
		return dao.getFileInfoPageListByMap(condMap, pageBean);
	}

	public FileUploadResultBean saveFile(HttpServletRequest request, String uploadPath, String fileName) {
		FileUploadKit fileUploadKit = new FileUploadKit();
		fileUploadKit.setAllowedFileType(FileInfoConfig.ALLOWED_FILE_TYPE);// 设置允许上传的文件类型
		fileUploadKit.setMaxSize(FileInfoConfig.MAX_SIZE);
		return fileUploadKit.fileUpload(request, "file", uploadPath, fileName);
	}

	public void deleteFile(FileInfoBean fileInfoBean) throws IOException {
		// 获取需要删除文件的路径
		String existsFilePath = FileInfoConfig.FILE_PATH + FileInfoConfig.FILE_UPLOAD_FOLDER
				+ fileInfoBean.getFilePath();
		// 生成备份文件路径文件夹
		String backupPath = DateKit.getCurrentDateTime("yyyy" + Constants.FILE_SEPARATOR + "MM"
				+ Constants.FILE_SEPARATOR + "dd");
		String backupFloder = FileInfoConfig.FILE_PATH + FileInfoConfig.FILE_BACKUP_FOLDER + backupPath;
		FileKit.createDir(backupFloder);
		// 备份及删除文件
		FileKit.copyFile(existsFilePath, backupFloder + Constants.FILE_SEPARATOR + fileInfoBean.getFileName()
				+ "." + fileInfoBean.getFileFormat());
		FileKit.deleteFile(existsFilePath);
		// 如果文件为图片，删除缩略图(缩略图不备份)
		if (fileInfoBean.getFileType().equals(FileInfoConfig.FILE_TYPE_PIC)) {
			String thumbnailPicPath = getThumbnailPicPath(existsFilePath);
			FileKit.deleteFile(thumbnailPicPath);
		}
	}

	public void generateThumbnail(FileInfoBean fileInfoBean) throws IOException {
		if (FileInfoConfig.FILE_TYPE_PIC.equals(fileInfoBean.getFileType())) {
			String picPath = FileInfoConfig.FILE_PATH + FileInfoConfig.FILE_UPLOAD_FOLDER + fileInfoBean.getFilePath();
			String thumbnailPath = getThumbnailPicPath(picPath);
			BufferedImage originalImage = ImageIO.read(new File(picPath));
			double proportion = originalImage.getWidth() / FileInfoConfig.THUMBNAIL_WIDTH;
			double height = NumeralOperationKit.divide(originalImage.getHeight(), proportion, 0);
			Thumbnails.of(new File(picPath))
					.size(FileInfoConfig.THUMBNAIL_WIDTH.intValue(), Double.valueOf(height).intValue())
					.toFile(new File(thumbnailPath));
		}
	}

	public String getThumbnailPicPath(String picPath) {
		return picPath.substring(0, picPath.lastIndexOf(".")) + FileInfoConfig.THUMBNAIL_NAME
				+ picPath.substring(picPath.lastIndexOf("."));
	}

}
package com.lpmas.file.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.file.bean.FileInfoBean;
import com.lpmas.file.factory.FileDBFactory;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class FileInfoDao {
	private static Logger log = LoggerFactory.getLogger(FileInfoDao.class);

	public int insertFileInfo(FileInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new FileDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into file_info ( file_id, app_id, info_type_1, info_type_2, info_type_3, info_id_1, info_id_2, info_id_3, file_name, file_type, file_format, file_path, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getFileId());
			ps.setInt(2, bean.getAppId());
			ps.setInt(3, bean.getInfoType1());
			ps.setInt(4, bean.getInfoType2());
			ps.setInt(5, bean.getInfoType3());
			ps.setInt(6, bean.getInfoId1());
			ps.setInt(7, bean.getInfoId2());
			ps.setInt(8, bean.getInfoId3());
			ps.setString(9, bean.getFileName());
			ps.setString(10, bean.getFileType());
			ps.setString(11, bean.getFileFormat());
			ps.setString(12, bean.getFilePath());
			ps.setInt(13, bean.getStatus());
			ps.setInt(14, bean.getCreateUser());
			ps.setString(15, bean.getMemo());

			result = db.executePstmtInsert();
		} catch (Exception e) {
			log.error("", e);
			result = -1;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

	public int updateFileInfo(FileInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new FileDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update file_info set app_id = ?, info_type_1 = ?, info_type_2 = ?, info_type_3 = ?, info_id_1 = ?, info_id_2 = ?, info_id_3 = ?, file_name = ?, file_type = ?, file_format = ?, file_path = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where file_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getAppId());
			ps.setInt(2, bean.getInfoType1());
			ps.setInt(3, bean.getInfoType2());
			ps.setInt(4, bean.getInfoType3());
			ps.setInt(5, bean.getInfoId1());
			ps.setInt(6, bean.getInfoId2());
			ps.setInt(7, bean.getInfoId3());
			ps.setString(8, bean.getFileName());
			ps.setString(9, bean.getFileType());
			ps.setString(10, bean.getFileFormat());
			ps.setString(11, bean.getFilePath());
			ps.setInt(12, bean.getStatus());
			ps.setInt(13, bean.getModifyUser());
			ps.setString(14, bean.getMemo());

			ps.setString(15, bean.getFileId());

			result = db.executePstmtUpdate();
		} catch (Exception e) {
			log.error("", e);
			result = -1;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

	public FileInfoBean getFileInfoByKey(String fileId) {
		FileInfoBean bean = null;
		DBFactory dbFactory = new FileDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from file_info where file_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, fileId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new FileInfoBean();
				bean = BeanKit.resultSet2Bean(rs, FileInfoBean.class);
			}
			rs.close();
		} catch (Exception e) {
			log.error("", e);
			bean = null;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return bean;
	}

	public PageResultBean<FileInfoBean> getFileInfoPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<FileInfoBean> result = new PageResultBean<FileInfoBean>();
		DBFactory dbFactory = new FileDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from file_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String fileId = condMap.get("fileId");
			if (StringKit.isValid(fileId)) {
				condList.add("file_id = ?");
				paramList.add(fileId);
			}
			String appId = condMap.get("appId");
			if (StringKit.isValid(appId)) {
				condList.add("app_id = ?");
				paramList.add(appId);
			}
			String fileName = condMap.get("fileName");
			if (StringKit.isValid(fileName)) {
				condList.add("file_name like ?");
				paramList.add("%" + fileName + "%");
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}

			String orderQuery = "order by file_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, FileInfoBean.class, pageBean, db);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

}

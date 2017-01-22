package com.lpmas.file.factory;

import java.sql.SQLException;

import com.lpmas.file.config.FileDBConfig;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.db.MysqlDBExecutor;
import com.lpmas.framework.db.MysqlDBObject;

public class FileDBFactory extends DBFactory {

	public DBObject getDBObjectR() throws SQLException {
		return new MysqlDBObject(FileDBConfig.DB_LINK_FILE_R);
	}

	public DBObject getDBObjectW() throws SQLException {
		return new MysqlDBObject(FileDBConfig.DB_LINK_FILE_W);
	}

	@Override
	public DBExecutor getDBExecutor() {
		return new MysqlDBExecutor();
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}
}

package com.lpmas.file.business;

import com.lpmas.log.business.DataLogSendHelper;

public class FileLogSendHelper extends DataLogSendHelper {

	public void sendAddLog(Object bean, Integer infoType, int infoId1, String field1, String appId) {
		super.sendAddLog(bean, infoType, infoId1, 0, 0, field1, "", "", appId);
	}

	public void sendUpdateLog(Object orgBean, Object newBean, Integer infoType, int infoId1, String field1, String appId) {
		super.sendUpdateLog(orgBean, newBean, infoType, infoId1, 0, 0, field1, "", "", appId);
	}

	public void sendRemoveLog(Object orgBean, Integer infoType, int infoId1, String field1, String appId) {
		super.sendRemoveLog(orgBean, infoType, infoId1, 0, 0, field1, "", "", appId);
	}

	public void sendAddLog(Object bean, Integer infoType, int infoId1, int infoId2, int infoId3, String field1,
			String appId) {
		super.sendAddLog(bean, infoType, infoId1, infoId2, infoId3, field1, "", "", appId);
	}

	public void sendUpdateLog(Object orgBean, Object newBean, Integer infoType, int infoId1, int infoId2, int infoId3,
			String field1, String appId) {
		super.sendUpdateLog(orgBean, newBean, infoType, infoId1, infoId2, infoId3, field1, "", "", appId);
	}

	public void sendRemoveLog(Object orgBean, Integer infoType, int infoId1, int infoId2, int infoId3, String field1,
			String appId) {
		super.sendRemoveLog(orgBean, infoType, infoId1, infoId2, infoId3, field1, "", "", appId);
	}

	public void sendAddLog(Object bean, Integer infoType, int infoId1, int infoId2, int infoId3, String field1,
			String field2, String field3, String appId) {
		super.sendAddLog(bean, infoType, infoId1, infoId2, infoId3, field1, field2, field3, appId);
	}

	public void sendUpdateLog(Object orgBean, Object newBean, Integer infoType, int infoId1, int infoId2, int infoId3,
			String field1, String field2, String field3, String appId) {
		super.sendUpdateLog(orgBean, newBean, infoType, infoId1, infoId2, infoId3, field1, field2, field3, appId);
	}

	public void sendRemoveLog(Object orgBean, Integer infoType, int infoId1, int infoId2, int infoId3, String field1,
			String field2, String field3, String appId) {
		super.sendRemoveLog(orgBean, infoType, infoId1, infoId2, infoId3, field1, field2, field3, appId);
	}
}

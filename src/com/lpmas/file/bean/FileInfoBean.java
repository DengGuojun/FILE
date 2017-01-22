package com.lpmas.file.bean;

import java.sql.Timestamp;

import com.lpmas.framework.annotation.FieldTag;

public class FileInfoBean {
	@FieldTag(name = "文件ID")
	private String fileId = "";
	@FieldTag(name = "应用ID")
	private int appId = 0;
	@FieldTag(name = "信息类型1")
	private int infoType1 = 0;
	@FieldTag(name = "信息类型2")
	private int infoType2 = 0;
	@FieldTag(name = "信息类型3")
	private int infoType3 = 0;
	@FieldTag(name = "信息ID1")
	private int infoId1 = 0;
	@FieldTag(name = "信息ID2")
	private int infoId2 = 0;
	@FieldTag(name = "信息ID3")
	private int infoId3 = 0;
	@FieldTag(name = "文件名")
	private String fileName = "";
	@FieldTag(name = "文件类型")
	private String fileType = "";
	@FieldTag(name = "文件格式")
	private String fileFormat = "";
	@FieldTag(name = "文件路径")
	private String filePath = "";
	@FieldTag(name = "状态")
	private int status = 0;
	@FieldTag(name = "创建时间")
	private Timestamp createTime = null;
	@FieldTag(name = "创建用户")
	private int createUser = 0;
	@FieldTag(name = "修改时间")
	private Timestamp modifyTime = null;
	@FieldTag(name = "修改用户")
	private int modifyUser = 0;
	@FieldTag(name = "备注")
	private String memo = "";

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getInfoType1() {
		return infoType1;
	}

	public void setInfoType1(int infoType1) {
		this.infoType1 = infoType1;
	}

	public int getInfoType2() {
		return infoType2;
	}

	public void setInfoType2(int infoType2) {
		this.infoType2 = infoType2;
	}

	public int getInfoType3() {
		return infoType3;
	}

	public void setInfoType3(int infoType3) {
		this.infoType3 = infoType3;
	}

	public int getInfoId1() {
		return infoId1;
	}

	public void setInfoId1(int infoId1) {
		this.infoId1 = infoId1;
	}

	public int getInfoId2() {
		return infoId2;
	}

	public void setInfoId2(int infoId2) {
		this.infoId2 = infoId2;
	}

	public int getInfoId3() {
		return infoId3;
	}

	public void setInfoId3(int infoId3) {
		this.infoId3 = infoId3;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getCreateUser() {
		return createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(int modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
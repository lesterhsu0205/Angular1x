package com.lester.core.model;

import java.io.Serializable;
import java.util.Date;

public class CurTicketFile implements Serializable{
	
	private Long id; //流水號
	private Long ticketId; //工單ID
	private String path; //檔案路徑
	private String fileName; //檔案名稱
	private Long fileType; //檔案類型
	private String remark; //備註
	private Date createDate; //新增日期
	private Long createUser; //新增人員
	private Date updateDate; //更新日期
	private Long updateUser; //更新人員
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Long getFileType() {
		return fileType;
	}
	public void setFileType(Long fileType) {
		this.fileType = fileType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
}

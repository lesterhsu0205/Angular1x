package com.ycci.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ycci.support.util.DateUtil;
import com.ycci.support.util.Pagination;

public class FileInfoRes implements Serializable{
	
	private Long id;
	private String fileName;
	private String fileType;
	private Date uploaDate; 
	private String uploaDateFormat; 
	private String remark;
	
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
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Date getUploaDate() {
		return uploaDate;
	}
	public void setUploaDate(Date uploaDate) {
		this.uploaDate = uploaDate;
		this.uploaDateFormat = DateUtil.format(uploaDate, DateUtil.FORMAT_Date);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}	
	
}

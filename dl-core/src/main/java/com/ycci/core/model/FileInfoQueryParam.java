package com.ycci.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ycci.support.util.DateUtil;
import com.ycci.support.util.Pagination;

public class FileInfoQueryParam implements Serializable{

	//分頁資訊
	private Pagination pagination;

	//工單編號
	private Long ticketId;
	//檔案類型
	private Long fileType;

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Long getFileType() {
		return fileType;
	}

	public void setFileType(Long fileType) {
		this.fileType = fileType;
	}
	
}

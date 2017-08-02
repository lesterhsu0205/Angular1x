package com.ycci.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ycci.support.util.DateUtil;
import com.ycci.support.util.Pagination;

public class TicketHisStatusRes implements Serializable{
	
	private String statusName;
	private String subStatusName; 
	private String remark;
	private Date createDate;
	private String createDateFormat;
	
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getSubStatusName() {
		return subStatusName;
	}
	public void setSubStatusName(String subStatusName) {
		this.subStatusName = subStatusName;
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
		this.createDateFormat = DateUtil.format(createDate, DateUtil.FORMAT_Date);
	}
	
}

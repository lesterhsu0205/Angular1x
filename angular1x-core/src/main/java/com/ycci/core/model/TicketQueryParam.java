package com.ycci.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ycci.support.util.DateUtil;
import com.ycci.support.util.Pagination;

public class TicketQueryParam implements Serializable{

	//分頁資訊
	private Pagination pagination;
	
	//案件類型
	private Long caseType;
	//案件分類
	private Long caseGroup;
	//建檔日期(起)
	private String startDate;
	//建檔日期(迄)
	private String endDate;
	//狀態
	private Long status;
	//子狀態
	private Long subStatus;
	//地址
	private Address address;
	
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	public Long getCaseType() {
		return caseType;
	}
	public void setCaseType(Long caseType) {
		this.caseType = caseType;
	}
	public Long getCaseGroup() {
		return caseGroup;
	}
	public void setCaseGroup(Long caseGroup) {
		this.caseGroup = caseGroup;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public Long getSubStatus() {
		return subStatus;
	}
	public void setSubStatus(Long subStatus) {
		this.subStatus = subStatus;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}

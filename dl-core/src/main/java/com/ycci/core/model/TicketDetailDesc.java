package com.ycci.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ycci.support.util.DateUtil;

public class TicketDetailDesc implements Serializable{

	//工程名稱
	private String ticketName;
	//案件類型
	private Long caseType;
	//案件分類
	private Long caseGroup;
	//年度
	private Long year;
	//期別
	private Long season;
	//說明
	private String reason;
	
	public String getTicketName() {
		return ticketName;
	}
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
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
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public Long getSeason() {
		return season;
	}
	public void setSeason(Long season) {
		this.season = season;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}

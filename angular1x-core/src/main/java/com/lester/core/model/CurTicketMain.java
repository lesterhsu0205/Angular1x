package com.lester.core.model;

import java.io.Serializable;
import java.util.Date;

public class CurTicketMain implements Serializable{
	
	
	private Long id; //工單ID
	private String ticketName; //工程名稱
	private Long caseType; //案件類型(EX:一般申請, 急件申請)
	private Long caseGroup; //案件分類(EX:道路標線, 標線暨標誌)
	private Long year; //年度
	private Long season; //期別
	private String longitude; //經度
	private String latitude; //緯度
	private String noticeDep; //通報單位
	private String noticePerson; //通報人員
	private String noticeAreacode; //通報人員_區碼
	private String noticeTel; //通報人員_電話
	private String noticeExten; //通報人員_分機
	private String noticeMobile; //通報人員_手機
	private String contractor; //承辦業務
	private String descReason; //描述說明
	private String locationReason; //位置說明
	private String noticeReason; //通報人員_說明
	private Long curStatusId; //目前狀態
	private Date createDate; //新增日期
	private Long createUser; //新增人員
	private Date updateDate; //更新日期
	private Long updateUser; //更新人員
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getNoticeDep() {
		return noticeDep;
	}
	public void setNoticeDep(String noticeDep) {
		this.noticeDep = noticeDep;
	}
	public String getNoticePerson() {
		return noticePerson;
	}
	public void setNoticePerson(String noticePerson) {
		this.noticePerson = noticePerson;
	}
	public String getNoticeAreacode() {
		return noticeAreacode;
	}
	public void setNoticeAreacode(String noticeAreacode) {
		this.noticeAreacode = noticeAreacode;
	}
	public String getNoticeTel() {
		return noticeTel;
	}
	public void setNoticeTel(String noticeTel) {
		this.noticeTel = noticeTel;
	}
	public String getNoticeExten() {
		return noticeExten;
	}
	public void setNoticeExten(String noticeExten) {
		this.noticeExten = noticeExten;
	}
	public String getNoticeMobile() {
		return noticeMobile;
	}
	public void setNoticeMobile(String noticeMobile) {
		this.noticeMobile = noticeMobile;
	}
	public String getContractor() {
		return contractor;
	}
	public void setContractor(String contractor) {
		this.contractor = contractor;
	}
	public String getDescReason() {
		return descReason;
	}
	public void setDescReason(String descReason) {
		this.descReason = descReason;
	}
	public String getLocationReason() {
		return locationReason;
	}
	public void setLocationReason(String locationReason) {
		this.locationReason = locationReason;
	}
	public String getNoticeReason() {
		return noticeReason;
	}
	public void setNoticeReason(String noticeReason) {
		this.noticeReason = noticeReason;
	}
	public Long getCurStatusId() {
		return curStatusId;
	}
	public void setCurStatusId(Long curStatusId) {
		this.curStatusId = curStatusId;
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

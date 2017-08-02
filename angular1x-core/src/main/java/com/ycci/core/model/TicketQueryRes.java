package com.ycci.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ycci.support.util.DateUtil;
import com.ycci.support.util.Pagination;

public class TicketQueryRes implements Serializable{

	private Long id;
	private String ticketName; 
	private String year;
	private String city;
	private String town;
	private String addr;
	private String noticeDep;
	private String noticePerson;
	private String noticeAreacode;
	private String noticeTel;
	private String noticeExten;
	private String noticeMobile;
	private String status;
	private String subStatus;
	private Integer season;
	
	
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubStatus() {
		return subStatus;
	}
	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}
	public Integer getSeason() {
		return season;
	}
	public void setSeason(Integer season) {
		this.season = season;
	}
}

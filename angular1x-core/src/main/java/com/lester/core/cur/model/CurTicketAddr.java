package com.lester.core.cur.model;

import java.io.Serializable;
import java.util.Date;

public class CurTicketAddr implements Serializable{
 
	private Long id; //流水號
	private Long ticketId; //工單ID
	private Long city; //縣市
	private Long town; //鄉鎮區
	private String addr; //路街巷號
	private Boolean status; //狀態(啟用,停用)
	private Date createDate; //新增日期
	private Long createUser; //新增人員
	private Date updateDate; //更新日期
	private Long updateUser; //更新人員
	
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
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
	public Long getCity() {
		return city;
	}
	public void setCity(Long city) {
		this.city = city;
	}
	public Long getTown() {
		return town;
	}
	public void setTown(Long town) {
		this.town = town;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
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

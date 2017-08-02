package com.lester.core.cur.model;

import java.io.Serializable;
import java.util.Date;

public class CurTicketStatus implements Serializable{
	
	private Long id; //流水號
	private Long ticketId; //工單ID
	private Long status; //狀態
	private Long subStatus; //狀態細項
	private String remark; //備註
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
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
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

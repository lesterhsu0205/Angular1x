package com.lester.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.lester.support.util.DateUtil;

/**
 * 新增工單備註
 *
 */
public class InsertRemark implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//工單編號
	private Long ticketId;
	//狀態
	private Long status;
	//子狀態
	private Long subStatus;
	//備註
	private String remark;
	
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
	
	
	
}

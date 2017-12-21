package com.lester.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.lester.support.util.DateUtil;

public class StatusDefault implements Serializable{

	//狀態ID
	private Long statusId;
	//子狀態ID
	private Long substatusId;
	
	
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Long getSubstatusId() {
		return substatusId;
	}
	public void setSubstatusId(Long substatusId) {
		this.substatusId = substatusId;
	}
}

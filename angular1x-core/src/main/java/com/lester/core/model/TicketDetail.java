package com.lester.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.lester.support.util.DateUtil;

/**
 * 工單明細
 * @author et06060606
 *
 */
public class TicketDetail extends ResultMsg implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//工單編號(null 表示尚未開單成功)
	private Long ticketId;
	
	//描述
	private TicketDetailDesc desc;
	//位置
	private TicketDetailLocation location;
	//通報
	private TicketDetailNotice notice;
	
	
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public TicketDetailDesc getDesc() {
		return desc;
	}
	public void setDesc(TicketDetailDesc desc) {
		this.desc = desc;
	}
	public TicketDetailLocation getLocation() {
		return location;
	}
	public void setLocation(TicketDetailLocation location) {
		this.location = location;
	}
	public TicketDetailNotice getNotice() {
		return notice;
	}
	public void setNotice(TicketDetailNotice notice) {
		this.notice = notice;
	}
	
}

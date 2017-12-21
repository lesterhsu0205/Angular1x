package com.lester.core.model;

import java.io.Serializable;

import com.lester.support.util.Pagination;

public class QueryTicketHisStatusParam extends Pagination implements Serializable{

	//工單編號
	private Long ticketId;

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
}

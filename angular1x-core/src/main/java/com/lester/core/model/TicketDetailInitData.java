package com.lester.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.lester.support.util.DateUtil;

public class TicketDetailInitData extends ResultMsg implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private TicketDetailInitStaticData staticData;

	public TicketDetailInitStaticData getStaticData() {
		return staticData;
	}

	public void setStaticData(TicketDetailInitStaticData staticData) {
		this.staticData = staticData;
	}
}

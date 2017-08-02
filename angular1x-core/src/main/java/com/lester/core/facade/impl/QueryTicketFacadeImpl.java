package com.lester.core.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lester.core.facade.ITicketQueryFacade;
import com.lester.core.model.TicketQueryInitData;
import com.lester.core.model.TicketQueryParam;
import com.lester.core.model.TicketQueryRes;
import com.lester.core.service.ITicketQueryService;
import com.lester.support.util.TableRes;

@Component
public class QueryTicketFacadeImpl implements ITicketQueryFacade{

	
	@Autowired
	private ITicketQueryService ticketQueryService;

	@Override
	public TicketQueryInitData getTicketQueryInitData() throws Exception {
		return ticketQueryService.getTicketQueryInitData();
	}

	@Override
	public TableRes<TicketQueryRes> queryTicket(TicketQueryParam param) throws Exception {
		return ticketQueryService.queryTicket(param);
	}

}

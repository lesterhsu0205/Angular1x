package com.ycci.core.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycci.core.facade.ITicketQueryFacade;
import com.ycci.core.model.TicketQueryInitData;
import com.ycci.core.model.TicketQueryParam;
import com.ycci.core.model.TicketQueryRes;
import com.ycci.core.service.ITicketQueryService;
import com.ycci.support.util.TableRes;

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

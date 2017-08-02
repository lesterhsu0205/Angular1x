package com.ycci.core.service;

import com.ycci.core.model.TicketQueryInitData;
import com.ycci.core.model.TicketQueryParam;
import com.ycci.core.model.TicketQueryRes;
import com.ycci.support.util.TableRes;

public interface ITicketQueryService {

	
	/**
	 * 工單查詢-取得初始畫資料
	 * @return
	 */
	public TicketQueryInitData getTicketQueryInitData() throws Exception;

	/**
	 * 工單查詢-取得查詢結果
	 * @return
	 */
	public TableRes<TicketQueryRes> queryTicket(TicketQueryParam param) throws Exception;
	
}

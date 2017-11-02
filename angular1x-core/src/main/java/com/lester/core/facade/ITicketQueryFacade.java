package com.lester.core.facade;

import com.lester.core.model.TicketQueryInitData;
import com.lester.core.model.TicketQueryParam;
import com.lester.core.model.TicketQueryRes;
import com.lester.support.util.TableRes;

/**
 * The interface Ticket query facade.
 */
public interface ITicketQueryFacade {

    /**
     * 工單查詢-取得初始畫資料
     *
     * @return ticket query init data
     * @throws Exception the exception
     */
    TicketQueryInitData getTicketQueryInitData() throws Exception;

    /**
     * 工單查詢-取得查詢結果
     *
     * @param param the param
     * @return table res
     * @throws Exception the exception
     */
    TableRes<TicketQueryRes> queryTicket(TicketQueryParam param) throws Exception;

}

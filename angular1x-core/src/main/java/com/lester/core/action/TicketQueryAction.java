package com.lester.core.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lester.core.config.SysConfig;
import com.lester.core.facade.ITicketQueryFacade;
import com.lester.core.model.CfgAuthUser;
import com.lester.core.model.QueryTicketHisStatusParam;
import com.lester.core.model.TicketQueryInitData;
import com.lester.core.model.TicketQueryParam;
import com.lester.core.model.TicketQueryRes;
import com.lester.support.handler.ActionSupport;
import com.lester.support.util.TableRes;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TicketQueryAction extends ActionSupport {
	
	

	@Autowired
	private ITicketQueryFacade ticketQueryFacade;
	
	/**
	 * 工單查詢-取得初始畫資料
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryTicket/getTicketQueryInitData.action", method = RequestMethod.POST)	
	public  @ResponseBody TicketQueryInitData getTicketQueryInitData(/*@RequestBody String req*/) throws Exception {
		TicketQueryInitData res = ticketQueryFacade.getTicketQueryInitData();
		return res;
	}
	
	/**
	 * 工單查詢-取得查詢結果
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryTicket/queryTicket.action", method = RequestMethod.POST)	
	public  @ResponseBody TableRes<TicketQueryRes> queryTicket(@RequestBody TicketQueryParam param) throws Exception {
		TableRes<TicketQueryRes> res = ticketQueryFacade.queryTicket(param);
		return res;
	}
	
}

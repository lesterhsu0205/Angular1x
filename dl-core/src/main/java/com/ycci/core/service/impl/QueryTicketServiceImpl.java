package com.ycci.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycci.core.dao.ICfgSysConfigDao;
import com.ycci.core.dao.ITicketDetailDao;
import com.ycci.core.model.TicketHisStatusRes;
import com.ycci.core.model.TicketQueryInitData;
import com.ycci.core.model.TicketQueryParam;
import com.ycci.core.model.TicketQueryRes;
import com.ycci.core.service.ITicketQueryService;
import com.ycci.support.util.LogUtil;
import com.ycci.support.util.TableRes;
import com.ycci.core.service.ITicketCommonService;

@Service
public class QueryTicketServiceImpl implements ITicketQueryService{


	@Autowired
	private ICfgSysConfigDao cfgSysConfigDao;
	@Autowired
	private ITicketCommonService ticketCommonService;
	@Autowired
	private ITicketDetailDao ticketDetailDao;
	
	
	
	@Override
	public TicketQueryInitData getTicketQueryInitData() throws Exception {
		TicketQueryInitData res = new TicketQueryInitData();
		res.setSuc(true);


		
		//取得案件類型下拉選單
		try {
			res.setCaseTypeItems(ticketCommonService.getCaseTypeItems());
		} catch (Exception e) {
			String msg = "取得案件類型下拉選單錯誤 msg : " + e.getMessage();
			res.setSuc(false);
			res.setMsg(msg);
			return res;
		}
		
		//取得案件分類下拉選單
		try {
			res.setCaseGroupItems(ticketCommonService.getCaseGroupItems());
		} catch (Exception e) {
			String msg = "取得案件分類下拉選單錯誤 msg : " + e.getMessage();
			res.setSuc(false);
			res.setMsg(msg);
			return res;
		}

		//取得工單狀態下拉選單
		try {
			res.setStatusItems(ticketCommonService.getStatusItems());
		} catch (Exception e) {
			String msg = "取得工單狀態下拉選單錯誤 msg : " + e.getMessage();
			res.setSuc(false);
			res.setMsg(msg);
			return res;
		}
		
		//取得工單子狀態下拉選單
		try {
			res.setSubStatusItems(ticketCommonService.getSubStatusItems());
		} catch (Exception e) {
			String msg = "取得工單子狀態下拉選單錯誤 msg : " + e.getMessage();
			res.setSuc(false);
			res.setMsg(msg);
			return res;
		}
		
		//取得縣市下拉選單
		try {
			res.setCityItems(ticketCommonService.getCityItems());
		} catch (Exception e) {
			String msg = "取得縣市下拉選單錯誤 msg : " + e.getMessage();
			res.setSuc(false);
			res.setMsg(msg);
			return res;
		}
		
		//取得鄉鎮下拉選單
		try {
			res.setTownItems(ticketCommonService.getTownItems());
		} catch (Exception e) {
			String msg = "取得鄉鎮下拉選單錯誤 msg : " + e.getMessage();
			res.setSuc(false);
			res.setMsg(msg);
			return res;
		}
		
		return res;
	}

	@Override
	public TableRes<TicketQueryRes> queryTicket(TicketQueryParam param) throws Exception {
		TableRes<TicketQueryRes> res = new TableRes<TicketQueryRes>();
		res.setSuc(true);
		
		Integer pageIdx = null;
		Integer pageSize = null;
		
		if(param != null && param.getPagination() != null){
			pageIdx = param.getPagination().getPageIdx();
			pageSize = param.getPagination().getPageSize();
		}
		
		//驗證
		if(pageIdx == null){
			res.setSuc(false);
			res.setMsg("缺少必要參數 pageIdx");
			return res;
		} else if(pageSize == null){
			res.setSuc(false);
			res.setMsg("缺少必要參數 pageSize");
			return res;
		}
	
		try {
			List<TicketQueryRes> resultList = ticketDetailDao.queryTicket(pageIdx, pageSize, param);
			res.setResult(resultList);
		} catch (Exception e) {
			LogUtil.info(getClass(), "[queryTicket] query ticket error", e);
			res.setSuc(false);
			res.setMsg("查詢分頁資料時發生錯誤");
			return res;
		}
	
		try {
			Long totalCount = ticketDetailDao.queryTicketCount(param);
		res.setTotalCount(totalCount);
		} catch (Exception e) {
			LogUtil.info(getClass(), "[queryTicket] query ticket count error", e);
			res.setSuc(false);
			res.setMsg("查詢分頁總數資料時發生錯誤");
			return res;
		}
		
		return res;
	}
	
}

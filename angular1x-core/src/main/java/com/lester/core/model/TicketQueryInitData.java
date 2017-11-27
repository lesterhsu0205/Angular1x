package com.lester.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lester.support.util.DateUtil;
import com.lester.core.viewModel.Option;

public class TicketQueryInitData extends ResultMsg implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//系統時間
	private String today = DateUtil.format(new Date(), DateUtil.FORMAT_Date);
	
	//案件類型
	private List<CfgSystemConfig> caseTypeItems;
	//案件分類
	private List<CfgSystemConfig> caseGroupItems;
	//工單狀態
	private List<CfgSystemConfig> statusItems;
	//工單子狀態
	private Map<Long, List<CfgSystemConfig>> subStatusItems;
	//縣市
	private List<CfgSystemConfig> cityItems;
	//鄉鎮
	private Map<Long, List<CfgSystemConfig>> townItems;
	
	
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public List<CfgSystemConfig> getCaseTypeItems() {
		return caseTypeItems;
	}
	public void setCaseTypeItems(List<CfgSystemConfig> caseTypeItems) {
		this.caseTypeItems = caseTypeItems;
	}
	public List<CfgSystemConfig> getCaseGroupItems() {
		return caseGroupItems;
	}
	public void setCaseGroupItems(List<CfgSystemConfig> caseGroupItems) {
		this.caseGroupItems = caseGroupItems;
	}
	public List<CfgSystemConfig> getStatusItems() {
		return statusItems;
	}
	public void setStatusItems(List<CfgSystemConfig> statusItems) {
		this.statusItems = statusItems;
	}
	public Map<Long, List<CfgSystemConfig>> getSubStatusItems() {
		return subStatusItems;
	}
	public void setSubStatusItems(Map<Long, List<CfgSystemConfig>> subStatusItems) {
		this.subStatusItems = subStatusItems;
	}
	public List<CfgSystemConfig> getCityItems() {
		return cityItems;
	}
	public void setCityItems(List<CfgSystemConfig> cityItems) {
		this.cityItems = cityItems;
	}
	public Map<Long, List<CfgSystemConfig>> getTownItems() {
		return townItems;
	}
	public void setTownItems(Map<Long, List<CfgSystemConfig>> townItems) {
		this.townItems = townItems;
	}
	
}

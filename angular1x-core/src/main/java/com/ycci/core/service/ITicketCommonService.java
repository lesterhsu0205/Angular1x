package com.ycci.core.service;

import java.util.List;
import java.util.Map;

import com.ycci.core.model.CfgSystemConfig;
import com.ycci.core.model.TicketQueryInitData;
import com.ycci.support.viewModel.Option;

public interface ITicketCommonService {


	/**
	 * 取得縣市下拉選單
	 * @return
	 */
	public List<CfgSystemConfig> getCityItems() throws Exception;
	
	/**
	 * 取得鄉鎮下拉選單
	 * @return
	 */
	public Map<Long, List<CfgSystemConfig>> getTownItems() throws Exception;
	
	/**
	 * 取得案件類型下拉選單
	 * @return
	 */
	public List<CfgSystemConfig> getCaseTypeItems() throws Exception;
	
	/**
	 * 取得案件分類下拉選單
	 * @return
	 */
	public List<CfgSystemConfig> getCaseGroupItems() throws Exception;
	
	/**
	 * 取得工單狀態下拉選單
	 * @return
	 */
	public List<CfgSystemConfig> getStatusItems() throws Exception;
	
	/**
	 * 取得工單子狀態下拉選單
	 * @return
	 */
	public Map<Long, List<CfgSystemConfig>> getSubStatusItems() throws Exception;
	
	/**
	 * 取得檔案類型下拉選單
	 * @return
	 */
	public List<CfgSystemConfig> getFileTypeItems() throws Exception;
	
	/**
	 * 取得年度下拉選單
	 * @return
	 */
	public List<Option<Long, String>> getYearItems() throws Exception;
	
	/**
	 * 取得期別下拉選單
	 * @return
	 */
	public List<Option<Long, Long>> getSeasonItems() throws Exception;
	
}

package com.lester.core.service;

import com.lester.core.model.CfgSystemConfig;
import com.lester.core.viewModel.Option;

import java.util.List;
import java.util.Map;

/**
 * The interface Ticket common service.
 */
public interface ITicketCommonService {


	/**
	 * 取得縣市下拉選單
	 *
	 * @return city items
	 * @throws Exception the exception
	 */
	List<CfgSystemConfig> getCityItems() throws Exception;

	/**
	 * 取得鄉鎮下拉選單
	 *
	 * @return town items
	 * @throws Exception the exception
	 */
	Map<Long, List<CfgSystemConfig>> getTownItems() throws Exception;

	/**
	 * 取得案件類型下拉選單
	 *
	 * @return case type items
	 * @throws Exception the exception
	 */
	List<CfgSystemConfig> getCaseTypeItems() throws Exception;

	/**
	 * 取得案件分類下拉選單
	 *
	 * @return case group items
	 * @throws Exception the exception
	 */
	List<CfgSystemConfig> getCaseGroupItems() throws Exception;

	/**
	 * 取得工單狀態下拉選單
	 *
	 * @return status items
	 * @throws Exception the exception
	 */
	List<CfgSystemConfig> getStatusItems() throws Exception;

	/**
	 * 取得工單子狀態下拉選單
	 *
	 * @return sub status items
	 * @throws Exception the exception
	 */
	Map<Long, List<CfgSystemConfig>> getSubStatusItems() throws Exception;

	/**
	 * 取得檔案類型下拉選單
	 *
	 * @return file type items
	 * @throws Exception the exception
	 */
	List<CfgSystemConfig> getFileTypeItems() throws Exception;

	/**
	 * 取得年度下拉選單
	 *
	 * @return year items
	 * @throws Exception the exception
	 */
	List<Option<Long, String>> getYearItems() throws Exception;

	/**
	 * 取得期別下拉選單
	 *
	 * @return season items
	 * @throws Exception the exception
	 */
	List<Option<Long, Long>> getSeasonItems() throws Exception;

}

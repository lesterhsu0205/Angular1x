package com.lester.core.service;

import com.lester.core.cur.model.CurTicketFile;
import com.lester.core.model.CfgAuthUser;
import com.lester.core.model.FileInfoQueryParam;
import com.lester.core.model.FileInfoRes;
import com.lester.core.model.InsertRemark;
import com.lester.core.model.QueryTicketHisStatusParam;
import com.lester.core.model.ResultMsg;
import com.lester.core.model.TicketCreateRes;
import com.lester.core.model.TicketDetail;
import com.lester.core.model.TicketDetailInitData;
import com.lester.core.model.TicketHisStatusRes;
import com.lester.core.model.UploadFileInfo;
import com.lester.support.util.TableRes;

public interface ITicketDetailService {

	
	/**
	 * 工單明細-取得初始畫資料
	 * @return
	 */
	public TicketDetailInitData getTicketDetailInitData() throws Exception;
	
	/**
	 * 工單明細-開單
	 * @return
	 */
	public TicketCreateRes createTicket(TicketDetail ticketDetail, CfgAuthUser user) throws Exception;
	
	/**
	 * 工單明細-讀取工單資料
	 * @return
	 */
	public TicketDetail getTicketData(Long ticketId) throws Exception;
	
	/**
	 * 工單明細-修改
	 * @return
	 */
	public ResultMsg modifyTicket(TicketDetail ticketDetail, CfgAuthUser user) throws Exception;

	/**
	 * 工單明細-新增備註
	 * @return
	 */
	public ResultMsg insertRemark(InsertRemark remark, CfgAuthUser user) throws Exception;

	/**
	 * 工單明細-讀取工單歷程table
	 * @return
	 */
	public TableRes<TicketHisStatusRes> queryTicketHisStatus(QueryTicketHisStatusParam param) throws Exception;

	/**
	 * 工單明細-檔案查詢
	 * @return
	 */
	public TableRes<FileInfoRes> queryFileInfo(FileInfoQueryParam param) throws Exception;
	
	/**
	 * 工單明細-檔案上傳
	 * @return
	 */
	public ResultMsg uploadfile(UploadFileInfo uploadFileInfo, CfgAuthUser user) throws Exception;

	/**
	 * 工單明細-查詢檔案資訊
	 * @return
	 */
	public CurTicketFile queryCurTicketFile(Long fileId) throws Exception;
}

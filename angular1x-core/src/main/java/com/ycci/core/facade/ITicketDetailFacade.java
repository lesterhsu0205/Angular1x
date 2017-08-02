package com.ycci.core.facade;

import com.ycci.core.cur.model.CurTicketFile;
import com.ycci.core.model.CfgAuthUser;
import com.ycci.core.model.FileInfoQueryParam;
import com.ycci.core.model.FileInfoRes;
import com.ycci.core.model.InsertRemark;
import com.ycci.core.model.QueryTicketHisStatusParam;
import com.ycci.core.model.ResultMsg;
import com.ycci.core.model.TicketCreateRes;
import com.ycci.core.model.TicketDetail;
import com.ycci.core.model.TicketDetailInitData;
import com.ycci.core.model.TicketHisStatusRes;
import com.ycci.core.model.UploadFileInfo;
import com.ycci.support.util.TableRes;

public interface ITicketDetailFacade {

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

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

/**
 * The interface Ticket detail service.
 */
public interface ITicketDetailService {


    /**
     * 工單明細-取得初始畫資料
     *
     * @return ticket detail init data
     * @throws Exception the exception
     */
    TicketDetailInitData getTicketDetailInitData() throws Exception;

    /**
     * 工單明細-開單
     *
     * @param ticketDetail the ticket detail
     * @param user         the user
     * @return ticket create res
     * @throws Exception the exception
     */
    TicketCreateRes createTicket(TicketDetail ticketDetail, CfgAuthUser user) throws Exception;

    /**
     * 工單明細-讀取工單資料
     *
     * @param ticketId the ticket id
     * @return ticket data
     * @throws Exception the exception
     */
    TicketDetail getTicketData(Long ticketId) throws Exception;

    /**
     * 工單明細-修改
     *
     * @param ticketDetail the ticket detail
     * @param user         the user
     * @return result msg
     * @throws Exception the exception
     */
    ResultMsg modifyTicket(TicketDetail ticketDetail, CfgAuthUser user) throws Exception;

    /**
     * 工單明細-新增備註
     *
     * @param remark the remark
     * @param user   the user
     * @return result msg
     * @throws Exception the exception
     */
    ResultMsg insertRemark(InsertRemark remark, CfgAuthUser user) throws Exception;

    /**
     * 工單明細-讀取工單歷程table
     *
     * @param param the param
     * @return table res
     * @throws Exception the exception
     */
    TableRes<TicketHisStatusRes> queryTicketHisStatus(QueryTicketHisStatusParam param) throws Exception;

    /**
     * 工單明細-檔案查詢
     *
     * @param param the param
     * @return table res
     * @throws Exception the exception
     */
    TableRes<FileInfoRes> queryFileInfo(FileInfoQueryParam param) throws Exception;

    /**
     * 工單明細-檔案上傳
     *
     * @param uploadFileInfo the upload file info
     * @param user           the user
     * @return result msg
     * @throws Exception the exception
     */
    ResultMsg uploadfile(UploadFileInfo uploadFileInfo, CfgAuthUser user) throws Exception;

    /**
     * 工單明細-查詢檔案資訊
     *
     * @param fileId the file id
     * @return cur ticket file
     * @throws Exception the exception
     */
    CurTicketFile queryCurTicketFile(Long fileId) throws Exception;
}

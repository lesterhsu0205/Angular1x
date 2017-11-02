package com.lester.action;

import com.lester.core.cur.model.CurTicketFile;
import com.lester.core.facade.ITicketDetailFacade;
import com.lester.core.model.*;
import com.lester.support.handler.ActionSupport;
import com.lester.support.util.ApEvn;
import com.lester.support.util.LogUtil;
import com.lester.support.util.TableRes;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * The type Ticket detail action.
 */
@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TicketDetailAction extends ActionSupport {

	@Autowired
	private ITicketDetailFacade ticketDetailFacade;

	/**
	 * 工單明細-取得初始畫資料
	 *
	 * @return TicketDetailInitData ticket detail init data
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/ticketDetail/getTicketDetailInitData.action", method = RequestMethod.POST)
	public  @ResponseBody TicketDetailInitData getTicketDetailInitData(/*@RequestBody String req*/) throws Exception {
		TicketDetailInitData ticketDetailInitData = ticketDetailFacade.getTicketDetailInitData();
		return ticketDetailInitData;
	}

	/**
	 * 工單明細-開單
	 *
	 * @param ticketDetail the ticket detail
	 * @return the ticket create res
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/ticketDetail/createTicket.action", method = RequestMethod.POST)
	public  @ResponseBody TicketCreateRes createTicket(@RequestBody TicketDetail ticketDetail) throws Exception {
		TicketCreateRes res = ticketDetailFacade.createTicket(ticketDetail, super.getCfgAuthUser());
		return res;
	}

	/**
	 * 工單明細-讀取工單資料
	 *
	 * @param ticketId the ticket id
	 * @return the ticket data
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/ticketDetail/getTicketData.action", method = RequestMethod.POST)
	public  @ResponseBody TicketDetail getTicketData(@RequestBody Long ticketId) throws Exception {
		TicketDetail res = ticketDetailFacade.getTicketData(ticketId);
		return res;
	}

	/**
	 * 工單明細-修改
	 *
	 * @param ticketDetail the ticket detail
	 * @return the result msg
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/ticketDetail/modifyTicket.action", method = RequestMethod.POST)
	public  @ResponseBody ResultMsg modifyTicket(@RequestBody TicketDetail ticketDetail) throws Exception {
		ResultMsg res = ticketDetailFacade.modifyTicket(ticketDetail, super.getCfgAuthUser());
		return res;
	}

	/**
	 * 工單明細-新增備註
	 *
	 * @param remark the remark
	 * @return the result msg
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/ticketDetail/insertRemark.action", method = RequestMethod.POST)
	public  @ResponseBody ResultMsg insertRemark(@RequestBody InsertRemark remark) throws Exception {
		ResultMsg res = ticketDetailFacade.insertRemark(remark, super.getCfgAuthUser());
		return res;
	}

	/**
	 * 工單明細-讀取工單歷程table
	 *
	 * @param param the param
	 * @return the table res
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/ticketDetail/queryTicketHisStatus.action", method = RequestMethod.POST)
	public  @ResponseBody TableRes<TicketHisStatusRes> queryTicketHisStatus(@RequestBody QueryTicketHisStatusParam param) throws Exception {
		TableRes<TicketHisStatusRes> res = ticketDetailFacade.queryTicketHisStatus(param);
		return res;
	}

	/**
	 * 工單明細-檔案上傳
	 *
	 * @param file     the file
	 * @param ticketId the ticket id
	 * @param fileType the file type
	 * @param remark   the remark
	 * @return the result msg
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/ticketDetail/uploadfile.action", method = RequestMethod.POST)
	public @ResponseBody ResultMsg uploadfile(
			@RequestParam(value="file", required=false) CommonsMultipartFile file,
			@RequestParam(value="ticketId", required=false) Long ticketId,
			@RequestParam(value="fileType", required=false) Long fileType,
			@RequestParam(value="remark", required=false) String remark) throws Exception {

		UploadFileInfo uploadFileInfo = new UploadFileInfo();
		uploadFileInfo.setFileType(fileType);
		uploadFileInfo.setTicketId(ticketId);
		uploadFileInfo.setRemark(remark);
		uploadFileInfo.setFile(file);

		return ticketDetailFacade.uploadfile(uploadFileInfo, super.getCfgAuthUser());
	}

	/**
	 * 工單明細-檔案查詢
	 *
	 * @param param the param
	 * @return the table res
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/ticketDetail/queryFileInfo.action", method = RequestMethod.POST)
	public  @ResponseBody TableRes<FileInfoRes> queryFileInfo(@RequestBody FileInfoQueryParam param) throws Exception {
		TableRes<FileInfoRes> res = ticketDetailFacade.queryFileInfo(param);
		return res;
	}

	/**
	 * 工單明細-檔案下載
	 *
	 * @param response the response
	 * @param fileId   the file id
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/ticketDetail/fileDownload.action", method = RequestMethod.POST)
	public void fileDownload(HttpServletResponse response, @RequestParam("fileId") Long fileId) throws Exception {


		String filePathProto = ApEvn.get("filePathProto");
		String fileRelativelyPath = null;
		String fileName = null;

		CurTicketFile curTicketFile = ticketDetailFacade.queryCurTicketFile(fileId);
		if(curTicketFile != null){
			fileRelativelyPath = curTicketFile.getPath();
			fileName = curTicketFile.getFileName();
		}

		if(StringUtils.isNotBlank(fileRelativelyPath)){

			//中文亂碼問題
			//http://blog.xuite.net/chocopie0226/programerJava/64510989-%E6%AA%94%E6%A1%88%E4%B8%8B%E8%BC%89-%E4%B8%AD%E6%96%87%E6%AA%94%E5%90%8D%E5%92%8C%E6%AA%94%E5%90%8D%E5%8C%85%E5%90%AB%E7%A9%BA%E7%99%BD%E5%95%8F%E9%A1%8C%E8%99%95%E7%90%86

			String fileNameEncode = null;
			if(StringUtils.isNotBlank(fileName)){
				fileNameEncode = URLEncoder.encode(fileName, "utf-8");

				//處理空白問題
				fileNameEncode = fileNameEncode.replaceAll("\\+", "%20");
			}

//	        response.setLocale(java.util.Locale.TAIWAN);
//	        response.setContentType("application/pdf;charset=UTF-8;");
			// 等號前要加*號，第一個位置編碼，第二個位置國別，然後檔名
			response.setHeader("Content-disposition", "attachment; filename*=utf-8'zh_TW'" + fileNameEncode);


//	        // for IE8 https download start
//	        response.setHeader("Cache-Control", "private");
//	        response.setHeader("Pragma", "private");
//	        // for IE8 https download end

			InputStream is = null;
			OutputStream os = null;
			try {
				String aa = filePathProto + fileRelativelyPath + fileName;
				is = new FileInputStream(aa);
				os = response.getOutputStream();
				byte[] pdfByte = IOUtils.toByteArray(is);
				os.write(pdfByte);
			} catch (Exception e){
				LogUtil.info(getClass(), "[fileDownload] file download error", e);
				throw e;
			} finally {
				if(is != null){
					is.close();
				}
				if(os != null){
					os.close();
				}
			}
		}
	}
}

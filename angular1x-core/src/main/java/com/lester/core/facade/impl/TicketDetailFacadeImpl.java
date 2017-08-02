package com.lester.core.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lester.core.cur.model.CurTicketFile;
import com.lester.core.facade.ITicketDetailFacade;
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
import com.lester.core.service.ITicketDetailService;
import com.lester.support.handler.FacadeSupport;
import com.lester.support.handler.ServiceClosure;
import com.lester.support.handler.TransactionManager;
import com.lester.support.util.TableRes;

@Component
public class TicketDetailFacadeImpl extends FacadeSupport implements ITicketDetailFacade{

	
	@Autowired
	private ITicketDetailService ticketDetailService;

	@Override
	public TicketDetailInitData getTicketDetailInitData() throws Exception {
		return ticketDetailService.getTicketDetailInitData();
	}

	@Override
	public TicketCreateRes createTicket(final TicketDetail ticketDetail, final CfgAuthUser user) throws Exception {
		TicketCreateRes res = super.doTransaction(new ServiceClosure.isRetrun<TicketCreateRes>() {

			public TicketCreateRes run(TransactionManager txManager) throws Exception {
				
				TicketCreateRes res = ticketDetailService.createTicket(ticketDetail, user);
				
				if(res == null || !res.isSuc()){
					txManager.rollback();
				}
				
				return res;
			}
			
		});
		return res;
	}

	@Override
	public TicketDetail getTicketData(Long ticketId) throws Exception {
		return ticketDetailService.getTicketData(ticketId);
	}

	@Override
	public ResultMsg modifyTicket(final TicketDetail ticketDetail, final CfgAuthUser user) throws Exception {
		ResultMsg res = super.doTransaction(new ServiceClosure.isRetrun<ResultMsg>() {

			public ResultMsg run(TransactionManager txManager) throws Exception {
				
				ResultMsg res = ticketDetailService.modifyTicket(ticketDetail, user);
				
				if(res == null || !res.isSuc()){
					txManager.rollback();
				}
				
				return res;
			}
			
		});
		return res;
	}

	@Override
	public ResultMsg insertRemark(final InsertRemark remark, final CfgAuthUser user) throws Exception {
		ResultMsg res = super.doTransaction(new ServiceClosure.isRetrun<ResultMsg>() {

			public ResultMsg run(TransactionManager txManager) throws Exception {
				
				ResultMsg res = ticketDetailService.insertRemark(remark, user);
				
				if(res == null || !res.isSuc()){
					txManager.rollback();
				}
				
				return res;
			}
			
		});
		return res;
	}

	@Override
	public TableRes<TicketHisStatusRes> queryTicketHisStatus(QueryTicketHisStatusParam param) throws Exception {
		return ticketDetailService.queryTicketHisStatus(param);
	}

	@Override
	public TableRes<FileInfoRes> queryFileInfo(FileInfoQueryParam param) throws Exception {
		return ticketDetailService.queryFileInfo(param);
	}

	@Override
	public ResultMsg uploadfile(final UploadFileInfo uploadFileInfo, final CfgAuthUser user) throws Exception {
		ResultMsg res = super.doTransaction(new ServiceClosure.isRetrun<ResultMsg>() {

			public ResultMsg run(TransactionManager txManager) throws Exception {
				
				ResultMsg res = ticketDetailService.uploadfile(uploadFileInfo, user);
				
				if(res == null || !res.isSuc()){
					txManager.rollback();
				}
				
				return res;
			}
			
		});
		return res;
	}

	@Override
	public CurTicketFile queryCurTicketFile(Long fileId) throws Exception {
		return ticketDetailService.queryCurTicketFile(fileId);
	}
}

package com.lester.core.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lester.core.cur.model.CurTicketAddr;
import com.lester.core.cur.model.CurTicketFile;
import com.lester.core.cur.model.CurTicketMain;
import com.lester.core.cur.model.CurTicketStatus;
import com.lester.core.dao.ITicketDetailDao;
import com.lester.core.model.Address;
import com.lester.core.model.FileInfoQueryParam;
import com.lester.core.model.FileInfoRes;
import com.lester.core.model.TicketHisStatusRes;
import com.lester.core.model.TicketQueryParam;
import com.lester.core.model.TicketQueryRes;
import com.lester.support.util.DateUtil;
import com.lester.support.util.DlDaoSupport;
import com.lester.support.util.Session;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TicketDetailDaoImpl extends DlDaoSupport implements ITicketDetailDao{

	
	@Override
	public CurTicketMain insertCurTicketMain(CurTicketMain curTicketMain) throws Exception {
		Session session = super.getSession();
		session.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-insertCurTicketMain");
		session.setObjParam(curTicketMain);
		return session.insertByRtnObjParam();
	}

	@Override
	public List<CurTicketAddr> insertCurTicketAddr(List<CurTicketAddr> curTicketAddr) throws Exception {
		Session session = super.getSession();
		session.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-insertCurTicketAddr");
		session.setObjParam(curTicketAddr);
		return session.insertByRtnObjParam();
	}

	@Override
	public CurTicketStatus insertCurTicketStatus(CurTicketStatus curTicketStatus) throws Exception {
		Session session = super.getSession();
		session.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-insertCurTicketStatus");
		session.setObjParam(curTicketStatus);
		return session.insertByRtnObjParam();
	}

	@Override
	public CurTicketFile insertCurTicketFile(CurTicketFile curTicketFile) throws Exception {
		Session session = super.getSession();
		session.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-insertCurTicketFile");
		session.setObjParam(curTicketFile);
		return session.insertByRtnObjParam();
	}
	
	@Override
	public CurTicketMain queryCurTicketMain(Long id) throws Exception {
		Session session = super.getSession();
		session.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-queryCurTicketMain");
		session.addParams("ID", id);
		session.setTransformBean(CurTicketMain.class);
		List<CurTicketMain> resList = session.selectList();
		return (resList != null && resList.size() > 0) ? resList.get(0) : null;
	}
	
	@Override
	public CurTicketFile queryCurTicketFile(Long id) throws Exception {
		Session session = super.getSession();
		session.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-queryCurTicketFile");
		session.addParams("ID", id);
		session.setTransformBean(CurTicketFile.class);
		List<CurTicketFile> resList = session.selectList();
		return (resList != null && resList.size() > 0) ? resList.get(0) : null;
	}

	@Override
	public List<CurTicketAddr> queryCurTicketAddr(Long ticketId) throws Exception {
		Session session = super.getSession();
		session.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-queryCurTicketAddr");
		session.addParams("TICKET_ID", ticketId);
		session.setTransformBean(CurTicketAddr.class);
		List<CurTicketAddr> resList = session.selectList();
		return resList;
	}

	@Override
	public Integer updateCurStatusId(Long ticketId, Long curStatusId, Long updateUser) throws Exception {
		Session session = super.getSession()
				.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-updateCurStatusId")
				.addParams("ID", ticketId)
				.addParams("CUR_STATUS_ID", curStatusId)
				.addParams("UPDATE_USER", updateUser);
		return session.update();
	}

	@Override
	public Integer updateCurTicketMainByModify(CurTicketMain curTicketMain) throws Exception {
		Session session = super.getSession()
				.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-updateCurTicketMainByModify")
				.setObjParam(curTicketMain);
		return session.updateByObj();
	}

	@Override
	public Integer updateCurTicketAddrStatusToClose(Long ticketId, Long updateUser) throws Exception {
		Session session = super.getSession()
				.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-updateCurTicketAddrStatusToClose")
				.addParams("UPDATE_USER", updateUser)
				.addParams("TICKET_ID", ticketId);
		return session.update();
	}

	@Override
	public List<TicketHisStatusRes> queryTicketHisStatus(Long ticketId, Integer pageIdx, Integer pageSize)
			throws Exception {
		Session session = super.getSession();
		session.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-queryTicketHisStatus");
		session.addParams("TICKET_ID", ticketId);
		session.addParams("PAGE_IDX", pageIdx);
		session.addParams("PAGE_SIZE", pageSize);
		session.setTransformBean(TicketHisStatusRes.class);
		List<TicketHisStatusRes> resList = session.selectList();
		return resList;
	}

	@Override
	public Long queryTicketHisStatusCount(Long ticketId) throws Exception {

		Session session = super.getSession()
				.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-queryTicketHisStatusCount")
				.addParams("TICKET_ID", ticketId);
		
		return session.selectOne();
	}

	@Override
	public List<FileInfoRes> queryFileInfo(FileInfoQueryParam param, Integer pageIdx, Integer pageSize)
			throws Exception {
		Session session = super.getSession();
		session.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-queryFileInfo");
		setQueryFileInfoParam(session, pageIdx, pageSize, param);
		session.setTransformBean(FileInfoRes.class);
		List<FileInfoRes> resList = session.selectList();
		return resList;
	}

	@Override
	public Long queryFileInfoCount(FileInfoQueryParam param) throws Exception {

		Session session = super.getSession();
		session.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-queryFileInfoCount");
		setQueryFileInfoParam(session, null, null, param);
		return session.selectOne();
	}
	
	private void setQueryFileInfoParam(Session session, Integer pageIdx, Integer pageSize, FileInfoQueryParam param){

		session.addParams("PAGE_IDX", pageIdx);
		session.addParams("PAGE_SIZE", pageSize);
		session.addParams("TICKET_ID", param.getTicketId());
		
		session.addParams("NOT_FILE_TYPE", param.getFileType() == null ? 1 : 0);
		session.addParams("FILE_TYPE", param.getFileType() != null ? param.getFileType() : "''");
	}

	@Override
	public List<TicketQueryRes> queryTicket(Integer pageIdx, Integer pageSize, TicketQueryParam param) throws Exception {
		Session session = super.getSession();
		session.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-queryTicket");
		
		setQueryTicketParam(session, pageIdx, pageSize, param);
		
		session.setTransformBean(TicketQueryRes.class);
		List<TicketQueryRes> resList = session.selectList();
		return resList;
	}

	@Override
	public Long queryTicketCount(TicketQueryParam param) throws Exception {
		
		Session session = super.getSession();
		session.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-queryTicketCount");


		setQueryTicketParam(session, null, null, param);

		return session.selectOne();
	}
	
	private void setQueryTicketParam(Session session, Integer pageIdx, Integer pageSize, TicketQueryParam param){
		session.addParams("PAGE_IDX", pageIdx);
		session.addParams("PAGE_SIZE", pageSize);

		session.addParams("NOT_CASE_TYPE", param.getCaseType() == null ? 1 : 0);
		session.addParams("CASE_TYPE", param.getCaseType() != null ? param.getCaseType() : "''");
		
		session.addParams("NOT_CASE_GROUP", param.getCaseGroup() == null ? 1 : 0);
		session.addParams("CASE_GROUP", param.getCaseGroup() != null ? param.getCaseGroup() : "''");

		session.addParams("NOT_STATUS", param.getStatus() == null ? 1 : 0);
		session.addParams("STATUS", param.getStatus() != null ? param.getStatus() : "''");
		
		session.addParams("NOT_SUB_STATUS", param.getSubStatus() == null ? 1 : 0);
		session.addParams("SUB_STATUS", param.getSubStatus() != null ? param.getSubStatus() : "''");
		
		
		String dataStr = DateUtil.format(new Date(), DateUtil.FORMAT_Date);
		session.addParams("NOT_CREATE_DATE_START", StringUtils.isBlank(param.getStartDate()) ? 1 : 0);
		session.addParams("CREATE_DATE_START", StringUtils.isNotBlank(param.getStartDate()) ? param.getStartDate() : dataStr);
		
		session.addParams("NOT_CREATE_DATE_END", StringUtils.isBlank(param.getEndDate()) ? 1 : 0);
		session.addParams("CREATE_DATE_END", StringUtils.isNotBlank(param.getEndDate()) ? param.getEndDate() : dataStr);
		
		
		
		Address address = param.getAddress() != null ? param.getAddress() : new Address();
		session.addParams("NOT_CITY", address.getCity() == null ? 1 : 0);
		session.addParams("CITY", address.getCity() != null ? address.getCity() : "''");
		
		session.addParams("NOT_TOWN", address.getTown() == null ? 1 : 0);
		session.addParams("TOWN", address.getTown() != null ? address.getTown() : "''");
		
		session.addParams("NOT_ADDR", StringUtils.isBlank(address.getAddr()) ? 1 : 0);
		session.addParams("ADDR", address.getAddr() != null ? address.getAddr().trim() : "");

	}

	@Override
	public Integer updateCurTicketFile(CurTicketFile curTicketFile) throws Exception {
		Session session = super.getSession()
				.setSqlName("com-ycci-core-dao-impl-TicketDetailDaoImpl-updateCurTicketFile")
				.setObjParam(curTicketFile);
		return session.updateByObj();
	}
}

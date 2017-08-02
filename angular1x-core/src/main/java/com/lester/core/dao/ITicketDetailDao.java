package com.lester.core.dao;

import java.util.List;

import com.lester.core.cur.model.CurTicketAddr;
import com.lester.core.cur.model.CurTicketFile;
import com.lester.core.cur.model.CurTicketMain;
import com.lester.core.cur.model.CurTicketStatus;
import com.lester.core.model.FileInfoQueryParam;
import com.lester.core.model.FileInfoRes;
import com.lester.core.model.TicketHisStatusRes;
import com.lester.core.model.TicketQueryParam;
import com.lester.core.model.TicketQueryRes;

public interface ITicketDetailDao {

	public CurTicketMain queryCurTicketMain(Long id) throws Exception;

	public List<CurTicketAddr> queryCurTicketAddr(Long ticketId) throws Exception;

	public List<TicketHisStatusRes> queryTicketHisStatus(Long ticketId, Integer pageIdx, Integer pageSize) throws Exception;

	public Long queryTicketHisStatusCount(Long ticketId) throws Exception;
	
	public List<TicketQueryRes> queryTicket(Integer pageIdx, Integer pageSize, TicketQueryParam param) throws Exception;

	public Long queryTicketCount(TicketQueryParam param) throws Exception;

	public List<FileInfoRes> queryFileInfo(FileInfoQueryParam param, Integer pageIdx, Integer pageSize) throws Exception;

	public Long queryFileInfoCount(FileInfoQueryParam param) throws Exception;
	
	public CurTicketFile queryCurTicketFile(Long id) throws Exception;
	
	

	public CurTicketMain insertCurTicketMain(CurTicketMain curTicketMain) throws Exception;

	public List<CurTicketAddr> insertCurTicketAddr(List<CurTicketAddr> curTicketAddr) throws Exception;
	
	public CurTicketStatus insertCurTicketStatus(CurTicketStatus curTicketStatus) throws Exception;

	public CurTicketFile insertCurTicketFile(CurTicketFile curTicketFile) throws Exception;

	

	public Integer updateCurStatusId(Long ticketId, Long curStatusId, Long updateUser) throws Exception;

	public Integer updateCurTicketAddrStatusToClose(Long ticketId, Long updateUser) throws Exception;
	
	public Integer updateCurTicketMainByModify(CurTicketMain curTicketMain) throws Exception;
	
	public Integer updateCurTicketFile(CurTicketFile curTicketFile) throws Exception;
	



}

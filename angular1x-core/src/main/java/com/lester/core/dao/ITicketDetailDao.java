package com.lester.core.dao;

import com.lester.core.model.*;

import java.util.List;

public interface ITicketDetailDao {

    CurTicketMain queryCurTicketMain(Long id) throws Exception;

    List<CurTicketAddr> queryCurTicketAddr(Long ticketId) throws Exception;

    List<TicketHisStatusRes> queryTicketHisStatus(Long ticketId, Integer pageIdx, Integer pageSize) throws Exception;

    Long queryTicketHisStatusCount(Long ticketId) throws Exception;

    List<TicketQueryRes> queryTicket(Integer pageIdx, Integer pageSize, TicketQueryParam param) throws Exception;

    Long queryTicketCount(TicketQueryParam param) throws Exception;

    List<FileInfoRes> queryFileInfo(FileInfoQueryParam param, Integer pageIdx, Integer pageSize) throws Exception;

    Long queryFileInfoCount(FileInfoQueryParam param) throws Exception;

    CurTicketFile queryCurTicketFile(Long id) throws Exception;


    CurTicketMain insertCurTicketMain(CurTicketMain curTicketMain) throws Exception;

    List<CurTicketAddr> insertCurTicketAddr(List<CurTicketAddr> curTicketAddr) throws Exception;

    CurTicketStatus insertCurTicketStatus(CurTicketStatus curTicketStatus) throws Exception;

    CurTicketFile insertCurTicketFile(CurTicketFile curTicketFile) throws Exception;


    Integer updateCurStatusId(Long ticketId, Long curStatusId, Long updateUser) throws Exception;

    Integer updateCurTicketAddrStatusToClose(Long ticketId, Long updateUser) throws Exception;

    Integer updateCurTicketMainByModify(CurTicketMain curTicketMain) throws Exception;

    Integer updateCurTicketFile(CurTicketFile curTicketFile) throws Exception;


}

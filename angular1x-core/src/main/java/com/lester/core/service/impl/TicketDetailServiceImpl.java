package com.lester.core.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lester.core.cur.model.CurTicketAddr;
import com.lester.core.cur.model.CurTicketFile;
import com.lester.core.cur.model.CurTicketMain;
import com.lester.core.cur.model.CurTicketStatus;
import com.lester.core.dao.ICfgSysConfigDao;
import com.lester.core.dao.ITicketDetailDao;
import com.lester.core.model.Address;
import com.lester.core.model.CfgAuthUser;
import com.lester.core.model.FileInfoQueryParam;
import com.lester.core.model.FileInfoRes;
import com.lester.core.model.InsertRemark;
import com.lester.core.model.Phone;
import com.lester.core.model.QueryTicketHisStatusParam;
import com.lester.core.model.ResultMsg;
import com.lester.core.model.StatusDefault;
import com.lester.core.model.TicketCreateRes;
import com.lester.core.model.TicketDetail;
import com.lester.core.model.TicketDetailDesc;
import com.lester.core.model.TicketDetailInitData;
import com.lester.core.model.TicketDetailInitStaticData;
import com.lester.core.model.TicketDetailLocation;
import com.lester.core.model.TicketDetailNotice;
import com.lester.core.model.TicketHisStatusRes;
import com.lester.core.model.UploadFileInfo;
import com.lester.core.service.ITicketCommonService;
import com.lester.core.service.ITicketDetailService;
import com.lester.support.util.ApEvn;
import com.lester.support.util.DateUtil;
import com.lester.support.util.LogUtil;
import com.lester.support.util.TableRes;

@Service
public class TicketDetailServiceImpl implements ITicketDetailService{


	@Autowired
	private ICfgSysConfigDao cfgSysConfigDao;
	@Autowired
	private ITicketDetailDao ticketDetailDao;
	@Autowired
	private ITicketCommonService ticketCommonService;
	
	
	
	
	
	@Override
	public TicketDetailInitData getTicketDetailInitData() throws Exception {
		TicketDetailInitData res = new TicketDetailInitData();
		res.setSuc(true);
		
		//取得靜態資料
		TicketDetailInitStaticData staticData = getTicketDetailInitStaticData();
		res.setStaticData(staticData);
		if(staticData == null || !staticData.isSuc()){
			String msg = staticData != null ? staticData.getMsg() : null;
			if(StringUtils.isBlank(msg)){
				msg = "取得靜態資料失敗";
			}
			res.setSuc(false);
			res.setMsg(msg);
			return res;
		}
		
		return res;
	}
	
	/**
	 * 取得靜態資料
	 * @return
	 */
	private TicketDetailInitStaticData getTicketDetailInitStaticData(){
		TicketDetailInitStaticData res = new TicketDetailInitStaticData();
		res.setSuc(true);

		//取得縣市下拉選單
		try {
			res.setCityItems(ticketCommonService.getCityItems());
		} catch (Exception e) {
			String msg = "取得縣市下拉選單錯誤 msg : " + e.getMessage();
			res.setSuc(false);
			res.setMsg(msg);
			return res;
		}
		
		//取得鄉鎮下拉選單
		try {
			res.setTownItems(ticketCommonService.getTownItems());
		} catch (Exception e) {
			String msg = "取得鄉鎮下拉選單錯誤 msg : " + e.getMessage();
			res.setSuc(false);
			res.setMsg(msg);
			return res;
		}
		
		//取得案件類型下拉選單
		try {
			res.setCaseTypeItems(ticketCommonService.getCaseTypeItems());
		} catch (Exception e) {
			String msg = "取得案件類型下拉選單錯誤 msg : " + e.getMessage();
			res.setSuc(false);
			res.setMsg(msg);
			return res;
		}
		//取得案件分類下拉選單
		try {
			res.setCaseGroupItems(ticketCommonService.getCaseGroupItems());
		} catch (Exception e) {
			String msg = "取得案件分類下拉選單錯誤 msg : " + e.getMessage();
			res.setSuc(false);
			res.setMsg(msg);
			return res;
		}
		//取得工單狀態下拉選單
		try {
			res.setStatusItems(ticketCommonService.getStatusItems());
		} catch (Exception e) {
			String msg = "取得工單狀態下拉選單錯誤 msg : " + e.getMessage();
			res.setSuc(false);
			res.setMsg(msg);
			return res;
		}
		
		//取得工單子狀態下拉選單
		try {
			res.setSubStatusItems(ticketCommonService.getSubStatusItems());
		} catch (Exception e) {
			String msg = "取得工單子狀態下拉選單錯誤 msg : " + e.getMessage();
			res.setSuc(false);
			res.setMsg(msg);
			return res;
		}
		
		//取得檔案類型下拉選單
		try {
			res.setFileTypeItems(ticketCommonService.getFileTypeItems());
		} catch (Exception e) {
			String msg = "取得檔案類型下拉選單錯誤 msg : " + e.getMessage();
			res.setSuc(false);
			res.setMsg(msg);
			return res;
		}
		
		//取得年度下拉選單
		try {
			res.setYearItems(ticketCommonService.getYearItems());
		} catch (Exception e) {
			String msg = "取得年度下拉選單錯誤 msg : " + e.getMessage();
			res.setSuc(false);
			res.setMsg(msg);
			return res;
		}
		
		//取得期別下拉選單
		try {
			res.setSeasonItems(ticketCommonService.getSeasonItems());
		} catch (Exception e) {
			String msg = "取得期別下拉選單錯誤 msg : " + e.getMessage();
			res.setSuc(false);
			res.setMsg(msg);
			return res;
		}
		
		return res;
	}

	@Override
	public TicketCreateRes createTicket(TicketDetail ticketDetail, CfgAuthUser user) throws Exception {

		TicketCreateRes res = new TicketCreateRes();
		res.setSuc(true); //預設成功
			
		//建立CurTicketMain
		Long ticketId = null; //工單ID	
		try {
			CurTicketMain curTicketMain = parseCurTicketMainByCreate(ticketDetail, user);
			curTicketMain = ticketDetailDao.insertCurTicketMain(curTicketMain);
			if(curTicketMain != null && curTicketMain.getId() != null){
				ticketId = curTicketMain.getId();
				res.setTicketId(ticketId);
			} else {
				res.setSuc(false);
				res.setMsg("建立工單發生錯誤");
				return res;
			}
		} catch (Exception e) {
			LogUtil.info(getClass(), "[createTicket] create curTicketMain error", e);
			res.setSuc(false);
			res.setMsg("建立工單錯誤");
			return res;
		}

		//建立地址
		if(ticketId != null){
			try {
				List<CurTicketAddr> curTicketAddr = parseCurTicketAddr(ticketId, ticketDetail, user);
				if(curTicketAddr != null && curTicketAddr.size() > 0){
					curTicketAddr = ticketDetailDao.insertCurTicketAddr(curTicketAddr);
				}
			} catch (Exception e) {
				LogUtil.info(getClass(), "[createTicket] create addr error", e);
				res.setSuc(false);
				res.setMsg("建立工單地址錯誤");
				return res;
			}
		}
		
		//建立狀態
		if(ticketId != null){
			//取得預設狀態
			StatusDefault statusDefault = null;
			try {
				statusDefault = cfgSysConfigDao.queryDefaultStatus();
				if(statusDefault == null){
					res.setSuc(false);
					res.setMsg("取得預設狀態發生錯誤");
					return res;
				}
			} catch (Exception e) {
				LogUtil.info(getClass(), "[createTicket] query default status error", e);
				res.setSuc(false);
				res.setMsg("取得預設狀態錯誤");
				return res;
			}
			//寫入
			Long curStatusId = null; //目前狀態
			if(statusDefault != null){
				try {
					CurTicketStatus curTicketStatus = parseCurTicketStatusByCreate(ticketId, statusDefault, user);
					curTicketStatus = ticketDetailDao.insertCurTicketStatus(curTicketStatus);
					if(curTicketStatus != null && curTicketStatus.getId() != null){
						curStatusId = curTicketStatus.getId();
					} else {
						res.setSuc(false);
						res.setMsg("寫入預設狀態發生錯誤");
						return res;
					}
				} catch (Exception e) {
					LogUtil.info(getClass(), "[createTicket] write default status error", e);
					res.setSuc(false);
					res.setMsg("寫入預設狀態錯誤");
					return res;
				}
			}
			//回寫 curStatusId cur_ticlet_main 主表
			if(curStatusId != null){
				try {
					Integer updateCount = ticketDetailDao.updateCurStatusId(ticketId, curStatusId, user.getId());
					if(updateCount == null || updateCount.intValue() == 0){
						res.setSuc(false);
						res.setMsg("設定預設狀態發生錯誤");
						return res;
					}
				} catch (Exception e) {
					LogUtil.info(getClass(), "[createTicket] update curStatusId error", e);
					res.setSuc(false);
					res.setMsg("設定預設狀態錯誤");
					return res;
				}
			}
		}
		
		return res;
	}

	private CurTicketStatus parseCurTicketStatusByCreate(Long ticketId, StatusDefault statusDefault, CfgAuthUser user){
		CurTicketStatus res = null;
        
		if(ticketId != null && statusDefault != null){
			res = new CurTicketStatus();
			
			//工單ID
			res.setTicketId(ticketId);
			//狀態            
			res.setStatus(statusDefault.getStatusId());
			//狀態細項       
			res.setSubStatus(statusDefault.getSubstatusId());
			//備註          
			res.setRemark("系統預設狀態");
			//新增日期
			res.setCreateDate(new Date());
			//新增人員
			res.setCreateUser(user.getId());
			//更新日期
			res.setUpdateDate(new Date());
			//更新人員
			res.setUpdateUser(user.getId());
		}
		        
		return res;
	}
	
	private List<CurTicketAddr> parseCurTicketAddr(Long ticketId, TicketDetail ticketDetail, CfgAuthUser user){
		List<CurTicketAddr> res = null;
		
		if(ticketId != null 
				&& ticketDetail != null 
				&& ticketDetail.getLocation() != null 
				&& ticketDetail.getLocation().getAllAddress() != null
				&& ticketDetail.getLocation().getAllAddress().size() > 0){
			List<Address> allAddress = ticketDetail.getLocation().getAllAddress();
			for(Address address : allAddress){
				if(address != null){
					CurTicketAddr curTicketAddr = new CurTicketAddr();
					
					//工單ID
					curTicketAddr.setTicketId(ticketId);
					//縣市
					curTicketAddr.setCity(address.getCity());
					//鄉鎮區
					curTicketAddr.setTown(address.getTown());
					//路街巷號
					curTicketAddr.setAddr(address.getAddr());
					//新增日期
					curTicketAddr.setCreateDate(new Date());
					//新增人員
					curTicketAddr.setCreateUser(user.getId());
					//更新日期
					curTicketAddr.setUpdateDate(new Date());
					//更新人員
					curTicketAddr.setUpdateUser(user.getId());
					
					if(res == null){
						res = new ArrayList<CurTicketAddr>();
					}
					
					res.add(curTicketAddr);
				}
			}
		}
		
		return res;
	}
	
	private CurTicketMain parseCurTicketMain(TicketDetail ticketDetail){
		CurTicketMain res = new CurTicketMain();
				
		if(ticketDetail != null){
			//工單編號
			res.setId(ticketDetail.getTicketId());
			
			//描述
			if(ticketDetail.getDesc() != null){
				TicketDetailDesc desc = ticketDetail.getDesc();
				//工程名稱
				res.setTicketName(desc.getTicketName());
				//案件類型(EX:一般申請, 急件申請)
				res.setCaseType(desc.getCaseType());
				//案件分類(EX:道路標線, 標線暨標誌)
				res.setCaseGroup(desc.getCaseGroup());
				//年度
				res.setYear(desc.getYear());
				//期別
				res.setSeason(desc.getSeason());
				//描述說明
				res.setDescReason(desc.getReason());
			}
			
			//位置
			if(ticketDetail.getLocation() != null){
				TicketDetailLocation location = ticketDetail.getLocation();
				//經度
				res.setLongitude(location.getLongitude());
				//緯度
				res.setLatitude(location.getLatitude());
				//位置說明
				res.setLocationReason(location.getReason());
			}
			
			//通報
			if(ticketDetail.getNotice() != null){
				TicketDetailNotice notice = ticketDetail.getNotice();
				//通報單位
				res.setNoticeDep(notice.getDep());
				//通報人員
				res.setNoticePerson(notice.getPerson());
				if(notice.getPhone() != null){
					//通報人員_區碼
					res.setNoticeAreacode(notice.getPhone().getAreaCode());
					//通報人員_電話
					res.setNoticeTel(notice.getPhone().getTel());
					//通報人員_分機
					res.setNoticeExten(notice.getPhone().getExten());
				}
				//通報人員_手機
				res.setNoticeMobile(notice.getMobile());
				//承辦業務
				res.setContractor(notice.getContractor());
				//通報人員_說明
				res.setNoticeReason(notice.getReason());
			}
		}
//		//目前狀態
//		res.setCurStatusId(curStatusId);
		
		return res;
	}
	
	private CurTicketMain parseCurTicketMainByCreate(TicketDetail ticketDetail, CfgAuthUser user){
		CurTicketMain res = parseCurTicketMain(ticketDetail);
		if(res != null){
			//新增日期
			res.setCreateDate(new Date());
			//新增人員
			res.setCreateUser(user.getId());
			//更新日期
			res.setUpdateDate(new Date());
			//更新人員
			res.setUpdateUser(user.getId());
		}
		return res;
	}
	
	private CurTicketMain parseCurTicketMainByModify(TicketDetail ticketDetail, CfgAuthUser user){
		CurTicketMain res = parseCurTicketMain(ticketDetail);
		if(res != null){
//			//新增日期
//			res.setCreateDate(new Date());
//			//新增人員
//			res.setCreateUser(1L);
//			//更新日期 sql 直接寫 now
//			res.setUpdateDate(new Date());
			//更新人員
			res.setUpdateUser(user.getId());
		}
		return res;
	}


	@Override
	public ResultMsg modifyTicket(TicketDetail ticketDetail, CfgAuthUser user) throws Exception {

		ResultMsg res = new ResultMsg();
		res.setSuc(true); //預設成功

		Long ticketId = ticketDetail != null ? ticketDetail.getTicketId() : null; //工單ID
		if(ticketId == null){
			res.setSuc(false);
			res.setMsg("無工單編號");
			return res;
		}
			
		//建立CurTicketMain	
		try {
			CurTicketMain curTicketMain = parseCurTicketMainByModify(ticketDetail, user);
			Integer updateCount = ticketDetailDao.updateCurTicketMainByModify(curTicketMain);
			if(updateCount == null || updateCount.intValue() == 0){
				res.setSuc(false);
				res.setMsg("修改時發生錯誤");
				return res;
			}
		} catch (Exception e) {
			LogUtil.info(getClass(), "[modifyTicket] update curTicketMain error", e);
			res.setSuc(false);
			res.setMsg("修改時錯誤");
			return res;
		}
		
		//將修改前的地址狀態給關閉
		try {
			ticketDetailDao.updateCurTicketAddrStatusToClose(ticketId, user.getId());
		} catch (Exception e) {
			LogUtil.info(getClass(), "[modifyTicket] status close before addr error", e);
			res.setSuc(false);
			res.setMsg("修改地址時錯誤");
			return res;
		}

		//建立地址
		if(ticketId != null){
			try {
				List<CurTicketAddr> curTicketAddr = parseCurTicketAddr(ticketId, ticketDetail, user);
				if(curTicketAddr != null && curTicketAddr.size() > 0){
					curTicketAddr = ticketDetailDao.insertCurTicketAddr(curTicketAddr);
				}
			} catch (Exception e) {
				LogUtil.info(getClass(), "[modifyTicket] create addr error", e);
				res.setSuc(false);
				res.setMsg("建立工單地址錯誤");
				return res;
			}
		}
		return res;
	}
	
	@Override
	public TicketDetail getTicketData(Long ticketId) throws Exception {
		TicketDetail res = new TicketDetail();
		
		if(ticketId == null){
			res.setSuc(false);
			res.setMsg("工單 ID 為空");
			return res;
		}
		
		//取得主表資料
		CurTicketMain curTicketMain = null;
		try {
			curTicketMain = ticketDetailDao.queryCurTicketMain(ticketId);
			if(curTicketMain == null){
				res.setSuc(false);
				res.setMsg("查無此筆工單");
				return res;
			}
		} catch (Exception e) {
			LogUtil.info(getClass(), "[getTicketData] query curTicketMain error", e);
			res.setSuc(false);
			res.setMsg("查詢工單錯誤");
			return res;
		}
		
		//取的地址資料
		List<CurTicketAddr> curTicketAddrs = null;
		try {
			curTicketAddrs = ticketDetailDao.queryCurTicketAddr(ticketId);
		} catch (Exception e) {
			LogUtil.info(getClass(), "[getTicketData] query curTicketAddr error", e);
			res.setSuc(false);
			res.setMsg("查詢工單地址錯誤");
			return res;
		}
		
		res = parseTicketDetail(curTicketMain, curTicketAddrs);
		res.setSuc(true);
		
		return res;
	}

	private TicketDetail parseTicketDetail(CurTicketMain curTicketMain, List<CurTicketAddr> curTicketAddrs){
		TicketDetail res = new TicketDetail();
		
		TicketDetailDesc desc = new TicketDetailDesc(); //描述
		TicketDetailLocation location = new TicketDetailLocation(); //位置
		TicketDetailNotice notice = new TicketDetailNotice(); //通報
		
		if(curTicketMain != null){

			//工程名稱
			desc.setTicketName(curTicketMain.getTicketName());
			//案件類型(EX:一般申請, 急件申請)
			desc.setCaseType(curTicketMain.getCaseType());
			//案件分類(EX:道路標線, 標線暨標誌)
			desc.setCaseGroup(curTicketMain.getCaseGroup());
			//年度
			desc.setYear(curTicketMain.getYear());
			//期別
			desc.setSeason(curTicketMain.getSeason());
			//描述說明
			desc.setReason(curTicketMain.getDescReason());

			//經度
			location.setLongitude(curTicketMain.getLongitude());
			//緯度
			location.setLatitude(curTicketMain.getLatitude());
			//位置說明
			location.setReason(curTicketMain.getLocationReason());

			
			//通報單位
			notice.setDep(curTicketMain.getNoticeDep());
			//通報人員
			notice.setPerson(curTicketMain.getNoticePerson());
			Phone phone = new Phone();
			//通報人員_區碼
			phone.setAreaCode(curTicketMain.getNoticeAreacode());
			//通報人員_電話
			phone.setTel(curTicketMain.getNoticeTel());
			//通報人員_分機
			phone.setExten(curTicketMain.getNoticeExten());
			notice.setPhone(phone);
			

			//通報人員_手機
			notice.setMobile(curTicketMain.getNoticeMobile());
			//承辦業務
			notice.setContractor(curTicketMain.getContractor());
			//通報人員_說明
			notice.setReason(curTicketMain.getNoticeReason());
		}
		
		if(curTicketAddrs != null && curTicketAddrs.size() > 0){
			List<Address> allAddress = null;
			for(CurTicketAddr curTicketAddr : curTicketAddrs){
				Address address = new Address();

				//縣市
				address.setCity(curTicketAddr.getCity());
				//鄉鎮區
				address.setTown(curTicketAddr.getTown());
				//路街巷號
				address.setAddr(curTicketAddr.getAddr());
				
				if(allAddress == null){
					allAddress = new ArrayList<Address>();
				}
				allAddress.add(address);
			}
			location.setAllAddress(allAddress);
		}
		
		res.setDesc(desc);
		res.setLocation(location);
		res.setNotice(notice);
		
		
		return res;
	}

	private CurTicketStatus parseCurTicketStatusByInsertRemark(InsertRemark remark, CfgAuthUser user){
		CurTicketStatus res = null;
        
		if(remark != null){
			res = new CurTicketStatus();
			
			//工單ID
			res.setTicketId(remark.getTicketId());
			//狀態            
			res.setStatus(remark.getStatus());
			//狀態細項       
			res.setSubStatus(remark.getSubStatus());
			//備註          
			res.setRemark(remark.getRemark());
			//新增日期
			res.setCreateDate(new Date());
			//新增人員
			res.setCreateUser(user.getId());
			//更新日期
			res.setUpdateDate(new Date());
			//更新人員
			res.setUpdateUser(user.getId());
		}
		        
		return res;
	}

	@Override
	public ResultMsg insertRemark(InsertRemark remark, CfgAuthUser user) throws Exception {
		ResultMsg res = new ResultMsg();
		res.setSuc(true);


		Long ticketId = remark != null ? remark.getTicketId() : null; //工單ID
		if(ticketId == null){
			res.setSuc(false);
			res.setMsg("無工單編號");
			return res;
		}
		
		//建立狀態
		if(ticketId != null){
			//寫入
			Long curStatusId = null; //目前狀態
			if(remark != null){
				try {
					CurTicketStatus curTicketStatus = parseCurTicketStatusByInsertRemark(remark, user);
					curTicketStatus = ticketDetailDao.insertCurTicketStatus(curTicketStatus);
					if(curTicketStatus != null && curTicketStatus.getId() != null){
						curStatusId = curTicketStatus.getId();
					} else {
						res.setSuc(false);
						res.setMsg("寫入預設狀態發生錯誤");
						return res;
					}
				} catch (Exception e) {
					LogUtil.info(getClass(), "[insertRemark] insert curTicketStatus error", e);
					res.setSuc(false);
					res.setMsg("寫入預設狀態錯誤");
					return res;
				}
			}
			//回寫 curStatusId cur_ticlet_main 主表
			if(curStatusId != null){
				try {
					Integer updateCount = ticketDetailDao.updateCurStatusId(ticketId, curStatusId, user.getId());
					if(updateCount == null || updateCount.intValue() == 0){
						res.setSuc(false);
						res.setMsg("設定預設狀態發生錯誤");
						return res;
					}
				} catch (Exception e) {
					LogUtil.info(getClass(), "[insertRemark] update curStatusId error", e);
					res.setSuc(false);
					res.setMsg("設定預設狀態錯誤");
					return res;
				}
			}
		}
		
		return res;
	}

	@Override
	public TableRes<TicketHisStatusRes> queryTicketHisStatus(QueryTicketHisStatusParam param) throws Exception {
		TableRes<TicketHisStatusRes> res = new TableRes<TicketHisStatusRes>();
		res.setSuc(true);
		
		Long ticketId = param != null ? param.getTicketId() : null;
		Integer pageIdx = param != null ? param.getPageIdx() : null;
		Integer pageSize = param != null ? param.getPageSize() : null;
		
		//驗證
		if(ticketId == null){
			res.setSuc(false);
			res.setMsg("缺少必要參數 ticketId");
			return res;
		} else if(pageIdx == null){
			res.setSuc(false);
			res.setMsg("缺少必要參數 pageIdx");
			return res;
		} else if(pageSize == null){
			res.setSuc(false);
			res.setMsg("缺少必要參數 pageSize");
			return res;
		}

		try {
			List<TicketHisStatusRes> resultList = ticketDetailDao.queryTicketHisStatus(ticketId, pageIdx, pageSize);
			res.setResult(resultList);
		} catch (Exception e) {
			LogUtil.info(getClass(), "[queryTicketHisStatus] query ticketHis status error", e);
			res.setSuc(false);
			res.setMsg("查詢分頁資料時發生錯誤");
			return res;
		}

		try {
			Long totalCount = ticketDetailDao.queryTicketHisStatusCount(ticketId);
			res.setTotalCount(totalCount);
		} catch (Exception e) {
			LogUtil.info(getClass(), "[queryTicketHisStatus] query ticketHis status count error", e);
			res.setSuc(false);
			res.setMsg("查詢分頁總數資料時發生錯誤");
			return res;
		}
		
		return res;
	}

	@Override
	public TableRes<FileInfoRes> queryFileInfo(FileInfoQueryParam param) throws Exception {
		TableRes<FileInfoRes> res = new TableRes<FileInfoRes>();
		res.setSuc(true);
		
		
		Integer pageIdx = null;
		Integer pageSize = null;
		Long ticketId = param != null ? param.getTicketId() : null;
		
		if(param != null && param.getPagination() != null){
			pageIdx = param.getPagination().getPageIdx();
			pageSize = param.getPagination().getPageSize();
		}
		
		//驗證
		if(ticketId == null){
			res.setSuc(false);
			res.setMsg("缺少必要參數 ticketId");
			return res;
		} else if(pageIdx == null){
			res.setSuc(false);
			res.setMsg("缺少必要參數 pageIdx");
			return res;
		} else if(pageSize == null){
			res.setSuc(false);
			res.setMsg("缺少必要參數 pageSize");
			return res;
		}

		try {
			List<FileInfoRes> resultList = ticketDetailDao.queryFileInfo(param, pageIdx, pageSize);
			res.setResult(resultList);
		} catch (Exception e) {
			LogUtil.info(getClass(), "[queryFileInfo] query fileInfo error", e);
			res.setSuc(false);
			res.setMsg("查詢分頁資料時發生錯誤");
			return res;
		}

		try {
			Long totalCount = ticketDetailDao.queryFileInfoCount(param);
			res.setTotalCount(totalCount);
		} catch (Exception e) {
			LogUtil.info(getClass(), "[queryFileInfo] query fileInfo count error", e);
			res.setSuc(false);
			res.setMsg("查詢分頁總數資料時發生錯誤");
			return res;
		}
		
		return res;
	}

	@Override
	public ResultMsg uploadfile(UploadFileInfo uploadFileInfo, CfgAuthUser user) throws Exception {
		ResultMsg res = new ResultMsg();
		res.setSuc(true);
		
		//資料驗證
		res = UploadFileInfo.validate(uploadFileInfo);
		if(!res.isSuc()){
			return res;
		}
		
		//取得工單資訊
		CurTicketMain curTicketMain = ticketDetailDao.queryCurTicketMain(uploadFileInfo.getTicketId());
		if(curTicketMain == null){
			res.setSuc(false);
			res.setMsg("查無工單");
			return res;
		}
		
		//取得檔案路徑
		String path = getFileStorePath(curTicketMain);
		if(StringUtils.isBlank(path)){
			res.setSuc(false);
			res.setMsg("計算檔案路徑失敗");
			return res;
		} else {
			path += "\\";
		}
		
		//儲存檔案
		CommonsMultipartFile file = uploadFileInfo.getFile();
		String fileName = file.getOriginalFilename(); //檔案名稱
		String filePathProto = ApEvn.get("filePathProto");
		
		File subPath = new File(path);
		File filePath = new File(filePathProto + subPath.getPath());
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		File newFile = new File(filePath.getPath() + "\\" + fileName);
		LogUtil.info(getClass(), "檔案上傳：" + newFile.getPath());
		file.transferTo(newFile);
		
		//檔案新增一筆記錄
		try {
			CurTicketFile curTicketFile = new CurTicketFile();
			curTicketFile.setTicketId(uploadFileInfo.getTicketId()); //工單ID
			curTicketFile.setPath(path.replaceAll("\\\\", "\\\\\\\\")); //檔案路徑
			curTicketFile.setFileName(fileName);
			curTicketFile.setFileType(uploadFileInfo.getFileType()); //檔案類型
			curTicketFile.setRemark(uploadFileInfo.getRemark()); //備註
			curTicketFile.setCreateDate(new Date()); //新增日期
			curTicketFile.setCreateUser(user.getId()); //新增人員
			curTicketFile.setUpdateDate(new Date()); //更新日期
			curTicketFile.setUpdateUser(user.getId());//更新人員
			curTicketFile = ticketDetailDao.insertCurTicketFile(curTicketFile);
		} catch (Exception e) {
			LogUtil.info(getClass(), "[uploadfile] insert curTicketFile error", e);
			res.setSuc(false);
			res.setMsg("存入資料庫失敗");
			return res;
		}
		
		return res;
	}
	
	private String getFileStorePath(CurTicketMain curTicketMain){
		String res = null;
		
		
		Date createDate = curTicketMain != null ? curTicketMain.getCreateDate() : null;
		Long ticketId = curTicketMain != null ? curTicketMain.getId() : null;
		
		if(createDate != null 
				&& ticketId != null){

		    Calendar cal = Calendar.getInstance();
		    cal.setTime(createDate);
		    int year = cal.get(Calendar.YEAR);
		    int month = cal.get(Calendar.MONTH)+1;

			//檔案路徑格式:\開單年分\\開單月份\\工單ID\\儲存時間\\檔案名稱
		    res = "";
		    res += "\\" + "YEAR_" + String.valueOf(year);
		    res += "\\" + "MONTH_" + String.valueOf(month);
		    res += "\\" + "TICKE_ID_" + String.valueOf(ticketId);
		    res += "\\" + "STORE_TIME_" + String.valueOf(DateUtil.format(new Date(), DateUtil.FORMAT_DATETIME_2));
		}
		
		return res;
	}
	
	@Override
	public CurTicketFile queryCurTicketFile(Long fileId) throws Exception {
		CurTicketFile curTicketFile = ticketDetailDao.queryCurTicketFile(fileId);
		return curTicketFile;
	}
}

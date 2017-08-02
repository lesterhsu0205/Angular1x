package com.lester.core.service.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lester.core.dao.IUserDao;
import com.lester.core.model.CfgAuthUser;
import com.lester.core.model.ModifyPasswordParam;
import com.lester.core.model.ResultMsg;
import com.lester.core.service.IUserService;
import com.lester.support.util.ApEvn;
import com.lester.support.util.CommonUtil;
import com.lester.support.util.LogUtil;
import com.lester.support.util.Pagination;
import com.lester.support.viewModel.Option;
import com.lester.support.viewModel.UserBoardEditReq;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;
	
	@Override
	public List<CfgAuthUser> queryUsers(Pagination pagination) throws Exception{
		return userDao.queryUsers(pagination);
	}

	@Override
	public String queryUsersCount() throws Exception {
		return userDao.queryUsersCount();
	}

	@Override
	public List<Option> queryDepOpts() throws Exception {
		return userDao.queryDepOpts();
	}

	@Override
	public List<Option> queryRoleOpts() throws Exception {
		return userDao.queryRoleOpts();
	}

	@Override
	public ResultMsg inserUser(UserBoardEditReq req, CfgAuthUser loginUser) throws Exception {
		ResultMsg res = new ResultMsg();
		
		CfgAuthUser user = req != null ? req.getUser() : null;
		
		if(loginUser == null){
			res.setSuc(false);
			res.setMsg("請重新登入");
			return res;
		} else if(user == null || StringUtils.isBlank(user.getUserName())){
			res.setSuc(false);
			res.setMsg("請輸入帳號");
			return res;
		} else if(user == null || StringUtils.isBlank(user.getPassword())){
			res.setSuc(false);
			res.setMsg("請輸入密碼");
			return res;
		} else if(user == null || StringUtils.isBlank(user.getName())){
			res.setSuc(false);
			res.setMsg("請輸入姓名");
			return res;
		} else if(user == null || user.getDepId() == null){
			res.setSuc(false);
			res.setMsg("請輸入部門");
			return res;
		} else if(user == null || user.getRoleId() == null){
			res.setSuc(false);
			res.setMsg("請輸入角色");
			return res;
		} else if(user == null || user.getStatus() == null){
			res.setSuc(false);
			res.setMsg("請輸入狀態");
			return res;
		} 
		
		//驗證是否有相同帳號
		CfgAuthUser queryUser = userDao.loadUserByUsername(user.getUserName(), true);
		if(queryUser != null){
			res.setSuc(false);
			res.setMsg("帳號已被使用");
			return res;
		}
				
		//進行新增
		// 加密
		user.setPassword(DigestUtils.sha512Hex(user.getPassword()));
		
		//新增
		user.setCreateUser(loginUser.getId());
		user.setUpdateUser(loginUser.getId());
		Integer insertCount = userDao.inserUser(user);
		if(insertCount > 0){
			res.setSuc(true);
		}
		
		return res;
	}

	@Override
	public ResultMsg updateUser(UserBoardEditReq req, CfgAuthUser loginUser) throws Exception {
		ResultMsg res = new ResultMsg();
		
		CfgAuthUser user = req != null ? req.getUser() : null;
		
		if(loginUser == null){
			res.setSuc(false);
			res.setMsg("請重新登入");
			return res;
		} else if(user == null || user.getId() == null){
			res.setSuc(false);
			res.setMsg("請重新登入後進行修改");
			return res;
		} else if(user == null || StringUtils.isBlank(user.getUserName())){
			res.setSuc(false);
			res.setMsg("請輸入帳號");
			return res;
		} else if(user == null || StringUtils.isBlank(user.getName())){
			res.setSuc(false);
			res.setMsg("請輸入姓名");
			return res;
		} else if(user == null || user.getDepId() == null){
			res.setSuc(false);
			res.setMsg("請輸入部門");
			return res;
		} else if(user == null || user.getRoleId() == null){
			res.setSuc(false);
			res.setMsg("請輸入角色");
			return res;
		} else if(user == null || user.getStatus() == null){
			res.setSuc(false);
			res.setMsg("請輸入狀態");
			return res;
		} 
		
		//驗證是否有相同帳號
		CfgAuthUser queryUser = userDao.loadUserByUsername(user.getUserName(), true);
		if(queryUser == null){
			res.setSuc(false);
			res.setMsg("請重新登入後再進行修改");
			return res;
		} else {
			if(!user.getId().equals(queryUser.getId())){
				res.setSuc(false);
				res.setMsg("請重新登入");
				return res;
			}
		} 
		
		user.setUpdateUser(loginUser.getId());
		Integer updateCount = userDao.updateUser(user);
		if(updateCount > 0){
			res.setSuc(true);
		}
		
		return res;
	}

	@Override
	public CfgAuthUser loadUserByUsername(String userName) throws Exception {
		return userDao.loadUserByUsername(userName, false);
	}

	@Override
	public boolean isHasUser(String userName, String oldPassword) throws Exception {
		return userDao.isHasUser(userName, oldPassword);
	}

	@Override
	public ResultMsg modifyPassword(CfgAuthUser user, ModifyPasswordParam param) throws Exception {
		ResultMsg res = new ResultMsg();
		
		try {
			LogUtil.info(getClass(), "modifyUserData=" + CommonUtil.toJson(user));
			
			String userName = user != null ? user.getUserName() : null;
			String oldPassword = param != null ? param.getOldPassword() : null;
			
			//驗證是否登入
			if(user == null){
				res.setSuc(false);
				res.setMsg("請重新登入後, 在進行修改密碼");
				return res;
			}
			
			//驗證舊密碼
			boolean isHasUser = this.isHasUser(userName, oldPassword);
			if(!isHasUser){
				res.setSuc(false);
				res.setMsg("舊密碼不正確");
				return res;
			}

			String newPassword = param != null ? param.getNewPassword() : null; //新密碼
			String newPassword2 = param != null ? param.getNewPassword2() : null; //再次輸入新密碼
			
			ResultMsg validateNewPasswordres = validateNewPassword(newPassword, newPassword2);
			if(!validateNewPasswordres.isSuc()){
				return validateNewPasswordres;
			}
			
			if(validateNewPasswordres.isSuc()){
				// 密碼加密
				user.setPassword(DigestUtils.sha512Hex(newPassword));
				userDao.updatePassword(user);
				res.setSuc(true);
			}
			
		} catch (Exception e) {
			LogUtil.debug(getClass(), "modifyPassword", e);
			res.setSuc(false);
			res.setMsg("修改失敗，請詢問工程人員！");
			return res;
		}
		
		return res;
	}
	
	private ResultMsg validateNewPassword(String newPassword, String newPassword2){
		ResultMsg res = new ResultMsg();
		res.setSuc(true);

		if(StringUtils.isBlank(newPassword)){
			res.setSuc(false);
			res.setMsg("請輸入新密碼");
			return res;
		}
		
		if(StringUtils.isBlank(newPassword2)){
			res.setSuc(false);
			res.setMsg("請再次輸入新密碼");
			return res;
		}
		
		if(!newPassword.equals(newPassword2)){
			res.setSuc(false);
			res.setMsg("請確認2次輸入的新密碼是否正確");
			return res;
		}
		
		return res;
	}

	@Override
	public ResultMsg revertPassword(CfgAuthUser user, CfgAuthUser loginUser) throws Exception {
		ResultMsg res = new ResultMsg();
		
		//驗證是否登入
		if(loginUser == null){
			res.setSuc(false);
			res.setMsg("請重新登入後, 在進行修改密碼");
			return res;
		}
		
		//驗證是否登入
		if(user == null || user.getId() == null){
			res.setSuc(false);
			res.setMsg("請重新操作");
			return res;
		}
		// 密碼加密
		user.setPassword(DigestUtils.sha512Hex(ApEvn.get("defaultPassword")));
		user.setUpdateUser(loginUser.getId());
		userDao.updatePassword(user);
		res.setSuc(true);
		
		return res;
	}

}

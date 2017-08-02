package com.ycci.core.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycci.core.facade.IUserFacade;
import com.ycci.core.model.CfgAuthUser;
import com.ycci.core.model.ModifyPasswordParam;
import com.ycci.core.model.ResultMsg;
import com.ycci.core.service.IUserService;
import com.ycci.support.util.Pagination;
import com.ycci.support.viewModel.Option;
import com.ycci.support.viewModel.UserBoardEditReq;

@Component
public class UserFacadeImpl implements IUserFacade{

	@Autowired
	private IUserService userService;
	
	@Override
	public List<CfgAuthUser> queryUsers(Pagination pagination) throws Exception{
		return userService.queryUsers(pagination);
	}

	@Override
	public String queryUsersCount() throws Exception {
		return userService.queryUsersCount();
	}

	@Override
	public List<Option> queryDepOpts() throws Exception {
		return userService.queryDepOpts();
	}

	@Override
	public List<Option> queryRoleOpts() throws Exception {
		return userService.queryRoleOpts();
	}

	@Override
	public ResultMsg inserUser(UserBoardEditReq req, CfgAuthUser loginUser) throws Exception {
		return userService.inserUser(req, loginUser);
	}

	@Override
	public ResultMsg updateUser(UserBoardEditReq req, CfgAuthUser loginUser) throws Exception {
		return userService.updateUser(req, loginUser);
	}

	@Override
	public boolean isHasUser(String userName, String oldPassword) throws Exception {
		return userService.isHasUser(userName, oldPassword);
	}

	@Override
	public ResultMsg modifyPassword(CfgAuthUser user, ModifyPasswordParam param) throws Exception {
		return userService.modifyPassword(user, param);
	}

	@Override
	public ResultMsg revertPassword(CfgAuthUser user, CfgAuthUser loginUser) throws Exception {
		return userService.revertPassword(user, loginUser);
	}

}

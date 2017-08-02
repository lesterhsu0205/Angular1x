package com.ycci.core.service;

import java.util.List;

import com.ycci.core.model.CfgAuthUser;
import com.ycci.core.model.ModifyPasswordParam;
import com.ycci.core.model.ResultMsg;
import com.ycci.support.util.Pagination;
import com.ycci.support.viewModel.Option;
import com.ycci.support.viewModel.UserBoardEditReq;

public interface IUserService {

	public List<CfgAuthUser> queryUsers (Pagination pagination) throws Exception;
	
	public String queryUsersCount() throws Exception;
	
	public List<Option> queryDepOpts() throws Exception;
	
	public List<Option> queryRoleOpts() throws Exception;
	
	public ResultMsg inserUser(UserBoardEditReq req, CfgAuthUser loginUser) throws Exception;
	
	public ResultMsg updateUser(UserBoardEditReq req, CfgAuthUser loginUser) throws Exception;
	
	public CfgAuthUser loadUserByUsername(String userName) throws Exception;
	
	public boolean isHasUser(String userName, String oldPassword) throws Exception;
	
	public ResultMsg modifyPassword(CfgAuthUser user, ModifyPasswordParam param)  throws Exception;
	
	public ResultMsg revertPassword(CfgAuthUser user, CfgAuthUser loginUser)  throws Exception;
}

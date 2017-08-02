package com.lester.core.facade;

import java.util.List;

import com.lester.core.model.CfgAuthUser;
import com.lester.core.model.ModifyPasswordParam;
import com.lester.core.model.ResultMsg;
import com.lester.support.util.Pagination;
import com.lester.support.viewModel.Option;
import com.lester.support.viewModel.UserBoardEditReq;

public interface IUserFacade {
	
	public List<CfgAuthUser> queryUsers (Pagination pagination) throws Exception;
	
	public String queryUsersCount() throws Exception;
	
	public List<Option> queryDepOpts() throws Exception;
	
	public List<Option> queryRoleOpts() throws Exception;
	
	public ResultMsg inserUser(UserBoardEditReq req, CfgAuthUser loginUser) throws Exception;
	
	public ResultMsg updateUser(UserBoardEditReq req, CfgAuthUser loginUser) throws Exception;
	
	public boolean isHasUser(String userName, String oldPassword) throws Exception;
	
	public ResultMsg modifyPassword(CfgAuthUser user, ModifyPasswordParam param)  throws Exception;
	
	public ResultMsg revertPassword(CfgAuthUser user, CfgAuthUser loginUser)  throws Exception;
}

package com.lester.core.dao;

import java.util.List;

import com.lester.core.model.CfgAuthUser;
import com.lester.support.util.Pagination;
import com.lester.support.viewModel.Option;

public interface IUserDao {

	public List<CfgAuthUser> queryUsers(Pagination pagination) throws Exception;
	
	public Integer inserUser(CfgAuthUser user) throws Exception;
	
	public Integer deleteUserById(String userId) throws Exception;
	
	public Integer updateUser(CfgAuthUser user) throws Exception;
	
	public String queryUsersCount() throws Exception;
	
	public List<Option> queryDepOpts() throws Exception;
	
	public List<Option> queryRoleOpts() throws Exception;
	
	public CfgAuthUser loadUserByUsername(String userName, boolean ignoreSts) throws Exception;
	
	public boolean isHasUser(String userName, String oldPassword) throws Exception;
	
	public Integer updatePassword(CfgAuthUser user) throws Exception;
}

package com.ycci.support.viewModel;

import java.io.Serializable;
import java.util.List;

import com.ycci.core.model.CfgAuthUser;

public class UserBoardInitResp implements Serializable{
	private static final long serialVersionUID = -9165054940710386069L;
	
	private List<CfgAuthUser> cfgAuthUsers;
	private String cfgAuthUserCount;
	private List<Option> depOpts;
	private List<Option> roleOpts;
	
	public UserBoardInitResp() {}
	
	public UserBoardInitResp(List<CfgAuthUser> cfgAuthUsers, String cfgAuthUserCount, List<Option> depOpts, List<Option> roleOpts) {
		this.cfgAuthUserCount = cfgAuthUserCount;
		this.cfgAuthUsers = cfgAuthUsers;
		this.depOpts = depOpts;
		this.roleOpts = roleOpts;
	}
	
	public List<CfgAuthUser> getCfgAuthUsers() {
		return cfgAuthUsers;
	}
	public void setCfgAuthUsers(List<CfgAuthUser> cfgAuthUsers) {
		this.cfgAuthUsers = cfgAuthUsers;
	}
	public String getCfgAuthUserCount() {
		return cfgAuthUserCount;
	}
	public void setCfgAuthUserCount(String cfgAuthUserCount) {
		this.cfgAuthUserCount = cfgAuthUserCount;
	}

	public List<Option> getDepOpts() {
		return depOpts;
	}

	public void setDepOpts(List<Option> depOpts) {
		this.depOpts = depOpts;
	}

	public List<Option> getRoleOpts() {
		return roleOpts;
	}

	public void setRoleOpts(List<Option> roleOpts) {
		this.roleOpts = roleOpts;
	}
	
}

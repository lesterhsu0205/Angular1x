package com.lester.support.viewModel;

import java.io.Serializable;

import com.lester.core.model.CfgAuthUser;

public class UserBoardEditReq implements Serializable{
	
	private static final long serialVersionUID = 8664860443911923136L;
	private Boolean isCreate;
	private CfgAuthUser user;
	private Boolean revertPassword;
	
	
	public Boolean getIsCreate() {
		return isCreate;
	}
	public void setIsCreate(Boolean isCreate) {
		this.isCreate = isCreate;
	}
	public CfgAuthUser getUser() {
		return user;
	}
	public void setUser(CfgAuthUser user) {
		this.user = user;
	}
	public Boolean getRevertPassword() {
		return revertPassword;
	}
	public void setRevertPassword(Boolean revertPassword) {
		this.revertPassword = revertPassword;
	}
	
}

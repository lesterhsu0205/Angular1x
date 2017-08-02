package com.ycci.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ycci.support.util.DateUtil;

/**
 * 修改密碼
 * @author et06060606
 *
 */
public class ModifyPasswordParam implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String oldPassword; //舊密碼
	private String newPassword; //新密碼
	private String newPassword2; //再次輸入新密碼
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPassword2() {
		return newPassword2;
	}
	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}
}

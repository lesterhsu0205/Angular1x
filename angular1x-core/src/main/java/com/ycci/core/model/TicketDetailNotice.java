package com.ycci.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ycci.support.util.DateUtil;

public class TicketDetailNotice implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//通報單位
	private String dep;
	//通報人員
	private String person;
	//市話
	private Phone phone;
	//手機
	private String mobile;	
	//承辦業務
	private String contractor;	
	//說明
	private String reason;
	
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContractor() {
		return contractor;
	}
	public void setContractor(String contractor) {
		this.contractor = contractor;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
}

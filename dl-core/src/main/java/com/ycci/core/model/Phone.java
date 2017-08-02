package com.ycci.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ycci.support.util.DateUtil;

public class Phone implements Serializable{

	//市話(區碼)
	private String areaCode;
	//市話(電話)
	private String tel;
	//市話(分機)
	private String exten;
	
	
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getExten() {
		return exten;
	}
	public void setExten(String exten) {
		this.exten = exten;
	}
	
	
	
}

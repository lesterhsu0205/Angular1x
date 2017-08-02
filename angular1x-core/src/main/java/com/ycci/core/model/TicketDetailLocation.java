package com.ycci.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ycci.support.util.DateUtil;

public class TicketDetailLocation implements Serializable{

	//地址
	private List<Address> allAddress;
	//經度
	private String longitude;
	//緯度
	private String latitude;
	//位置說明
	private String reason;
	
	
	public List<Address> getAllAddress() {
		return allAddress;
	}
	public void setAllAddress(List<Address> allAddress) {
		this.allAddress = allAddress;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}

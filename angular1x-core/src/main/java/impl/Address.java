package com.lester.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.lester.support.util.DateUtil;

public class Address implements Serializable{

	//縣市
	private Long city;
	//鄉鎮
	private Long town;
	//地址
	private String addr;
	
	public Long getCity() {
		return city;
	}
	public void setCity(Long city) {
		this.city = city;
	}
	public Long getTown() {
		return town;
	}
	public void setTown(Long town) {
		this.town = town;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}

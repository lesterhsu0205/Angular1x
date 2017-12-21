package com.lester.core.model;

import java.io.Serializable;

public class CfgAuthRole implements Serializable {
	private static final long serialVersionUID = 7215678251579498051L;

	
	private String id;
	private String name;
	private String decs;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDecs() {
		return decs;
	}
	public void setDecs(String decs) {
		this.decs = decs;
	}
	
	
	
}

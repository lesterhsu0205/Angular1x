package com.ycci.core.model;

import java.io.Serializable;
import java.util.Date;

public class CfgSystemConfig implements Serializable{
	
	
	private static final long serialVersionUID = -2965537662179531097L;
	
	private Long id;
	private String codeCate;
	private String cateName;
	private Long parentId;
	private Long depth;
	private Long seq;
	private String code;
	private String codeName;
	private String codeValue;
	private Boolean statusFlag;
	private String codeDesc;
	private Date createDate;
	private Long createUser;
	private Date updateDate;
	private Long updateUser;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodeCate() {
		return codeCate;
	}
	public void setCodeCate(String codeCate) {
		this.codeCate = codeCate;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getDepth() {
		return depth;
	}
	public void setDepth(Long depth) {
		this.depth = depth;
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public Boolean getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(Boolean statusFlag) {
		this.statusFlag = statusFlag;
	}
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
}

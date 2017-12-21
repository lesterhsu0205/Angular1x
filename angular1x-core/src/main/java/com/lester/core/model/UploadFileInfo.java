package com.lester.core.model;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadFileInfo implements Serializable{
	
	private CommonsMultipartFile file;
	private Long ticketId;
	private Long fileType;
	private String remark;
	
	public static ResultMsg validate(UploadFileInfo uploadFileInfo){
		ResultMsg res = new ResultMsg();
		res.setSuc(true);
		
		if(uploadFileInfo == null){
			res.setSuc(false);
			res.setMsg("上傳檔案錯誤");
			return res;
		} else if (uploadFileInfo.getTicketId() == null){
			res.setSuc(false);
			res.setMsg("無工單編號");
			return res;
		} else if (uploadFileInfo.getFile() == null){
			res.setSuc(false);
			res.setMsg("請選擇檔案");
			return res;
		}
		
		
		return res;
	}
	
	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Long getFileType() {
		return fileType;
	}

	public void setFileType(Long fileType) {
		this.fileType = fileType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}

package com.ycci.support.util;

import java.util.List;

import com.ycci.core.model.ResultMsg;

public class TableRes<T> extends ResultMsg{
	
	//此分頁的資訊
	private List<T> result;
	//總筆數
	private Long totalCount;
	
	
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
}

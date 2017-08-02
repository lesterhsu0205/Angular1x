package com.ycci.support.util;

/**
 * <p>Title: 分頁元件</p>
 * <p>Description: <br>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 */
public class Pagination {
	private Integer pageSize;//每頁幾筆
	private Integer pageNumber;//第幾頁
	private Integer pageIdx;
	private Integer totalCount;//共有幾筆
	private Integer sortColumn;//排序欄位
	
	public Pagination() {
		
	}
	
	public Pagination(Integer pageNumber, Integer pageSize){
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.pageIdx = (pageNumber-1)*pageSize;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageIdx() {
		return pageIdx;
	}

	public void setPageIdx(Integer pageIdx) {
		this.pageIdx = pageIdx;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(Integer sortColumn) {
		this.sortColumn = sortColumn;
	}


}

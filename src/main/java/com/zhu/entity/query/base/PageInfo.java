package com.zhu.entity.query.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象类
 */
public class PageInfo<E> {
	
	//当前页常量名称
	public static final String CURRENT_PAGE = "currentPage";
	
	public static final String PAGE_SIZE= "pageSize";

	//总行数
	private int totalRows = 0;
	
	//总页数
	private int totalPages = 0;
	
	//默认每页条数
	private int pageSize = 10;
	
	//默认当前页
	private int currentPage = 1;
	
	//是否有上一页
	private boolean hasPrevious = false;
	
	//是否有下一页
	private boolean hasNext = false;
	
	//排序列名称
	@JsonIgnore
	private String sortName;
	
	//排序标识 asc desc
	@JsonIgnore
	private String sortOrder;
	
	//结果集合
	private List<E> items = new ArrayList<E>();

	public PageInfo() {
		super();
	}


	public PageInfo(int currentPage, int pageSize, int totalRows) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalRows = totalRows;
		totalPages = totalRows / pageSize;
		int mod = totalRows % pageSize;
		if (mod > 0) {
			totalPages++;
		}
		refresh();
	}

	public void reset() {
		this.currentPage = 1;
		refresh();
	}

	public void init(int totalRows) {
		this.totalRows = totalRows;
		totalPages = ((totalRows + pageSize) - 1) / pageSize;
		refresh();
	}

	public void init(int totalRows, int pageSize) {
		this.totalRows = totalRows;
		this.pageSize = pageSize;
		totalPages = ((totalRows + pageSize) - 1) / pageSize;
		refresh();
	}

	public int getCurrentPage() {

		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;

		refresh();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		refresh();
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
		refresh();
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		refresh();
	}

	public void first() {
		currentPage = 1;
		this.setHasPrevious(false);
		refresh();
	}

	public void previous() {
		currentPage--;
		refresh();
	}

	public void next() {
		if (currentPage < totalPages) {
			currentPage++;
		}
		refresh();
	}

	public void last() {
		currentPage = totalPages;
		this.setHasNext(false);
		refresh();
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public void refresh() {
		if (totalPages <= 1) {
			hasPrevious = false;
			hasNext = false;
		} else if (currentPage == 1) {
			hasPrevious = false;
			hasNext = true;
		} else if (currentPage == totalPages) {
			hasPrevious = true;
			hasNext = false;
		} else {
			hasPrevious = true;
			hasNext = true;
		}

		if (currentPage <= 0) {
			currentPage = 1;
		}
	}

	public List<E> getItems() {
		return items;
	}

	public void setItems(List<E> items) {
		this.items = items;
	}


	public String getSortName() {
		return sortName;
	}


	public void setSortName(String sortName) {
		this.sortName = sortName;
	}


	public String getSortOrder() {
		return sortOrder;
	}


	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

}
package com.spider.db.repository.support;

import java.util.List;

public class DataPagination<T> {
	private List<T> rows;
	private int total;
	private List<T> footer;
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getFooter() {
		return footer;
	}
	public void setFooter(List<T> footer) {
		this.footer = footer;
	}
	
	
	
}

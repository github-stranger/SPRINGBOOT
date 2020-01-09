package com.cy.pj.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询数据并对数据进行封装
 * @author Administrator
 *
 * @param <T>
 */
/**封装分页信息以及当前数据*/
public class PageObject<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**当前页的页码值*/
	private Integer pageCurrent=1;
    /**页面大小*/
    private Integer pageSize=3;
    /**总行数(通过查询获得)*/
    private Integer rowCount=0;
    /**总页数(通过计算获得)*/
    private Integer pageCount=0;
    /**当前页记录*/
    private List<T> records;
    
	public Integer getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
	   this.pageCount = pageCount;
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}


}

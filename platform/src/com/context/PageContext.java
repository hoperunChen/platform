package com.context;

import java.util.List;

import com.orm.entity.OrmEntity;
/**
 * 页面上下文环境
 * @author cr
 *
 */
public class PageContext {
	
	public PageContext(){}
	
	
	private List<OrmEntity> pageDatas;
	private Object entity;
	private int recordsCount;
	private String pageUrl;
	private String contextPath;
	
	


	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public int getRecordsCount() {
		return recordsCount;
	}

	public void setRecordsCount(int recordsCount) {
		this.recordsCount = recordsCount;
	}

	public List<OrmEntity> getAll(){
		return getPageDatas();
	}

	public List<OrmEntity> getPageDatas() {
		return pageDatas;
	}

	public void setPageDatas(List<OrmEntity> pageDatas) {
		this.pageDatas = pageDatas;
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}
	
	
}

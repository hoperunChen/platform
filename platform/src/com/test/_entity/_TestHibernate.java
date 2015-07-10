package com.test._entity;

import java.sql.Timestamp;

import com.orm.entity.OrmEntity;

public class _TestHibernate extends OrmEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public _TestHibernate(){}
	
	protected int sid;
	protected String textValue;
	protected Integer intValue;
	protected Timestamp dateValue;
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getTextValue() {
		return textValue;
	}
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}
	public Integer getIntValue() {
		return intValue;
	}
	public void setIntValue(Integer intValue) {
		this.intValue = intValue;
	}
	public Timestamp getDateValue() {
		return dateValue;
	}
	public void setDateValue(Timestamp dateValue) {
		this.dateValue = dateValue;
	}
}

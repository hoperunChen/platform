package com.pdmall.entities;

public class PdmColumn {
	private String sid;
	private String name;
	private String code;
	private String dataType;
	private String length;
	private boolean notNull;//true:1 false:0
	private boolean isPrimaryKey;
	//ref
	private boolean isParent; //����falseʱʵ�� ���Ƕ�������,����trueʱ��set����
	private String refName;
	private PdmTable refTable;
	private PdmColumn refColumn;
	
	
	
	public PdmColumn getRefColumn() {
		return refColumn;
	}
	public void setRefColumn(PdmColumn refColumn) {
		this.refColumn = refColumn;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public boolean isNotNull() {
		return notNull;
	}
	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}
	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}
	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public PdmTable getRefTable() {
		return refTable;
	}
	public void setRefTable(PdmTable refTable) {
		this.refTable = refTable;
	}
	public String getRefName() {
		return refName;
	}
	public void setRefName(String refName) {
		this.refName = refName;
	}
	
}

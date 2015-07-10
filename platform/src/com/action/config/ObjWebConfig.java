package com.action.config;

/**
 * 已弃用,本类原来的功能是存储webAction中的一些信息;
 * 现在这些信息已经融入到WebAction中,所以本类不再使用
 * @author cr
 *
 */
@Deprecated
public class ObjWebConfig {
	
	public String className;
	public String metaName;
	public String bizDefinition;
	
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMetaName() {
		return metaName;
	}
	public void setMetaName(String metaName) {
		this.metaName = metaName;
	}
	public String getBizDefinition() {
		return bizDefinition;
	}
	public void setBizDefinition(String bizDefinition) {
		this.bizDefinition = bizDefinition;
	}
	
	
	
	
}

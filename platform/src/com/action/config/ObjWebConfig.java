package com.action.config;

/**
 * ������,����ԭ���Ĺ����Ǵ洢webAction�е�һЩ��Ϣ;
 * ������Щ��Ϣ�Ѿ����뵽WebAction��,���Ա��಻��ʹ��
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

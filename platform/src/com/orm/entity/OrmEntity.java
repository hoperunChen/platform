package com.orm.entity;

import java.io.Serializable;

import user.UserInfo;

import com.util.ReflectUtilEx;
import com.util.StringUtilEx;
/**
 * 数据库实体
 * @author cr
 *
 */
public abstract class OrmEntity implements Serializable{
	private static final long serialVersionUID = -2062416897855944597L;

	public void set(String propName,Object value){
		ReflectUtilEx.invoke(this, "set"+StringUtilEx.convertToPascalFromCamel(propName), new Object[]{value});
	}
	
	public Object get(String propName){
		return ReflectUtilEx.invoke(this, "get"+StringUtilEx.convertToPascalFromCamel(propName), null);
		
	}
	
	public String getDbName(){
		String objName = this.getClass().getName();
		objName = objName.substring(objName.lastIndexOf(".")+1);
		return StringUtilEx.convertToDbNameFromCamel(objName);
	}
	
	public String getObjName(){
		String objName = this.getClass().getName();
		return objName.substring(objName.lastIndexOf(".")+1);
	}
	
	public static void main(String[] args) {
		UserInfo u = new UserInfo();
//		u.set("userName","aaa");
//		System.out.println(u.get("userName"));
		u.getDbName();
	}
}

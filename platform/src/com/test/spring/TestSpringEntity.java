package com.test.spring;

import java.util.List;

public class TestSpringEntity {
	private int intValue;
	private String stringValue;
	private List<Object> listValue;
	
	
	public int getIntValue() {
		return intValue;
	}
	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public List<Object> getListValue() {
		return listValue;
	}
	public void setListValue(List<Object> listValue) {
		this.listValue = listValue;
	}
	
	public void helloSpring(){
		System.out.println("======helloSpring");
	}
	
	
}

package com.biz;

import java.util.Map;

import com.biz.biz.Biz;
import com.biz.statemachine.StateMachine;



public class BizFlow {
	/**
	 * bizs�����Ӧ����һ��������
	 */
	private Map<String, Biz> bizs;
	private StateMachine sm;
	
	public Map<String, Biz> getBizs() {
		return bizs;
	}
	public void setBizs(Map<String, Biz> bizs) {
		this.bizs = bizs;
	}
	public StateMachine getSm() {
		return sm;
	}
	public void setSm(StateMachine sm) {
		this.sm = sm;
	}
	
	
	
	
	
}

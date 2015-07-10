package com.pdmall.test;


import com.pdmall.generate.ActionGenerater;
import com.pdmall.generate.BeansGenerater;
import com.pdmall.generate.BizGenerater;
import com.pdmall.generate.Generater;
import com.pdmall.generate.HbmGenerater;
import com.pdmall.generate.JavaGenerater;

public class PdmAll {
	public static void main(String[] args) {
		PdmAll t = new PdmAll();
		t.generateJava();
		t.generateHbm();
		t.generateAction();
		t.generateBeans();
		t.generateBiz();
	}
	
	public void generateBiz(){
		Generater generater = new BizGenerater();
		generater.generate();
	}

	public void generateJava() {
		Generater generater = new JavaGenerater();
		generater.generate();
	}

	public void generateHbm() {
		Generater generater = new HbmGenerater();
		generater.generate();
	}
	
	public void generateAction(){
		Generater generater = new ActionGenerater();
		generater.generate();
	}
	public void generateBeans(){
		Generater generater = new BeansGenerater();
		generater.generate();
	}
}

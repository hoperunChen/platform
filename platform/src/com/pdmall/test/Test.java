package com.pdmall.test;


import junit.framework.TestCase;
import com.pdmall.config.PdmConfig;
import com.pdmall.generate.multiFileGenerater;
import com.pdmall.generate.JavaGenerater;


public class Test extends TestCase{

	public void testConfig() {
		assertNotNull(PdmConfig.get("pdm.path"));
	}
	
	public void testPdmParser(){
		multiFileGenerater javaGenerater= new JavaGenerater();
		javaGenerater.generate();
	}
	
		
}

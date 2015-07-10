package com.test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import junit.framework.TestCase;

public class TestSpring extends TestCase{
	public void testSpring(){
		ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
		TestSpringEntity springEntity = context.getBean("testSpringEntity",TestSpringEntity.class);
		System.out.println(springEntity.getIntValue());
		System.out.println(springEntity.getStringValue());
		for (Object o : springEntity.getListValue()) {
			System.out.println(o.toString());
		}
		springEntity.helloSpring();
	}
}

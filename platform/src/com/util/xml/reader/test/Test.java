package com.util.xml.reader.test;

import java.util.List;

import junit.framework.TestCase;

import com.util.FileUtilEx;
import com.util.xml.reader.AbstractXmlReader;
import com.util.xml.reader.XmlReaderUtil;


public class Test extends TestCase{
	
	private XmlReaderUtil reader;
	

	@Override
	protected void setUp() throws Exception {
		reader = new XmlReaderUtil(FileUtilEx.getContextPathOnWeb()+"/defaultroot/_config/web/user/UserInfo.biz.xml");
		super.setUp();
	}

	/**
	 * ok
	 */
	public void testCurrent() {
		reader.getRoot();
		reader.findOne("bizs");
		String s = reader.getNodeName();
		reader.getParent();
		reader.findOne("biz");
		String s1 = reader.getNodeName();
		assertEquals(s, "bizs");
		assertEquals(s1, "biz");
	}
	
	/**
	 * OK
	 */
	public void testFindInDocument() {
		reader.getRoot();
		List<AbstractXmlReader> list = reader.findInDocument("biz");
		assertNotNull(list);
		for (AbstractXmlReader o : list) {
			assertEquals("biz", o.getNodeName());
		}
	}
	
	
	/**
	 * OK
	 */
	public void testFindOne(){
		reader.getRoot();
		reader.findOne("biz");
		assertEquals("biz", reader.getNodeName());
		reader.findOne("abc");
		System.out.println(reader.getNodeName());
		assertEquals("biz", reader.getNodeName());
	}
	
	
	/**
	 * ok
	 */
	public void testFind(){
		reader.getRoot();
		List<AbstractXmlReader> list = reader.find("biz");
		for (AbstractXmlReader o : list) {
			assertEquals(o.getNodeName(), "biz");
		}
		reader.getRoot();
		list = reader.find("abc");
		assertNull(list);
	}
	
	
	/**
	 * ok
	 */
	public void testChild(){
		reader.getRoot();
		reader.getChild("bizs");
		assertEquals("bizs", reader.getNodeName());
		reader.getRoot();
		AbstractXmlReader biz = reader.getChild("biz");
		assertNull(biz);
	}
	
	public void testChildren(){
		reader.getRoot();
		List<AbstractXmlReader> rtn = reader.getChildren();
		assertNotNull(rtn);
		for (AbstractXmlReader o : rtn) {
			System.out.println(o.getNodeName());
		}
		reader.getRoot();
		rtn = reader.getChildren("bizs");
		assertNotNull(rtn);
		reader.getRoot();
		rtn = reader.getChildren("biz");
		assertNull(rtn);
	}
	
	

}

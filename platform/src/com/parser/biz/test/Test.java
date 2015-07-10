package com.parser.biz.test;

import junit.framework.TestCase;

import com.parser.biz.BizParser;
import com.parser.biz.parser.BizReader;
import com.parser.biz.parser.container.ReaderGetter;

public class Test extends TestCase{

	@org.junit.Test
	public void testParseContainer() {
		ReaderGetter pc = ReaderGetter.getInstance();
		BizReader p = pc.getParser("sss","defaultroot/_config/web/user/UserInfo.biz.xml");
		assertEquals(p, pc.get("sss"));
	}
	
	public void testParse(){
		BizParser parser = new BizParser();
		parser.parse("defaultroot/_config/web/user/UserInfo.biz.xml");
	}
	
	

}

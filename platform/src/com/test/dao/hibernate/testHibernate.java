package com.test.dao.hibernate;

import junit.framework.TestCase;

public class testHibernate extends TestCase{
	@SuppressWarnings("deprecation")
	public void testHibernatedao(){
		hibernateDao dao = new hibernateDao();
		assertNotNull(dao.testInsert());
	}
}

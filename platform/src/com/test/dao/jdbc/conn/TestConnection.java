package com.test.dao.jdbc.conn;

import junit.framework.Assert;
import junit.framework.TestCase;


public class TestConnection extends TestCase{

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	public void testConnectionMySql() {
		Assert.assertNotNull(DbConnection.connMySql());
	}
	
	public void testConnectionMySql1() {
		Assert.assertNotNull(DbConnection.connMySql());
	}

}

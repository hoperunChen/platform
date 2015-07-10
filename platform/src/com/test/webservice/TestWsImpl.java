package com.test.webservice;

import javax.jws.WebService;
@WebService
public class TestWsImpl implements TestWs{

	@Override
	public String testWebService(String userName) {
		String rtn = "hello" + userName;
		System.out.println("[service]:" + rtn);
		return rtn;
	}

}

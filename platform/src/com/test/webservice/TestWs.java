package com.test.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;


@WebService
public interface TestWs {
	@WebMethod
	@WebResult(name="hello")
	public String testWebService(@WebParam(name="userName",partName="userName")String userName);
}

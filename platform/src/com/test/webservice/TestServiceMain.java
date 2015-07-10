package com.test.webservice;

import javax.xml.ws.Endpoint;

import com.log.Log;


public class TestServiceMain {
	public static void main(String[] args) {
		String address ="http://127.0.0.1:8888/testws/testws";
		Log.printLog("begin start WebService-----[serviceName:"+TestWsImpl.class+"]\n---[wsdl_url:"+address+"?wsdl]");
		Endpoint.publish(address,new TestWsImpl());
		Log.printLog("end start WebService-----[serviceName:"+TestWsImpl.class+"]");
	}
}

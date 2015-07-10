package com.test.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.test.TestHibernate;
import com.test.hibernatedao.TestHibernateTemplate;
/**
 * 测试servlet
 * @author cr
 *
 */
public class TestServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1844666801488153380L;
	private ServletContext application;
	private WebApplicationContext wac;
	public void init(){
		application = getServletContext();
		wac = WebApplicationContextUtils.getWebApplicationContext(application); 
	}
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		TestHibernateTemplate t = (TestHibernateTemplate) wac.getBean("testHibernateTemplate");
		TestHibernate testEntity = new TestHibernate();
		testEntity.setTextValue("test");
		testEntity.setIntValue(4);
		testEntity.setDateValue(new Timestamp(System.currentTimeMillis()));
		t.savePojo(testEntity);
		super.service(arg0, arg1);
	}
	
}

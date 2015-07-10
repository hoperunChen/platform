package com.test.controller;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.orm.dao.OrmDao;
import com.test.TestHibernate;

@Controller
@RequestMapping("/test/test.do")
public class TestController {
	private OrmDao ormDao;
	
	
	
	public OrmDao getOrmDao() {
		return ormDao;
	}
	@Resource(name="ormDao")
	public void setOrmDao(OrmDao ormDao) {
		this.ormDao = ormDao;
	}



	@RequestMapping
	public String testLogin(HttpServletRequest req){
		TestHibernate testEntity = new TestHibernate();
		testEntity.setTextValue("test");
		testEntity.setIntValue(4);
		testEntity.setDateValue(new Timestamp(System.currentTimeMillis()));
		ormDao.add(testEntity);
		return "/test/loginSuccess";
	}
}

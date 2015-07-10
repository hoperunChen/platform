package com.test.hibernatedao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.test.TestHibernate;

public class TestHibernateTemplate extends HibernateDaoSupport{
	public void savePojo(TestHibernate test){
		HibernateTemplate h = getHibernateTemplate();
		System.out.println(h.save(test));
	}
}

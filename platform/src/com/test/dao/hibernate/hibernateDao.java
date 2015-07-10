package com.test.dao.hibernate;

import java.sql.Timestamp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.test.TestHibernate;
/**
 * �ò����Ѿ���������.��Ϊ�÷�����Ҫ��hibernate.cfg.xml�Ѿ���������
 */
@Deprecated
public class hibernateDao {
	
	public TestHibernate testInsert(){
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		TestHibernate testEntity = new TestHibernate();
		testEntity.setTextValue("test");
		testEntity.setIntValue(1);
		testEntity.setDateValue(new Timestamp(System.currentTimeMillis()));
		Transaction t = session.beginTransaction();
		System.out.println(session.save(testEntity));
		t.commit();
		session.close();
		sessionfactory.close();
		return testEntity;
	}
	
	public static void main(String[] args) {
		new hibernateDao().testInsert();
	}
}

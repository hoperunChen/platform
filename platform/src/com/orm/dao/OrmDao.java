package com.orm.dao;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import user.UserInfo;

import com.log.Log;
import com.orm.entity.OrmEntity;

public class OrmDao extends HibernateDaoSupport implements AbstraceDao {

	@Override
	public void add(OrmEntity ormEntity) {
		getHibernateTemplate().save(ormEntity);
	}

	/**
	 * ɾ�����ݿ�ĳʵ��
	 * @param ormEntity Ҫɾ����ʵ�����(sid ����Ϊ��,����ᱨ�쳣)
	 * @throws NullPointerException ��sid==null ʱ�׳�
	 */
	@Override
	public void remove(OrmEntity ormEntity) {
		Object sidO = ormEntity.get("sid");
		if(sidO==null || sidO.toString().isEmpty())
			//TODO ����Ӧ���׳�һ��CheckedException �Ա��ϲ��������֪����������,���׳���ui��,�����������������׳�һ��UncheckedException
			throw new NullPointerException("sid is null");
		OrmEntity entity = load(ormEntity, ormEntity.get("sid").toString());
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(OrmEntity ormEntity) {
		getHibernateTemplate().update(ormEntity);
	}

	@Override
	public List<OrmEntity> findAll(OrmEntity ormEntity) {
		String hql = String.format("select o from %s o", ormEntity.getClass()
				.getName());
		getHibernateTemplate().find(hql);
		return null;
	}

	@Override
	public OrmEntity get(OrmEntity ormEntity, String sid) {
		if (ormEntity == null)
			throw new NullPointerException("entityName is null");
		Integer id = Integer.parseInt(sid);
		OrmEntity rtn = getHibernateTemplate().get(ormEntity.getClass(), id);
		return rtn;
	}

	@Override
	public OrmEntity load(OrmEntity ormEntity, String sid) {
		if (ormEntity == null)
			throw new NullPointerException("entityName is null");
		Integer id = Integer.parseInt(sid);
		OrmEntity rtn = getHibernateTemplate().load(ormEntity.getClass(), id);
		return rtn;
	}

	@Override
	@SuppressWarnings("unchecked")
	public int getRecordsCount(OrmEntity ormEntity) {
		if (ormEntity == null)
			throw new NullPointerException("entityName is null");
		String dbName = ormEntity.getDbName();
		List<BigInteger> execResult = findBySql("select count(*) from "
				+ dbName);
		if (execResult.size() > 0 && !execResult.isEmpty())
			return execResult.get(0).intValue();
		else
			return 0;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List findByHql(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrmEntity> findByHql(String hql, String[] params,
			Object[] values) {
		Log.printSql(hql, params, values);
		return getHibernateTemplate().findByNamedParam(hql, params, values);
	}

	@SuppressWarnings("rawtypes")
	public List findBySql(String sql) {
		final String sqlf = sql;
		List execResult = getHibernateTemplate().executeFind(
				new HibernateCallback<List>() {
					@Override
					public List doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sqlf);
						List result = query.list();
						return result;
					}
				});
		if (execResult != null)
			return execResult;
		else
			return null;
	}

	public static void main(String[] args) {
		UserInfo u = new UserInfo();
		System.out.println(u.getClass().getName());
	}
	
	/**
	 * ��ҳ��ѯ
	 * @param ormEntity
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<OrmEntity> pagingQuery(OrmEntity ormEntity, int pageNum,
			int pageSize) {
		final int offset = (pageNum - 1) * pageSize;
		final int pageSizef = pageSize;
		final String hql = String.format("select o from %s o",ormEntity.getClass().getName());
		List<OrmEntity> rtn = getHibernateTemplate().executeFind(
				new HibernateCallback<List>() {
					@Override
					public List doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setFirstResult(offset);
						query.setMaxResults(pageSizef);
						return query.list();
					}

				});
		if (rtn != null)
			return rtn;
		else
			return null;
	}

}

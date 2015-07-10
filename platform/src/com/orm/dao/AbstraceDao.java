package com.orm.dao;

import java.util.List;


import com.orm.entity.OrmEntity;

public interface AbstraceDao {

	public void add(OrmEntity ormEntity);

	public void remove(OrmEntity ormEntity);

	public void update(OrmEntity ormEntity);

	public List<OrmEntity> findAll(OrmEntity ormEntity);
	
	public OrmEntity get(OrmEntity ormEntity, String id);

	public OrmEntity load(OrmEntity ormEntity, String id);

	public int getRecordsCount(OrmEntity ormEntity);

	public List<OrmEntity> findByHql(String hql);
	
	public List<OrmEntity> findByHql(String hql,String[] params, Object[] values);
}

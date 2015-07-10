package com.key;

import java.security.KeyException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.orm.dao.OrmDao;
import com.orm.entity.OrmEntity;

/**
 * 类<code>Key</code>是一个数据库主键生成器，用序列号的方式来产生数据库中需要的主键值。
 */
public class KeyGenerater {
	
	public KeyGenerater() {
	}
	
	private OrmDao ormDao;
	
	

	public OrmDao getOrmDao() {
		return ormDao;
	}

	public void setOrmDao(OrmDao ormDao) {
		this.ormDao = ormDao;
	}

	public HashMap<String, KeyInfo> getKeyList() {
		return keyList;
	}

	public void setKeyList(HashMap<String, KeyInfo> keyList) {
		this.keyList = keyList;
	}

	/**
	 * KeyInfo类的实例列表，默认容量为5个
	 * */
	private HashMap<String, KeyInfo> keyList = new HashMap<String, KeyInfo>(5); // keyInfo 列表

	/**
	 * 用指定的表和字段获得key的下一个值，主键的值不得超过2147483647
	 * 
	 * @param tableName
	 *            数据库中的表名，表中必须有一个数字主键
	 * @param keyName
	 *            表(tableName)中的字段名
	 * @param conn
	 *            JDBC连接，如果欲获得的key是第一次取值，则必须保证conn能连接到数据库
	 * @return key的下一个主键的int值
	 * @throws <code>KeyException</code>
	 *         如果表名或字段名不存在、访问数据库错误或key的值大于2147483647时抛出
	 */
	public int getNextKey(String tableName, String keyName, Connection conn)
			throws KeyException {
		long value = getNextKeyLong(tableName, keyName, conn);
		if (value > 2147483647L) {
			throw new KeyException(
					"Key's value too big,please call getNextKeyLong method!");
		}
		return (new Long(value)).intValue();
	}

	/**
	 * 用指定的表和字段获得key的下一个值，最大为9223372036854775807
	 * 
	 * @param tableName
	 *            数据库中的表名，表中必须有一个数字主键
	 * @param keyName
	 *            表(tableName)中的字段名
	 * @return key的下一个主键的long值
	 */
	public long getNextKeyLong(String tableName, String keyName, Connection conn)
			throws KeyException {
		KeyInfo keyinfo;
		String item = tableName + "." + keyName;
		try {
			if (keyList.containsKey(item)) {
				keyinfo = (KeyInfo) keyList.get(item);
			} else {
				keyinfo = new KeyInfo(tableName, keyName);
				keyList.put(item, keyinfo);
			}
			return keyinfo.getNextKey();
		} catch (SQLException sqle) {
			throw new KeyException(sqle);
		}
	}

	/**
	 * 用指定的"表<code>.</code>字段"形式的字符串获得key的下一个值，主键的值不得超过2147483647
	 * 
	 * @param tableDotField
	 *            "表.字段"形式的字符串，如：message.id
	 * @return key的下一个主键的int值
	 * @throws <code>KeyException</code> 如果表名或字段名不存在、访问数据库错误或key的值
	 *         大于2147483647时抛出
	 */
	public int getNextKey(String tableDotField, Connection conn)
			throws KeyException {
		long value = getNextKeyLong(tableDotField, conn);
		if (value > 2147483647L) {
			throw new KeyException(
					"Key's value too big,please call getNextKeyLong method!");
		}
		return (new Long(value)).intValue();
	}

	/**
	 * 用指定的"表<code>.</code>字段"形式的字符串获得key的下一个值，最大为9223372036854775807
	 * 
	 * @param tableDotField
	 *            "表.字段"形式的字符串，如：message.id
	 * @param conn
	 *            JDBC连接，如果欲获得的key是第一次取值，则必须保证conn能连接到数据库
	 * @return key的下一个主键的int值
	 * @throws <code>KeyException</code> 如果表名或字段名不存在或访问数据库错误时抛出
	 */
	public long getNextKeyLong(String tableDotField, Connection conn)
			throws KeyException {
		int dot_index = tableDotField.indexOf(".");
		if (tableDotField.indexOf(".") < 1) {
			throw new KeyException("Unknown Key '" + tableDotField + "'!");
		}
		String tab = tableDotField.substring(0, dot_index);
		String key = tableDotField.substring(dot_index);
		return getNextKeyLong(tab, key, conn);
	}
	
	/**
	 * 用指定的"表<code>.</code>字段"形式的字符串获得key的下一个值，主键的值不得超过2147483647
	 * 
	 * @param entity
	 * @return key的下一个主键的int值
	 * @throws <code>KeyException</code> 如果表名或字段名不存在、访问数据库错误或key的值
	 *         大于2147483647时抛出
	 */
	public int getNextKey(OrmEntity entity)
			throws KeyException {
		long value = getNextKeyLong(entity);
		if (value > 2147483647L) {
			throw new KeyException(
					"Key's value too big,please call getNextKeyLong method!");
		}
		return (new Long(value)).intValue();
	}

	/**
	 * 用指定的实体获得key的下一个值，最大为9223372036854775807
	 * 
	 * @param entity 实体对象 
	 * @return key的下一个主键的int值
	 * @throws <code>KeyException</code> 如果表名或字段名不存在或访问数据库错误时抛出
	 */
	public long getNextKeyLong(OrmEntity entity)
			throws KeyException {
		KeyInfo keyinfo;
		String item =  entity.getObjName()+ ".sid";
		try {
			if (keyList.containsKey(item)) {
				keyinfo = (KeyInfo) keyList.get(item);
			} else {
				keyinfo = new KeyInfo(entity);
				keyList.put(item, keyinfo);
			}
			return keyinfo.getNextKey();
		} catch (SQLException sqle) {
			throw new KeyException(sqle);
		}
	}

	/**
	 * 用指定的表和字段获得key的当前值，主键的值不得超过2147483647
	 * 
	 * @param tableName
	 *            数据库中的表名，表中必须有一个数字主键
	 * @param keyName
	 *            表(tableName)中的字段名
	 * @param conn
	 *            JDBC连接，如果欲获得的key是第一次取值，则必须保证conn能连接到数据库
	 * @return key的当前int值
	 * @throws <code>KeyException</code>
	 *         如果表名或字段名不存在、访问数据库错误或key的值大于2147483647时抛出
	 */
	public int getCurrentKey(String tableName, String keyName, Connection conn)
			throws KeyException {
		long value = getCurrentKeyLong(tableName, keyName, conn);
		if (value > 2147483647L) {
			throw new KeyException(
					"Key's value too big,please call getCurrentKeyLong method!");
		}
		return (new Long(value)).intValue();
	}

	/**
	 * 用指定的表和字段获得key的当前值，最大为9223372036854775807
	 * 
	 * @param tableName
	 *            数据库中的表名，表中必须有一个数字主键
	 * @param keyName
	 *            表(tableName)中的字段名
	 * @param conn
	 *            JDBC连接，如果欲获得的key是第一次取值，则必须保证conn能连接到数据库
	 * @return key的当前long值
	 * @throws <code>KeyException</code> 如果表名或字段名不存在或访问数据库错误时抛出
	 */
	public long getCurrentKeyLong(String tableName, String keyName,
			Connection conn) throws KeyException {
		KeyInfo keyinfo;
		String item = tableName + "." + keyName;
		try {
			synchronized (keyList) {
				if (keyList.containsKey(item)) {
					keyinfo = (KeyInfo) keyList.get(item);
				} else {
					keyinfo = new KeyInfo(tableName, keyName);
					keyList.put(item, keyinfo);
				}
			}
			return keyinfo.getCurrentKey();
		} catch (SQLException sqle) {
			throw new KeyException(sqle);
		}
	}

	/**
	 * 用指定的"表<code>.</code>字段"形式的字符串获得key的当前值，主键的值不得超过2147483647
	 * 
	 * @param tableDotField
	 *            "表.字段"形式的字符串，如：message.id
	 * @param conn
	 *            JDBC连接，如果欲获得的key是第一次取值，则必须保证conn能连接到数据库
	 * @return key的当前int值
	 * @throws <code>KeyException</code> 如果表名或字段名不存在、访问数据库错误或key的值
	 *         大于2147483647时抛出
	 */
	public int getCurrentKey(String tableDotField, Connection conn)
			throws KeyException {
		long value = getCurrentKeyLong(tableDotField, conn);
		if (value > 2147483647L) {
			throw new KeyException(
					"Key's value too big,please call getNextKeyLong method!");
		}
		return (new Long(value)).intValue();
	}

	/**
	 * 用指定的"表<code>.</code>字段"形式的字符串获得key的当前值，最大为9223372036854775807
	 * 
	 * @param tableDotField
	 *            "表.字段"形式的字符串，如：message.id
	 * @param conn
	 *            JDBC连接，如果欲获得的key是第一次取值，则必须保证conn能连接到数据库
	 * @return key的当前int值
	 * @throws <code>KeyException</code> 如果表名或字段名不存在或访问数据库错误时抛出
	 */
	public long getCurrentKeyLong(String tableDotField, Connection conn)
			throws KeyException {
		int dot_index = tableDotField.indexOf(".");
		if (tableDotField.indexOf(".") < 1) {
			throw new KeyException("Unknown Key '" + tableDotField + "'!");
		}
		String tab = tableDotField.substring(0, dot_index);
		String key = tableDotField.substring(dot_index);
		return getCurrentKeyLong(tab, key, conn);
	}

	/**
	 * 内部类,用来存储主键信息并取值.
	 * */
	class KeyInfo {
		private long max = 9223372036854775807L;
		private long min = 1L;
		private long currentKey;
		private String tableName;
		private String keyName;
		private OrmEntity ormEntity;

		/**
		 * keyInfo 对象初始化
		 * 
		 * @throws KeyException
		 */
		KeyInfo(String tableName, String keyName)
				throws SQLException, KeyException {
			this.tableName = tableName;
			this.keyName = keyName;
			getMaxKeyByEntity();
		}
		
		/**
		 * 
		 * @param entity
		 * @throws SQLException
		 * @throws KeyException
		 */
		KeyInfo(OrmEntity entity)
				throws SQLException, KeyException {
			this.ormEntity = entity;
			this.keyName = "sid";
			initKeyInfo();
		}

		int getMax() {
			return (new Long(max)).intValue();
		}

		long getMaxLong() {
			return max;
		}

		int getMin() {
			return (new Long(min)).intValue();
		}

		long getMinLong() {
			return min;
		}

		/**
		 * 取下一键值
		 */
		int getNextKey() {
			return (new Long(getNextKeyLong())).intValue();
		}

		/**
		 * 取下一键值
		 */
		synchronized long getNextKeyLong() {
			currentKey++;
			return currentKey;
		}

		/**
		 * 取当前键值
		 */
		synchronized int getCurrentKey() {
			return (new Long(currentKey)).intValue();
		}

		/**
		 * 取当前键值
		 */
		synchronized long getCurrentKeyLong() {
			return currentKey;
		}

		/**
		 * 从数据库中取当前最大值
		 * 
		 * @throws SQLException
		 *             ,KeyException
		 */
		void initKeyInfo() throws SQLException, SQLException, KeyException {
			if(ormEntity == null){
				if(keyName == null || tableName == null)
					throw new KeyException("keyName is null or tableName is null,please check it");
				else
					getMaxKeyBySql();
			}else
				getMaxKeyByEntity();
				
		}
		
		@SuppressWarnings("rawtypes")
		void getMaxKeyBySql(){
			String sql = String.format("select max(%s) from %s", keyName,tableName);
			List rs = ormDao.findBySql(sql);
			currentKey = (long) rs.get(1);
			if (currentKey < min) {
				currentKey = min;
			}
		}
		
		@SuppressWarnings("rawtypes")
		void getMaxKeyByEntity(){
			String hql = String.format("select max(sid) from %s", ormEntity.getObjName());
			List rs = ormDao.findByHql(hql);
			if(rs == null || rs.isEmpty())
				currentKey = min;
			currentKey = new Long(rs.get(0).toString());
			if (currentKey < min) {
				currentKey = min;
			}
		}
	}
}

package com.key;

import java.security.KeyException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.orm.dao.OrmDao;
import com.orm.entity.OrmEntity;

/**
 * ��<code>Key</code>��һ�����ݿ������������������кŵķ�ʽ���������ݿ�����Ҫ������ֵ��
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
	 * KeyInfo���ʵ���б�Ĭ������Ϊ5��
	 * */
	private HashMap<String, KeyInfo> keyList = new HashMap<String, KeyInfo>(5); // keyInfo �б�

	/**
	 * ��ָ���ı���ֶλ��key����һ��ֵ��������ֵ���ó���2147483647
	 * 
	 * @param tableName
	 *            ���ݿ��еı��������б�����һ����������
	 * @param keyName
	 *            ��(tableName)�е��ֶ���
	 * @param conn
	 *            JDBC���ӣ��������õ�key�ǵ�һ��ȡֵ������뱣֤conn�����ӵ����ݿ�
	 * @return key����һ��������intֵ
	 * @throws <code>KeyException</code>
	 *         ����������ֶ��������ڡ��������ݿ�����key��ֵ����2147483647ʱ�׳�
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
	 * ��ָ���ı���ֶλ��key����һ��ֵ�����Ϊ9223372036854775807
	 * 
	 * @param tableName
	 *            ���ݿ��еı��������б�����һ����������
	 * @param keyName
	 *            ��(tableName)�е��ֶ���
	 * @return key����һ��������longֵ
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
	 * ��ָ����"��<code>.</code>�ֶ�"��ʽ���ַ������key����һ��ֵ��������ֵ���ó���2147483647
	 * 
	 * @param tableDotField
	 *            "��.�ֶ�"��ʽ���ַ������磺message.id
	 * @return key����һ��������intֵ
	 * @throws <code>KeyException</code> ����������ֶ��������ڡ��������ݿ�����key��ֵ
	 *         ����2147483647ʱ�׳�
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
	 * ��ָ����"��<code>.</code>�ֶ�"��ʽ���ַ������key����һ��ֵ�����Ϊ9223372036854775807
	 * 
	 * @param tableDotField
	 *            "��.�ֶ�"��ʽ���ַ������磺message.id
	 * @param conn
	 *            JDBC���ӣ��������õ�key�ǵ�һ��ȡֵ������뱣֤conn�����ӵ����ݿ�
	 * @return key����һ��������intֵ
	 * @throws <code>KeyException</code> ����������ֶ��������ڻ�������ݿ����ʱ�׳�
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
	 * ��ָ����"��<code>.</code>�ֶ�"��ʽ���ַ������key����һ��ֵ��������ֵ���ó���2147483647
	 * 
	 * @param entity
	 * @return key����һ��������intֵ
	 * @throws <code>KeyException</code> ����������ֶ��������ڡ��������ݿ�����key��ֵ
	 *         ����2147483647ʱ�׳�
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
	 * ��ָ����ʵ����key����һ��ֵ�����Ϊ9223372036854775807
	 * 
	 * @param entity ʵ����� 
	 * @return key����һ��������intֵ
	 * @throws <code>KeyException</code> ����������ֶ��������ڻ�������ݿ����ʱ�׳�
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
	 * ��ָ���ı���ֶλ��key�ĵ�ǰֵ��������ֵ���ó���2147483647
	 * 
	 * @param tableName
	 *            ���ݿ��еı��������б�����һ����������
	 * @param keyName
	 *            ��(tableName)�е��ֶ���
	 * @param conn
	 *            JDBC���ӣ��������õ�key�ǵ�һ��ȡֵ������뱣֤conn�����ӵ����ݿ�
	 * @return key�ĵ�ǰintֵ
	 * @throws <code>KeyException</code>
	 *         ����������ֶ��������ڡ��������ݿ�����key��ֵ����2147483647ʱ�׳�
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
	 * ��ָ���ı���ֶλ��key�ĵ�ǰֵ�����Ϊ9223372036854775807
	 * 
	 * @param tableName
	 *            ���ݿ��еı��������б�����һ����������
	 * @param keyName
	 *            ��(tableName)�е��ֶ���
	 * @param conn
	 *            JDBC���ӣ��������õ�key�ǵ�һ��ȡֵ������뱣֤conn�����ӵ����ݿ�
	 * @return key�ĵ�ǰlongֵ
	 * @throws <code>KeyException</code> ����������ֶ��������ڻ�������ݿ����ʱ�׳�
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
	 * ��ָ����"��<code>.</code>�ֶ�"��ʽ���ַ������key�ĵ�ǰֵ��������ֵ���ó���2147483647
	 * 
	 * @param tableDotField
	 *            "��.�ֶ�"��ʽ���ַ������磺message.id
	 * @param conn
	 *            JDBC���ӣ��������õ�key�ǵ�һ��ȡֵ������뱣֤conn�����ӵ����ݿ�
	 * @return key�ĵ�ǰintֵ
	 * @throws <code>KeyException</code> ����������ֶ��������ڡ��������ݿ�����key��ֵ
	 *         ����2147483647ʱ�׳�
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
	 * ��ָ����"��<code>.</code>�ֶ�"��ʽ���ַ������key�ĵ�ǰֵ�����Ϊ9223372036854775807
	 * 
	 * @param tableDotField
	 *            "��.�ֶ�"��ʽ���ַ������磺message.id
	 * @param conn
	 *            JDBC���ӣ��������õ�key�ǵ�һ��ȡֵ������뱣֤conn�����ӵ����ݿ�
	 * @return key�ĵ�ǰintֵ
	 * @throws <code>KeyException</code> ����������ֶ��������ڻ�������ݿ����ʱ�׳�
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
	 * �ڲ���,�����洢������Ϣ��ȡֵ.
	 * */
	class KeyInfo {
		private long max = 9223372036854775807L;
		private long min = 1L;
		private long currentKey;
		private String tableName;
		private String keyName;
		private OrmEntity ormEntity;

		/**
		 * keyInfo �����ʼ��
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
		 * ȡ��һ��ֵ
		 */
		int getNextKey() {
			return (new Long(getNextKeyLong())).intValue();
		}

		/**
		 * ȡ��һ��ֵ
		 */
		synchronized long getNextKeyLong() {
			currentKey++;
			return currentKey;
		}

		/**
		 * ȡ��ǰ��ֵ
		 */
		synchronized int getCurrentKey() {
			return (new Long(currentKey)).intValue();
		}

		/**
		 * ȡ��ǰ��ֵ
		 */
		synchronized long getCurrentKeyLong() {
			return currentKey;
		}

		/**
		 * �����ݿ���ȡ��ǰ���ֵ
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

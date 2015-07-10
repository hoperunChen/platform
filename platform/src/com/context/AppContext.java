package com.context;

import java.util.HashMap;
import java.util.Map;

import com.log.Log;



/**
 * ϵͳ�����Ļ���.
 * ���ϵͳ����<br/>
 * ��������:��ϵͳ����ֱ���ر�
 * @author cr
 *
 */
public class AppContext{
	private static Map<String, Object> webContext = new HashMap<String,Object>();
	/**
	 * 
	 */
	
	
	public static void set(String key,Object obj){
		if(webContext==null)
			webContext = new HashMap<String,Object>();
		synchronized (obj) {
			webContext.put(key, obj);
		}
		
	}
	
	public static Object get(String key){
		if(webContext == null ){
			Log.printErr("webContext is null");
			return  null;
		}
		return webContext.get(key);
	}
	
}

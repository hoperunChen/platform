package com.context;

import java.util.HashMap;
import java.util.Map;

import com.log.Log;



/**
 * 系统上下文环境.
 * 存放系统变量<br/>
 * 声明周期:从系统启动直到关闭
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

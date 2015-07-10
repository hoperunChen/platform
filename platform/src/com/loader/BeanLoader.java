package com.loader;

import org.springframework.context.ApplicationContext;

import com.context.AppContext;
import com.log.Log;

/**
 * javabean加载类
 * 在系统启动的情况下可以取到spring中注册过的bean
 * @author cr
 *
 */
public class BeanLoader {
	public static Object get(String beanName){
		Log.printLog("loader bean : "+beanName);
		ApplicationContext wac = (ApplicationContext) AppContext.get("wac");
		return wac.getBean(beanName);
	}

}

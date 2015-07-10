package com.register;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.context.AppContext;
import com.log.Log;


/**
 *    加载Spring配置文件时，如果Spring配置文件中所定义的Bean类实现了ApplicationContextAware 接口，
 *    那么在加载Spring配置文件时，会自动调用ApplicationContextAware 接口中的
 *	  public void setApplicationContext(ApplicationContext context) throws BeansException
 *    方法，获得ApplicationContext对象。
 *    前提必须在Spring配置文件中指定该类
 * @author cr
 *
 */
public class ApplicationContextRegister implements ApplicationContextAware{

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		Log.printLog("register applicationContext");
		AppContext.set("wac", context);
	}

}

package com.register;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.context.AppContext;
import com.log.Log;


/**
 *    ����Spring�����ļ�ʱ�����Spring�����ļ����������Bean��ʵ����ApplicationContextAware �ӿڣ�
 *    ��ô�ڼ���Spring�����ļ�ʱ�����Զ�����ApplicationContextAware �ӿ��е�
 *	  public void setApplicationContext(ApplicationContext context) throws BeansException
 *    ���������ApplicationContext����
 *    ǰ�������Spring�����ļ���ָ������
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

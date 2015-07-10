package com.action;

import java.security.KeyException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.biz.BizFlow;
import com.config.Config;
import com.constants.ConfigConstants;
import com.container.BizFlows;
import com.key.KeyGenerater;
import com.loader.BeanLoader;
import com.orm.entity.OrmEntity;
import com.util.CheckUtil;
import com.util.ReflectUtilEx;

/**
 * ���������.
 * 
 * @author cr
 */
public class WebAction {

	public void init() {
		this.ormEntity = (OrmEntity) ReflectUtilEx.getObj(getClassName());
		readBizFlow();
	}

	protected final String PARAM_OBJ_NAME = "objectName";
	protected final String PARAM_OBJ_EVENT = "objectEvent";
	protected final String PARAM_OBJ_SID = "sid";

	private String className;
	private String metaName;
	private String bizFlowDefinition;
	private OrmEntity ormEntity;

	public OrmEntity getOrmEntity() {
		return ormEntity;
	}

	public void setOrmEntity(OrmEntity ormEntity) {
		this.ormEntity = ormEntity;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMetaName() {
		return metaName;
	}

	public void setMetaName(String metaName) {
		this.metaName = metaName;
	}

	public String getBizFlowDefinition() {
		return bizFlowDefinition;
	}

	public void setBizFlowDefinition(String bizFlowDefinition) {
		this.bizFlowDefinition = bizFlowDefinition;
	}
	
	
	/**
	 * ���BizFlow,���bizʹ�û�����ôֱ�ӷ��ػ����bizFlow,�������¶�ȡbiz�ļ�����BizFlow
	 * @return BizFlow
	 */
	protected BizFlow getBiz(){
		BizFlow biz = null;
		if(isBizUseCache())
			biz = BizFlows.getInstance().get(ormEntity.getObjName());
		if(biz == null)
			biz = readBizFlow();
		return biz;
	}
	
	/**
	 * �ж�Biz�Ƿ�ʹ�û���
	 * @return boolean
	 */
	boolean isBizUseCache(){
		if(Config.get(ConfigConstants.BIZ_USE_CACHE).equals("true"))
			return true;
		return false;
	}
	
	/**
	 * ͨ��Biz�ļ���·����ȡbiz,��������BizFlow
	 * @return BizFlow
	 */
	private BizFlow readBizFlow(){
		BizFlow biz = BizFlows.getInstance().readBiz(getObjectName(),this.bizFlowDefinition);
		return biz;
	}

	/**
	 * �õ���׼ҳ��ĵ�ַ
	 * 
	 * @param pageType
	 * @return
	 */
	protected String getStdPageUrl(String pageType) {
		String pageUrl = null;
		switch (pageType) {
		case PageTypeConst.PAGE_TYPE_LIST:
			pageUrl = Config.get(ConfigConstants.STD_LIST_URL);
			break;
		case PageTypeConst.PAGE_TYPE_UPDATE:
			pageUrl = Config.get(ConfigConstants.STD_UPDATE_URL);
			break;
		case PageTypeConst.PAGE_TYPE_VIEW:
			pageUrl = Config.get(ConfigConstants.STD_VIEW_URL);
			break;
		default:
			break;
		}

		if (pageUrl.indexOf(".jsp") != -1)
			;
		pageUrl = pageUrl.substring(0, pageUrl.indexOf(".jsp"));
		return pageUrl;
	}

	/**
	 * ͨ���洢������mapװ��ʵ��
	 * @param requestParams
	 *            �洢�������ƺ�ֵ������,key:������;value:
	 * @param isGenId �Ƿ�����Id
	 * @return
	 */
	protected OrmEntity loadEntity(Map<String, String[]> requestParams, boolean isGenId) {
		for (String key : requestParams.keySet()) {
			if (key.trim().equals(PARAM_OBJ_NAME)
					|| key.trim().equals(PARAM_OBJ_EVENT))
				continue;
			ormEntity.set(key, requestParams.get(key)[0]);
		}
		if (isGenId) {
			// ����id
			KeyGenerater keygen = (KeyGenerater) BeanLoader.get("keyGenerater");
			try {
				ormEntity.set(PARAM_OBJ_SID, keygen.getNextKey(ormEntity));
			} catch (KeyException e) {
				e.printStackTrace();
			}
		}
		return ormEntity;
	}
	
	protected String getObjectName(){
		if(ormEntity==null)
			return null;
		return ormEntity.getObjName();
	}
	protected String getObjectName(HttpServletRequest request){
		String name = request.getParameter(PARAM_OBJ_NAME);
		if(name==null||name.isEmpty()){
			name = getObjectName();
		}
		return name;
	}
	
	/**
	 * ����ʵ���id
	 * @param sid
	 */
	protected void setOrmEntityId(String sid){
		this.ormEntity.set(PARAM_OBJ_SID, sid);
	}
	
	/**
	 * �ڲ���,���ڱ����е�check
	 * 
	 * @author cr
	 * 
	 */
	protected static class Checker {
		static void checkSid(String sid) {
			CheckUtil.checkNull(new CheckUtil.IExtCheck() {
				@Override
				public boolean extCheck() {
					return true;
				}

				@Override
				public void throwErr() {
					throw new RuntimeException("sid is null");
				}
			}, sid);
		}
		
		static void checkBaseParams(String objectName, String objectEvent) {
			final String namef = objectName;
			final String eventf = objectEvent;
			CheckUtil.check(new CheckUtil.IExtCheck() {
				String name = namef;
				String event = eventf;

				@Override
				public boolean extCheck() {
					if (name != null && event != null)
						return true;
					else if (name == null || name.isEmpty())
						if (event != null && event.trim().equals("Login"))
							return true;
					return false;
				}

				@Override
				public void throwErr() {
					throw new RuntimeException(
							"objectName or objectEvent is invalid | objectName:["
									+ namef + "],objectEvent:[" + eventf + "]");
				}
			});
		}
		
		static void checkObjName(String objectName){
			try{
			CheckUtil.checkNull(objectName);
			}catch(NullPointerException e){
				throw new RuntimeException("objectName is null");
			}
		}
		
		static void checkObjEvent(String objectEvent){
			try{
			CheckUtil.checkNull(objectEvent);
			}catch(NullPointerException e){
				throw new RuntimeException("objectEvent is null");
			}
		}
	}

	/**
	 * �ڲ�������,�洢ҳ������
	 * 
	 * @author cr
	 * 
	 */
	protected interface PageTypeConst {
		final String PAGE_TYPE_LIST = "list";
		final String PAGE_TYPE_VIEW = "view";
		final String PAGE_TYPE_UPDATE = "update";
	}

}

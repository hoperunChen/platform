package com.parser.biz.parser.container;

import java.util.HashMap;
import java.util.Map;

import com.config.Config;
import com.constants.ConfigConstants;
import com.parser.biz.parser.BizReader;
import com.util.CheckUtil;
import com.util.ElUtilEx;
import com.util.FileUtilEx;
import com.util.StringUtilEx;


/**
 * Parser�Ļ�ȡ��(����),�������biz������ô��ÿ�μ���һ��Parser�󶼻�洢������,�´���Ҫ���parser��ʱ���ֱ��������ȡ
 * @author Administrator
 *
 */
public class ReaderGetter extends HashMap<String, BizReader>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3714831209165658645L;
	private static final boolean useCache = StringUtilEx.booleanValue(Config.get(ConfigConstants.BIZ_USE_CACHE));
	
	/*Singleton begin*/
	private ReaderGetter() {}
	
	private static class ContainerInstance{
		private static ReaderGetter instance= new ReaderGetter(); 
	}
	
	public static ReaderGetter getInstance(){
		return ContainerInstance.instance;
	}
	/*Singleton end*/
	
	
	/**
	 * ��дget����,��ֱ�Ӵ�������ȡԪ��,����ͨ��getParser��ȡ
	 */
	@Override
	public BizReader get(Object obj) {
		BizReader rtn = null;
		if(obj instanceof String)
			rtn = getParser((String)obj);
		else{
			throw new RuntimeException("obj can't be a Object");
		}
		return rtn;
	}
	
	/**
	 * ��дput����,��ֱ���������д洢Ԫ��,����ͨ��putParser����
	 */
	@Override
	public BizReader put(String key, BizReader value) {
		return putParser(key, value);
	}
	
	/**
	 * ��дputAll,��ֱ�Ӵ洢,����ͨ��setParser
	 */
	@Override
	public void putAll(Map<? extends String, ? extends BizReader> map) {
		for (String key : map.keySet()) {
			BizReader value = map.get(key);
			putParser(key, value);
		}
	}

	/**
	 * ��parsers�����д洢Parser
	 * @param key
	 * @param parser
	 */
	private BizReader putParser(String key,BizReader parser){
		CheckUtil.checkNull(key);
		if(useCache)
			return super.put(key, parser);
		else
			return null;
	}
	
	/**
	 * ͨ��key��������ȡ��Parser
	 * @param key
	 * @return
	 */
	public BizReader getParser(String objectName) {
		CheckUtil.checkNull(objectName);
		return getParser(objectName,null);
		
	}
	
	/**
	 * ͨ���������ƻ���biz�ļ�·���õ�Parser��
	 * @param objectName
	 * @param path
	 * @return Parser
	 */
	public BizReader getParser(String objectName,String path){
		String key = ElUtilEx.nvl(objectName, path).toString();
		if(key==null)
			throw new RuntimeException("[BizParser]objectName or path is invalid || objectName:["+objectName+"]; path:["+path+"]");
		BizReader rtn = null;
		if(useCache)
			rtn = super.get(key);
		if(rtn == null && path != null){
			rtn = new BizReader(FileUtilEx.getContextPathOnWeb()+"/"+path);
			putParser(key, rtn);
		}
		return rtn;
	}
	
}

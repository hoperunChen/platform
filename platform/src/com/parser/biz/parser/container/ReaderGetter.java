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
 * Parser的获取类(单例),如果开了biz缓存那么在每次加载一个Parser后都会存储在这里,下次需要这个parser的时候就直接在这里取
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
	 * 重写get方法,不直接从容器中取元素,而是通过getParser获取
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
	 * 重写put方法,不直接向容器中存储元素,而是通过putParser方法
	 */
	@Override
	public BizReader put(String key, BizReader value) {
		return putParser(key, value);
	}
	
	/**
	 * 重写putAll,不直接存储,而是通过setParser
	 */
	@Override
	public void putAll(Map<? extends String, ? extends BizReader> map) {
		for (String key : map.keySet()) {
			BizReader value = map.get(key);
			putParser(key, value);
		}
	}

	/**
	 * 向parsers容器中存储Parser
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
	 * 通过key从容器中取出Parser
	 * @param key
	 * @return
	 */
	public BizReader getParser(String objectName) {
		CheckUtil.checkNull(objectName);
		return getParser(objectName,null);
		
	}
	
	/**
	 * 通过对象名称或者biz文件路径得到Parser类
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

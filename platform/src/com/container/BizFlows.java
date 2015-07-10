package com.container;

import java.util.HashMap;

import com.biz.BizFlow;
import com.parser.biz.BizParser;


/**
 * Bizflow的容器类,缓存所有biz
 * @author cr
 *
 */
public class BizFlows extends HashMap<String, BizFlow>{

	private static final long serialVersionUID = 1L;
	/*Singleton begin*/
	private BizFlows(){}
	
	private static class BizFlowsInstance{
		private static BizFlows instance= new BizFlows(); 
	}
	
	public static BizFlows getInstance(){
		return BizFlowsInstance.instance;
	}
	/*Singleton end*/
	
	/**
	 * 读取biz文件,并且解析成为BizFlow类
	 * @param bizFlowDefinition
	 * @return BizFlow 
	 */
	public BizFlow readBiz(String objectName , String bizFlowDefinition){
		BizParser bizParser = new BizParser();
		BizFlow biz = bizParser.parse(objectName,bizFlowDefinition);
		if(biz!=null){
			put(objectName, biz);
		}
		return biz;
	}
	
}

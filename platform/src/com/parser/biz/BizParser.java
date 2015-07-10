package com.parser.biz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.biz.BizFlow;
import com.biz.biz.Biz;
import com.log.Log;
import com.parser.biz.parser.BizReader;
import com.parser.biz.parser.container.ReaderGetter;
import com.util.CheckUtil;


/**
 * biz������
 * @author cr
 *	
 */
public class BizParser{
	
	public BizParser() {
	}
	
	/**
	 * BizParser����ڷ���,ͨ��path��ȡbiz.xml�ļ����ҽ�����ΪBizFlow.(���봫��path����.)
	 * @param path
	 * @return BizFlow
	 */
	public BizFlow parse(String path){
		return  parse(null,path);
	}
	
	/**
	 * BizParser����ڷ���,ͨ��path��ȡbiz.xml�ļ����ҽ�����ΪBizFlow.(���봫��path����.)
	 * @param objectName
	 * @param path
	 * @return BizFlow
	 */
	public BizFlow parse(String objectName,String path){
		CheckUtil.checkNull("[BizParser] path is null", path);
		BizReader reader = ReaderGetter.getInstance().getParser(objectName,path);
		BizFlow rtn = new BizFlow();
		rtn.setBizs(buildBizs(reader.getBizs()));
		return null;
	}
	
	
	/**
	 * ����bizs,��bizs��Ϊ�������,����ֵҪ��Ӧ�ı�
	 * @param bizs
	 * @return
	 */
	private Map<String, Biz> buildBizs(List<BizReader> bizs){
		Map<String , Biz> rtn = null;
		if(bizs == null || bizs.isEmpty())
			Log.printErr("there had not anyone biz in the bizFlow file");
		else{
			rtn = new HashMap<String, Biz>();
			Iterator<BizReader> it = bizs.iterator();
			while(it.hasNext()){
				Biz biz = buildBiz(it.next());
				rtn.put(biz.getId(), biz);
				
			}
		}
		
		return null;
	}
	
	private Biz buildBiz(BizReader bizParser){
		Biz biz = new Biz();
		biz.setId(bizParser.attr("id"));
		return biz;
	}
	
	
	
	
	
}

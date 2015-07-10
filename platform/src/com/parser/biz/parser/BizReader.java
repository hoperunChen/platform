package com.parser.biz.parser;

import java.util.List;

import org.dom4j.Element;

import com.util.xml.reader.XmlReaderUtil;


/*
 * @author cr
 *
 */
public class BizReader extends XmlReaderUtil{
	
	
	public BizReader(String path) {
		super(path);
	}
	
	public BizReader(Element element) {
		super(element);
	}

	@SuppressWarnings("unchecked")
	public List<BizReader> getBizs(){
		return find("biz");
	}
	
	@Override
	protected XmlReaderUtil getNewReader(Element element) {
		return new BizReader(element);
	}
	
}

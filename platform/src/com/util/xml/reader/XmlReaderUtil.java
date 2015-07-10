package com.util.xml.reader;

import org.dom4j.Element;



/**
 * Parser File which dataType is Xml
 * @author cr
 *
 */
public class XmlReaderUtil extends AbstractXmlReader{
	
	public XmlReaderUtil(String path){
		super(path);
	}

	public XmlReaderUtil(Element element) {
		super(element);
	}
	
	protected  XmlReaderUtil getNewReader(Element element){
		return new XmlReaderUtil(element);
	};
	
}

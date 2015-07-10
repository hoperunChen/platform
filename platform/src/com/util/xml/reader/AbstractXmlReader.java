package com.util.xml.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Parser File which dataType is Xml
 * 
 * @author cr
 *
 */
public abstract class AbstractXmlReader {

	protected Element element;
	protected Document document;

	public AbstractXmlReader(String path) {
		readXml(path);
	}

	public AbstractXmlReader(Element element) {
		this.element = element;
	}

	@Override
	protected void finalize() throws Throwable {
		this.element = null;
		this.document = null;
		super.finalize();
	}

	/**
	 * ���õ�ǰreader��element
	 * 
	 * @param element
	 * @return
	 */
	public AbstractXmlReader setElement(Element element) {
		if (element != null) {
			this.element = element;
			return this;
		}else{
			return null;
		}
	}

	/**
	 * ��ȡ�ĵ�,����docuemnt��element��ֵ(elementΪ���ڵ�)
	 * 
	 * @param path
	 */
	private void readXml(String path) {
		if (path == null || path.isEmpty())
			throw new RuntimeException("path is invalid:" + path);
		File f = new File(path);
		SAXReader reader = new SAXReader();
		try {
			document = reader.read(f);
			setElement(document.getRootElement());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���ص�ǰ�ڵ�
	 * 
	 * @param attrName
	 * @return
	 */
	public String attr(String attrName) {
		return this.element.attributeValue(attrName);
	}

	/**
	 * ͨ��Ԫ�ص����Ʋ���Ԫ��,���һ������,Ĭ��ʵ��������AbstractXmlReader,���ϣ�����ص�ÿ��ʵ���������������,
	 * ��ô����дgetNewReader����.
	 * 
	 * @param elementName
	 * @return List
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List find(String nodeName) {
		List<AbstractXmlReader> rtn = null;
		Iterator it = element.selectNodes(nodeName).iterator();
		if(it.hasNext())
			rtn = buildReaders(it);
		else{
			Iterator children = element.elements().iterator();
			while(children.hasNext()){
				rtn = getNewReader((Element) children.next()).find(nodeName);
				if(rtn!=null)
					return rtn;
			}
		}
		if (rtn==null || rtn.isEmpty())
			return null;
		return rtn;
	}
	
	/**
	 * ���ĵ�������node,����node λ��
	 * @param nodeName
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findInDocument(String nodeName){
		List<AbstractXmlReader> rtn = new ArrayList<AbstractXmlReader>();
		rtn = buildReaders(element.selectNodes("//"+nodeName).iterator());
		if (rtn.isEmpty())
			return null;
		return rtn;
	}

	/**
	 * ����һ����ڵ�������ͬ�ĵ�һ���ڵ�,<br/>
	 * ��getChild������:findOne���𼶲����ӽڵ�ֱ�����ҵ�ƥ��Ԫ��,��getChildֻ����ҵ���һ���ڵ�
	 * 
	 * @param elementName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public AbstractXmlReader findOne(String elementName) {
		Element e =  element.element(elementName);
		if(e == null){
			Iterator it = element.elements().iterator();
			while (it.hasNext()) {
				AbstractXmlReader rtn = getNewReader((Element) it.next()).findOne(elementName);
				if(rtn != null){
					e = rtn.element;
				}
			}
			
		}
		if(e==null)
			return null;
		else
			return setElement(e);
		
	};
	
	/**
	 * ����һ����ڵ�������ͬ�Ľڵ�(����ж��,��ȡ��һ��),����λ��.
	 * @param nodeName
	 * @return
	 */
	public AbstractXmlReader findOneInDocument(String nodeName){
		AbstractXmlReader rtn = (AbstractXmlReader) findInDocument(nodeName).get(0);
		return setElement(rtn.element);
	}

	/**
	 * ��õ�ǰ�ڵ������
	 * 
	 * @return
	 */
	public String getNodeName() {
		return element.getQName().getName();
	}

	/**
	 * ��ø��ڵ�
	 * 
	 * @return
	 */
	public AbstractXmlReader getParent() {
		this.element = element.getParent();
		return this;
	}

	/**
	 * Ѱ�ҽڵ������������ͬ�ĸ��ڵ�,��������Ͼͼ������ϲ���
	 * 
	 * @param nodeName
	 * @return
	 */
	public AbstractXmlReader getParentByNodeName(String nodeName) {
		for (Element e = element; e != null; e = e.getParent()) {
			if (e.getQName().getName().equals(nodeName.trim())) {
				this.element = e;
				return this;
			}
		}

		return null;
	}

	/**
	 * ͨ���ڵ����ƻ����Ԫ��
	 * @param nodeName
	 * @return
	 */
	public AbstractXmlReader getChild(String nodeName) {
		return setElement(element.element(nodeName));
	}

	/**
	 * ���������Ԫ��
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getChildren() {
		List<AbstractXmlReader> rtn = null;
		rtn = buildReaders(element.elements().iterator());
		if(rtn==null||rtn.isEmpty())
			return null;
		return rtn;
	}

	/**
	 * �����ڵ�������ͬ�������ӽڵ�
	 * @param nodeName
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getChildren(String nodeName) {
		List<AbstractXmlReader> rtn = null;
		rtn = buildReaders(element.elements(nodeName).iterator());
		if(rtn==null||rtn.isEmpty())
			return null;
		return rtn;
	}

	/**
	 * ���ĳ�ڵ���ı�����
	 * 
	 * @param nodeName
	 * @return
	 */
	public String getChildText(String nodeName) {
		if (element == null)
			return null;
		return element.elementTextTrim(nodeName);
	}

	/**
	 * ��ø��ڵ�
	 * 
	 * @return
	 */
	public AbstractXmlReader getRoot() {
		return setElement(this.document.getRootElement());
	}

	/**
	 * ����find��children�ȷ������Ѳ�ѯ����Ԫ�����һ����abstractXmlReader��ɵļ���
	 * @param elements
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected List buildReaders(Iterator elements) {
		List<AbstractXmlReader> rtn = new ArrayList<AbstractXmlReader>();
		while (elements.hasNext()) {
			AbstractXmlReader reader = getNewReader((Element) elements.next());
			reader.document = this.document;
			rtn.add(reader);
		}
		return rtn;
	}

	/**
	 * ���ϣ��find����AbstractXmlReader��ĳ������,������д�÷���
	 * 
	 * @param element
	 * @return
	 */
	protected abstract AbstractXmlReader getNewReader(Element element);

	/**
	 * ת��Ϊelement����
	 * 
	 * @return
	 */
	public Element toElement() {
		return element;
	}
	

}

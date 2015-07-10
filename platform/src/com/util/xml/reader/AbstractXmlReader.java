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
	 * 设置当前reader的element
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
	 * 读取文档,并对docuemnt和element赋值(element为根节点)
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
	 * 返回当前节点
	 * 
	 * @param attrName
	 * @return
	 */
	public String attr(String attrName) {
		return this.element.attributeValue(attrName);
	}

	/**
	 * 通过元素的名称查找元素,获得一个集合,默认实例类型是AbstractXmlReader,如果希望返回的每个实例类型是子类对象,
	 * 那么请重写getNewReader方法.
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
	 * 在文档中搜索node,不论node 位置
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
	 * 查找一个与节点名称相同的第一个节点,<br/>
	 * 与getChild的区别:findOne会逐级查找子节点直到查找到匹配元素,而getChild只会查找到下一级节点
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
	 * 查找一个与节点名称相同的节点(如果有多个,就取第一个),不论位置.
	 * @param nodeName
	 * @return
	 */
	public AbstractXmlReader findOneInDocument(String nodeName){
		AbstractXmlReader rtn = (AbstractXmlReader) findInDocument(nodeName).get(0);
		return setElement(rtn.element);
	}

	/**
	 * 获得当前节点的名称
	 * 
	 * @return
	 */
	public String getNodeName() {
		return element.getQName().getName();
	}

	/**
	 * 获得父节点
	 * 
	 * @return
	 */
	public AbstractXmlReader getParent() {
		this.element = element.getParent();
		return this;
	}

	/**
	 * 寻找节点名称与参数相同的父节点,如果不符合就继续向上查找
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
	 * 通过节点名称获得子元素
	 * @param nodeName
	 * @return
	 */
	public AbstractXmlReader getChild(String nodeName) {
		return setElement(element.element(nodeName));
	}

	/**
	 * 获得所有子元素
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
	 * 获得与节点名称相同的所有子节点
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
	 * 获得某节点的文本内容
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
	 * 获得根节点
	 * 
	 * @return
	 */
	public AbstractXmlReader getRoot() {
		return setElement(this.document.getRootElement());
	}

	/**
	 * 用于find、children等方法，把查询到的元素组成一个由abstractXmlReader组成的集合
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
	 * 如果希望find返回AbstractXmlReader的某个子类,可以重写该方法
	 * 
	 * @param element
	 * @return
	 */
	protected abstract AbstractXmlReader getNewReader(Element element);

	/**
	 * 转换为element类型
	 * 
	 * @return
	 */
	public Element toElement() {
		return element;
	}
	

}

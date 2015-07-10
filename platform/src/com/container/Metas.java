package com.container;

import java.util.HashMap;

import com.meta.Meta;
//TODO
/**
 * metaµÄÈİÆ÷Àà(µ¥Àı)
 * @author cr
 *
 */
public class Metas extends HashMap<String, Meta>{

	private static final long serialVersionUID = 1L;
	
	private Metas(){}
	
	private static class MetasInstance{
		private static Metas instance = new Metas();
	}
	
	public static Metas getInstance(){
		return MetasInstance.instance;
	}
	
}

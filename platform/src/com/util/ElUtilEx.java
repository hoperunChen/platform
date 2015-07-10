package com.util;

import java.util.Collection;

public class ElUtilEx {
	@SuppressWarnings("rawtypes")
	public static Object nvl(Object o,Object o1){
		if(o==null || o instanceof Collection && ((Collection)o).isEmpty())
			return o1;
		return o;
	}
	
	public static Object iif(boolean b,Object o1, Object o2 ){
		if(b)
			return o1;
		else
			return o2;
	}
}

package com.util;



//TODO need Refactoring
/**
 * ºÏ≤È¿‡
 * @author cr
 */
public class CheckUtil {
	public static void checkNull(Object... o) {
		check(null,true, null, o);
	}
	
	public static void checkNull(String errorMessage,Object... o){
		check(null,true,errorMessage,o);
	}
	
	public static void check(IExtCheck extCheck){
		check(extCheck,false,null, new Object[]{});
	}
	
	public static void check(IExtCheck extCheck,Object... o){
		check(extCheck,true,null, o);
	}

	public static void check(IExtCheck extCheck,boolean checkNull,String errorMessage , Object... o) {
		for (Object oe : o) {
			if (oe == null && checkNull){
				if(extCheck !=null)
					extCheck.throwErr();
				else
					throw new NullPointerException();
			}
		}
		if(extCheck != null)
			if (!extCheck.extCheck())
				extCheck.throwErr();
		
	}
	
	public interface IExtCheck {
		boolean extCheck();

		void throwErr();
	}
}

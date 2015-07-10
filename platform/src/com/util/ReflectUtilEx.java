package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Set;

import com.log.Log;

/**
 * 反射工具类
 * 
 * @author cr
 * 
 */
public class ReflectUtilEx {
	
	public static String TYPE_INTEGER = "java.lang.Integer";
	public static String TYPE_STRING = "java.lang.String";
	public static String TYPE_DOUBLE = "java.lang.Double";
	public static String TYPE_TIMESTAMP = "java.sql.Timestamp";
	public static String TYPE_SET = "java.util.Set";
	
	public static Object getObj(String className) {
		if (className == null || className.isEmpty())
			return null;
		Object obj = null;
		try {
			obj = (Object) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (obj == null)
			Log.printErr("class Not found :<" + className + ">");
		return obj;
	}

	public static Method[] getAllMethods(String className) {
		Method[] allM = getObj(className).getClass().getDeclaredMethods();
		return allM;
	}

	public static Method getMethodByName(String className, String methodName) {
		if ("".equals(methodName) || methodName.isEmpty())
			return null;
		Method rtn = null;
		Method[] methods = getAllMethods(className);
		for (Method method : methods) {
			if ("".equals(method.getName()) || method.getName().isEmpty())
				continue;
			if (methodName.trim().toLowerCase()
					.equals(method.getName().trim().toLowerCase()))
				rtn = method;
		}
		if (rtn == null) {
			if (getObj(className).getClass().getSuperclass() == null)
				Log.printErr("method :<" + methodName + "> not find in class:<"
						+ className + ">");
			else
				rtn = getMethodByName(getObj(className).getClass().getSuperclass()
						.getName(), methodName);
		} 
		return rtn;
	}

	public static Method getMethodByName(Object obj, String methodName) {
		return getMethodByName(obj.getClass().getName(), methodName);
	}

	public static Field[] getAllFields(String className) {
		Field[] allF = getObj(className).getClass().getDeclaredFields();
		return allF;
	}

	public static Field getFieldByName(String className, String filedName) {
		if ("".equals(filedName) || filedName.isEmpty())
			return null;
		Field[] fields = getAllFields(className);
		for (Field field : fields) {
			if ("".equals(field.getName()) || field.getName().isEmpty())
				continue;
			if (filedName.trim().toLowerCase()
					.equals(field.getName().trim().toLowerCase()))
				return field;
		}
		Log.printErr("field :<" + filedName + "> not find in class:<"
				+ className + ">");
		return null;
	}

	public static Object invoke(Object obj, String methodName,
			Object[] params) {
		Method method = getMethodByName(obj.getClass().getName(), methodName);
		try {
			@SuppressWarnings({"rawtypes" })
			Class [] types = method.getParameterTypes();
			if(params != null){
				for(int i = 0 ; i < params.length ;i++){
					if(params[i].getClass().getName().equals(types[i].getName())){
						continue;
					}else{
						params[i] = castParamType(params[i],types[i].getName());
					}
				}
			}
			return method.invoke(obj, params);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//TODO
	/**
	 * 转换参数数据类型.暂时只实现了常用基本类型(set 未测试)
	 * @param param
	 * @param typeName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static Object castParamType(Object param , String typeName){
		if(typeName.equals(TYPE_STRING))
			return (String)param;
		else if(typeName.equals(TYPE_INTEGER)){
			if(param instanceof String)
				return Integer.parseInt(param.toString());
			return (Integer)param;
		}else if(typeName.equals(TYPE_DOUBLE))
			return (Double)param;
		else if(typeName.equals(TYPE_TIMESTAMP))
			return (Timestamp)param;
		else if(typeName.equals(TYPE_SET))
			return (Set)param;
		return param;
	}
	
}

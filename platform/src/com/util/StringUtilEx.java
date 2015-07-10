package com.util;

import java.io.UnsupportedEncodingException;


public class StringUtilEx {
	
	public static String toUpperFirstChar(String s) {
		byte[] items = s.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}
	
	public static String toLowerFirstChar(String s) {
		byte[] items = s.getBytes();
		items[0] = (byte) ((char) items[0] - 'A' + 'a');
		return new String(items);
	}
	
	/**
	 * convert variables to Pascal
	 * example: XXxX_YYYY --> XxxxYyyy
	 * @param s
	 * @return string with Pascal
	 */
	public static String convertToPascalFromDb(String s){
		String rtn = "";
		s = s.toLowerCase();
		String [] ss = s.split("_");
		for (int i = 0; i < ss.length; i++) {
			rtn = rtn.concat(StringUtilEx.toUpperFirstChar(ss[i]));
		}
		return rtn;
	}
	/**
	 * convert variables to Camel
	 * example: XXxX_YYYY --> xxxxYyyy
	 * @param s
	 * @return
	 */
	public static String convertToCamelFromDb(String s){
		String rtn = "";
		if(s == null)
			return rtn;
		s = s.toLowerCase();
		String [] ss = s.split("_");
		rtn = rtn.concat(ss[0]);
		for (int i = 1; i < ss.length; i++) {
			rtn = rtn.concat(StringUtilEx.toUpperFirstChar(ss[i]));
		}
		return rtn;
	}
	
	/**
	 * convert variables to Camel
	 * example: XxxxYyyy --> xxxxYyyy
	 * @param s
	 * @return
	 */
	public static String convertToCamelFromPascal(String s){
		String rtn = "";
		if(s == null)
			return rtn;
		return StringUtilEx.toLowerFirstChar(s);
	}
	
	/**
	 * convert variables to Camel
	 * example: xxxxYyyy --> XxxxYyyy
	 * @param s
	 * @return
	 */
	public static String convertToPascalFromCamel(String s){
		String rtn = "";
		if(s == null)
			return rtn;
		return StringUtilEx.toUpperFirstChar(s);
	}
	
	/**
	 * convert variables to Db_Name
	 * example: xxxxYyyy --> XXXX_YYYY
	 * @param s
	 * @return
	 */
	public static String convertToDbNameFromCamel(String s){
		String rtn = "";
		if(s == null)
			return rtn;
		s = StringUtilEx.toLowerFirstChar(s);
		byte[] b = s.getBytes();
		byte[] newB = new byte[b.length+StringUtilEx.getUpperLettersNum(s)];
		for (int i= 0,newBIndex = 0; i < b.length ; i++,newBIndex++) {
			if(i != 0 && b[i]<=90&&b[i]>=65){
				newB[newBIndex] = 95;
				newBIndex++;
			}
			newB[newBIndex] = b[i];
		}
		try {
			rtn = new String(newB,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rtn = rtn.toUpperCase();
		return rtn;
	}
	
	/**
	 * Get the number of capital letters in the following variables which named 's'
	 * @param s
	 * @return lettersNum
	 */
	public static int getUpperLettersNum(String s){
		int rtn = 0;
		if(s == null)
			return 0;
		byte[] b = s.getBytes();
		for (byte c : b) {
			if(c<=90&&c>=65)
				rtn++;
		}
		return rtn;
	}
	
	public static boolean booleanValue(String s){
		if(s!= null &&s.equals("true"))
			return true;
		else
			return false;
	}
	
	public static void main(String []args){
		System.out.println(StringUtilEx.convertToDbNameFromCamel("UserInfo"));
	}
	
	
}

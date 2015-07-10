package com.log;

import java.util.Date;

import com.config.Config;
import com.constants.ConfigConstants;


/**
 * 日志打印
 * 系统中打印日志的工具
 * @author cr
 *
 */
public class Log {
	private static final String ERR = "error";
	private static final String SQL = "sql";
	
	
	private static boolean checkShowAuth(String propertyName) {
		String showlog = Config.get(ConfigConstants.STD_SHOW_LOG);
		if (showlog != null && showlog.equals("true"))
			return true;
		return false;
	}

	private static boolean checkShowLog() {
		return checkShowAuth(ConfigConstants.STD_SHOW_LOG);
	}

	private static boolean checkShowSql() {
		return checkShowAuth(ConfigConstants.STD_SHOW_SQL);
	}

	private static void print(Object s) {
		print(null, s);
	}

	private static void print(String type, Object s) {
		String out = "[Log]" + new Date(System.currentTimeMillis());
		out += " < " + s + " > ";
		if (type != null){
			out = "[" + type + "]"+out;
			if (type.equals("error")) {
				System.err.println(out);
			}
		}else{
			System.out.println(out);
		}
	}

	public static void printLog(Object s) {
		if (checkShowLog())
			print(s);
	}

	public static void printSql(String sql, String[] params, Object[] values) {
		if (!checkShowSql())
			return;
		String out = sql + "; ";
		for (int i = 0; i < params.length; i++) {
			out += params[i] + ":" + values[i] + ",";
		}
		print(SQL, out);
	}
	
	public static void printErr(Object o){
		print(ERR, o);
	}

}

package com.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 获取系统设置
 * @author cr
 *
 */
public class Config {
	private static String filePath="/std_config.properties";
	private static Properties prop;
	static {   
		buildProp();
    }   
	public static String get(String propertyName){
		if(prop == null)
			buildProp();
		return prop.getProperty(propertyName.trim());
	}
	
	private static void buildProp(){
		prop = new Properties();   
        try { 
        	InputStream in = Config.class.getResourceAsStream(getFilePath()); 
            prop.load(in);   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
	}
	
	public static void setFilePath(String path){
		filePath = path; 
	}
	public static String getFilePath(){
		return filePath;
	}
}

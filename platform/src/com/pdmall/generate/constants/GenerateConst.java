package com.pdmall.generate.constants;

public interface GenerateConst {
	public static int GENERATE_TYPE_JAVA_ENTITY = 0;
	public static int GENERATE_TYPE_JAVA_HBM = 1;
	public static int GENERATE_TYPE_JAVA_BEANS = 2;
	//访问修饰符 
	public static String GENERATE_JAVA_CODE_PUBLIC = "public ";
	public static String GENERATE_JAVA_CODE_PRIVATE = "private ";
	public static String GENERATE_JAVA_CODE_PROTECTED = "protected ";
	public static String GENERATE_JAVA_CODE_STATIC = "static ";
	public static String GENERATE_JAVA_CODE_FINAL = "final ";
	public static String GENERATE_JAVA_CODE_ABSTRACT = "abstract ";
	//类型关键字
	public static String GENERATE_JAVA_CODE_CLASS = "class ";
	public static String GENERATE_JAVA_CODE_PACKAGE = "package ";
	
	// java 关键字
	public static String GENERATE_JAVA_CODE_EXTENDS = "extends ";
	public static String GENERATE_JAVA_CODE_VOID = "void ";
	//类型
	public static String GENERATE_JAVA_CODE_INTEGER = "java.lang.Integer ";
	public static String GENERATE_JAVA_CODE_STRING = "java.lang.String ";
	public static String GENERATE_JAVA_CODE_DOUBLE = "java.lang.Double ";
	public static String GENERATE_JAVA_CODE_TIMESTAMP = "java.sql.Timestamp ";
	public static String GENERATE_JAVA_CODE_SET = "java.util.Set ";
	
	/*xml*/
	public static String GENERATE_XML_CODE_XML_HEAD="<?xml version=\"1.0\"?> ";
	public static String GENERATE_XML_CODE_ID = "id ";
	public static String GENERATE_XML_CODE_NAME = "name ";
	
	//HBM
	public static String GENERATE_HBM_CODE_INTEGER = "int";
	public static String GENERATE_HBM_CODE_STRING = "string";
	public static String GENERATE_HBM_CODE_DOUBLE = "java.lang.Double";
	public static String GENERATE_HBM_CODE_TIMESTAMP = "java.sql.Timestamp";
	public static String GENERATE_HBM_CODE_SET = "java.util.Set";
	public static String GENERATE_HBM_CODE_CLASS = "class ";
	public static String GENERATE_HBM_CODE_TABLE = "table ";
	public static String GENERATE_HBM_CODE_TYPE = "type ";
	public static String GENERATE_HBM_CODE_COLUMN = "column ";
	public static String GENERATE_HBM_CODE_NOTNULL = "not-null ";
	public static String GENERATE_HBM_CODE_INSERT = "insert";
	public static String GENERATE_HBM_CODE_UPDATE = "update";
	
	//spring
	public static String GENERATE_SPR_CODE_PARENT = "parent";
	public static String GENERATE_SPR_CODE_PROPERTY = "property";
	public static String GENERATE_SPR_CODE_VALUE = "value";
	public static String GENERATE_SPR_CODE_INIT_METHOD = "init-method";
	public static String GENERATE_SPR_CODE_HEAD = "\n<!DOCTYPE beans PUBLIC \"-//SPRING//DTD BEAN//EN\" \"http://www.springframework.org/dtd/spring-beans.dtd\">\n";
}

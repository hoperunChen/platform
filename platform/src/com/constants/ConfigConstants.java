package com.constants;
/**
 * 系统设置的key
 * @author cr
 *
 */
public interface ConfigConstants {
	public static String STD_MAIN_RUL="app.main_url";
	public static String STD_LOGIN_URL="app.login_url";
	public static String STD_LIST_URL="app.list_url";
	public static String STD_UPDATE_URL="app.update_url";
	public static String STD_VIEW_URL="app.view_url";	
	
	public static String STD_SYS_HM_NAME="app.sys_hm_name"; //系统中文名称
	public static String STD_SHOW_LOG="log.show_log";	//是否显示日志
	public static String STD_SHOW_SQL="log.show_sql";	//是否显示sql日志
	public static String STD_PAGE_SIZE="web.list.page_size";	//列表页面每页的记录数量
	
	public static String BIZ_USE_CACHE = "biz.use_cache"; //biz是否使用缓存
}

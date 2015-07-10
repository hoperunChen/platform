package com.util;

import java.sql.Timestamp;
import java.util.Date;
/**
 * 时间工具类,本身存储时间,并且可以对时间进行操作
 * @author cr
 *
 */
public interface EasyCalendar {
	/**
	 * 获得年份
	 * @return
	 */
	int getYear();
	/**
	 * 获得月份
	 * @return
	 */
	int getMonth();
	/**
	 * 获得日期
	 * @return
	 */
	int getDay();
	/**
	 * 获得小时
	 * @return
	 */
	int getHours();
	/**
	 * 获得分钟
	 * @return
	 */
    int getMinutes();
    /**
     * 获得秒
     * @return
     */
    int getSeconds();
    /**
     * 得到当前月份的天数
     * @return
     */
    int getMaxDayOfMonth();
    /**
     * 得到当前年的天数
     * @return
     */
    int getMaxDayOfYear();
    /**
     * 时间是否相同
     * @param date
     * @return
     */
    boolean isSame(Date date);
    /**
     * 时间是否相同
     * @param date
     * @return
     */
    boolean isSame(EasyCalendar date);
    /**
     * 时间是否相同
     * @param date
     * @return
     */
    boolean isSame(Timestamp date);
    /**
     * 改变当前年份
     * @param yearDiff (1,-1)
     * @return
     */
    EasyCalendar moveYear(int yearDiff);
    /**
     * 改变当前月份
     * @param monthDiff
     * @return
     */
    EasyCalendar moveMonth(int monthDiff);
    /**
     * 改变周
     * @param weekDiff
     * @return
     */
    EasyCalendar moveWeek(int weekDiff);
    /**
     * 改变日
     * @param dayCount
     * @return
     */
    EasyCalendar moveDay(int dayCount);
    /**
     * 改变小时
     * @param hour
     * @return
     */
    EasyCalendar moveToHour(int hour);
    /**
     * 通过date类型获得EasyCalendar
     * @param date
     * @return
     */
    EasyCalendar fromDate(Date date);
    /**
     * 通过String类型获得easyCalendar
     * @param str
     * @return
     */
    EasyCalendar fromString(String str);
    /**
     * 转换为date类型
     * @return
     */
    Date toDate();
    /**
     * 转换为timestamp类型
     * @return
     */
    Timestamp toTimestamp();
    /**
     * 格式化时间.
     * @param format
     * @return
     */
    String format(String format);
    
    @Override
    public String toString();
}

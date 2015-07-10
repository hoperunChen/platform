package com.util;

import java.sql.Timestamp;
import java.util.Date;
/**
 * ʱ�乤����,����洢ʱ��,���ҿ��Զ�ʱ����в���
 * @author cr
 *
 */
public interface EasyCalendar {
	/**
	 * ������
	 * @return
	 */
	int getYear();
	/**
	 * ����·�
	 * @return
	 */
	int getMonth();
	/**
	 * �������
	 * @return
	 */
	int getDay();
	/**
	 * ���Сʱ
	 * @return
	 */
	int getHours();
	/**
	 * ��÷���
	 * @return
	 */
    int getMinutes();
    /**
     * �����
     * @return
     */
    int getSeconds();
    /**
     * �õ���ǰ�·ݵ�����
     * @return
     */
    int getMaxDayOfMonth();
    /**
     * �õ���ǰ�������
     * @return
     */
    int getMaxDayOfYear();
    /**
     * ʱ���Ƿ���ͬ
     * @param date
     * @return
     */
    boolean isSame(Date date);
    /**
     * ʱ���Ƿ���ͬ
     * @param date
     * @return
     */
    boolean isSame(EasyCalendar date);
    /**
     * ʱ���Ƿ���ͬ
     * @param date
     * @return
     */
    boolean isSame(Timestamp date);
    /**
     * �ı䵱ǰ���
     * @param yearDiff (1,-1)
     * @return
     */
    EasyCalendar moveYear(int yearDiff);
    /**
     * �ı䵱ǰ�·�
     * @param monthDiff
     * @return
     */
    EasyCalendar moveMonth(int monthDiff);
    /**
     * �ı���
     * @param weekDiff
     * @return
     */
    EasyCalendar moveWeek(int weekDiff);
    /**
     * �ı���
     * @param dayCount
     * @return
     */
    EasyCalendar moveDay(int dayCount);
    /**
     * �ı�Сʱ
     * @param hour
     * @return
     */
    EasyCalendar moveToHour(int hour);
    /**
     * ͨ��date���ͻ��EasyCalendar
     * @param date
     * @return
     */
    EasyCalendar fromDate(Date date);
    /**
     * ͨ��String���ͻ��easyCalendar
     * @param str
     * @return
     */
    EasyCalendar fromString(String str);
    /**
     * ת��Ϊdate����
     * @return
     */
    Date toDate();
    /**
     * ת��Ϊtimestamp����
     * @return
     */
    Timestamp toTimestamp();
    /**
     * ��ʽ��ʱ��.
     * @param format
     * @return
     */
    String format(String format);
    
    @Override
    public String toString();
}

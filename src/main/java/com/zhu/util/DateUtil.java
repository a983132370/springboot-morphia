package com.zhu.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class DateUtil {

	public static final String PATTERN_YEAR2MONTH = "yyyyMM";
	public static final String PATTERN_YEAR2DAY = "yyyyMMdd";
	public static final String PATTERN_YEAR2SECOND = "yyyyMMddHHmmss";
	public static final String PATTERN_YEAR2MILLISECOND = "yyyyMMddHHmmssSSS";
	public static final String PATTERN_NORMAL_YEAR2SECOND = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_NORMAL_YEAR2MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";

	protected static ThreadLocal<Map<String, DateFormat>> formatLocal = new ThreadLocal<Map<String, DateFormat>>();

	/**
	 * 获取pattern样式的DateFormat 线程安全
	 * 
	 * @param pattern
	 * @return
	 */
	protected static DateFormat getFormat(String pattern) {
		Map<String, DateFormat> formatMap = formatLocal.get();

		if (formatMap == null) {
			formatMap = new HashMap<String, DateFormat>();
			formatLocal.set(formatMap);
		}

		DateFormat format = formatMap.get(pattern);

		if (format == null) {
			format = new SimpleDateFormat(pattern);
			formatMap.put(pattern, format);
		}

		return format;
	}

	/**
	 * 校验
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static boolean valid(String dateStr, String pattern) {
		try {
			getFormat(pattern).parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * 解析日期字符串
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date parse(String dateStr, String pattern) {
		if (dateStr == null) {
			return null;
		}
		try {
			return getFormat(pattern).parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 格式化
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		return getFormat(pattern).format(date);
	}

	/**
	 * 将时间转换成Long型
	 * 
	 * @param date
	 * @return
	 */
	public static Long parseToLong(Date date, String pattern) {
		return Long.valueOf(format(date, pattern));
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date now() {
		return new Date();
	}

/*	public static Long nowInMilliseconds() {
		return System.currentTimeMillis();
	}*/

	public static int nowInMilliseconds() {
		return (int)(System.currentTimeMillis()/1000);
	}
}

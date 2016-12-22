package com.zhu.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

import java.io.IOException;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class JsonUtil {

	protected static TheadLocalDateFormat dateFormat = new TheadLocalDateFormat();
	protected static ObjectMapper objectMapper = new ObjectMapper();
	protected static ObjectMapper objectMapperWithDateFormat = new ObjectMapper();
	protected static ObjectMapper objectMapperIncludeIgnoredProperties = new ObjectMapper();
	protected static ObjectMapper objectMapperIncludeIgnoredPropertiesWithDateFormat = new ObjectMapper();

	static {
		objectMapperWithDateFormat.setDateFormat(dateFormat);
		objectMapperIncludeIgnoredPropertiesWithDateFormat.setDateFormat(dateFormat);

		objectMapperIncludeIgnoredProperties.setAnnotationIntrospector(new NoIgnoreJacksonAnnotationIntrospector());
		objectMapperIncludeIgnoredPropertiesWithDateFormat.setAnnotationIntrospector(new NoIgnoreJacksonAnnotationIntrospector());
		
		/**定义当属性不存在则忽略**/
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	public static <T> T parse(String content, Class<T> type) {
		try {
			return objectMapper.readValue(content, type);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String toString(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String toString(Object obj, String datePattern) {
		if (datePattern == null) {
			return toString(obj);
		}
		dateFormat.set(datePattern);
		try {
			return objectMapperWithDateFormat.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String toStringIncludeIgnoredProperties(Object obj) {
		try {
			return objectMapperIncludeIgnoredProperties.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String toStringIncludeIgnoredProperties(Object obj, String datePattern) {
		if (datePattern == null) {
			return toStringIncludeIgnoredProperties(obj);
		}
		dateFormat.set(datePattern);
		try {
			return objectMapperIncludeIgnoredPropertiesWithDateFormat.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static class TheadLocalDateFormat extends SimpleDateFormat {
		private static final long serialVersionUID = -659481710224224430L;
		protected String defaultPattern = DateUtil.PATTERN_YEAR2SECOND;
		protected ThreadLocal<String> patternThreadLocal = new ThreadLocal<String>();

		public void reset() {
			patternThreadLocal.set(null);
		}

		public void set(String pattern) {
			patternThreadLocal.set(pattern);
		}

		public String get() {
			String p = patternThreadLocal.get();
			if (p == null) {
				return defaultPattern;
			} else {
				return p;
			}
		}

		@Override
		public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
			return DateUtil.getFormat(get()).format(date, toAppendTo, fieldPosition);
		}

		@Override
		public Date parse(String source) throws ParseException {
			return DateUtil.getFormat(get()).parse(source);
		}

	}

	public static class NoIgnoreJacksonAnnotationIntrospector extends JacksonAnnotationIntrospector {
		@Override
		protected boolean _isIgnorable(Annotated a) {
			return false;
		}

		@Override
		public String[] findPropertiesToIgnore(Annotated ac) {
			return null;
		}

		@Override
		public Boolean findIgnoreUnknownProperties(AnnotatedClass ac) {
			return null;
		}
	}
}

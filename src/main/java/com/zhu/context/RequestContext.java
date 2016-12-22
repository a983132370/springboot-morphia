package com.zhu.context;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求上下文
 */
public abstract class RequestContext {

	public static final String CLIENT_IP = RequestContext.class + ".client_ip";

	protected static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

	public static Map<String, Object> get() {
		Map<String, Object> map = threadLocal.get();
		if (map == null) {
			map = new HashMap<String, Object>();
			threadLocal.set(map);
		}
		return map;
	}
	
	public static void clear() {
		threadLocal.remove();
	}

	public static void set(String key, Object val) {
		get().put(key, val);
	}

	public static Object get(String key) {
		return get().get(key);
	}

	/**
	 * <pre>
	 * 设置客户端IP
	 * </pre>
	 * @param ip
	 */
	public static void setClientIp(String ip) {
		set(CLIENT_IP, ip);
	}

	/**
	 * <pre>
	 * 获取客户端IP
	 * </pre>
	 * @return
	 */
	public static String getClientIp() {
		return (String) get(CLIENT_IP);
	}

}

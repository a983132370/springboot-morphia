package com.zhu.util;

import java.util.UUID;

/**
 * token通用类 用于生成token
 */
public class TokenUtil {

	public static String createToken() {
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString().toUpperCase().replace("-", "");
	}


}

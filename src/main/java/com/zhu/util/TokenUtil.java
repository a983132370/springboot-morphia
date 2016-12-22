package com.zhu.util;

import java.util.UUID;

public class TokenUtil {

	public static String createToken() {
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString().toUpperCase().replace("-", "");
	}


}

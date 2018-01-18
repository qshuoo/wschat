package com.qshuoo.wschat.utils;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author qiaoyongshuo
 *
 */
public class JsonUtils {
	
	
	public static Object jsonToObject(String json) {
		return JSON.parse(json);
	}
	
	public static String ObjectToJson(Object object) {
		return JSON.toJSONString(object);
	}

}

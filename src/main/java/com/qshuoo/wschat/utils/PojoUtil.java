package com.qshuoo.wschat.utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 * @author qiaoyongshuo
 *
 */
public class PojoUtil {
	
	/**
	 * map to bean
	 * @param map
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static Object map2Object(Map<String, Object> map, Class<?> bean) throws Exception {
		Object obj =bean.newInstance();
		BeanUtils.populate(obj, map);
		return obj;
	}

}

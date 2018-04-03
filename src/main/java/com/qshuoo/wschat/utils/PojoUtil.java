package com.qshuoo.wschat.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 * @author qiaoyongshuo
 *
 */
public class PojoUtil {
	
	/**
	 * map转换对应的对象
	 * @param map
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static Object map2Object(Map<String, Object> map, Class<?> bean) throws Exception {
		Object obj = bean.newInstance();
		BeanUtils.populate(obj, map);
		return obj;
	}
	
	/**
	 * map转换对应的对象 只转换指定的值(其他值返回NULL)
	 * @param map
	 * @param bean
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object map2Object(Map<String, Object> map, Class<?> bean, String... args ) throws Exception {
		Map<String, Object> newMap = new HashMap<String, Object>();
		for (String key : args) {
			newMap.put(key, map.get(key));
		}
		return map2Object(newMap, bean);
	}
	

}

package com.qshuoo.wschat.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SqlUtils {
	
	/**
	 * 根据对象生成插入sql 只插入不为空的属性
	 * @param object
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public static String generateInsertSQL(Object object, Class<?> c) throws Exception {
		StringBuilder sql = new StringBuilder("INSERT INTO ");
		StringBuilder fieldBuilder = new StringBuilder("(");
		StringBuilder valueBuilder = new StringBuilder("(");
		sql.append(c.getSimpleName().toLowerCase()).append(" "); // 类名转换小写
		
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) { // append不为空属性
			String fieldName = field.getName();
			String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Method method = c.getMethod(methodName);
			Object res = method.invoke(object);
			if (res != null) {
				fieldBuilder.append(fieldName).append(",");
				if (field.getType().equals(String.class)) {
					valueBuilder.append("'").append(res).append("'").append(",");
				} else {
					valueBuilder.append(res).append(",");
				}
					
			}
		}
		
		if (fieldBuilder.length() < 2) { // 属性全为空  
			return null;
		}
		
		String field = fieldBuilder.substring(0, fieldBuilder.length() - 1) + ")";
		String value = valueBuilder.substring(0, valueBuilder.length() - 1) + ")";
		return sql.append(field).append(" VALUES ").append(value).toString();
	}

}

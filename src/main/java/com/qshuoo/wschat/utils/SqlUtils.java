package com.qshuoo.wschat.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author qshuoo
 *
 */
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
		return sql.append(field).append(" VALUES ").append(value).append(";").toString();
	}
	
	public static String generateUpdateSql(Object o, Class<?> c, String[] condis, String[] elems) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ").append(c.getSimpleName().toLowerCase()).append(" SET ");
		List<String> elemList = new ArrayList<>();
		// 更新属性
		for (String elem : elems) {
			Field field = c.getDeclaredField(elem);
			field.setAccessible(true);
			String value = "'" + field.get(o).toString() + "'";
			elemList.add(elem + " = " + value);
		}
		// 更新条件
		List<String> condiList = new ArrayList<>();
		
		for (String condi : condis) {
			Field field = c.getDeclaredField(condi);
			field.setAccessible(true);
			Object value = field.get(o);
			if (!(value instanceof Number)) {
				value = "'" + value.toString() + "'";
			}
			condiList.add(condi + " = " + value.toString());
		}
		String elemStr = String.join(", ", elemList);
		String condiStr = String.join(" AND ", condiList);
		builder.append(elemStr).append(" WHERE ").append(condiStr).append(";");
		return builder.toString();
	}

}

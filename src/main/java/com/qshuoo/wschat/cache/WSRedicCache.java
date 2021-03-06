package com.qshuoo.wschat.cache;

/**
 * redis缓存接口
 * @author qiaoyongshuo
 *
 */
public interface WSRedicCache {
	
	/**
	 * 获取value
	 * @param key
	 * @return
	 */
	public String getValue(String key);
	
	/**
	 * 设置key and value
	 * @param key
	 * @param value
	 */
	public void setKAndV(String key, String value);
	
	/**
	 * 设置 k and v 有时间限制
	 * @param key
	 * @param value
	 * @param time
	 */
	public void setKAndV(String key, String value, Long offset);
	
	/**
	 * value 自增
	 * @param key
	 * @param value
	 * @return
	 */
	public Long increment(String key, Long value);
	
	/**
	 * 是否存在key
	 * @param key
	 * @return
	 */
	public boolean existsKey(String key);
	
	public void delKey(String key);

}

package com.qshuoo.wschat.dao;

import java.util.List;
import java.util.Map;


/**
 * 用户dao
 * @author qiaoyongshuo
 *
 */
public interface UserDao {
	
	/**
	 * 通过id查询用户
	 * @return
	 */
	public List<Map<String, Object>> getUserById(Integer account);

}

package com.qshuoo.wschat.dao;

import java.util.List;
import java.util.Map;

import com.qshuoo.wschat.pojo.User;


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
	public List<Map<String, Object>> getUserById(Long account);

	/**
	 * 保存用户
	 * @param user 用户信息
	 * @return
	 * @throws Exception 
	 */
	public int saveUser(User user) throws Exception;
	
	/**
	 * 获取当前最大值的账号
	 * @return
	 */
	public List<Map<String, Object>> getMaxAccount();
	
	/**
	 * 获取好友列表
	 * @param account 用户账号
	 * @return
	 */
	public List<Map<String, Object>> listFriends(Long account);

}

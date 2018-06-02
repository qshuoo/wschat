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
	 * 通过id获取群组
	 * @param account 账户
	 * @return 群组信息
	 */
	public List<Map<String, Object>> getGroupById(Long account);

	/**
	 * 更新用户信息
	 * @param user 更新后的用户信息
	 * @param elem 更新的属性列
	 * @return
	 * @throws Exception 
	 */
	public int updateUser(User user, String[] elem, String[] condi) throws Exception;

}

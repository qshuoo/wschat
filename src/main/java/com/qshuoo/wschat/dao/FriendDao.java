package com.qshuoo.wschat.dao;

import java.util.List;
import java.util.Map;

import com.qshuoo.wschat.pojo.NewFriend;

/**
 * 好友DAO
 * @author qshuoo
 *
 */
public interface FriendDao {
	
	/**
	 * 获取好友列表
	 * @param account 用户账号
	 * @return
	 */
	public List<Map<String, Object>> listFriends(Long account);

	/**
	 * 
	 * @param newFriend 添加信息
	 * @return
	 * @throws Exception 
	 */
	public int saveFriend(NewFriend newFriend) throws Exception;
}

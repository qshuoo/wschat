package com.qshuoo.wschat.dao;

import java.util.List;
import java.util.Map;

import com.qshuoo.wschat.pojo.FriendRelation;
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
	public int saveNewFriend(NewFriend newFriend) throws Exception;

	/**
	 * 获取对应状态的好友添加纪录
	 * @param aimUid 目标账号
	 * @param applyUid 申请账号
	 * @param i 申请状态
	 * @return
	 */
	public List<Map<String, Object>> getNewFriend(Long aimUid, Long applyUid, int i);

	/**
	 * 获取好友申请列表
	 * @param account
	 * @return
	 */
	public List<Map<String, Object>> listNewFriend(Long account);

	/**
	 * 更改申请状态
	 * @param aimUid 目标账号
	 * @param applyUid 申请账号
	 * @param i 申请状态
	 * @return
	 */
	public int updateStateByUids(Long aimUid, Long applyUid, int i);
	
	/**
	 * 添加好友关系
	 * @param fr 好友关系信息
	 * @return
	 * @throws Exception 
	 */
	public int saveFriendRelation(FriendRelation fr) throws Exception;
}

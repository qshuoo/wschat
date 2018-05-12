package com.qshuoo.wschat.service;

import com.qshuoo.wschat.utils.WSChatResult;

/**
 * 好友服务
 * @author qshuoo
 *
 */
public interface FriendService {

	/**
	 * 获取好友列表
	 * @param account 账户
	 * @return
	 * @throws Exception 
	 */
	public WSChatResult listFriends(Long account) throws Exception;
	
	/**
	 * 添加好友
	 * @param applyUid 申请用户
	 * @param aimUid 目标用户
	 * @param msg 申请信息
	 * @return
	 * @throws Exception 
	 */
	public int addFriend(Long applyUid, Long aimUid, String msg) throws Exception;
}

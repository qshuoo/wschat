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
	public WSChatResult listFriends(Long account);
	
	/**
	 * 添加好友
	 * @param applyUid 申请用户
	 * @param aimUid 目标用户
	 * @param msg 申请信息
	 * @return
	 * @throws Exception 
	 */
	public WSChatResult addFriend(Long applyUid, Long aimUid, String msg) throws Exception;
	
	/**
	 * 获取好友申请列表
	 * @param account 账号
	 * @return
	 */
	public WSChatResult listNewFriends(Long account);

	/**
	 * 同意好友申请
	 * @param aimUid 目标用户
	 * @param applyUid 申请用户
	 * @return
	 * @throws Exception 
	 */
	public WSChatResult agreeFriendApply(Long aimUid, Long applyUid) throws Exception;

	/**
	 * 拒绝好友申请
	 * @param aimUid 目标用户
	 * @param applyUid 申请用户
	 * @return
	 * @throws Exception 
	 */
	public WSChatResult refuseFriendApply(Long aimUid, Long applyUid) throws Exception;
}

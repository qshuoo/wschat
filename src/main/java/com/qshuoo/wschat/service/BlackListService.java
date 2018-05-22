package com.qshuoo.wschat.service;

import com.qshuoo.wschat.utils.WSChatResult;

/**
 * 黑名单service
 * @author qshuoo
 *
 */
public interface BlackListService {

	/**
	 * 获取黑名单列表
	 * @param account 用户账号
	 * @return
	 */
	WSChatResult listBlackList(Long account);

	/**
	 * 添加至黑名单
	 * @param applyUid 申请账号
	 * @param aimUid 目标账号
	 * @return
	 * @throws Exception 
	 */
	WSChatResult addToBlackList(Long applyUid, Long aimUid) throws Exception;
	
	/**
	 * 从黑名单恢复
	 * @param applyUid 申请账号
	 * @param aimUid 目标账号
	 * @return
	 * @throws Exception 
	 */
	WSChatResult delFromBlackList(Long applyUid, Long aimUid) throws Exception;
	
	/**
	 * 是否存在黑名单关系
	 * @param uid1
	 * @param uid2
	 * @return
	 */
	boolean isInBlackList(Long uid1, Long uid2);
}

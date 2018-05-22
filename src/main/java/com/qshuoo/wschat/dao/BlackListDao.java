package com.qshuoo.wschat.dao;

import java.util.List;
import java.util.Map;

import com.qshuoo.wschat.pojo.BlackList;

/**
 * 黑名单DAO
 * @author qshuoo
 *
 */
public interface BlackListDao {

	/**
	 * 获取用户黑名单
	 * @param account 用户账号
	 * @return
	 */
	public List<Map<String, Object>> getBlackList(Long account);
	
	/**
	 * 添加至黑名单
	 * @param bList 黑名单信息 
	 * @return
	 * @throws Exception 
	 */
	public int saveBlackList(BlackList bList) throws Exception;
	
	/**
	 * 更改黑名单状态
	 * @param applyUid 申请账号
	 * @param aimUid 目标账号
	 * @param i 状态( 1:正常, 0:解除黑名单)
	 * @return
	 */
	public int updateBLStateByUid(Long applyUid, Long aimUid, int i);

}

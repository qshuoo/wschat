package com.qshuoo.wschat.dao;

import java.util.List;
import java.util.Map;

import com.qshuoo.wschat.pojo.Message;

/**
 * 消息dao层
 * @author qiaoyongshuo
 *
 */
public interface MessageDao {
	
	/**
	 * 保存消息
	 * @param msg
	 * @return
	 * @throws Exception 
	 */
	public int saveMessage(Message msg) throws Exception;
	
	/**
	 * 根据toUid读取离线消息
	 * @param account
	 * @return
	 */
	public List<Map<String, Object>> listMsgsByToUid(Long account);

	/**
	 * 根据用户id更新离线消息
	 * @param account
	 * @return
	 */
	public int updateMsgByToUid(Long account);

}

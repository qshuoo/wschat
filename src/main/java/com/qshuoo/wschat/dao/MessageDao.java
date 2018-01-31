package com.qshuoo.wschat.dao;

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

}

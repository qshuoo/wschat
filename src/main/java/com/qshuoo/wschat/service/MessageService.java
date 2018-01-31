package com.qshuoo.wschat.service;

import com.qshuoo.wschat.pojo.Message;
import com.qshuoo.wschat.utils.WSChatResult;

/**
 * 聊天消息服务接口
 * @author qiaoyongshuo
 *
 */
public interface MessageService {
	
	/**
	 * 保存msg
	 * @param msg
	 * @return
	 * @throws Exception 
	 */
	public WSChatResult saveMessage(Message msg) throws Exception;
}

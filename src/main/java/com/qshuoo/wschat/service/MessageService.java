package com.qshuoo.wschat.service;

import java.util.List;

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
	 * @param msg 消息
	 * @return
	 * @throws Exception 
	 */
	public WSChatResult saveMessage(Message msg) throws Exception;
	
	/**
	 * 读取离线消息
	 * @param account toUser账号
	 * @return
	 * @throws Exception 
	 */
	public List<String> getOffLineMsgs(Long account) throws Exception;

	/**
	 * 更新离线消息
	 * @param account 
	 */
	public void updateOfflineMsgs(Long account);
}

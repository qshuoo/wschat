package com.qshuoo.wschat.utils;

import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.qshuoo.wschat.pojo.Message;
import com.qshuoo.wschat.pojo.MsgHelper;

/**
 * 消息工具
 * @author qiaoyongshuo
 *
 */
public class MsgUtils {
	
	/**
	 * 格式消息
	 * @param id
	 * @param msg
	 * @return
	 */
	private static String getSendMsg(Long id, String msg) {
		MsgHelper helper = new MsgHelper();
		helper.setFromUid(id);
		helper.setMsg(msg);
		return JSON.toJSONString(helper);
	}
	
	/**
	 * 格式离线消息
	 * @param map
	 * @retur
	 */
	public static String getOffMsgs(Map<String, Object> map) {
		// 逻辑 fromUid msg 不为空
		Long id = new Long(map.get("fromuid").toString()); 
		String msg = map.get("msg").toString();
		return getSendMsg(id, msg);
	}
	
	/**
	 * 格式在线消息
	 * @param message
	 * @return
	 */
	public static String getOnMsgs(Message message) {
		Long id = message.getFromUid();
		String msg = message.getMsg();
		return getSendMsg(id, msg);
	}
	

}

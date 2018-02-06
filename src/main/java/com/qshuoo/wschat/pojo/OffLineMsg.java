package com.qshuoo.wschat.pojo;

import java.util.List;
import java.util.Map;

/**
 * 离线消息 Help
 * @author qiaoyongshuo
 *
 */
public class OffLineMsg {
	
	private Map<Long, List<Message>> msgMap; // 发送方消息

	public Map<Long, List<Message>> getMsgMap() {
		return msgMap;
	}

	public void setMsgMap(Map<Long, List<Message>> msgMap) {
		this.msgMap = msgMap;
	}
	
	
	/**
	public void setMsgs(List<Map<String, Object>> list) throws Exception {
		Map<Long, List<Message>> msgMap = new HashMap<Long, List<Message>>();
		for (Map<String, Object> map : list) {
			Message msg = (Message) PojoUtil.map2Object(map, Message.class);
			Long key = msg.getFromUid(); // fromUid逻辑不为空
			if (msgMap.containsKey(key)) { // 存在 list add
				msgMap.get(key).add(msg);
			} else { // 不存在 map put
				List<Message> msgs = new ArrayList<>();
				msgs.add(msg);
				msgMap.put(key, msgs);
			}
		}
		this.msgMap = msgMap;
	}
	*/
	

}

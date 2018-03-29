package com.qshuoo.wschat.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.qshuoo.wschat.dao.MessageDao;
import com.qshuoo.wschat.dao.impl.MessageDaoImpl;
import com.qshuoo.wschat.pojo.Message;
import com.qshuoo.wschat.service.MessageService;
import com.qshuoo.wschat.utils.MsgUtils;
import com.qshuoo.wschat.utils.WSChatResult;

@Service("MessageService")
@Transactional
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageDao dao = new MessageDaoImpl();
	
	@Override
	public WSChatResult saveMessage(Message msg) throws Exception {
		msg.setStatus(false); // 默认未读
		msg.setGroup(false); // TODO 暂不讨论 群组 
		int row = dao.saveMessage(msg);
		return row == 1 ? WSChatResult.ok() : WSChatResult.notOk();
	}

	@Override
	public List<String> getOffLineMsgs(Long account) throws Exception {
		List<Map<String, Object>> msgs = dao.listMsgsByToUid(account);
		
		// 无离线消息
		if (CollectionUtils.isEmpty(msgs)) {
			return null;
		}
		
		// 返回离线消息
		List<String> offMsgs = new ArrayList<String>();
		msgs.forEach(map -> {offMsgs.add(MsgUtils.getOffMsgs(map));});
		return offMsgs;
	}

	@Override
	public void updateOfflineMsgs(Long account) {
		dao.updateMsgByToUid(account);
	}

}

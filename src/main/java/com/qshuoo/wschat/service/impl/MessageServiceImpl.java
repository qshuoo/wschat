package com.qshuoo.wschat.service.impl;

import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qshuoo.wschat.dao.MessageDao;
import com.qshuoo.wschat.dao.impl.MessageDaoImpl;
import com.qshuoo.wschat.pojo.Message;
import com.qshuoo.wschat.service.MessageService;
import com.qshuoo.wschat.utils.WSChatResult;

@Service("MessageService")
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageDao dao = new MessageDaoImpl();
	
	@Override
	public WSChatResult saveMessage(Message msg) throws Exception {
		msg.setStatus(false);
		msg.setGroup(false);
		int row = dao.saveMessage(msg);
		return row == 1 ? WSChatResult.ok() : WSChatResult.notOk();
	}

	@Override
	public WSChatResult getOffLineMsgs(Long account) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> msgs = dao.listMsgsByToUid(account);
		for (Map<String, Object> map : msgs) {
			
		}
		if (CollectionUtils.isEmpty(msgs)) {
			return WSChatResult.ok();
		}
		
		return null;
	}

}

package com.qshuoo.wschat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}

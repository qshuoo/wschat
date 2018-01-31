package com.qshuoo.wschat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qshuoo.wschat.dao.MessageDao;
import com.qshuoo.wschat.pojo.Message;
import com.qshuoo.wschat.service.MessageService;
import com.qshuoo.wschat.utils.WSChatResult;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageDao dao;
	
	@Override
	public WSChatResult saveMessage(Message msg) throws Exception {
		int row = dao.saveMessage(msg);
		if (row != 1) {
			return WSChatResult.notOk();
		}
		
		return WSChatResult.ok();
	}

}

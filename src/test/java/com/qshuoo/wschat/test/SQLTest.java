package com.qshuoo.wschat.test;

import org.junit.Test;

import com.qshuoo.wschat.pojo.Message;
import com.qshuoo.wschat.utils.sqlUtils;

public class SQLTest {
	
	@Test
	public void insertTest() {
		Message message = new Message();
		message.setFromUid(99999l);
		message.setToUid(100000l);
		message.setMsg("hi");
		String res = null;
		try {
			res = sqlUtils.generateInsertSQL(message, Message.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(res);
	}

}

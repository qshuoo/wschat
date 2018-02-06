package com.qshuoo.wschat.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.qshuoo.wschat.dao.MessageDao;
import com.qshuoo.wschat.pojo.Message;
import com.qshuoo.wschat.utils.SqlUtils;

@Repository
public class MessageDaoImpl implements MessageDao {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int saveMessage(Message msg) throws Exception {
		String sql = SqlUtils.generateInsertSQL(msg, Message.class);
		int row = jdbcTemplate.update(sql);
		return row;
	}

}

package com.qshuoo.wschat.dao.impl;

import java.util.List;
import java.util.Map;

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

	@Override
	public List<Map<String, Object>> listMsgsByToUid(Long account) {
		String sql = "SELECT * FROM message WHERE toUid = ? AND status = 0";
		List<Map<String, Object>> msgs = jdbcTemplate.queryForList(sql, account);
		return msgs;
	}

	@Override
	public int updateMsgByToUid(Long account) {
		String sql = "UPDATE message SET status = 1 WHERE toUid = ?";
		int row = jdbcTemplate.update(sql, account);
		return row;
	}

}

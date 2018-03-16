package com.qshuoo.wschat.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.qshuoo.wschat.dao.UserDao;
import com.qshuoo.wschat.pojo.User;
import com.qshuoo.wschat.utils.SqlUtils;

/**
 * 用户Dao
 * @author qiaoyongshuo
 *
 */
@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Map<String, Object>> getUserById(Long account) {
		String sql = "SELECT * FROM user WHERE uid = ?";
		List<Map<String, Object>> users = jdbcTemplate.queryForList(sql, new Object[] {account});
		return users;
	}

	@Override
	public int saveUser(User user) throws Exception {
		String sql = SqlUtils.generateInsertSQL(user, User.class);
		int cnt = jdbcTemplate.update(sql);
		return cnt;
	}

	@Override
	public List<Map<String, Object>> getMaxAccount() {
		String sql = "SELECT MAX(uid) AS ID FROM user";
		List<Map<String, Object>> id = jdbcTemplate.queryForList(sql);
		return id;
	}

}

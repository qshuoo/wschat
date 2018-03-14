package com.qshuoo.wschat.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.qshuoo.wschat.dao.UserDao;
import com.qshuoo.wschat.pojo.User;

/**
 * 用户Dao
 * @author qiaoyongshuo
 *
 */
@Repository
public class UserDaoImpl implements UserDao {
	
	/**
	 * 根据账户查询用户
	 * @param account
	 * @return
	 */
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
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}

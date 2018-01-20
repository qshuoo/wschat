package com.qshuoo.wschat.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.qshuoo.wschat.pojo.User;

/**
 * 用户Dao
 * @author qiaoyongshuo
 *
 */
@Repository
public class UserDao {
	
	/**
	 * 根据账户查询用户
	 * @param account
	 * @return
	 */
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public User getUserById(Integer account) {
		String sql = "SELECT * FROM user WHERE uid = ?";
		User user = jdbcTemplate.queryForObject(sql, User.class, account);
		return user;
	}

}

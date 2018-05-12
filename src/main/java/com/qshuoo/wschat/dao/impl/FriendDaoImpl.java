package com.qshuoo.wschat.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.qshuoo.wschat.dao.FriendDao;
import com.qshuoo.wschat.pojo.NewFriend;
import com.qshuoo.wschat.utils.SqlUtils;

/**
 * 
 * @author qshuoo
 *
 */

@Repository
public class FriendDaoImpl implements FriendDao{
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate; 
	
	@Override
	public List<Map<String, Object>> listFriends(Long account) {
		String sql = "SELECT a.uid, a.uname, a.img FROM user a JOIN friend_relation b ON a.uid = b.uid2 WHERE b.uid1 = ? AND b.state = 1;";
		List<Map<String, Object>> friends = jdbcTemplate.queryForList(sql, new Object[] {account});
		return friends;
	}

	@Override
	public int saveFriend(NewFriend newFriend) throws Exception {
		String sql = SqlUtils.generateInsertSQL(newFriend, NewFriend.class);
		int row = jdbcTemplate.update(sql);
		return row;
	}
}

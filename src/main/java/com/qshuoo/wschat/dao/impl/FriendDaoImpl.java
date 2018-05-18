package com.qshuoo.wschat.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.qshuoo.wschat.dao.FriendDao;
import com.qshuoo.wschat.pojo.FriendRelation;
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
		String sql = "SELECT a.uid, a.uname, a.img FROM user a JOIN friendrelation b ON a.uid = b.uid2 WHERE b.uid1 = ? AND b.state = 1;";
		List<Map<String, Object>> friends = jdbcTemplate.queryForList(sql, new Object[] {account});
		return friends;
	}

	@Override
	public int saveNewFriend(NewFriend newFriend) throws Exception {
		String sql = SqlUtils.generateInsertSQL(newFriend, NewFriend.class);
		int row = jdbcTemplate.update(sql);
		return row;
	}

	@Override
	public List<Map<String, Object>> getNewFriend(Long aimUid, Long applyUid, int i) {
		String sql = "SELECT * FROM newfriend WHERE uid1 = ? AND uid2 = ? AND state = ?;";
		List<Map<String, Object>> res = jdbcTemplate.queryForList(sql, aimUid, applyUid, i);
		return res;
	}

	@Override
	public List<Map<String, Object>> listNewFriend(Long account) {
		String sql = "SELECT a.uid, a.uname, a.img, b.msg FROM user a, newfriend b WHERE a.uid = b.uid2 AND b.uid1 = ? AND b.state = 0";
		List<Map<String, Object>> res = jdbcTemplate.queryForList(sql, account);
		return res;
	}

	@Override
	public int updateStateByUids(Long aimUid, Long applyUid, int i) {
		String sql = "UPDATE newfriend a SET a.state = ? WHERE uid1 = ? AND uid2 = ? AND state = 0";
		int row = jdbcTemplate.update(sql, i, aimUid, applyUid);
		return row;
	}

	@Override
	public int saveFriendRelation(FriendRelation fr) throws Exception {
		String sql = SqlUtils.generateInsertSQL(fr, FriendRelation.class);
		int row = jdbcTemplate.update(sql);
		return row;
	}
}

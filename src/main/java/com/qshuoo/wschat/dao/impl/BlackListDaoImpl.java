package com.qshuoo.wschat.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.qshuoo.wschat.dao.BlackListDao;
import com.qshuoo.wschat.pojo.BlackList;
import com.qshuoo.wschat.utils.SqlUtils;

/**
 * 
 * @author qshuoo
 *
 */
@Repository
public class BlackListDaoImpl implements BlackListDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Map<String, Object>> listBlackList(Long account) {
		String sql = "SELECT a.uid, a.uname, a.img FROM user a JOIN blacklist b ON a.uid = b.uid2 WHERE b.uid1 = ? AND b.state = 1;";
		List<Map<String, Object>> res = jdbcTemplate.queryForList(sql, account);
		return res;
	}

	@Override
	public int saveBlackList(BlackList bList) throws Exception {
		String sql = SqlUtils.generateInsertSQL(bList, BlackList.class);
		int row = jdbcTemplate.update(sql);
		return row;
	}

	@Override
	public int updateBLStateByUid(Long applyUid, Long aimUid, int i) {
		String sql = "UPDATE blacklist a SET state = ? WHERE uid1 = ? AND uid2 = ?;";
		int row = jdbcTemplate.update(sql, i, applyUid, aimUid);
		return row;
	}

	@Override
	public List<Map<String, Object>> getBLStateByIds(Long uid1, Long uid2) {
		String sql = "SELECT a.state FROM blacklist a WHERE a.uid1 = ? AND a.uid2 = ?;";
		List<Map<String, Object>> res = jdbcTemplate.queryForList(sql, uid1, uid2);
		return res;
	}

}

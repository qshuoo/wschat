package com.qshuoo.wschat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qshuoo.wschat.dao.BlackListDao;
import com.qshuoo.wschat.dao.FriendDao;
import com.qshuoo.wschat.pojo.BlackList;
import com.qshuoo.wschat.service.BlackListService;
import com.qshuoo.wschat.utils.WSChatResult;

/**
 * 
 * @author qshuoo
 *
 */
@Service
@Transactional
public class BlackListServiceImpl implements BlackListService{

	@Autowired
	private BlackListDao blackListDao;
	
	@Autowired
	private FriendDao friendDao;
	
	@Override
	public WSChatResult getBlackList(Long account) {
		List<Map<String, Object>> lists = blackListDao.getBlackList(account);
		return WSChatResult.ok(lists);
	}

	@Override
	public WSChatResult addToBlackList(Long applyUid, Long aimUid) throws Exception {
		// 添加至黑名单
		BlackList bList = new BlackList();
		bList.setUid1(applyUid);
		bList.setUid2(aimUid);
		bList.setState(1);
		int row = blackListDao.saveBlackList(bList);
		if (row != 1) {
			throw new Exception("添加失败");
		}
		
		// 更改好友状态
		row = friendDao.updateFRStateByUids(applyUid, aimUid, 3);
		if (row != 1) {
			throw new Exception("添加失败");
		}
		row = friendDao.updateFRStateByUids(aimUid, applyUid, 4);
		if (row != 1) {
			throw new Exception("添加失败");
		}
		return WSChatResult.ok();
	}

}

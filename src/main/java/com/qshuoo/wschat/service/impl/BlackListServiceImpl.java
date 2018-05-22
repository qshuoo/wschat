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
@Service("BlackListService")
@Transactional
public class BlackListServiceImpl implements BlackListService{

	@Autowired
	private BlackListDao blackListDao;
	
	@Autowired
	private FriendDao friendDao;
	
	@Override
	public WSChatResult listBlackList(Long account) {
		List<Map<String, Object>> lists = blackListDao.listBlackList(account);
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
		int row1 = friendDao.updateFRStateByUids(applyUid, aimUid, 3);
		
		// 更新好友申请状态
		int row2 = friendDao.updateNewFStateByUids(applyUid, aimUid, 3);
		if (row1 + row2 < 1) {
			throw new Exception("添加失败");
		}
		return WSChatResult.ok();
	}

	@Override
	public WSChatResult delFromBlackList(Long applyUid, Long aimUid) throws Exception {
		// 更新黑名单状态
		int row = blackListDao.updateBLStateByUid(applyUid, aimUid, 0);
		if (row != 1) {
			throw new Exception();
		}
		
		// 更新好友状态 对方拉黑己方 -> 不处理 , 未拉黑 -> 更新与对方状态一致
		List<Map<String, Object>> res = friendDao.getFRStateByIds(aimUid, applyUid);
		if(res.isEmpty()) {
			return WSChatResult.ok(0);
		}
		int state = (int) res.get(0).get("state");
		if (state != 3) {
			row = friendDao.updateFRStateByUids(applyUid, aimUid, state);
			if (row != 1) {
				throw new Exception();
			}
		}
		
		return WSChatResult.ok(state);
	}

	@Override
	public boolean isInBlackList(Long uid1, Long uid2) {
		List<Map<String, Object>> res = blackListDao.getBLStateByIds(uid1, uid2);
		return !res.isEmpty() && (int) res.get(0).get("state") == 1;
	}

}

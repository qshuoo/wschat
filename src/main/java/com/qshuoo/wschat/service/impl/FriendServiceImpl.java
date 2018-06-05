package com.qshuoo.wschat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qshuoo.wschat.dao.BlackListDao;
import com.qshuoo.wschat.dao.FriendDao;
import com.qshuoo.wschat.pojo.FriendRelation;
import com.qshuoo.wschat.pojo.NewFriend;
import com.qshuoo.wschat.service.FriendService;
import com.qshuoo.wschat.utils.WSChatResult;

@Service("FriendService")
@Transactional
public class FriendServiceImpl implements FriendService{
	
	@Autowired
	private FriendDao friendDao;
	
	@Autowired
	private BlackListDao blistDao;
	
	@Override
	public WSChatResult listFriends(Long account) {
		List<Map<String, Object>> friends = friendDao.listFriends(account);
		return WSChatResult.ok(friends);
	}
	
	@Override
	public WSChatResult addFriend(Long applyUid, Long aimUid, String msg) throws Exception {
		// 在黑名单中 -> 返回
		List<Map<String, Object>> state = blistDao.getBLStateByIds(aimUid, applyUid);
		if (!state.isEmpty() && (int) state.get(0).get("state") == 1) {
			return WSChatResult.ok();
		}
		
		// 添加消息已发送 -> 返回
		List<?> res = friendDao.getNewFriend(aimUid, applyUid, 0);
		if (!res.isEmpty()) {
			return WSChatResult.notOk("您已发送过此申请，请勿重复申请");
		}
		
		// 未发送 -> 发送添加消息
		NewFriend newFriend = new NewFriend();
		newFriend.setUid1(aimUid);
		newFriend.setUid2(applyUid);
		newFriend.setMsg(msg);
		newFriend.setState(0);
		int row = friendDao.saveNewFriend(newFriend);
		if (row < 1) {
			throw new Exception("添加失败");
		}
		return WSChatResult.ok();
	}

	@Override
	public WSChatResult listNewFriends(Long account) {
		List<Map<String, Object>> res = friendDao.listNewFriend(account);
		return WSChatResult.ok(res);
	}

	@Override
	public WSChatResult agreeFriendApply(Long aimUid, Long applyUid) throws Exception {
		// 变更申请状态
		int row = friendDao.updateNewFStateByUids(aimUid, applyUid, 1);
		if (row != 1) {
			throw new Exception("添加失败");
		}
		// 好友关系已存在 -> 更新好友状态
		List<Map<String, Object>> res = friendDao.getFRStateByIds(aimUid, applyUid);
		if (!res.isEmpty()) {
			friendDao.updateFRStateByUids(applyUid, aimUid, 1);
			friendDao.updateFRStateByUids(aimUid, applyUid, 1);
			return WSChatResult.ok();
		}
		// 添加好友关系
		FriendRelation fr = new FriendRelation();
		fr.setUid1(applyUid);
		fr.setUid2(aimUid);
		fr.setState(1);
		row = friendDao.saveFriendRelation(fr);
		if (row != 1) {
			throw new Exception("添加失败");
		}
		fr.setUid1(aimUid);
		fr.setUid2(applyUid);
		row = friendDao.saveFriendRelation(fr);
		if (row != 1) {
			throw new Exception("添加失败");
		}
		return WSChatResult.ok();
	}

	@Override
	public WSChatResult refuseFriendApply(Long aimUid, Long applyUid) throws Exception {
		// 变更申请状态
		int row = friendDao.updateNewFStateByUids(aimUid, applyUid, 2);
		if (row != 1) {
			throw new Exception("拒绝失败,请刷新重试");
		}
		return WSChatResult.ok();
	}

	@Override
	public WSChatResult delFriend(Long applyUid, Long aimUid) throws Exception {
		// 变更好友关系状态
		int row = friendDao.updateFRStateByUids(applyUid, aimUid, 2);
		if (row != 1) {
			throw new Exception("删除失败");
		}
		
		// 变更对方好友状态 对方未拉黑 更新状态为删除
		List<Map<String, Object>> res = friendDao.getFRStateByIds(aimUid, applyUid);
		int state = (int) res.get(0).get("state");
		if (state == 1) {
			row = friendDao.updateFRStateByUids(aimUid, applyUid, 2);
			if (row > 1) {
				throw new Exception("删除失败");
			}
		}
		return WSChatResult.ok();
	}

	@Override
	public boolean isNotFriendRelation(Long uid1, Long uid2) {
		List<Map<String, Object>> state = friendDao.getFRStateByIds(uid1, uid2);
		return !state.isEmpty() && (int) state.get(0).get("state") == 2;
	}

	@Override
	public WSChatResult updateFreindRemark(Long account, Long friendId, String remark) {
		friendDao.updateFriendRemark(account, friendId, remark);
		return WSChatResult.ok();
	}

}

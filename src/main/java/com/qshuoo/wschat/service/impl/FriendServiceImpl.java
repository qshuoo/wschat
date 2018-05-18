package com.qshuoo.wschat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qshuoo.wschat.dao.FriendDao;
import com.qshuoo.wschat.pojo.FriendRelation;
import com.qshuoo.wschat.pojo.NewFriend;
import com.qshuoo.wschat.service.FriendService;
import com.qshuoo.wschat.utils.WSChatResult;

@Service
@Transactional
public class FriendServiceImpl implements FriendService{
	
	@Autowired
	private FriendDao friendDao;
	
	@Override
	public WSChatResult listFriends(Long account) {
		List<Map<String, Object>> friends = friendDao.listFriends(account);
		/*List<Friend> friendList = new ArrayList<Friend>();
		for (Map<String, Object> map : friends) {
			friendList.add((Friend) PojoUtil.map2Object(map, Friend.class));
		}*/
		return WSChatResult.ok(friends);
	}
	
	@Override
	public WSChatResult addFriend(Long applyUid, Long aimUid, String msg) throws Exception {
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
		int row = friendDao.updateStateByUids(aimUid, applyUid, 1);
		if (row != 1) {
			throw new Exception("添加失败");
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
		int row = friendDao.updateStateByUids(aimUid, applyUid, 1);
		if (row != 1) {
			throw new Exception("拒绝失败,请刷新重试");
		}
		return WSChatResult.ok();
	}

}

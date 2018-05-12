package com.qshuoo.wschat.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qshuoo.wschat.dao.FriendDao;
import com.qshuoo.wschat.pojo.Friend;
import com.qshuoo.wschat.pojo.NewFriend;
import com.qshuoo.wschat.service.FriendService;
import com.qshuoo.wschat.utils.PojoUtil;
import com.qshuoo.wschat.utils.WSChatResult;

@Service
public class FriendServiceImpl implements FriendService{
	
	@Autowired
	private FriendDao friendDao;
	
	@Override
	public WSChatResult listFriends(Long account) throws Exception {
		List<Map<String, Object>> friends = friendDao.listFriends(account);
		List<Friend> friendList = new ArrayList<Friend>();
		for (Map<String, Object> map : friends) {
			friendList.add((Friend) PojoUtil.map2Object(map, Friend.class));
		}
		return WSChatResult.ok(friendList);
	}
	
	@Override
	public int addFriend(Long applyUid, Long aimUid, String msg) throws Exception {
		// TODO 添加消息已发送 -> 返回
		// 未发送 -> 发送添加消息
		NewFriend newFriend = new NewFriend();
		newFriend.setUid1(aimUid);
		newFriend.setUid2(applyUid);
		newFriend.setMsg(msg);
		newFriend.setState(0);
		int row = friendDao.saveFriend(newFriend);
		if (row < 1) {
			throw new Exception("添加失败");
		}
		return row;
	}

}

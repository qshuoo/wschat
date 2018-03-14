package com.qshuoo.wschat.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qshuoo.wschat.dao.UserDao;
import com.qshuoo.wschat.pojo.User;
import com.qshuoo.wschat.service.UserService;
import com.qshuoo.wschat.utils.PojoUtil;
import com.qshuoo.wschat.utils.WSChatResult;

/**
 * 
 * @author qiaoyongshuo
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public WSChatResult checkLoginUser(Long account, String password) throws Exception {
		List<Map<String, Object>> users = userDao.getUserById(account);
		if (!CollectionUtils.isEmpty(users) && users.get(0).get("pwd").equals(password)) {
			Object user = PojoUtil.map2Object(users.get(0), User.class);
			return WSChatResult.ok(user);
		}
		return WSChatResult.notOk("用户名或密码错误");
	}

	@Override
	public WSChatResult registerUser(String username, String password, String checkinfo, String checktype) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUname(username);
		user.setPwd(password);
		if ("email".equals(checktype)) {
			user.setEmail(checkinfo);
		} else {
			user.setPhone(checkinfo);
		}
		User doneUser = userDao.saveUser(user);
		return WSChatResult.ok(doneUser);
	}
	

}

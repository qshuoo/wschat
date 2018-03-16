package com.qshuoo.wschat.service.impl;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.qshuoo.wschat.cache.WSRedicCache;
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
@Transactional
public class UserServiceImpl implements UserService{

	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private WSRedicCache redisCache;
	
	@Value("${redis.key.account}")
	private String accountKey;
	
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
	public WSChatResult registerUser(String username, String password, String checkinfo, String checktype) throws Exception {
		// 自增1生成账号后插入 , 若服务分离考虑起始值与递增梯度的配置
		Long id = redisCache.increment(accountKey, 1L); 
		logger.info("AUTO GENERATE ACCOUNT {}", id);
		User user = new User();
		user.setUid(id);
		user.setUname(username);
		user.setPwd(password);
		if ("email".equals(checktype)) {
			user.setEmail(checkinfo);
		} else {
			user.setPhone(checkinfo);
		}
		int cnt = userDao.saveUser(user);
		if (cnt != 1) {
			throw new Exception("添加用户失败");
		}
		
		logger.info("REG SUCCESS", id);
		return WSChatResult.ok(id);
	}
	

}

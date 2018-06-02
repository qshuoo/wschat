package com.qshuoo.wschat.service.impl;


import java.util.ArrayList;
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
	
	// 用户展示的信息
	private static final String[] USER_SHOW_INFO = {"uid", "uname", "img", "email", "phone", "signature"}; 
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private WSRedicCache redisCache;
	
	@Value("${redis.key.account}")
	private String accountKey;
	
	@Value("${redis.key.match}")
	private String matchKey;
	
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

	@Override
	public WSChatResult search(Long account, String type) throws Exception {
		List<Map<String, Object>> res = new ArrayList<>();
		if ("user".equals(type)) {
			res = userDao.getUserById(account);
			if (CollectionUtils.isEmpty(res)) {
				return WSChatResult.ok();
			}
			Object resObj = PojoUtil.map2Object(res.get(0), User.class, USER_SHOW_INFO);
			return WSChatResult.ok(resObj);
		}
		// TODO 查找群组
		return WSChatResult.ok();
	}

	@Override
	public WSChatResult match(Long account) {
		logger.info("user {} is matching chat", account);
		// 1 查找redis中账号 存在 -> 返回
		if (redisCache.existsKey(matchKey)) { // 存在key
			synchronized (this) {
				if (redisCache.existsKey(matchKey)) {
					String value = redisCache.getValue(matchKey).trim();
					redisCache.delKey(matchKey);
					logger.info("user {} matching {} is success", account, value);
					return WSChatResult.ok(value);
				}
			}
		}
		
		// 不存在 -> 加入 时间 55s
		redisCache.setKAndV(matchKey, account.toString(), 55L);
		return WSChatResult.ok();
	}

	@Override
	public WSChatResult updateUserInfo(User user, String[] elem, String[] condi) throws Exception {
		int row = userDao.updateUser(user, elem, condi);
		if (row <= 0) {
			throw new Exception();
		}
		return WSChatResult.ok();
	}

	@Override
	public WSChatResult findPwd(Long account, String checkinfo) {
		List<Map<String, Object>> user = userDao.getUserById(account);
		if (CollectionUtils.isEmpty(user)) {
			return WSChatResult.notOk("用户不存在");
		}
		if (!checkinfo.equals(user.get(0).get("email"))) {
			return WSChatResult.notOk("邮箱输入错误");
		}
		return WSChatResult.ok();
	}

	@Override
	public WSChatResult setNewPassword(Long account, String password) throws Exception {
		User user = new User();
		user.setUid(account);
		user.setPwd(password);
		String[] elem = {"pwd"};
		String[] condi = {"uid"};
		userDao.updateUser(user, condi, elem);
		return WSChatResult.ok();
	}

}

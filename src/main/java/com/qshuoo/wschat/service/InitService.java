package com.qshuoo.wschat.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qshuoo.wschat.cache.WSRedicCache;
import com.qshuoo.wschat.dao.UserDao;

/**
 * 初始化执行service
 * @author qiaoyongshuo
 *
 */
@Service
public class InitService implements InitializingBean{

	private Logger logger = LoggerFactory.getLogger(InitService.class);
	
	@Value("${redis.key.account}")
	private String accountKey;
	
	@Autowired
	private WSRedicCache redisCache;
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 加载缓存生成账号
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if(!redisCache.existsKey(accountKey)) {
			List<Map<String,Object>> maxAccount = userDao.getMaxAccount();
			if (CollectionUtils.isEmpty(maxAccount)) {
				logger.error("初始化账号生成缓存失败");
				return;
			}
			String id = maxAccount.get(0).get("ID").toString();
			redisCache.setKAndV(accountKey, id);
		}
		logger.info("初始化账号生成缓存成功");
	}

}

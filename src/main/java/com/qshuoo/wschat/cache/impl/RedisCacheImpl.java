package com.qshuoo.wschat.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.qshuoo.wschat.cache.WSRedicCache;

@Component
public class RedisCacheImpl implements WSRedicCache {

	@Autowired
	private RedisTemplate<String, String> template;
	
	@Override
	public String getValue(String key) {
		return template.opsForValue().get(key);
	}

	@Override
	public void setKAndV(String key, String value) {
		template.opsForValue().set(key, value);

	}

	@Override
	public Long increment(String key, Long value) {
		return template.opsForValue().increment(key, value);
	}

	@Override
	public boolean existsKey(String key) {
		return template.hasKey(key);
	}

}

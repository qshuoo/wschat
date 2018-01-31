package com.qshuoo.wschat.utils;

import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

import org.springframework.stereotype.Component;


/**
 * session池
 * @author qiaoyongshuo
 *
 */

@Component
public class ChatsPool {
	
	// 已与服务端建立连接的客户端
	private static ConcurrentHashMap<Long, Session> chatsPool = new ConcurrentHashMap<>();
	
	/**
	 * 添加session至池中
	 * @param key
	 * @param session
	 */
	public static void addSession(Long key, Session session) {
		chatsPool.put(key, session);
	}
	
	/**
	 * 根据用户账号，获取连接
	 * @param key
	 * @return
	 */
	public static Session getSession(Long key) {
		return chatsPool.get(key);
	}
	
	/**
	 * 从池中移除session
	 * @param key
	 */
	public static void removeSession(Long key) {
		chatsPool.remove(key);
	}

}

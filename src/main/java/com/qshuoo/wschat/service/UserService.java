package com.qshuoo.wschat.service;

import com.qshuoo.wschat.utils.WSChatResult;

/**
 * 用户服务接口
 * @author qiaoyongshuo
 *
 */
public interface UserService {
	
	/**
	 * 登陆验证
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception 
	 */
	public WSChatResult checkLoginUser(Integer account, String password) throws Exception;

}

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
	 * @param account 账号
	 * @param password 密码
	 * @return
	 * @throws Exception 
	 */
	public WSChatResult checkLoginUser(Long account, String password) throws Exception;
	
	public WSChatResult registerUser(String username, String password, String phone, String code);

}

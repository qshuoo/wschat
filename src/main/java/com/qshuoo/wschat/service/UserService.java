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
	
	/**
	 * 注册
	 * @param username 用户名
	 * @param password 密码
	 * @param checkinfo 校验信息
	 * @param checktype 校验类型
	 * @return
	 */
	public WSChatResult registerUser(String username, String password, String checkinfo, String checktype);

}

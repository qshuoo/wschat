package com.qshuoo.wschat.service;

import com.qshuoo.wschat.pojo.User;
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
	 * @throws Exception 
	 */
	public WSChatResult registerUser(String username, String password, String checkinfo, String checktype) throws Exception;

	/**
	 * 查找用户或群组
	 * @param account 账户
	 * @param type 类型
	 * @return 用户和群组信息
	 * @throws Exception 
	 */
	public WSChatResult search(Long account, String type) throws Exception;
	
	/**
	 * 匹配聊天
	 * @param account 账号
	 * @return
	 */
	public WSChatResult match(Long account);
	
	/**
	 * 更新用户信息
	 * @param user 更新后的属性值
	 * @param elem 更新的属性
	 * @param condi 更新的条件
	 * @return
	 * @throws Exception
	 */
	public WSChatResult updateUserInfo(User user, String[] elem, String[] condi) throws Exception;
	
	/**
	 * 验证邮箱
	 * @param account 账号
	 * @param checkinfo 验证信息
	 * @return
	 */
	public WSChatResult findPwd(Long account, String checkinfo);
	
	/**
	 * 设置新密码
	 * @param account 用户账号
	 * @param password 新密码
	 * @return
	 * @throws Exception 
	 */
	public WSChatResult setNewPassword(Long account, String password) throws Exception;
	
}

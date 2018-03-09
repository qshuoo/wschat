package com.qshuoo.wschat.service;


/**
 * 发送验证码接口
 * @author qiaoyongshuo
 *
 */

public interface CodeService {
	
	/**
	 * 发送验证码
	 * @param toPlace 发送目的地 邮箱/手机
	 * @param code 验证码
	 * @return
	 */
	public String sendCheckCode(String receiver);

}

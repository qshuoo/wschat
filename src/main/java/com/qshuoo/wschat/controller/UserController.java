package com.qshuoo.wschat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qshuoo.wschat.service.CodeService;
import com.qshuoo.wschat.service.UserService;
import com.qshuoo.wschat.utils.WSChatResult;

/**
 * 登陆校验
 * @author qiaoyongshuo
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CodeService codeService;
	
	/**
	 * 验证用户名和密码
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/login/check")
	public WSChatResult checkLogin(Long account, String password) throws Exception {
		return userService.checkLoginUser(account, password);
	}
	
	/**
	 * 发送验证码
	 * @param codeReceiver 验证码接收方
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/register/code")
	public WSChatResult sendCode(String codeReceiver) {
		String code = codeService.sendCheckCode(codeReceiver);
		return WSChatResult.ok(code);
	}
	
	// TODO 注册
	
	// TODO 获取好友列表
	
	// TODO 获取群列表
	
	// TODO 搜索用户/群
	
	// TODO 添加好友/群
	
	// TODO 

}

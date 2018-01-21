package com.qshuoo.wschat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qshuoo.wschat.service.UserService;
import com.qshuoo.wschat.utils.WSChatResult;

/**
 * 登陆校验
 * @author qiaoyongshuo
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService service;
	
	/**
	 * 验证用户名和密码
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/check")
	public WSChatResult checkLogin(Integer account, String password) throws Exception {
		return service.checkLoginUser(account, password);
	}

}

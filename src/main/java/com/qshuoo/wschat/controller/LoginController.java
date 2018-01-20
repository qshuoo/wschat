package com.qshuoo.wschat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登陆校验
 * @author qiaoyongshuo
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	/**
	 * 验证用户名和密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/check")
	public String checkLogin() {
		
		return "success";
	}

}

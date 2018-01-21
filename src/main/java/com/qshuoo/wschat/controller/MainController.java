package com.qshuoo.wschat.controller;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author qiaoyongshuo
 *
 */
@Controller
public class MainController {
	
	/**
	 * 跳转主页
	 * @return
	 */
	@RequestMapping("/index/{account}")
	public String toIndex(@PathParam("account") Integer account) {
		return "index";
	}

	/**
	 * 跳转登陆
	 * @return
	 */
	@RequestMapping("/login")
	public String toLogin() {
		return "login";
	}
}

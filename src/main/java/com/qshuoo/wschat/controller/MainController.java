package com.qshuoo.wschat.controller;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String toIndex(@PathVariable("account") String account, ModelMap map) {
		System.out.println(account);
		map.put("username", account);
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

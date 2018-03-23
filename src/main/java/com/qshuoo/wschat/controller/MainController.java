package com.qshuoo.wschat.controller;

import javax.servlet.http.HttpSession;

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
@RequestMapping("/wschat")
public class MainController {
	
	/**
	 * 跳转主页
	 * @return
	 */
	@RequestMapping("/index")
	public String toIndex(HttpSession session, ModelMap map) {
		if (session.getAttribute("user") == null) { // 未登录 返回登录页面
			return "login";
		}
		return "index";
	}

	/**
	 * 跳转登录
	 * @return
	 */
	@RequestMapping("/login")
	public String toLogin() {
		return "login";
	}
	
	/**
	 * 跳转注册
	 * @return
	 */
	@RequestMapping("/register")
	public String toReg() {
		return "register";
	}
	
	/**
	 * 跳转注册成功
	 * @return
	 */
	@RequestMapping("/regsucc/{account}")
	public String toRegSuccess(@PathVariable("account") String account, ModelMap map) {
		map.put("account", account);
		return "regSuccess";
	}
}

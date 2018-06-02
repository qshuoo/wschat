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
	
	@RequestMapping("/findpwd")
	public String toFindPwd() {
		return "findpwd";
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
	
	/**
	 * 跳转重设密码页面
	 * @param account
	 * @param map
	 * @return
	 */
	@RequestMapping("/setpwd")
	public String toFindSuccess(ModelMap map, HttpSession session) {
		Object object = session.getAttribute("RESET_PWD_ACCOUNT");
		if (object == null) {
			map.put("errMsg", "未检测到验证过程，请重新验证");
			return "set-new-pwd";
		}
		session.removeAttribute("RESET_PWD_ACCOUNT");
		map.put("account", object);
		return "set-new-pwd";
	}
}

package com.qshuoo.wschat.controller;


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
	@RequestMapping("/index/{account}")
	public String toIndex(@PathVariable("account") String account, ModelMap map) {
		map.put("username", account);
		return "index";
	}

	/**
	 * 跳转页面
	 * @return
	 */
	@RequestMapping("/{page}")
	public String toLogin(@PathVariable("page") String page) {
		return page;
	}
}

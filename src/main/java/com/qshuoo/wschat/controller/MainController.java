package com.qshuoo.wschat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author qiaoyongshuo
 *
 */
@Controller
public class MainController {
	
	@RequestMapping("/index")
	public String toIndex() {
		return "index";
	}

}

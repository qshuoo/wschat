package com.qshuoo.wschat.controller;

import javax.servlet.http.HttpSession;

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
	public WSChatResult sendCode(String codeReceiver, HttpSession session) {
		WSChatResult result =  codeService.sendCheckCode(codeReceiver);
		if (result.getCode() == 0) { // 验证失败返回错误信息
			return result;
		}
		session.setAttribute("check_code", result.getData());
		return WSChatResult.ok();
	}
	
	/**
	 * 注册
	 * @param username 用户名
	 * @param password 密码
	 * @param checkinfo 校验方式  手机/邮箱
	 * @param inputcode 输入的验证码
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/user/register")
	public WSChatResult register(String username, String password, String checkinfo, String inputcode, HttpSession session) {
		// TODO 注册
		if (!session.getAttribute("check_code").equals(inputcode)) {
			return WSChatResult.notOk("验证码输入错误");
		}
		userService.registerUser(username, password, checkinfo, "email");
		return null;
	}
	// TODO 获取好友列表
	
	// TODO 获取群列表
	
	// TODO 搜索用户/群
	
	// TODO 添加好友/群
	
	// TODO 

}

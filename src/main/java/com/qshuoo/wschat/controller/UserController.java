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
	public WSChatResult checkLogin(Long account, String password, HttpSession session) throws Exception {
		WSChatResult res = userService.checkLoginUser(account, password);
		if (res.getCode() == 0) {
			return res;
		}
		session.setAttribute("user", res.getData());
		return WSChatResult.ok();
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
		session.setMaxInactiveInterval(200); // 200s过期
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
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/user/register")
	public WSChatResult register(String username, String password, String checkinfo, String inputcode, HttpSession session) throws Exception {
		if (session.getAttribute("check_code") == null) {
			return WSChatResult.notOk("验证码已过期，请重新发送");
		}
		if (!session.getAttribute("check_code").equals(inputcode)) {
			return WSChatResult.notOk("验证码输入错误");
		}
		return userService.registerUser(username, password, checkinfo, "email");
	}
	
	/**
	 * 获取好友列表
	 * @param account 账号
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/user/friend")
	public WSChatResult getFriendsList(Long account) throws Exception {
		return userService.listFriends(account);
	}
	// TODO 获取群列表
	
	// TODO 搜索用户/群
	
	// TODO 添加好友/群
	
	// TODO 

}

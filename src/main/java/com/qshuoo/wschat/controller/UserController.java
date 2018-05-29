package com.qshuoo.wschat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qshuoo.wschat.service.BlackListService;
import com.qshuoo.wschat.service.CodeService;
import com.qshuoo.wschat.service.FriendService;
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
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private BlackListService blistService;
	
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
	 */
	@ResponseBody
	@RequestMapping("/user/friend")
	public WSChatResult getFriendsList(Long account) {
		return friendService.listFriends(account);
	}
	// TODO 获取群列表
	
	/**
	 * 搜索用户
	 * @param account 账号
	 * @return 用户信息(账号，昵称，头像，邮箱，手机，签名)
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/user/search/{account}")
	public WSChatResult searchUser(@PathVariable("account")Long account) throws Exception {
		return userService.search(account, "user");
	}
	
	/**
	 * 搜索群组
	 * @param account 群号
	 * @return 群组信息(群号，群名，群头像)
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/group/search/{account}")
	public WSChatResult searchGroup(@PathVariable("account")Long account) throws Exception {
		return userService.search(account, "group");
	}
	
	/**
	 * 添加好友
	 * @param applyUid 申请用户
	 * @param aimUid 目标用户
	 * @param msg 添加信息
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/friend/add")
	public WSChatResult addFriend(Long applyUid, Long aimUid, String msg) throws Exception {
		return friendService.addFriend(applyUid, aimUid, msg);
	}
	
	/**
	 * 获取好友申请列表
	 * @param account
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/friend/new")
	public WSChatResult getApplyFriends(Long account) {
		return friendService.listNewFriends(account);
	}
	
	/**
	 * 处理好友申请
	 * @param aimUid 目标账号
	 * @param applyUid 申请账号
	 * @param type 处理方式
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/friend/deal")
	public WSChatResult dealFriendApply(Long aimUid, Long applyUid, String type) throws Exception {
		WSChatResult result = null;
		if ("agree".equals(type)) {
			result = friendService.agreeFriendApply(aimUid, applyUid);
		} else if ("refuse".equals(type)) {
			result = friendService.refuseFriendApply(aimUid, applyUid);
		}
		return result;
	}
	
	/**
	 * 删除好友
	 * @param applyUid 申请账号
	 * @param aimUid 目标账号
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/friend/del")
	public WSChatResult delFriend(Long applyUid, Long aimUid) throws Exception {
		WSChatResult result = friendService.delFriend(applyUid, aimUid);
		return result;
	}
	
	/**
	 * 获取黑名单
	 * @param account 用户账号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/user/blist")
	public WSChatResult getBlackList(Long account) {
		WSChatResult result = blistService.listBlackList(account);
		return result;
	}
	
	/**
	 * 添加至黑名单
	 * @param applyUid 申请账号
	 * @param aimUid 目标账号
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/blist/add")
	public WSChatResult addToBlackList(Long applyUid, Long aimUid) throws Exception {
		WSChatResult result = blistService.addToBlackList(applyUid, aimUid);
		return result;
	}
	
	/**
	 * 从黑名单恢复
	 * @param applyUid 申请账号
	 * @param aimUid 目标账号
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/blist/del")
	public WSChatResult delFromBList(Long applyUid, Long aimUid) throws Exception {
		WSChatResult result = blistService.delFromBlackList(applyUid, aimUid);
		return result;
	}
	
	/**
	 * 开启聊天匹配
	 * @param account 账号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sctchat/match")
	public WSChatResult matchChat(Long account) {
		WSChatResult result = userService.match(account);
		return result;
	}

}

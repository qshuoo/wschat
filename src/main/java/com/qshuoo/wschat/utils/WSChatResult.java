package com.qshuoo.wschat.utils;

/**
 * 返回格式
 * @author qiaoyongshuo
 *
 */
public class WSChatResult {
	
	private Integer code; // 结果码 1 成功 0 失败
	private String msg; // 信息
	
	private WSChatResult(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	

	/**
	 * 成功
	 * @return
	 */
	public static WSChatResult ok() {
		return new WSChatResult(1, null);
	}
	
	/**
	 * 失败
	 * @param msg 失败原因
	 * @return
	 */
	public static WSChatResult notOk(String msg) {
		return new WSChatResult(0, msg);
	}
	
	/**
	 * 失败
	 * @return
	 */
	public static WSChatResult notOk() {
		return new WSChatResult(0, null);
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}

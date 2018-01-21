package com.qshuoo.wschat.utils;

/**
 * 返回格式
 * @author qiaoyongshuo
 *
 */
public class WSChatResult {
	
	private Integer code; // 结果码 1 成功 0 失败
	private Object data; // 通过的数据
	private String msg; // 信息
	
	private WSChatResult(Integer code, Object data, String msg) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	/**
	 * 成功 不返回数据
	 * @return
	 */
	public static WSChatResult ok() {
		return new WSChatResult(1, null, null);
	}

	/**
	 * 成功 返回数据
	 * @return
	 */
	public static WSChatResult ok(Object data) {
		return new WSChatResult(1, data, null);
	}
	
	/**
	 * 失败
	 * @param msg 失败原因
	 * @return
	 */
	public static WSChatResult notOk(String msg) {
		return new WSChatResult(0, null, msg);
	}
	
	/**
	 * 失败
	 * @return
	 */
	public static WSChatResult notOk() {
		return new WSChatResult(0, null, null);
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}

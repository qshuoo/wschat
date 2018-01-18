package com.qshuoo.wschat.pojo;

/**
 * 
 * @author qiaoyongshuo
 *
 */
public class Message {
	private Integer from; // 消息来源
	private Integer to; // 消息目的地
	private String msg; // 消息内容

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}

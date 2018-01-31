package com.qshuoo.wschat.pojo;

import java.util.Date;

/**
 * 
 * @author qiaoyongshuo
 *
 */
public class Message {
	
	private Long mid; // 标识主键
	private Long fromUid; // 消息来源
	private Long toUid; // 消息目的地
	private String msg; // 消息内容
	private Boolean status; // 是否已读 0 未读
	private Date sendTime; // 发送时间
	private Boolean isGroup; // 是否群消息
	
	public Long getMid() {
		return mid;
	}
	public void setMid(Long mid) {
		this.mid = mid;
	}
	public Long getFromUid() {
		return fromUid;
	}
	public void setFromUid(Long fromUid) {
		this.fromUid = fromUid;
	}
	public Long getToUid() {
		return toUid;
	}
	public void setToUid(Long toUid) {
		this.toUid = toUid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Boolean getIsGroup() {
		return isGroup;
	}
	public void setGroup(Boolean isGroup) {
		this.isGroup = isGroup;
	}
	
	

}

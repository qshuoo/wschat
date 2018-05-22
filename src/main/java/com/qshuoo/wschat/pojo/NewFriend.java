package com.qshuoo.wschat.pojo;

/**
 * 添加朋友
 * @author qshuoo
 *
 */
public class NewFriend {
	
	private Long uid1; // 被添加方id
	private Long uid2; // 添加方id
	private String msg; // 添加时验证信息
	private Integer state; // 添加状态 1正常 2拒绝 3拉入黑名单
	
	public Long getUid1() {
		return uid1;
	}
	public void setUid1(Long uid1) {
		this.uid1 = uid1;
	}
	public Long getUid2() {
		return uid2;
	}
	public void setUid2(Long uid2) {
		this.uid2 = uid2;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	

}

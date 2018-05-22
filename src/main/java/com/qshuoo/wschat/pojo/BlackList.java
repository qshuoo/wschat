package com.qshuoo.wschat.pojo;

/**
 * 黑名单
 * @author qshuoo
 *
 */
public class BlackList {
	private Long uid1;
	private Long uid2;
	private Integer state; // 黑名单状态 0 已恢复 1 正常
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	

}

package com.qshuoo.wschat.pojo;

/**
 * 用户好友
 * @author qiaoyongshuo
 *
 */
public class Friend {
	private Long uid; // 用户id
	private String uname; // 用户昵称
	private String img; // 用户头像
	private String signature; // 用户签名
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	

}

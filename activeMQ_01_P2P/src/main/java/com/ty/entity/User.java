package com.ty.entity;

import java.io.Serializable;

public class User implements Serializable{
	private int uId;
	private String uName;
	private String uPwd;
	public User(int uId, String uName, String uPwd) {
		super();
		this.uId = uId;
		this.uName = uName;
		this.uPwd = uPwd;
	}
	public User() {
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPwd() {
		return uPwd;
	}
	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}
	@Override
	public String toString() {
		return "User [uId=" + uId + ", uName=" + uName + ", uPwd=" + uPwd + "]";
	}
}

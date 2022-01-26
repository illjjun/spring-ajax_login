package com.human.login;

public class rlogin {
	String userid;
	String passcode;
	
	public rlogin() {
		super();
	}
	public rlogin(String userid, String passcode) {
		super();
		this.userid = userid;
		this.passcode = passcode;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasscode() {
		return passcode;
	}
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	
}

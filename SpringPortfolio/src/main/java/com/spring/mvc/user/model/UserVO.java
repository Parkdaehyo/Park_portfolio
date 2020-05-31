package com.spring.mvc.user.model;

import java.util.Date;

public class UserVO {
	
	private String account;
	private String password;
	private String name;
	private Date regDate;
	private String sessionId;
	private Date limitTime;
	private Integer phoneNum;
	
	public Integer getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(Integer phoneNum) {
		this.phoneNum = phoneNum;
	}

	//자동 로그인 체크 여부
	private boolean autoLogin;
	
	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}
	
	public boolean isAutoLogin() {
		return autoLogin;
	}
	
	
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) { //여기요  Client -> Server Controller 에서 Param 받는곳 무슨 말씀이신지??
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(Date limitTime) {
		this.limitTime = limitTime;
	}

	@Override
	public String toString() {
		return "UserVO [account=" + account + ", password=" + password + ", name=" + name + ", regDate=" + regDate
				+ ", sessionId=" + sessionId + ", limitTime=" + limitTime + ", phoneNum=" + phoneNum + ", autoLogin="
				+ autoLogin + "]";
	}

	



	
	
}

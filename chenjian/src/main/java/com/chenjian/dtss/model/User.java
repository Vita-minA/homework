package com.chenjian.dtss.model;

public class User {
	int uid;
	String username;
	String  itcode;
	boolean islock;
	public User() {}

	public User(int uid, String username, String itcode, boolean islock) {
		super();
		this.uid = uid;
		this.username = username;
		this.itcode = itcode;
		this.islock = islock;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getItcode() {
		return itcode;
	}
	public void setItcode(String itcode) {
		this.itcode = itcode;
	}
	
	public boolean isIslock() {
		return islock;
	}
	public void setIslock(boolean islock) {
		this.islock = islock;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", itcode=" + itcode + ", islock=" + islock + "]";
	}
}

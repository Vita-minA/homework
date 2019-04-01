package com.luckymoney.dtss.model;


public class User {
	int uid;
	String username;
	String password;
	String faceicod;
	String  itcode;
	int islock;
	int isadmin;
	public User() {}
	public User(int uid, String username, String password, String faceicod, String itcode, int islock,
			int isadmin) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.faceicod = faceicod;
		this.itcode = itcode;
		this.islock = islock;
		this.isadmin = isadmin;
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
	
	
	public int getIslock() {
		return islock;
	}
	public void setIslock(int islock) {
		this.islock = islock;
	}
	public int getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFaceicod() {
		return faceicod;
	}

	public void setFaceicod(String faceicod) {
		this.faceicod = faceicod;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", faceicod=" + faceicod
				+ ", itcode=" + itcode + ", islock=" + islock + ", isadmin=" + isadmin + "]";
	}

	

}

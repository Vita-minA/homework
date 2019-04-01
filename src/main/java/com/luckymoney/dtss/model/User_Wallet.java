package com.luckymoney.dtss.model;

public class User_Wallet {
	private int uid;
	private String username;
	private String itcode;
	private int amount;
	private boolean islock;

	public User_Wallet() {
	}

	public User_Wallet(int uid, String username, String itcode, int amount, boolean islock) {
		super();
		this.uid = uid;
		this.username = username;
		this.itcode = itcode;
		this.amount = amount;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isIslock() {
		return islock;
	}

	public void setIslock(boolean islock) {
		this.islock = islock;
	}

	@Override
	public String toString() {
		return "User_Wallet [uid=" + uid + ", username=" + username + ", itcode=" + itcode + ", amount=" + amount
				+ ", islock=" + islock + "]";
	}

}

package com.luckymoney.dtss.model;

public class Wallet {
	int wid;
	int uid;
	String username;
	int amount;
	public Wallet() {}
	public Wallet(int wid, int uid, String username,int amount) {
		super();
		this.wid = wid;
		this.uid = uid;
		this.username=username;
		this.amount = amount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Wallet [wid=" + wid + ", uid=" + uid + ", amount=" + amount + "]";
	}
	
}

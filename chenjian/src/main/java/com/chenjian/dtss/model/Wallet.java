package com.chenjian.dtss.model;

public class Wallet {
	int wid;
	int uid;
	int volume;
	public Wallet() {}
	public Wallet(int wid, int uid, int amount) {
		super();
		this.wid = wid;
		this.uid = uid;
		this.volume = amount;
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
		return volume;
	}
	public void setAmount(int amount) {
		this.volume = amount;
	}
	@Override
	public String toString() {
		return "Wallet [wid=" + wid + ", uid=" + uid + ", volume=" + volume + "]";
	}
	
}

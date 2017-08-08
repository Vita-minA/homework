package com.chenjian.dtss.model;

import java.sql.Timestamp;
import java.text.DateFormat;

public class Trade {
	int tid;
	int wid;
	int volume;
	Timestamp tradetime;
	String memo;
	public Trade() {}

	public Trade(int tid, int wid, int volume, Timestamp tradetime, String memo) {
		super();
		this.tid = tid;
		this.wid = wid;
		this.volume = volume;
		this.tradetime = tradetime;
		this.memo = memo;
	}

	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public Timestamp getTradetime() {
		return tradetime;
	}
	public void setTradetime(Timestamp tradetime) {
		this.tradetime = tradetime;
	}
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "Trade [tid=" + tid + ", wid=" + wid + ", volume=" + volume + ", tradetime=" + tradetime + ", memo="
				+ memo + "]";
	}


}

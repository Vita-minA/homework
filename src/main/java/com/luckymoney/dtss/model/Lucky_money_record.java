package com.luckymoney.dtss.model;

import java.sql.Timestamp;

public class Lucky_money_record {
	int rid;
	int wid;
	int uid;
	String username;
	int lucky_number;
	Timestamp tradetime;
	int round;
	/**
	 * @param rid 记录编号
	 * @param wid 当前钱包id
	 * @param lucky_number 红包钱数
	 * @param round 红包雨轮次
	 */
	public Lucky_money_record() {};
	public Lucky_money_record(int rid, int wid,int uid,String username, int lucky_number,Timestamp tradetime,int round) {
		super();
		this.rid = rid;
		this.wid = wid;
		this.uid=uid;
		this.username=username;
		this.lucky_number = lucky_number;
		this.tradetime=tradetime;
		this.round = round;
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
	public Timestamp getTradetime() {
		return tradetime;
	}
	public void setTradetime(Timestamp tradetime) {
		this.tradetime = tradetime;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getLucky_number() {
		return lucky_number;
	}
	public void setLucky_number(int lucky_number) {
		this.lucky_number = lucky_number;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	@Override
	public String toString() {
		return "Lucky_money_record [rid=" + rid + ", wid=" + wid + ", lucky_number=" + lucky_number + ", tradetime="
				+ tradetime + ", round=" + round + "]";
	}
	
}

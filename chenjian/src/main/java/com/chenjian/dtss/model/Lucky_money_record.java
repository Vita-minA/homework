package com.chenjian.dtss.model;
/**
*@author ���ߣ��½�
*@version ����ʱ�䣺2017��8��7�� ����11:09:32
*˵����
*/
public class Lucky_money_record {
	int rid;
	int wid;
	int lucky_number;
	int round;
	public Lucky_money_record(int rid, int wid, int lucky_number, int round) {
		super();
		this.rid = rid;
		this.wid = wid;
		this.lucky_number = lucky_number;
		this.round = round;
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
		return "Lucky_money_record [rid=" + rid + ", wid=" + wid + ", lucky_number=" + lucky_number + ", round=" + round
				+ "]";
	}
}

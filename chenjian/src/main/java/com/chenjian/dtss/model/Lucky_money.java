package com.chenjian.dtss.model;
/**
*@author ���ߣ��½�
*@version ����ʱ�䣺2017��8��7�� ����11:06:10
*˵����
*/
public class Lucky_money {
	int lid;
	int round;
	int total;
	Lucky_money(){}
	public Lucky_money(int lid, int round, int total) {
		super();
		this.lid = lid;
		this.round = round;
		this.total = total;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Lucky_money [lid=" + lid + ", round=" + round + ", total=" + total + "]";
	};
	
}

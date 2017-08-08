package com.chenjian.dtss.model;
/**
*@author 作者：陈健
*@version 创建时间：2017年8月7日 下午11:06:10
*说明：
*/
public class Lucky_money {
	int lid;
	int Round;
	int Total;
	Lucky_money(){}
	public Lucky_money(int lid, int Round, int Total) {
		this.lid = lid;
		this.Round = Round;
		this.Total = Total;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getRound() {
		return Round;
	}
	public void setRound(int round) {
		this.Round = round;
	}
	public int getTotal() {
		return Total;
	}
	public void setTotal(int total) {
		this.Total = total;
	}
	@Override
	public String toString() {
		return "Lucky_money [lid=" + lid + ", Round=" + Round + ", Total=" + Total + "]";
	};
	
}

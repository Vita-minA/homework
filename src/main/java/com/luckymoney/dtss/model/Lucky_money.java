package com.luckymoney.dtss.model;
/**
*@author 作者：陈健
*@version 创建时间：2017年8月7日 下午11:06:10
*说明：
*/
public class Lucky_money {
	int lid;
	int round;
	int Total;
	public Lucky_money(){}
	/**
	 * @param lid 每一轮的id
	 * @param Round 红包雨轮次
	 * @param Total 该轮红包雨总钱数
	 */
	public Lucky_money(int lid, int Round, int Total) {
		this.lid = lid;
		this.round = Round;
		this.Total = Total;
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
		return Total;
	}
	public void setTotal(int total) {
		this.Total = total;
	}
	@Override
	public String toString() {
		return "Lucky_money [lid=" + lid + ", round=" + round + ", Total=" + Total + "]";
	};
	
}

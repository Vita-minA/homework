package com.luckymoney.dtss.model;
/**
*@author 作者：陈健
*@version 创建时间：2017年8月21日 上午1:13:25
*说明：在线用户
*/
public class Onlineuser{
	int oid;
	String username;
	public Onlineuser() {}
	public Onlineuser(int oid, String username) {
		super();
		this.oid = oid;
		this.username = username;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "OnlineuserDAO [oid=" + oid + ", username=" + username + "]";
	}
	
	
}

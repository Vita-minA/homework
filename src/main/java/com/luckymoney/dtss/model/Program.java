package com.luckymoney.dtss.model;

/**
 * @author 作者：陈健
 * @version 创建时间：2017年8月15日 上午12:30:23 说明：这个是节目的模板
 */
public class Program {
	int pid;
	int prorder;
	String proname;
	String time;
	String phone;
	String name;
	String type;
	String department;
	int award;
	public Program() {}
	public Program(int pid, int prorder, String proname, String time, String phone, String name, String type,
			String department, int award) {
		super();
		this.pid = pid;
		this.prorder = prorder;
		this.proname = proname;
		this.time = time;
		this.phone = phone;
		this.name = name;
		this.type = type;
		this.department = department;
		this.award = award;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getProrder() {
		return prorder;
	}
	public void setProrder(int prorder) {
		this.prorder = prorder;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getAward() {
		return award;
	}
	public void setAward(int award) {
		this.award = award;
	}
	@Override
	public String toString() {
		return "Program [pid=" + pid + ", prorder=" + prorder + ", proname=" + proname + ", time=" + time + ", phone="
				+ phone + ", name=" + name + ", type=" + type + ", department=" + department + ", award=" + award + "]";
	}
	
}

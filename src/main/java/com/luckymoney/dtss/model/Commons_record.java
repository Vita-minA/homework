package com.luckymoney.dtss.model;

import java.sql.Timestamp;

public class Commons_record {
   String cid;
   Timestamp time;
   String message;
   public Commons_record() {};
   public Commons_record(String message,String cid,Timestamp time){
	   super();
	   this.message=message;	    
	   this.cid=cid;
	   this.time=time;
   }
   public String getMessage() {
	return message;
}
   public void setMessage(String message) {
	this.message = message;
}
public String getCid() {
	return cid;
}
public void setCid(String cid) {
	this.cid = cid;
}
public Timestamp getTime() {
	return time; 
}
public void setTime(Timestamp time) {
	this.time = time;
}
   
   
}


package com.chenjian.dtss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chenjian.dtss.DB.UserDAO;

import mythread.LuckyNumberThread;



/**
*@author 作者：陈健
*@version 创建时间：2017年8月7日 下午5:10:23
*说明：
*/

@Controller
public class Admin {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(String username,String itcode) {
		logger.info("是这里啊");
		int flag=UserDAO.checkUserInfo(itcode, username, jdbcTemplate);
		//是要在这里写异步的，所以现在就先从简
		if(flag==0) {
			return username;
		}
		if(flag==1) {
			boolean isadmin=UserDAO.isadminByItcode(itcode, jdbcTemplate);
			if(isadmin) {
			return "admin";
			}
			else
				return "ChatRoom";
		}
		if(flag==-1) {
			return itcode;
		}
		return itcode;
		
	}
	@RequestMapping("/lucky_on") 
	public String Lucky_on(String round) {
		LuckyNumberThread t = new LuckyNumberThread();
		t.setTemplate(jdbcTemplate);
		int r = 0;
		try {
			r = Integer.parseInt(round);
		} catch (Exception e) {
			// TODO: handle exception
		}
		t.setRound(r);
		t.setFlag(true);
		t.start();
		return "lucky_on";
	}
	
}

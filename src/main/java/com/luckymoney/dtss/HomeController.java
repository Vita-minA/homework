package com.luckymoney.dtss;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luckymoney.dtss.DAO.LuckyMoneyDAO;
import com.luckymoney.dtss.DAO.LuckyMoneyTradeDAO;
import com.luckymoney.dtss.DAO.TradeDAO;
import com.luckymoney.dtss.DAO.WalletDAO;
import com.luckymoney.dtss.model.Lucky_money_record;
import com.luckymoney.dtss.model.Trade;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//统计客户端的地区
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		logger.info(formattedDate);
		
		
	
//		List<Lucky_money_record> list =new ArrayList<Lucky_money_record>();
//		list=LuckyMoneyTradeDAO.getAllRecords(jdbcTemplate);
//		model.addAttribute("list", list);
		
//		List<Trade> list = new ArrayList<Trade>();
//		list=TradeDAO.getAllTrades(jdbcTemplate);
//		model.addAttribute("list", list);
		return "home";
	}
}

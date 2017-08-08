package com.chenjian.dtss;

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

import com.chenjian.dtss.BalanceController;
import com.chenjian.dtss.DB.WalletDAO;
import com.chenjian.dtss.model.Trade;
import com.chenjian.dtss.DB.UserDAO;

@Controller
public class BalanceController {
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
	@Autowired
	public JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/balance_add", method = RequestMethod.GET)
	public String balanceAdd() {
		return null;

	}

	@RequestMapping(value = "/balance_adding", method = RequestMethod.GET)
	public String balanceAdding(String itcode, String username, String volume, Locale locale, Model model) {
		logger.info(jdbcTemplate.toString());
		int i = WalletDAO.balance_add(itcode, username, volume, locale, jdbcTemplate);
		String result = "";
		if (i == 1) {
			result = "充值成功！";
		} else if (i == -1) {
			result = "用户填写信息有问题！";
		} else {
			result = "充值失败，请稍后重试!";
		}
		model.addAttribute("result", result);
		return "balance_adding";
	}

	@RequestMapping(value = "/inquiry", method = RequestMethod.GET)
	public String inquiry(String from, String to, Model model) {
		logger.info("from" + from + "to" + to);
		List<Trade> trade = WalletDAO.inquire(from, to, jdbcTemplate);
		model.addAttribute("trade", trade);
		return "inquiry";
	}

}

package com.luckymoney.dtss.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.luckymoney.dtss.BalanceController;
import com.luckymoney.dtss.model.User;
import com.luckymoney.dtss.model.Wallet;

public class RestMoneyDAO {
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
	


	public static Wallet restmoney(String itcode, String username,JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		logger.info(jdbcTemplate.toString());
		RowMapper<User> user_mapper = new BeanPropertyRowMapper<User>(User.class);
		User user = jdbcTemplate.queryForObject("select * from user where itcode=? and username=?", user_mapper,
				new Object[] { itcode, username });
		RowMapper<Wallet> wallet_mapper = new BeanPropertyRowMapper<Wallet>(Wallet.class);
		Wallet wallet = jdbcTemplate.queryForObject("select * from wallet where uid=?", wallet_mapper,
				user.getUid());
		System.out.println(username);
		System.out.println(wallet.getWid());
		System.out.println(wallet.getUid());
		System.out.println(wallet.getUsername());
		System.out.println(wallet. getAmount());
		return wallet;
	} 
}

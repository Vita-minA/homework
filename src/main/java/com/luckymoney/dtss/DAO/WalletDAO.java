package com.luckymoney.dtss.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.luckymoney.dtss.BalanceController;
import com.luckymoney.dtss.model.Trade;
import com.luckymoney.dtss.model.User;
import com.luckymoney.dtss.model.Wallet;


public class WalletDAO {
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);

	/**
	 *钱包充值
	 * @param itcode：用户账号
	 * @param username：用户名
	 * @param amount：充钱金额
	 * @param jdbcTemplate：模板
	 * @return 返回我们充钱的操作是否成功
	 */
//	public static int balance_add(String itcode, String username, String amount, JdbcTemplate jdbcTemplate) {
//		try {
//			logger.info(jdbcTemplate.toString());
//			RowMapper<User> user_mapper = new BeanPropertyRowMapper<User>(User.class);
//			User user = jdbcTemplate.queryForObject("select * from user where itcode=? and username=?", user_mapper,
//					new Object[] { itcode, username });
//			RowMapper<Wallet> wallet_mapper = new BeanPropertyRowMapper<Wallet>(Wallet.class);
//			Wallet wallet = jdbcTemplate.queryForObject("select * from wallet where uid=?", wallet_mapper,
//					user.getUid());
//			java.util.Date date = new java.util.Date();
//			Timestamp timeStamp = new Timestamp(date.getTime());
//			String memo="钱包充值";
//			int i = jdbcTemplate.update("insert into trade values(null, ?,?,?,memo);",
//					new Object[] { wallet.getWid(), amount,timeStamp,memo});
//			if (i > 0) {
//				int j = jdbcTemplate.update("update wallet set amount = amount + ? where wid=?",
//						new Object[] { amount, wallet.getWid() });
//				if (j > 0) {
//					return 1;
//				}
//			}
//		} catch (Exception e) {
//			return -1;
//		}
//		return 0;
//	}
	
	
	
	//赖
	public static int balance_add(String itcode, String username, String amount, JdbcTemplate jdbcTemplate) {
		try {
			logger.info 

(jdbcTemplate.toString());
			RowMapper<User> user_mapper = new BeanPropertyRowMapper<User>(User.class);
			User user = jdbcTemplate.queryForObject("select * from user where itcode=? and username=?", user_mapper,
					new Object[] { itcode, username });
			RowMapper<Wallet> wallet_mapper = new BeanPropertyRowMapper<Wallet>(Wallet.class);
			Wallet wallet = jdbcTemplate.queryForObject("select * from wallet where uid=?", wallet_mapper,
					user.getUid());
			java.util.Date date = new java.util.Date();
			Timestamp timeStamp = new Timestamp(date.getTime());
			String memo="钱包充值";
			int i = jdbcTemplate.update("insert into trade values(null, ?,?,?,?,?,?);",
					new Object[] { wallet.getWid(),user.getUid(),user.getUsername(), amount,timeStamp,memo}); 
			if (i > 0) {
				int j = jdbcTemplate.update("update wallet set amount = amount + ? where wid=?",
						new Object[] { amount, wallet.getWid() });
				if (j > 0) {
					return 1;
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

/**
 * 获得某个用户的钱包信息
 * @param username
 * @param jdbcTemplate
 * @return
 */
	public static Wallet getWalletByUsername(String username, JdbcTemplate jdbcTemplate) {
		RowMapper<Wallet> wallet_mapper = new BeanPropertyRowMapper<Wallet>(Wallet.class);
		String query = "select * from Wallet where username = ?";
		Wallet result = new Wallet();
		try {
			result = jdbcTemplate.queryForObject(query, wallet_mapper, username);
			logger.info(result.toString());
		} catch (Exception e) {
			logger.info("根据用户uid获取用户钱包信息--失败");
			return null;
		}
		return result;
	}
	
	
	/**
	 * 通过用户uid获取钱包信息	  
	 * @param uid
	 * @return 返回这个钱包的信息
	 */
	public static Wallet getWalletById(int uid, JdbcTemplate jdbcTemplate) {
		RowMapper<Wallet> wallet_mapper = new BeanPropertyRowMapper<Wallet>(Wallet.class);
		String query = "select * from Wallet where uid = ?";
		Wallet result = new Wallet();
		try {
			result = jdbcTemplate.queryForObject(query, wallet_mapper, uid);
			logger.info(result.toString());
		} catch (Exception e) {
			logger.info("根据用户uid获取用户钱包信息--失败");
			return null;
		}
		return result;
	}

	/**
	 * 通过itcode获得钱包的相关信息 
	 * @param itcode  用户账号
	 * @param jdbcTemplate
	 * @return 返回这个钱包的信息
	 */
	public static Wallet getWalletByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		RowMapper<Wallet> wallet_mapper = new BeanPropertyRowMapper<Wallet>(Wallet.class);
		String query = "select * from Wallet where itcode = ?";
		Wallet result = new Wallet();
		try {
			result = jdbcTemplate.queryForObject(query, wallet_mapper, itcode);
			logger.info(result.toString());
		} catch (Exception e) {
			logger.info("根据用户itcode获取用户钱包信息--失败");
			return null;
		}
		return result;
	}
    /**
     * 获得某段时间的所有交易信息
     * @param from
     * @param to
     * @param jdbcTemplate
     * @return
     */
	public static List<Trade> inquire(String from, String to, JdbcTemplate jdbcTemplate) {
		// Timestamp f = Timestamp.valueOf(from);
		// Timestamp t = Timestamp.valueOf(to);
		logger.info (from);
		logger.info (to);
		RowMapper<Trade> trade_mapper = new BeanPropertyRowMapper<Trade>(Trade.class);
		List<Trade> trade = new ArrayList<Trade>();
		String query = "select * from trade where unix_timestamp(tradetime) > unix_timestamp(?) and unix_timestamp(tradetime) < unix_timestamp(?)";
		trade = jdbcTemplate.query(query, trade_mapper, new Object[] { from, to });
		for (int i = 0; i < trade.size(); i++) {
			logger.info (trade.get(i).toString());
		}
		return trade;

	}
	/**
	 * 根据用户账号，为这个用户申请一个钱包
	 * @param uid
	 * @return 返回这个操作是否成功的布尔值
	 */
	public static boolean initWallet(int uid, JdbcTemplate jdbcTemplate) {
		User result = UserDAO.getUserByUid(uid, jdbcTemplate);
		if (result == null) {
			return false;
		} else {
			String query = "insert into Wallet values(null,?,0)";
			try {
				jdbcTemplate.update(query, uid);
			} catch (Exception e) {
				logger.info("根据用户itcode为用户申请钱包--失败！");
				return false;
			}
			return true;
		}
	}

	/**
	 * 目的：根据新用户itcode信息，申请一个钱包	 * 
	 * @param itcode
	 * @return 返回这个操作是否成功的布尔值
	 */
	public static boolean initWallet(String itcode, JdbcTemplate jdbcTemplate) {
		User result = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		if (result == null) {
			return false;
		} else {
			if(initWallet(result.getUid(), jdbcTemplate) )
			return true;
			else return false;
		}
	}

	/**
	 * 获得所有用户的钱包信息
	 * @return 返回一个钱包的list
	 */
	public static List<Wallet> getAllWallets(JdbcTemplate jdbcTemplate) {
		RowMapper<Wallet> wallet_mapper = new BeanPropertyRowMapper<Wallet>(Wallet.class);
		List<Wallet> wallet = new ArrayList<Wallet>();
		String query = "select * from Wallet";
		wallet = jdbcTemplate.query(query, wallet_mapper);
		return wallet;
	}
	/**
	 *打钱进钱包
	 * @param wid 钱包
	 * @param lucknumber 数额
	 * @param template 
	 * @return 返回你的操作0失败，1成功
	 */
	public static int walletAdd(int wid, int lucknumber, JdbcTemplate template) {
		String query="update wallet set amount = amount + ? where wid=?";
		try {
			template.update(query,new Object[] {lucknumber,wid});
			return 1;
		}catch(Exception e) {
			return 0;
		}
		// TODO Auto-generated method stub
	}
	/**
	 * 想红包雨初始化时从公司账户扣减
	 * @param wid
	 * @param number
	 * @param template
	 * @return
	 */
	public static int walletcut(int wid, int number, JdbcTemplate template) {
		String query="update wallet set amount = amount - ? where uid=?";
		try {
			template.update(query,new Object[] {number,1});
			if(TradeDAO.createtotalTrade(number,template));
			return 1;
		}catch(Exception e) {
			return 0;
		}
		// TODO Auto-generated method stub
	}
}

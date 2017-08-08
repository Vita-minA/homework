package com.chenjian.dtss.DB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.chenjian.dtss.BalanceController;
import com.chenjian.dtss.model.Trade;
import com.chenjian.dtss.model.User;
import com.chenjian.dtss.model.Wallet;

/**
 * 
 * @author 千见
 *
 */
public class WalletDAO {
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);

	/**
	 * 目的：这个是使得钱包鼓起来的一个操作，注意在此基础上我们是要添加转账记录的。
	 * 
	 * @param itcode：用户账号
	 * @param username：用户名
	 * @param amount：充钱的数量
	 * @param locale：时间
	 * @param jdbcTemplate：模板
	 * @return 返回我们充钱的操作是否成功
	 */
	public static int balance_add(String itcode, String username, String amount, Locale locale,
			JdbcTemplate jdbcTemplate) {

		try {
			logger.info(jdbcTemplate.toString());
			RowMapper<User> user_mapper = new BeanPropertyRowMapper<User>(User.class);
			User user = jdbcTemplate.queryForObject("select * from user where itcode=? and username=?", user_mapper,
					new Object[] { itcode, username });
			RowMapper<Wallet> wallet_mapper = new BeanPropertyRowMapper<Wallet>(Wallet.class);
			Wallet wallet = jdbcTemplate.queryForObject("select * from wallet where uid=?", wallet_mapper,
					user.getUid());
			java.util.Date date = new java.util.Date();
			Timestamp timeStamp = new Timestamp(date.getTime());
			int i = jdbcTemplate.update("insert into trade values(null, ?,?,?);",
					new Object[] { wallet.getWid(), amount, timeStamp });
			if (i > 0) {
				int j = jdbcTemplate.update("update wallet set amount = amount + ? where wid=?",
						new Object[] { amount, wallet.getWid() });
				if (j > 0) {
					return 1;
				}
			}
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}

	/**
	 * 目的：查询一段时间内的转账记录
	 * 
	 * @param from
	 *            起始时间
	 * @param to
	 *            终止时间
	 * @param jdbcTemplate
	 *            数据库池
	 * @return 返回交♂易记录
	 */
	public static List<Trade> inquire(String from, String to, JdbcTemplate jdbcTemplate) {

		// Timestamp f = Timestamp.valueOf(from);
		// Timestamp t = Timestamp.valueOf(to);
		logger.info(from);
		logger.info(to);
		RowMapper<Trade> trade_mapper = new BeanPropertyRowMapper<Trade>(Trade.class);
		List<Trade> trade = new ArrayList<Trade>();
		String query = "select * from trade where unix_timestamp(tradetime) > unix_timestamp(?) and unix_timestamp(tradetime) < unix_timestamp(?)";
		trade = jdbcTemplate.query(query, trade_mapper, new Object[] { from, to });
		for (int i = 0; i < trade.size(); i++) {
			logger.info(trade.get(i).toString());
		}
		return trade;

	}

	/**
	 * 目的：通过uid获取钱包信息
	 * 
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
			logger.info("好气哦");
			return null;
		}
		return result;
	}

	/**
	 * 目的：通过itcode获得钱包的相关信息
	 * 
	 * @param itcode
	 *            用户账号
	 * @param jdbcTemplate
	 *            数据池
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
			logger.info("好气哦");
			return null;
		}
		return result;
	}

	/**
	 * 目的：根据用户得信息，为这个用户申请一个钱包，即创建一个钱包
	 * 
	 * @param uid
	 * @return 返回这个操作是否成功的布尔值
	 */
	public static boolean initWallet(int uid, JdbcTemplate jdbcTemplate) {
		User result = UserDAO.getUserByUid(uid, jdbcTemplate);
		// 为空的话，这个账户就不存在，我们就不能给这个人创建账户了
		if (result == null) {
			return false;
		} else {
			String query = "insert into Wallet values(null,?,0)";
			try {
				jdbcTemplate.update(query, uid);
			} catch (Exception e) {
				logger.info("代码有问题诶");
				return false;
			}
			return true;
		}
	}

	/**
	 * 目的：根据itcode得信息，申请一个钱包
	 * 
	 * @param itcode
	 * @return 返回这个操作是否成功的布尔值
	 */
	public static boolean initWallet(String itcode, JdbcTemplate jdbcTemplate) {
		User result = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		if (result == null) {
			return false;
		} else {
			boolean i = initWallet(result.getUid(), jdbcTemplate);
			if (i) {
				return true;
			} else
				return false;
		}
	}

	/**
	 * 获得我们所有的钱包信息
	 * 
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
	 * 目的：打钱进钱包
	 * @param wid 钱包
	 * @param lucknumber  数额
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
}

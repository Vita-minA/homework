package com.chenjian.dtss.DB;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.chenjian.dtss.BalanceController;
import com.chenjian.dtss.DB.UserDAO;
import com.chenjian.dtss.model.Trade;
import com.chenjian.dtss.model.User;
import com.chenjian.dtss.model.Wallet;

/**
 * @author 作者：陈健
 * @version 创建时间：2017年8月3日 下午7:04:30 说明：
 */
public class TradeDAO {
	// 用来显示信息以测试方法的正确性
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);

	// 这个是用来连接数据库池的
	/**
	 * 目的：查询某个人的交易记录-----><-------
	 * 
	 * @param uid
	 *            用户的id
	 * @return 返回的是该用户的trade对象list 未完成
	 */
	public static List<Trade> getTradesByUid(int uid, JdbcTemplate jdbcTemplate) {
		RowMapper<Trade> trade_mapper = new BeanPropertyRowMapper<Trade>(Trade.class);
		List<Trade> trade = new ArrayList<Trade>();
		try {
			String query = "select tid,trade.wid,volume,tradetime" + "from wallet,trade"
					+ "where wallet.wid=trade.wid and wallet.uid=?";
			trade = jdbcTemplate.query(query, trade_mapper, uid);
		} catch (Exception e) {
			return null;
		}
		for (int i = 0; i < trade.size(); i++) {
			logger.info(trade.get(i).toString());
		}
		return trade;
	}

	/**
	 * 目的：查询某个用户在一段时间内的交易记录
	 * 
	 * @param uid
	 *            用户的id值
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return 返回的是Trade的list 未完成
	 */
	public static List<Trade> getTradesByUid(int uid, String start, String end, JdbcTemplate jdbcTemplate) {
		RowMapper<Trade> trade_mapper = new BeanPropertyRowMapper<Trade>(Trade.class);
		List<Trade> trade = new ArrayList<Trade>();
		try {
			String query = "select * from trade where unix_timestamp(tradetime) > unix_timestamp(?) and unix_timestamp(tradetime) < unix_timestamp(?)";
			trade = jdbcTemplate.query(query, trade_mapper, new Object[] { start, end });
		} catch (Exception e) {
			return null;
		}
		return trade;
	}

	/**
	 * 目的：通过itcode获得这个人的交♂易记录
	 * 
	 * @param itcode
	 *            账户
	 * @param jdbcTemplate
	 *            数据库连接池
	 * @return 返回交易记录list
	 */
	public static List<Trade> getTradesByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		User user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		if (user == null) {
			return null;
		} else
			return getTradesByUid(user.getUid(), jdbcTemplate);
	}

	/**
	 * 目的：根据user对象获得这个交易信息
	 * 
	 * @param user
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Trade> getTradesByUser(User user, JdbcTemplate jdbcTemplate) {
		return getTradesByUid(user.getUid(), jdbcTemplate);
	}

	// 根据交易记录编号来获取交易信息
	public static Trade getTradeByTid(int tid, JdbcTemplate jdbcTemplate) {
		RowMapper<Trade> trade_mapper = new BeanPropertyRowMapper<Trade>(Trade.class);
		String query = "select * from Trade where tid=?";
		try {
			Trade trade = jdbcTemplate.queryForObject(query, trade_mapper, tid);
			return trade;
		} catch (Exception e) {
			logger.info("完蛋啦");
			return null;
		}
	}

	/**
	 * 目的:判断交易能不能够进行
	 * 
	 * @param wid
	 *            钱包的编号
	 * @param amount
	 *            我们要打赏的金额
	 * @return 返回我们明智的判断
	 */
	private static boolean preTrade(int wid, int amount, JdbcTemplate jdbcTemplate) {
		Wallet wallet = WalletDAO.getWalletById(wid, jdbcTemplate);
		if (wallet.getAmount() >= amount) {
			return true;
		} else
			return false;
	}
	/**
	 * 目的：添加交易记录
	 * @param wid 钱包	
	 * @param amount	数额	
	 * @param date	时间	
	 * @param memo	备注消息
	 * @param jdbcTemplate 
	 * @return 返回成功与否
	 */
	public static int createTrade(int wid, int amount, String date, String memo, JdbcTemplate jdbcTemplate) {
		if (preTrade(wid, amount, jdbcTemplate)) {
			// 写入交易数据
			String query = "insert into trade values(null,?,?,?,null)";
			Timestamp t = Timestamp.valueOf(query);
			int i = jdbcTemplate.update(query, new Object[] { wid, amount, t });
			return 1;
		}
		return 0; 
	}

}

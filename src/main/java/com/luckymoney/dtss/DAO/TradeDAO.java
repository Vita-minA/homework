package com.luckymoney.dtss.DAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.luckymoney.dtss.BalanceController;
import com.luckymoney.dtss.DAO.UserDAO;
import com.luckymoney.dtss.model.Trade;
import com.luckymoney.dtss.model.User;
import com.luckymoney.dtss.model.Wallet;

public class TradeDAO {
	// 用来显示信息以测试方法的正确性
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
	
	/**
	 * 获取所有的trade
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Trade> getAllTrades( JdbcTemplate jdbcTemplate) {
		RowMapper<Trade> trade_mapper = new BeanPropertyRowMapper<Trade>(Trade.class);
		List<Trade> trade = new ArrayList<Trade>();
		try {
			String query = "select * from Trade ";
			trade = jdbcTemplate.query(query, trade_mapper);
		} catch (Exception e) {
			return null;
		}
		return trade;
	}

	/**
	 * 查询某个用户的交易记录-----><------- 
	 * @param uid 用户的id
	 * @return 返回的是该用户的所有交易记录
	 */
	public static List<Trade> getTradesByUid(int uid, JdbcTemplate jdbcTemplate) {
		RowMapper<Trade> trade_mapper = new BeanPropertyRowMapper<Trade>(Trade.class);
		List<Trade> trade = new ArrayList<Trade>();
		try {
			String query = "select * from Trade where uid=?";
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
	 * 查询在一段时间内的交易记录
	 * @param uid 用户的id值
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return 返回的是某段时间内的交易记录
	 */
	public static List<Trade> getTradesByTime( Timestamp start, Timestamp end, JdbcTemplate jdbcTemplate) {
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
	 * 获得username用户某段时间的所有交易记录
	 * @param username
	 * @param start
	 * @param end
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Trade> getTradesByUsernameandTime( String username,Timestamp start, Timestamp end, JdbcTemplate jdbcTemplate) {
		RowMapper<Trade> trade_mapper = new BeanPropertyRowMapper<Trade>(Trade.class);
		List<Trade> trade = new ArrayList<Trade>();
		try {
			String query = "select * from trade where unix_timestamp(tradetime) > unix_timestamp(?) and unix_timestamp(tradetime) < unix_timestamp(?) and username=?";
			trade = jdbcTemplate.query(query, trade_mapper, new Object[] { start, end,username });
		} catch (Exception e) {
			return null;
		}
		return trade;
	}
	
	/**
	 * 获得某段时间的某类记录
	 * @param start
	 * @param end
	 * @param memo
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Trade> getTradesByTimeandMemo( Timestamp start, Timestamp end, String memo,JdbcTemplate jdbcTemplate) {
		RowMapper<Trade> trade_mapper = new BeanPropertyRowMapper<Trade>(Trade.class);
		List<Trade> trade = new ArrayList<Trade>();
		try {
			String query = "select * from trade where unix_timestamp(tradetime) > unix_timestamp(?) and unix_timestamp(tradetime) < unix_timestamp(?) and memo=?";
			trade = jdbcTemplate.query(query, trade_mapper, new Object[] { start, end,memo });
		} catch (Exception e) {
			return null;
		}
		return trade;
	}
	/**
	 * 通过username获得某段时间内的交易记录
	 * @param username
	 * @param start
	 * @param end
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Trade> getTradesByUsernameandTimeandMemo(String username, Timestamp start, Timestamp end, String memo,JdbcTemplate jdbcTemplate) {

		RowMapper<Trade> trade_mapper = new BeanPropertyRowMapper<Trade>(Trade.class);
		List<Trade> trade = new ArrayList<Trade>();
		try {
			String query = "select * from trade where unix_timestamp(tradetime) > unix_timestamp(?) and unix_timestamp(tradetime) < unix_timestamp(?) and username=? and memo=?";
			trade = jdbcTemplate.query(query, trade_mapper, new Object[] { start, end,username,memo});
		} catch (Exception e) {
			return null;
		}
		return trade;
	}
	
	/**
	 * 通过itcode获得交易记录
	 * @param itcode 用户账户
	 * @param jdbcTemplate 数据库连接池
	 * @return 返回交易记录
	 */
	public static List<Trade> getTradesByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		User user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		if (user == null) {
			return null;
		} else
			return getTradesByUid(user.getUid(), jdbcTemplate);
	}

	/**
	 * 根据user对象获得这个交易信息* 
	 * @param user
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Trade> getTradesByUser(User user, JdbcTemplate jdbcTemplate) {
		return getTradesByUid(user.getUid(), jdbcTemplate);
	}

	/**
	 * 根据交易记录编号查询交易信息
	 */
	public static Trade getTradeByTid(int tid, JdbcTemplate jdbcTemplate) {
		RowMapper<Trade> trade_mapper = new BeanPropertyRowMapper<Trade>(Trade.class);
		String query = "select * from Trade where tid=?";
		try {
			Trade trade = jdbcTemplate.queryForObject(query, trade_mapper, tid);
			return trade;
		} catch (Exception e) {
			logger.info("根据记录编号获得交易信息--失败！");
			return null;
		}
	}

	/**
	 * 判断交易（用户钱包--打赏）能不能够进行
	 * @param wid  钱包的编号
	 * @param amount  我们要打赏的金额
	 * @return  是否可以打赏
	 */
	private static boolean preawardTrade(int wid, int amount, JdbcTemplate jdbcTemplate) {
		Wallet wallet = WalletDAO.getWalletById(wid, jdbcTemplate);
		if (wallet.getAmount() >= amount) {
			return true;
		} else
			return false;
	}
	/**
	 * 判断主持人添加红包总钱数是否小于公司账户余额
	 * @param number
	 * @param jdbcTemplate
	 * @return
	 */
	private static boolean pretotalTrade( int number, JdbcTemplate jdbcTemplate) {
		Wallet wallet = WalletDAO.getWalletById(1, jdbcTemplate);
		if (wallet.getAmount() >= number) {
			return true;
		} else
			return false;
	}
	
	
	/**
	 * admin向红包充值记录
	 * @param number
	 * @param jdbcTemplate
	 * @return
	 */
	public static boolean createtotalTrade( int number, JdbcTemplate jdbcTemplate) {
		if (pretotalTrade( number, jdbcTemplate)) {
			// 写入交易数据
			Timestamp date= new Timestamp(System.currentTimeMillis()); 
			String memo="公司账户admin提取到红包";
			String query = "insert into Trade values(null,?,?,?,?,?,?)";
			int i = jdbcTemplate.update(query, new Object[] { 1,1,"admin", number, date ,memo});
			if (i>0);return true;
		}
		return false; 
	}

	/**
	 * 目的：添加交易记录（用户钱包--打赏）	
	 * @param wid 钱包	
	 * @param amount  打赏金额	
	 * @param date	时间	
	 * @param memo	备注消息（节目）
	 * @param jdbcTemplate 
	 * @return 返回成功与否
	 */
	public static int createawardTrade(int wid, int uid,String username,int amount, Timestamp date,String memo,JdbcTemplate jdbcTemplate) {
		if (preawardTrade(wid, amount, jdbcTemplate)) {
			// 写入交易数据
			String query = "insert into Trade values(null,?,?,?,?,?,?)";
			int i = jdbcTemplate.update(query, new Object[] { wid,uid,username, amount, date ,memo});
			if (i>0);return 1;
		}
		return 0; 
	}
	/**
	 * 添加用户获得红包记录
	 * @param wid
	 * @param number
	 * @param date
	 * @param round
	 * @param jdbcTemplate
	 * @return
	 */
	public static int createluckyTrade(int wid, int uid,String username,int number, Timestamp date,int round,JdbcTemplate jdbcTemplate) {
		    String memo="红包雨--第"+round+"轮获得红包";
			String query = "insert into Trade values(null,?,?,?,?,?,?)";
			int i = jdbcTemplate.update(query, new Object[] { wid,uid,username, number, date ,memo});
			if (i>0) return 1;
			else return 0; 
	}
	
	/**
	 * 抢红包金额
	 * @param wid
	 * @param uid
	 * @param username
	 * @param number
	 * @param date
	 * @param round
	 * @param jdbcTemplate
	 * @return
	 */
	public static int createsnatchTrade(int wid, int uid,String username,int number, Timestamp date,int round,JdbcTemplate jdbcTemplate) {
	    String memo="抢红包环节获得";
		String query = "insert into Trade values(null,?,?,?,?,?,?)";
		int i = jdbcTemplate.update(query, new Object[] { wid,uid,username, number, date ,memo});
		if (i>0) return 1;
		else return 0; 
		}

	
	/**
	 * 目的：根据页获取交易记录
	 * @param rid1
	 * @param rid2
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Trade> getTradesByPage(int rid1, int rid2, JdbcTemplate jdbcTemplate) {
		RowMapper<Trade> trade_mapper = new BeanPropertyRowMapper<Trade>(Trade.class);
		List<Trade> trade = new ArrayList<Trade>();
		try {
			String query = "select * from trade where tid > ? and tid < ?";
			trade = jdbcTemplate.query(query, trade_mapper,new Object[] {rid1,rid2});
		} catch (Exception e) {
			return null;
		}
		return trade;
	}
	/**
	 * 目的：获得记录的数目
	 * 
	 * @param jdbcTemplate
	 * @return
	 */
	public static int getnum(JdbcTemplate jdbcTemplate) {
		String query = "select count(*) from trade";
		int num = 0;
		try {
			num = jdbcTemplate.queryForInt(query);
		} catch (Exception e) {
			return 0;
		}
		return num;
	}
	
	
	
	
	//赖
	public static List<Trade> getTrades(String username,String itcode,JdbcTemplate jdbcTemplate){
		RowMapper<User> user_mapper = new BeanPropertyRowMapper<User>(User.class);
		User user = jdbcTemplate.queryForObject("select * from user where itcode=? and username=?", user_mapper,
				new Object[] { itcode, username });
		RowMapper<Wallet> wallet_mapper = new BeanPropertyRowMapper<Wallet>(Wallet.class);
		Wallet wallet = jdbcTemplate.queryForObject("select * from wallet where uid=?", wallet_mapper,
				user.getUid()); 
		RowMapper<Trade> trade_mapper = new BeanPropertyRowMapper<Trade>(Trade.class);
		List<Trade> trade = new ArrayList<Trade>();
		trade = jdbcTemplate.query("select * from trade where wid=?", trade_mapper, wallet.getWid());
		return trade; 
		 
	}  
}

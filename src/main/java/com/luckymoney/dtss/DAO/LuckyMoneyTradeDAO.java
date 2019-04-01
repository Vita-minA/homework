package com.luckymoney.dtss.DAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.luckymoney.dtss.AdminController;
import com.luckymoney.dtss.BalanceController;
import com.luckymoney.dtss.model.Lucky_money_record;
import com.luckymoney.dtss.model.Trade;
import com.luckymoney.dtss.model.User;
import com.luckymoney.dtss.model.Wallet;

public class LuckyMoneyTradeDAO {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	/**
	 * 添加红包雨记录（公司账户交易--红包雨）
	 * 
	 * @return 返回我们的操作成果 0失败，1成功
	 */
	public static int newRecord(int wid, int uid, String username, int number, Timestamp currenttime, int round,
			JdbcTemplate jdbcTemplate) {
		String query = "insert into lucky_money_record values(null,?,?,?,?,?,?)";
		try {
			jdbcTemplate.update(query, new Object[] { wid, uid, username, number, currenttime, round });
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获得所有用户的所有记录
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Lucky_money_record> getAllRecords(JdbcTemplate jdbcTemplate) {
		RowMapper<Lucky_money_record> trade_mapper = new BeanPropertyRowMapper<Lucky_money_record>(
				Lucky_money_record.class);
		List<Lucky_money_record> trade = new ArrayList<Lucky_money_record>();
		try {
			String query = "select * from Lucky_money_record";
			trade = jdbcTemplate.query(query, trade_mapper);
		} catch (Exception e) {
			return null;
		}
		for (int i = 0; i < trade.size(); i++) {
			logger.info(trade.get(i).toString());
		}
		return trade;
	}

	/**
	 * 获得第i轮所有用户的所有记录
	 * @param round
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Lucky_money_record> getAllRecordsbyround(int round, JdbcTemplate jdbcTemplate) {
		RowMapper<Lucky_money_record> trade_mapper = new BeanPropertyRowMapper<Lucky_money_record>(
				Lucky_money_record.class);
		List<Lucky_money_record> trade = new ArrayList<Lucky_money_record>();
		try {
			String query = "select * from Lucky_money_record where round=?";
			trade = jdbcTemplate.query(query, trade_mapper, round);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return trade;
	}

	/**
	 * 查询某段时间发给用户username的所有红包发放记录
	 * 
	 * @param username
	 * @param start
	 * @param end
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Lucky_money_record> getRecordsByUsernameandTime(String username, Timestamp start, Timestamp end,
			JdbcTemplate jdbcTemplate) {

		RowMapper<Lucky_money_record> trade_mapper = new BeanPropertyRowMapper<Lucky_money_record>(
				Lucky_money_record.class);
		List<Lucky_money_record> trade = new ArrayList<Lucky_money_record>();
		try {
			String query = "select * from Lucky_money_record where unix_timestamp(tradetime) > unix_timestamp(?) and unix_timestamp(tradetime) < unix_timestamp(?) and username=?";
			trade = jdbcTemplate.query(query, trade_mapper, new Object[] { start, end, username });
		} catch (Exception e) {
			return null;
		}
		return trade;
	}

	
	/**
	 * 获得某段时间的所有记录
	 * @param start起始时间
	 * @param end终止时间
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Lucky_money_record> getRecordsByTime(Timestamp start, Timestamp end, JdbcTemplate jdbcTemplate) {

		RowMapper<Lucky_money_record> trade_mapper = new BeanPropertyRowMapper<Lucky_money_record>(
				Lucky_money_record.class);
		List<Lucky_money_record> trade = new ArrayList<Lucky_money_record>();
		try {
			String query = "select * from Lucky_money_record where unix_timestamp(tradetime) > unix_timestamp(?) and unix_timestamp(tradetime) < unix_timestamp(?)  ";
			trade = jdbcTemplate.query(query, trade_mapper, new Object[] { start, end, });
		} catch (Exception e) {
			return null;
		}
		return trade;
	}

	/**
	 * 获得某个用户某段时间获得第i轮红包的记录
	 * @param username
	 * @param start
	 * @param end
	 * @param round
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Lucky_money_record> getRecordsByUsernameandTimeandRound(String username, Timestamp start,
			Timestamp end, int round, JdbcTemplate jdbcTemplate) {

		RowMapper<Lucky_money_record> trade_mapper = new BeanPropertyRowMapper<Lucky_money_record>(
				Lucky_money_record.class);
		List<Lucky_money_record> trade = new ArrayList<Lucky_money_record>();
		try {
			String query = "select * from Lucky_money_record where unix_timestamp(tradetime) > unix_timestamp(?) and unix_timestamp(tradetime) < unix_timestamp(?) and username=? and round=?";
			trade = jdbcTemplate.query(query, trade_mapper, new Object[] { start, end, username, round });
		} catch (Exception e) {
			return null;
		}
		return trade;
	}

	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 目的：根据页首的id和页尾的id值返回本页所有的记录
	 * 
	 * @param rid1
	 *            页首id
	 * @param rid2
	 *            页尾id
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Lucky_money_record> getRecordsByPage(int rid1, int rid2, JdbcTemplate jdbcTemplate) {
		RowMapper<Lucky_money_record> trade_mapper = new BeanPropertyRowMapper<Lucky_money_record>(
				Lucky_money_record.class);
		List<Lucky_money_record> trade = new ArrayList<Lucky_money_record>();
		try {
			String query = "select * from Lucky_money_record where rid > ? and rid < ?";
			trade = jdbcTemplate.query(query, trade_mapper, new Object[] { rid1, rid2 });
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
		String query = "select count(*) from Lucky_money_record";
		int num = 0;
		try {
			num = jdbcTemplate.queryForInt(query);
		} catch (Exception e) {
			return 0;
		}
		return num;
	}

}

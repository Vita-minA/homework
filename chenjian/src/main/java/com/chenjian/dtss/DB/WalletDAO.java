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
 * @author ǧ��
 *
 */
public class WalletDAO {
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);

	/**
	 * Ŀ�ģ������ʹ��Ǯ����������һ��������ע���ڴ˻�����������Ҫ���ת�˼�¼�ġ�
	 * 
	 * @param itcode���û��˺�
	 * @param username���û���
	 * @param amount����Ǯ������
	 * @param locale��ʱ��
	 * @param jdbcTemplate��ģ��
	 * @return �������ǳ�Ǯ�Ĳ����Ƿ�ɹ�
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
	 * Ŀ�ģ���ѯһ��ʱ���ڵ�ת�˼�¼
	 * 
	 * @param from
	 *            ��ʼʱ��
	 * @param to
	 *            ��ֹʱ��
	 * @param jdbcTemplate
	 *            ���ݿ��
	 * @return ���ؽ����׼�¼
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
	 * Ŀ�ģ�ͨ��uid��ȡǮ����Ϣ
	 * 
	 * @param uid
	 * @return �������Ǯ������Ϣ
	 */
	public static Wallet getWalletById(int uid, JdbcTemplate jdbcTemplate) {
		RowMapper<Wallet> wallet_mapper = new BeanPropertyRowMapper<Wallet>(Wallet.class);
		String query = "select * from Wallet where uid = ?";
		Wallet result = new Wallet();
		try {
			result = jdbcTemplate.queryForObject(query, wallet_mapper, uid);
			logger.info(result.toString());
		} catch (Exception e) {
			logger.info("����Ŷ");
			return null;
		}
		return result;
	}

	/**
	 * Ŀ�ģ�ͨ��itcode���Ǯ���������Ϣ
	 * 
	 * @param itcode
	 *            �û��˺�
	 * @param jdbcTemplate
	 *            ���ݳ�
	 * @return �������Ǯ������Ϣ
	 */
	public static Wallet getWalletByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		RowMapper<Wallet> wallet_mapper = new BeanPropertyRowMapper<Wallet>(Wallet.class);
		String query = "select * from Wallet where itcode = ?";
		Wallet result = new Wallet();
		try {
			result = jdbcTemplate.queryForObject(query, wallet_mapper, itcode);
			logger.info(result.toString());
		} catch (Exception e) {
			logger.info("����Ŷ");
			return null;
		}
		return result;
	}

	/**
	 * Ŀ�ģ������û�����Ϣ��Ϊ����û�����һ��Ǯ����������һ��Ǯ��
	 * 
	 * @param uid
	 * @return ������������Ƿ�ɹ��Ĳ���ֵ
	 */
	public static boolean initWallet(int uid, JdbcTemplate jdbcTemplate) {
		User result = UserDAO.getUserByUid(uid, jdbcTemplate);
		// Ϊ�յĻ�������˻��Ͳ����ڣ����ǾͲ��ܸ�����˴����˻���
		if (result == null) {
			return false;
		} else {
			String query = "insert into Wallet values(null,?,0)";
			try {
				jdbcTemplate.update(query, uid);
			} catch (Exception e) {
				logger.info("������������");
				return false;
			}
			return true;
		}
	}

	/**
	 * Ŀ�ģ�����itcode����Ϣ������һ��Ǯ��
	 * 
	 * @param itcode
	 * @return ������������Ƿ�ɹ��Ĳ���ֵ
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
	 * ����������е�Ǯ����Ϣ
	 * 
	 * @return ����һ��Ǯ����list
	 */
	public static List<Wallet> getAllWallets(JdbcTemplate jdbcTemplate) {
		RowMapper<Wallet> wallet_mapper = new BeanPropertyRowMapper<Wallet>(Wallet.class);
		List<Wallet> wallet = new ArrayList<Wallet>();
		String query = "select * from Wallet";
		wallet = jdbcTemplate.query(query, wallet_mapper);
		return wallet;
	}
	/**
	 * Ŀ�ģ���Ǯ��Ǯ��
	 * @param wid Ǯ��
	 * @param lucknumber  ����
	 * @param template 
	 * @return ������Ĳ���0ʧ�ܣ�1�ɹ�
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

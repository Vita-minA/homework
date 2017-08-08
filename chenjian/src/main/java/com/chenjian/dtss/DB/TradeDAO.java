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
 * @author ���ߣ��½�
 * @version ����ʱ�䣺2017��8��3�� ����7:04:30 ˵����
 */
public class TradeDAO {
	// ������ʾ��Ϣ�Բ��Է�������ȷ��
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);

	// ����������������ݿ�ص�
	/**
	 * Ŀ�ģ���ѯĳ���˵Ľ��׼�¼-----><-------
	 * 
	 * @param uid
	 *            �û���id
	 * @return ���ص��Ǹ��û���trade����list δ���
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
	 * Ŀ�ģ���ѯĳ���û���һ��ʱ���ڵĽ��׼�¼
	 * 
	 * @param uid
	 *            �û���idֵ
	 * @param start
	 *            ��ʼʱ��
	 * @param end
	 *            ����ʱ��
	 * @return ���ص���Trade��list δ���
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
	 * Ŀ�ģ�ͨ��itcode�������˵Ľ����׼�¼
	 * 
	 * @param itcode
	 *            �˻�
	 * @param jdbcTemplate
	 *            ���ݿ����ӳ�
	 * @return ���ؽ��׼�¼list
	 */
	public static List<Trade> getTradesByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		User user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		if (user == null) {
			return null;
		} else
			return getTradesByUid(user.getUid(), jdbcTemplate);
	}

	/**
	 * Ŀ�ģ�����user���������������Ϣ
	 * 
	 * @param user
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Trade> getTradesByUser(User user, JdbcTemplate jdbcTemplate) {
		return getTradesByUid(user.getUid(), jdbcTemplate);
	}

	// ���ݽ��׼�¼�������ȡ������Ϣ
	public static Trade getTradeByTid(int tid, JdbcTemplate jdbcTemplate) {
		RowMapper<Trade> trade_mapper = new BeanPropertyRowMapper<Trade>(Trade.class);
		String query = "select * from Trade where tid=?";
		try {
			Trade trade = jdbcTemplate.queryForObject(query, trade_mapper, tid);
			return trade;
		} catch (Exception e) {
			logger.info("�군��");
			return null;
		}
	}

	/**
	 * Ŀ��:�жϽ����ܲ��ܹ�����
	 * 
	 * @param wid
	 *            Ǯ���ı��
	 * @param amount
	 *            ����Ҫ���͵Ľ��
	 * @return �����������ǵ��ж�
	 */
	private static boolean preTrade(int wid, int amount, JdbcTemplate jdbcTemplate) {
		Wallet wallet = WalletDAO.getWalletById(wid, jdbcTemplate);
		if (wallet.getAmount() >= amount) {
			return true;
		} else
			return false;
	}
	/**
	 * Ŀ�ģ���ӽ��׼�¼
	 * @param wid Ǯ��	
	 * @param amount	����	
	 * @param date	ʱ��	
	 * @param memo	��ע��Ϣ
	 * @param jdbcTemplate 
	 * @return ���سɹ����
	 */
	public static int createTrade(int wid, int amount, String date, String memo, JdbcTemplate jdbcTemplate) {
		if (preTrade(wid, amount, jdbcTemplate)) {
			// д�뽻������
			String query = "insert into trade values(null,?,?,?,null)";
			Timestamp t = Timestamp.valueOf(query);
			int i = jdbcTemplate.update(query, new Object[] { wid, amount, t });
			return 1;
		}
		return 0; 
	}

}

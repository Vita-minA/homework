package com.chenjian.dtss.DB;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.chenjian.dtss.DB.UserDAO;
import com.chenjian.dtss.model.User;
import com.chenjian.dtss.model.User_Wallet;

/**
 * @author ���ߣ��½�
 * @version ����ʱ�䣺2017��8��3�� ����7:05:18 ˵����
 */

public class UserWalletDAO {
	/**
	 * Ŀ�ģ�����user��������ȡ���ǵ�Ǯ����Ϣ
	 * 
	 * @param user
	 * @return ����һ��������Ǯ����Ϣ
	 */
	public User_Wallet getWallInfoByUser(User user, JdbcTemplate jdbcTemplate) {
		RowMapper<User_Wallet> userwallet_mapper = new BeanPropertyRowMapper<User_Wallet>(User_Wallet.class);
		String query = "select * from user_wallet where uid=?";
		try {
			User_Wallet userwallet = jdbcTemplate.queryForObject(query, userwallet_mapper, user.getUid());
			return userwallet;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Ŀ�ģ�����uid���Ǯ������Ϣ
	 * 
	 * @param uid
	 * @param jdbcTemplate
	 * @return ������Ǯ����Ϣ
	 */
	public User_Wallet getWallInfoByUid(int uid, JdbcTemplate jdbcTemplate) {
		User user = UserDAO.getUserByUid(uid, jdbcTemplate);
		return getWallInfoByUser(user, jdbcTemplate);
	}

	/**
	 * Ŀ�ģ������û��˺Ż��Ǯ������Ϣ
	 * 
	 * @param itcode
	 * @param jdbcTemplate
	 * @return ������Ǯ����Ϣ
	 */
	public User_Wallet getWallInfoByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		User user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return getWallInfoByUser(user, jdbcTemplate);
	}

	/**
	 * Ŀ�ģ���ʾ���е�Ǯ����Ϣ
	 * 
	 * @return
	 */
	public List<User_Wallet> getAllWallInfoByUser(JdbcTemplate jdbcTemplate) {
		RowMapper<User_Wallet> userwallet_mapper = new BeanPropertyRowMapper<User_Wallet>(User_Wallet.class);
		List<User_Wallet> result = new ArrayList<User_Wallet>();
		String query = "select * from User_Wallet";
		try {
			result = jdbcTemplate.query(query, userwallet_mapper);
			return result;
		} catch (Exception e) {
			return null;
		}
	}
}

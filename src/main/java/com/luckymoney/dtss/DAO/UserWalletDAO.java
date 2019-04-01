package com.luckymoney.dtss.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.luckymoney.dtss.DAO.UserDAO;
import com.luckymoney.dtss.model.User;
import com.luckymoney.dtss.model.User_Wallet;

public class UserWalletDAO {
	/**
	 * 目的：根据user这个对象获取我们的钱包信息
	 * @param user
	 * @return 返回一个完整的钱包信息
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
	 * 目的：根据uid获得钱包的信息
	 * @param uid
	 * @param jdbcTemplate
	 * @return 完整的钱包信息
	 */
	public User_Wallet getWallInfoByUid(int uid, JdbcTemplate jdbcTemplate) {
		User user = UserDAO.getUserByUid(uid, jdbcTemplate);
		return getWallInfoByUser(user, jdbcTemplate);
	}

	/**
	 * 目的：根据用户账号获得钱包的信息
	 * @param itcode
	 * @param jdbcTemplate
	 * @return 完整的钱包信息
	 */
	public User_Wallet getWallInfoByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		User user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return getWallInfoByUser(user, jdbcTemplate);
	}

	/**
	 * 目的：显示所有的钱包信息
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

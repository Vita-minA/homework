package com.luckymoney.dtss.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.luckymoney.dtss.model.Onlineuser;

/**
*@author 作者：陈健
*@version 创建时间：2017年8月21日 上午1:13:25
*说明：方法
*/
public class OnlineuserDAO {
	/**
	 * 目的：根据username检测是否在线
	 * @param username
	 * @param jdbcTemplate
	 * @return true在线 false不在线
	 */
	public static boolean IsOnline(String username, JdbcTemplate jdbcTemplate) {
		RowMapper<Onlineuser> Onlineuser_mapper = new BeanPropertyRowMapper<Onlineuser>(Onlineuser.class);
		String query="select * from Onlineuser where username=?";
		Onlineuser user=new Onlineuser();
		try {
			user=jdbcTemplate.queryForObject(query,Onlineuser_mapper,username);
			if(user==null) {
				//不在线
				return false;
			}
			else return true;
		} catch (Exception e) {
			return false;
		}

	}
	/**
	 * 目的：添加在线用户
	 * @param username
	 * @param jdbcTemplate
	 */
	public static void insertOnline(String username, JdbcTemplate jdbcTemplate) {
		String query="insert into onlineuser(username) values(?)";
		try {
			jdbcTemplate.update(query,username);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 目的：删除在线用户
	 * @param username
	 * @param jdbcTemplate
	 */
	public static void deleteOnline(String username,JdbcTemplate jdbcTemplate) {
		String query="delete from onlineuser where username=?";
		try {
			jdbcTemplate.update(query,username);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 目的：删除所有的在线用户（防止服务器崩了的情况）
	 * @param jdbcTemplate
	 */
	public static void deleteAllOnlineuser(JdbcTemplate jdbcTemplate) {
		String query="delete from onlineuser";
		try {
			jdbcTemplate.update(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

package com.luckymoney.dtss.DAO;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.luckymoney.dtss.BalanceController;
import com.luckymoney.dtss.model.Lucky_money;
import com.luckymoney.dtss.model.User;
public class LuckyDAO {
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
	
	/**
	 * admin向红包雨和抢红包充值
	 * @param jdbcTemplate
	 * @param round1number,round2number,round3number,number分别为三轮红包雨发放金额和抢红包环节金额
	 * @param number
	 * @return
	 */
	public static boolean LuckyRainTotal( String round1number,String round2number,String round3number, String number,JdbcTemplate jdbcTemplate) {
		RowMapper<Lucky_money> Lucky_money_mapper = new BeanPropertyRowMapper<Lucky_money>(Lucky_money.class);
		List<Lucky_money> wallet = new ArrayList<Lucky_money>();
		String query1="select * from Lucky_money";	
		wallet = jdbcTemplate.query(query1, Lucky_money_mapper);
		if(wallet.isEmpty())
		{
			String query = "insert into Lucky_money values(null,?,?)";
			try {
				jdbcTemplate.update(query, new Object[] { 1,Integer.valueOf(round1number).intValue()*100 });
				jdbcTemplate.update(query, new Object[] { 2,Integer.valueOf(round2number).intValue()*100  });
				jdbcTemplate.update(query, new Object[] { 3,Integer.valueOf(round3number).intValue()*100 });
				jdbcTemplate.update(query, new Object[] {4,Integer.valueOf(number).intValue()*100  });
				return true;
			} catch (Exception e) {
				logger.info("主持人人初始化--失败！");
				return false;
			}
		}		
		else {
			String query = "update Lucky_money set Total=Total+? where round=?";
		try {
			jdbcTemplate.update(query, new Object[] { Integer.valueOf(round1number).intValue()*100,1 });
			jdbcTemplate.update(query, new Object[] { Integer.valueOf(round2number).intValue()*100,2  });
			jdbcTemplate.update(query, new Object[] { Integer.valueOf(round3number).intValue()*100,3 });
			jdbcTemplate.update(query, new Object[] { Integer.valueOf(number).intValue()*100,4  });
			return true;
		} catch (Exception e) {
			logger.info("主持人人初始化--失败！");
			return false;
		}
		}
	}
	
	/**
	 * admin开启抢红包功能时修改数据库中属性的值
	 * @param jdbcTemplate
	 * @return
	 */
	public static boolean startsnatch(JdbcTemplate jdbcTemplate)
	{
		try {
			System.out.println("fafa");
			String query = "update user set islock=? where isadmin=?";
			jdbcTemplate.update(query, new Object[] { 1, 1});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 用户点击抢红包时判断抢红包功能是否开启
	 * @param jdbcTemplate
	 * @return
	 */
	public static int judgesnatch(JdbcTemplate jdbcTemplate)
	{
		RowMapper<User> user_mapper = new BeanPropertyRowMapper<User>(User.class);
		User user=new User();
		String query="select * from user where isadmin=?";
		try {
			user=jdbcTemplate.queryForObject(query, user_mapper, 1);
			System.out.println("fafa");
			int result=user.getIslock();
			System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 2;
	}

}

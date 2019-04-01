package com.luckymoney.dtss.DAO;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.luckymoney.dtss.BalanceController;
import com.luckymoney.dtss.model.Lucky_money;

public class LuckyMoneyDAO {
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
	/**
	 * 目的：返回第i轮红包雨剩余总金额
	 * @param i 第i轮
	 * @param jdbcTemplate
	 * @return
	 */
	public static int getTotalbyRound(int i,JdbcTemplate jdbcTemplate) {
		RowMapper<Lucky_money> luckymoney_mapper = new BeanPropertyRowMapper<Lucky_money>(Lucky_money.class);
		String query="select * from lucky_money where round=?";
		try {
			logger.info(jdbcTemplate.toString());
			Lucky_money result=jdbcTemplate.queryForObject(query, luckymoney_mapper,i);
			return result.getTotal();
		}catch(Exception e) {
			logger.info("无法获得红包雨总金额"+i);
			return 0;
		}
	}
	/**
	 * 红包雨剩余总额（计减）
	 * @param round 轮次
	 * @param number 发放的红包数额
	 * @return 1 成功, 0失败
	 */
	public static int luckyRain(int round, int number,JdbcTemplate jdbcTemplate) {		
		int j = jdbcTemplate.update("update Lucky_money set Total = Total - ? where round=?",new Object[]{number,round});
			if(j>0) {
				return 1;
			}		
		else return 0;
	}
}

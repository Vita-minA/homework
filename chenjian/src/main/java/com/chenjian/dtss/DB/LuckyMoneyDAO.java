package com.chenjian.dtss.DB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.chenjian.dtss.BalanceController;
import com.chenjian.dtss.model.Lucky_money;;

/**
*@author 作者：陈健
*@version 创建时间：2017年8月7日 下午11:32:51
*说明：
*/
public class LuckyMoneyDAO {
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
	/**
	 * 目的：返回第几轮的要发的钱
	 * @param i 第几轮
	 * @param jdbcTemplate
	 * @return
	 */
	public static int getTotalbyRound(int i,JdbcTemplate jdbcTemplate) {
		RowMapper<Lucky_money> luckymoney_mapper = new BeanPropertyRowMapper<Lucky_money>(Lucky_money.class);
		String query="select * from lucky_money where round=?";
		try {
			Lucky_money result=jdbcTemplate.queryForObject(query, luckymoney_mapper,i);
			return result.getTotal();
		}catch(Exception e) {
			logger.info("错了啦");
			return 0;
		}
	}
	/**
	 * 红包总额计减
	 * @param round 轮次
	 * @param number 红包数额
	 * @return 1 成功, 0失败
	 */
	public static int luckyRain(int round, int number) {
		return 0;
	}
}

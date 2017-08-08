package com.chenjian.dtss.DB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.chenjian.dtss.BalanceController;
import com.chenjian.dtss.model.Lucky_money;;

/**
*@author ���ߣ��½�
*@version ����ʱ�䣺2017��8��7�� ����11:32:51
*˵����
*/
public class LuckyMoneyDAO {
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
	/**
	 * Ŀ�ģ����صڼ��ֵ�Ҫ����Ǯ
	 * @param i �ڼ���
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
			logger.info("������");
			return 0;
		}
	}
	/**
	 * ����ܶ�Ƽ�
	 * @param round �ִ�
	 * @param number �������
	 * @return 1 �ɹ�, 0ʧ��
	 */
	public static int luckyRain(int round, int number) {
		return 0;
	}
}

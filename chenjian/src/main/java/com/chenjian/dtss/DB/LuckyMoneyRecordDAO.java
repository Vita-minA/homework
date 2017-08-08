package com.chenjian.dtss.DB;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.chenjian.dtss.model.Lucky_money_record;

/**
*@author 作者：陈健
*@version 创建时间：2017年8月8日 上午9:29:51
*说明：
*/
public class LuckyMoneyRecordDAO {
	/**
	 * 目的：添加新的红包记录到我们的数据库当中
	 * @return 返回我们的操作成果 0失败，1成功
	 */
	public static int newRecord(int wid,int lucky_number,int round, JdbcTemplate jdbcTemplate) {
		String query="insert into lucky_money_record values(null,?,?,?)";
		try {
			jdbcTemplate.update(query,new Object[] {wid,lucky_number,round});
			return 1;
		}catch(Exception e) {
			return 0;
		}
	}
	public static List<Lucky_money_record> getAllRecords() {
		return null;
	}
}

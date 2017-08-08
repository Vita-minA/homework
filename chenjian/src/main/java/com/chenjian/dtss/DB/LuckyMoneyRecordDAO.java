package com.chenjian.dtss.DB;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.chenjian.dtss.model.Lucky_money_record;

/**
*@author ���ߣ��½�
*@version ����ʱ�䣺2017��8��8�� ����9:29:51
*˵����
*/
public class LuckyMoneyRecordDAO {
	/**
	 * Ŀ�ģ�����µĺ����¼�����ǵ����ݿ⵱��
	 * @return �������ǵĲ����ɹ� 0ʧ�ܣ�1�ɹ�
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

package com.chenjian.dtss.DB;

import org.springframework.jdbc.core.JdbcTemplate;

import com.chenjian.dtss.model.Wallet;

/**
 * @author 作者：陈健
 * @version 创建时间：2017年8月7日 下午7:24:13 说明：
 */
public class LuckyDAO {
	/**
	 * 
	 * @param jdbcTemplate
	 * @param wallet
	 * @param number
	 * @return
	 */
	public static int LuckyRain(JdbcTemplate jdbcTemplate, Wallet wallet, int number) {

		// 修改系统红包总余额
		// 添加红包记录
		// 添加交易记录
		// 给用户钱包加入指定数额

		return 0;
	}

}

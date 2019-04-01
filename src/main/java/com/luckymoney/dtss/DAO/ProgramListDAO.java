package com.luckymoney.dtss.DAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.luckymoney.dtss.BalanceController;
import com.luckymoney.dtss.model.Program;
import com.luckymoney.dtss.model.User;
import com.luckymoney.dtss.model.Wallet;

public class ProgramListDAO {
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
	
	/**
	 * ���ｫ��Ŀ������ӡ����ҳ��
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Program> Program_Lt(JdbcTemplate jdbcTemplate) {
		RowMapper<Program> Program_mapper = new BeanPropertyRowMapper<Program>(Program.class);
		List<Program> Program= new ArrayList<Program>();
		String query = "select * from Program";
		
		//�����Ҫ����Ŀ������Ϣչʾ��������������ط�
		Program= jdbcTemplate.query(query, Program_mapper);
		for (int i = 0; i < Program.size(); i++) {
			logger.info(Program.get(i).toString()); 
		}
		return Program;  
	} 
	
	/*public ModelAndView register(@ModelAttribute("form") Users usrs,RedirectAttributes arrt,
		@RequestParam("confirm_password")String repassowrd,@RequestParam("kaptchaImage")String kaptchaImage*/
	/**
	 * 目的：完成打赏操作，注意同时减少钱包钱，增加节目钱，添加交易记录
	 * @param itcode
	 * @param username
	 * @param pid
	 * @param amount
	 * @param jdbcTemplate
	 * @return
	 */
	public static int reward(String itcode, String username, String pid, int amount, JdbcTemplate jdbcTemplate) {
		logger.info(jdbcTemplate.toString());
		try {
			RowMapper<User> user_mapper = new BeanPropertyRowMapper<User>(User.class);
			User user = jdbcTemplate.queryForObject("select * from user where itcode=? and username=?", user_mapper,
					new Object[] { itcode, username });
			RowMapper<Wallet> wallet_mapper = new BeanPropertyRowMapper<Wallet>(Wallet.class);
			Wallet wallet = jdbcTemplate.queryForObject("select * from wallet where uid=?", wallet_mapper,
					user.getUid());
			if (amount == 0) {
				return 0;
			}
			if (amount > wallet.getAmount()) {
				return -2;
			} else {
				logger.info(jdbcTemplate.toString());
				java.util.Date date = new java.util.Date();
				Timestamp timeStamp = new Timestamp(date.getTime());
				String memo = "节目打赏";
				int i = jdbcTemplate.update("insert into trade values(null, ?,?,?,?,?,?);",
						new Object[] { wallet.getWid(), user.getUid(), user.getUsername(), amount, timeStamp, memo });
				if (i > 0) {
					int j = jdbcTemplate.update("update wallet set amount = amount - ? where wid=?",
							new Object[] { amount, wallet.getWid() });
					System.out.println(pid);
					int k = jdbcTemplate.update("update Program set award = award + ? where pid=?",
							new Object[] { amount, pid });
					if (j > 0 && k > 0) {
						return 1;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
	/**
	 * 目的：在login显示最开始的页面时，获得我们的节目列表
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Program> Headperfectprogram(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		logger.info(jdbcTemplate.toString());
		RowMapper<Program> Program_mapper= new BeanPropertyRowMapper<Program>(Program.class);
		List<Program> Program= new ArrayList<Program>();
	    Program=jdbcTemplate.query("select * from Program order by award desc", Program_mapper);
		return Program; 
	} 
	
	public static String fingPronameByPid(int pid, JdbcTemplate jdbcTemplate) {
		RowMapper<Program> Program_mapper = new BeanPropertyRowMapper<Program>(Program.class);
		String query = "select * from Program where pid=?";
		Program program = new Program();
		try {
			program = jdbcTemplate.queryForObject(query, Program_mapper, pid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return program.getProname();
	}

}

package com.luckymoney.dtss.DAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.luckymoney.dtss.model.Commons_record;
import com.luckymoney.dtss.model.Lucky_money_record;
import com.luckymoney.dtss.model.Program;

/**
 * @author 作者：陈健
 * @version 创建时间：2017年8月15日 上午12:34:12 说明：program的方法
 */
public class ProgramDAO {
	/**
	 * 目的：得到所有的节目信息
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<Program> getAllProgram(JdbcTemplate jdbcTemplate) {
		RowMapper<Program> program_mapper = new BeanPropertyRowMapper<Program>(Program.class);
		List<Program> list = new ArrayList<Program>();
		try {
			String query = "select * from program order by prorder";
			list = jdbcTemplate.query(query,program_mapper);
			System.out.println("3");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 目的：根据pid获得program的值
	 * @param id
	 * @param jdbcTemplate
	 * @return
	 */
	public static Program getProgramById(int id,JdbcTemplate jdbcTemplate) {
		RowMapper<Program> program_mapper = new BeanPropertyRowMapper<Program>(Program.class);
		Program result=new Program();
		try {
			String query="select * from program where pid=?";
			result =jdbcTemplate.queryForObject(query, program_mapper,id);
		} catch (Exception e) {
			return null;
		}
		return result;
	}
	/**
	 * 目的：更新program的值
	 * @param pid 节目序号	
	 * @param name 
	 * @param phone
	 * @param type
	 * @param time
	 * @param department
	 */
	public static void updateProgramByForm(String pid,String proname,String name,String phone,String type,String time,String department,JdbcTemplate jdbcTemplate) {
		String query="update program set proname=?,time=?,phone=?,name=?,type=?,department=? where pid=?";
		int i=jdbcTemplate.update(query,new Object[]{proname,time,phone,name,type,department,pid});
	}
	/**
	 * 目的：根据id值删除节目
	 * @param pid
	 * @param jdbcTemplate
	 */
	public static void deleteProgramByForm(String pid,JdbcTemplate jdbcTemplate) {
		String query="delete from program where pid=?";
		jdbcTemplate.update(query,pid);
	}
	/**
	 * 目的：根据我们添加节目的信息添加消息
	 * @param pid
	 * @param proname
	 * @param name
	 * @param phone
	 * @param type
	 * @param time
	 * @param department
	 * @param jdbcTemplate
	 */
	public static void insertProgramByForm(String pid,String proname,String name,String phone,String type,String time,String department,JdbcTemplate jdbcTemplate) {
		String query="insert into program(pid,proname,name,phone,type,time,department,award) values(?,?,?,?,?,?,?,0)";
		jdbcTemplate.update(query,new Object[] {pid,proname,name,phone,type,time,department});
		String query2="select pid from program where pid in (select max(pid) from program)";
		int getpid = jdbcTemplate.queryForInt(query2);
		System.out.println(getpid);
		int prorder=getpid*100;
		String query3="update program set prorder=? where pid=?";
		jdbcTemplate.update(query3,new Object[] {prorder,getpid});
	}
	
	
	
	
	//弹幕
	public static List<Commons_record> Commons_rds(Timestamp time,JdbcTemplate jdbcTemplate) {
		RowMapper<Commons_record> Commons_record_mapper = new BeanPropertyRowMapper<Commons_record>(Commons_record.class);
		List<Commons_record> Commons_record = new ArrayList<Commons_record>();
		String query = "select * from commons_record where unix_timestamp(time) >= unix_timestamp(?)";
		Commons_record= jdbcTemplate.query(query, Commons_record_mapper,time);
		for (int i = 0; i < Commons_record.size(); i++) {
//			logger.info(Commons_record.get(i).toString());
		}  
		System.out.println(Commons_record.size());
		    /*
	        java.util.Date date = new java.util.Date();
			Timestamp timeStamp = new Timestamp(date.getTime());
			httpsession.setAttribute("timeStamp", timeStamp);*/
		  
		return Commons_record; 
	}
public static void sentmessages_dm(String dm, JdbcTemplate jdbcTemplate) {
	// TODO Auto-generated method stub
//	logger.info(jdbcTemplate.toString());
	System.out.println("OK2"); 
	System.out.println(dm);
	java.util.Date date = new java.util.Date();
	Timestamp timeStamp = new Timestamp(date.getTime());
	jdbcTemplate.update("insert into Commons_record values(null, ?,?);", new Object[] {timeStamp,dm});
}

	
	
	
	
}

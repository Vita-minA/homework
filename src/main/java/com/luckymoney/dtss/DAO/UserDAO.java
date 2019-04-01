package com.luckymoney.dtss.DAO;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.luckymoney.dtss.BalanceController;
import com.luckymoney.dtss.model.Trade;
import com.luckymoney.dtss.model.User;

public class UserDAO {
	// 用来显示信息以测试方法的正确性
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
	
	public static boolean IsRegister(String itcode, JdbcTemplate jdbcTemplate) {
		User result = new User();
		result=getUserByItcode(itcode,jdbcTemplate);
		if(result==null)
		return false;
		else
			return true;
		
	}
	/**
	 * 获取当前用户列表
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<User> getalluser( JdbcTemplate jdbcTemplate) {
		RowMapper<User> User_mapper = new BeanPropertyRowMapper<User>(User.class);
		List<User> list = new ArrayList<User>();
		try {
			String query = "select * from User";
			list = jdbcTemplate.query(query, User_mapper);
			return list;
		} catch (Exception e) {
			System.out.println("666");
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 当前用户数量
	 * @param jdbcTemplate
	 * @return
	 */
	public static int countuser(JdbcTemplate jdbcTemplate)
	{
		if(getalluser(jdbcTemplate).isEmpty())
			{System.out.println("88888");
			return 0;}
		else
		try 
		{
		int a=getalluser(jdbcTemplate).size();
		return a;
		}catch(Exception e)
		{
			return 0;
		}
		
	}
	/**
	 * 目的：根据id获取用户的User对象(内包含，username，itcode,)查询功能诶~~~~
	 * 
	 * @param uid
	 * @return 返回User对象 暂时先这样，然后去找找使用静态传值的方法
	 */
	public static User getUserByUid(int uid, JdbcTemplate jdbcTemplate) {
		RowMapper<User> user_mapper = new BeanPropertyRowMapper<User>(User.class);
		String query = "select * from User where uid = ?";
		User result = new User();
		try {
			result = jdbcTemplate.queryForObject(query, user_mapper, uid);
			logger.info(result.toString());
		} catch (Exception e) {
			logger.info("根据uid获得用户--失败");
			return null;

		}
		return result;
	}
	
	/**
	 * 目的：根据用户名获得，头像
	 * @param username
	 * @param jdbcTemplate
	 * @return
	 */
	public static String getUserface(String username,JdbcTemplate jdbcTemplate) {
		System.out.println(username);
		RowMapper<User> user_mapper = new BeanPropertyRowMapper<User>(User.class);
		User user=new User();
		String query="select faceicod from user where username=?";
		try {
			user=jdbcTemplate.queryForObject(query, user_mapper, username);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return user.getFaceicod();
	}


	/**
	 * 目的：根据Itcode来查找你的用户
	 * 
	 * @param itcode
	 *            用户的工号
	 * @return User对象
	 */
	public static User getUserByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		RowMapper<User> user_mapper = new BeanPropertyRowMapper<User>(User.class);
		String query = "select * from User where Itcode = ?";
		User result = new User();
		try {
			result = jdbcTemplate.queryForObject(query, user_mapper, itcode);
			logger.info(result.toString());
		} catch (Exception e) {
			logger.info("好气哦");
			return null;
		}
		return result;
	}

	/**
	 * 目的：创建一个用户//这个是在我们有注册页面的时候使用的，之后再加注册页面吧
	 * 
	 * @param itcode
	 *            用户的工号
	 * @param username
	 *            用户名
	 * @return 返回几种状态，就现在想好的 -1代表不成功(发现公司里面并没有这号人) 0代表注册成功
	 *         1代表这个工号用户名已经被注册好了(不同工号的用户名可以一致) 2就是咱的代码有问题，只能先让他之后尝试
	 *         由于现在没有办法获得公司的信息，我们就只在这里先写上0和1的情况，日后在完善
	 */
	public static int createUser(String itcode, String username, JdbcTemplate jdbcTemplate) {
		User result = getUserByItcode(itcode, jdbcTemplate);
		// 如果没有查询到用户，这个工号就是没有的，实际上我们应该先找一下公司里面有没有这个人，先这样
		if (result == null) {
			int i;
			// 一开始创建的用户如果没有开通钱包的话，我是不让他用的。我们之后还有一个申请钱包的地方
			String query = "insert into User values (null,?,?,1,0)";
			try {
				i = jdbcTemplate.update(query, new Object[] { username, itcode });
			} catch (Exception e) {
				logger.info("代码有问题");
				return 2;
			}
			// 插入成功
			if (i > 0)
				return 0;
			else
				return 2;
		}
		return 1;
	}

	/**
	 * 目的是检查用户的信息，看看这个人注册了没
	 * 
	 * @param itcode
	 *            工号
	 * @param username
	 *            用户名
	 * @return 返回的状态1：代表用户名等一致
	 *         返回的状态-1：不存在该用户
	 *         返回的状态0：用户名账户不一致
	 */
//	public static int checkUserInfo(String itcode, String username, JdbcTemplate jdbcTemplate) {
//		User result = getUserByItcode(itcode, jdbcTemplate);
//		// 没有查询到用户，代表着我们的这个就是空的，没有这个的存在
//		if (result == null) {
//			logger.info("不存在该用户");
//			return -1;
//		} else if (result.getUsername().equals(username)) {// 用户名一致
//			logger.info("对哒");
//			return 1;
//		} else
//			// 用户名不一致
//			logger.info("用户名不一致");
//		return 0;
//	}
	
	
	
	/**
	 * 目的是检查用户的信息，看看这个人注册了没
	 * 
	 * @param itcode
	 *            工号
	 * @param username
	 *            用户名
	 * @return 返回的状态1：代表用户名等一致
	 *         返回的状态-1：不存在该用户
	 *         返回的状态0：用户名账户不一致
	 */
	public static int checkUserInfo(String itcode, String username, JdbcTemplate jdbcTemplate) {
		User result = getUserByItcode(itcode, jdbcTemplate);
		// 没有查询到用户，代表着我们的这个就是空的，没有这个的存在
		if (result == null) {
			return -1;
		} else if (result.getUsername().equals(username)) {// 用户名一致
			return 1;
		} else
			// 用户名不一致
			System.out.println("x");
		return 0;
	}
	/**
	 * 目的：根据用户的填的信息返回登录情况
	 * @param itcode
	 * @param username
	 * @param password
	 * @param jdbcTemplate
	 * @return 0登录失败，1登录成功
	 */
	public static int checkUserInfo(String itcode, String username, String password, JdbcTemplate jdbcTemplate) {
		//
		int result=checkUserInfo(itcode,username,jdbcTemplate);
		if(result==-1||result==0) {
			System.out.println("budui");
			return 0;
		}
		else if(result==1){
			RowMapper<User> user_mapper = new BeanPropertyRowMapper<User>(User.class);
			String query = "select password from User where itcode = ?";
			User user = new User();
			try {
				user = jdbcTemplate.queryForObject(query, user_mapper, itcode);
				logger.info 

(user.toString());
			} catch (Exception e) {
				logger.info 

("好气哦");
				return 0;
			}
			if(user.getPassword().equals(password)) {
				return 1;
			}
		}
		return 0;
	}


	/**
	 * 目的：用户状态异常的话进行锁定 修改数据库,修改model,完成用户锁定
	 * 
	 * @param uid
	 *            User表上的顺序
	 * @return 看看是不是锁成功了,或者说是锁的状态
	 */
	public static boolean lockUserById(int uid, JdbcTemplate jdbcTemplate) {
		// 看看能不能根据uid查到这个用户。
		User user = getUserByUid(uid, jdbcTemplate);
		if (user == null) {
			return false;
		}
		// 如果有这么一个人的话，我们自然就上锁
		else {
			try {
				jdbcTemplate.update("update user set islock=1 where uid=?", user.getUid());
			} catch (Exception e) {
				logger.info("没锁上");
				return false;
			}
			return true;
		}
	}

	/**
	 * 目的：通过itcode完成用户异常状态时的锁定
	 * 
	 * @param itcode
	 *            用户的账号id
	 * @return 锁的状态
	 */
	public static boolean lockUserByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		User user = getUserByItcode(itcode, jdbcTemplate);
		if (user == null) {
			return false;
		} else {
			try {
				jdbcTemplate.update("update user set islock=1 where uid=?", user.getUid());
			} catch (Exception e) {
				logger.info("没锁上");
				return false;
			}
			return true;
		}
	}

	/**
	 * 目的：当然是选择原谅他了啊，上游洗头绿莹莹一片春意盎然
	 * 
	 * @param uid
	 *            这个就是啊，那个，我想想，哦哦，就是用户的id
	 * @return 返回操作成功不成功
	 */
	public static boolean unlockUserByUid(int uid, JdbcTemplate jdbcTemplate) {
		User user = getUserByUid(uid, jdbcTemplate);
		if (user == null) {
			return false;
		}
		// 如果有这么一个人的话，我们自然就上锁
		else {
			try {
				jdbcTemplate.update("update user set islock=0 where uid=?", user.getUid());
			} catch (Exception e) {
				logger.info("没解锁");
				return false;
			}
			return true;
		}
	}

	/**
	 * 目的：查看这个用户的锁的状态
	 * 
	 * @param uid
	 * @return 返回这个锁的状态
	 */
	public static boolean isLock(int uid, JdbcTemplate jdbcTemplate) {
		User user = getUserByUid(uid, jdbcTemplate);
		if (user == null) {
			logger.info("没有这个用户欸，好气哦");
			return true;// 自然没有这个用户，就当作是“锁”上了，这样就不能进行操作了
		} else {
			if(user.getIsadmin()==1)
				return true;
				else return false;
		}

	}
	/**
	 * 目的：返回的是这个人是不是管理员
	 * @param itcode 工号
	 * @param jdbcTemplate
	 * @return 判断结果
	 */
	public static boolean isadminByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		User user =getUserByItcode(itcode,jdbcTemplate);
		if(user.getIsadmin()==1)
		return true;
		else return false;
	}
	
	
	
	//赖
	public static User getpreuser(String username, String itcode, JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		
		RowMapper<User> User_mapper = new BeanPropertyRowMapper<User>(User.class);
		User user = jdbcTemplate.queryForObject("select * from user where itcode=? and username=?", User_mapper,
				new Object[] { itcode, username });
		//如果需要将节目所有信息展示出来可以在这个地方
		return user; 
	}  


}


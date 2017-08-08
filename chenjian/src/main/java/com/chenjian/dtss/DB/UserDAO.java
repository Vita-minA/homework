package com.chenjian.dtss.DB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.chenjian.dtss.BalanceController;
import com.chenjian.dtss.model.User;

/**
 * @author ���ߣ��½�
 * @version ����ʱ�䣺2017��8��3�� ����7:04:03 ˵����
 */
public class UserDAO {
	// ������ʾ��Ϣ�Բ��Է�������ȷ��
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
	 * Ŀ�ģ�����id��ȡ�û���User����(�ڰ�����username��itcode,)��ѯ������~~~~
	 * 
	 * @param uid
	 * @return ����User���� ��ʱ��������Ȼ��ȥ����ʹ�þ�̬��ֵ�ķ���
	 */
	public static User getUserByUid(int uid, JdbcTemplate jdbcTemplate) {
		RowMapper<User> user_mapper = new BeanPropertyRowMapper<User>(User.class);
		String query = "select * from User where uid = ?";
		User result = new User();
		try {
			result = jdbcTemplate.queryForObject(query, user_mapper, uid);
			logger.info(result.toString());
		} catch (Exception e) {
			logger.info("����Ŷ");
			return null;

		}
		return result;
	}

	/**
	 * Ŀ�ģ�����Itcode����������û�
	 * 
	 * @param itcode
	 *            �û��Ĺ���
	 * @return User����
	 */
	public static User getUserByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		RowMapper<User> user_mapper = new BeanPropertyRowMapper<User>(User.class);
		String query = "select * from User where Itcode = ?";
		User result = new User();
		try {
			result = jdbcTemplate.queryForObject(query, user_mapper, itcode);
			logger.info(result.toString());
		} catch (Exception e) {
			logger.info("����Ŷ");
			return null;
		}
		return result;
	}

	/**
	 * Ŀ�ģ�����һ���û�//�������������ע��ҳ���ʱ��ʹ�õģ�֮���ټ�ע��ҳ���
	 * 
	 * @param itcode
	 *            �û��Ĺ���
	 * @param username
	 *            �û���
	 * @return ���ؼ���״̬����������õ� -1�����ɹ�(���ֹ�˾���沢û�������) 0����ע��ɹ�
	 *         1������������û����Ѿ���ע�����(��ͬ���ŵ��û�������һ��) 2�����۵Ĵ��������⣬ֻ��������֮����
	 *         ��������û�а취��ù�˾����Ϣ�����Ǿ�ֻ��������д��0��1��������պ�������
	 */
	public static int createUser(String itcode, String username, JdbcTemplate jdbcTemplate) {
		User result = getUserByItcode(itcode, jdbcTemplate);
		// ���û�в�ѯ���û���������ž���û�еģ�ʵ��������Ӧ������һ�¹�˾������û������ˣ�������
		if (result == null) {
			int i;
			// һ��ʼ�������û����û�п�ͨǮ���Ļ������ǲ������õġ�
			String query = "insert into User values (null,?,?,1)";
			try {
				i = jdbcTemplate.update(query, new Object[] { username, itcode });
			} catch (Exception e) {
				logger.info("����������");
				return 2;
			}
			// ����ɹ�
			if (i > 0)
				return 0;
			else
				return 2;
		}
		return 1;
	}

	/**
	 * Ŀ���Ǽ���û�����Ϣ�����������ע����û
	 * 
	 * @param itcode
	 *            ����
	 * @param username
	 *            �û���
	 * @return ���ص�״̬1�������û�����һ��
	 *         ���ص�״̬-1�������ڸ��û�
	 *         ���ص�״̬0���û����˻���һ��
	 */
	public static int checkUserInfo(String itcode, String username, JdbcTemplate jdbcTemplate) {
		User result = getUserByItcode(itcode, jdbcTemplate);
		// û�в�ѯ���û������������ǵ�������ǿյģ�û������Ĵ���
		if (result == null) {
			logger.info("�����ڸ��û�");
			return -1;
		} else if (result.getUsername().equals(username)) {// �û���һ��
			logger.info("����");
			return 1;
		} else
			// �û�����һ��
			logger.info("�û�����һ��");
		return 0;
	}

	/**
	 * Ŀ�ģ��û�״̬�쳣�Ļ��������� �޸����ݿ�,�޸�model,����û�����
	 * 
	 * @param uid
	 *            User���ϵ�˳��
	 * @return �����ǲ������ɹ���,����˵������״̬
	 */
	public static boolean lockUserById(int uid, JdbcTemplate jdbcTemplate) {
		// �����ܲ��ܸ���uid�鵽����û���
		User user = getUserByUid(uid, jdbcTemplate);
		if (user == null) {
			return false;
		}
		// �������ôһ���˵Ļ���������Ȼ������
		else {
			try {
				jdbcTemplate.update("update user set islock=1 where uid=?", user.getUid());
			} catch (Exception e) {
				logger.info("û����");
				return false;
			}
			return true;
		}
	}

	/**
	 * Ŀ�ģ�ͨ��itcode����û��쳣״̬ʱ������
	 * 
	 * @param itcode
	 *            �û����˺�id
	 * @return ����״̬
	 */
	public static boolean lockUserByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		User user = getUserByItcode(itcode, jdbcTemplate);
		if (user == null) {
			return false;
		} else {
			try {
				jdbcTemplate.update("update user set islock=1 where uid=?", user.getUid());
			} catch (Exception e) {
				logger.info("û����");
				return false;
			}
			return true;
		}
	}

	/**
	 * Ŀ�ģ���Ȼ��ѡ��ԭ�����˰�������ϴͷ��ӨӨһƬ���ⰻȻ
	 * 
	 * @param uid
	 *            ������ǰ����Ǹ��������룬ŶŶ�������û���id
	 * @return ���ز����ɹ����ɹ�
	 */
	public static boolean unlockUserByUid(int uid, JdbcTemplate jdbcTemplate) {
		User user = getUserByUid(uid, jdbcTemplate);
		if (user == null) {
			return false;
		}
		// �������ôһ���˵Ļ���������Ȼ������
		else {
			try {
				jdbcTemplate.update("update user set islock=0 where uid=?", user.getUid());
			} catch (Exception e) {
				logger.info("û����");
				return false;
			}
			return true;
		}
	}

	/**
	 * Ŀ�ģ��鿴����û�������״̬
	 * 
	 * @param uid
	 * @return �����������״̬
	 */
	public static boolean isLock(int uid, JdbcTemplate jdbcTemplate) {
		User user = getUserByUid(uid, jdbcTemplate);
		if (user == null) {
			logger.info("û������û��G������Ŷ");
			return true;// ��Ȼû������û����͵����ǡ��������ˣ������Ͳ��ܽ��в�����
		} else {
			return user.isIslock();
		}

	}

}

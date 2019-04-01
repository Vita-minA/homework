package mythread;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.luckymoney.dtss.HomeController;
import com.luckymoney.dtss.DAO.LuckyMoneyDAO;
import com.luckymoney.dtss.DAO.LuckyMoneyTradeDAO;
import com.luckymoney.dtss.DAO.TradeDAO;
import com.luckymoney.dtss.DAO.UserDAO;
import com.luckymoney.dtss.DAO.WalletDAO;

public class LuckyNumberThread extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	boolean flag = false;
	JdbcTemplate jdbcTemplate;	
	int round=0;
	@Override
	public void run() {
		int count=UserDAO.countuser(jdbcTemplate);
		// TODO Auto-generated method stub
		System.out.println("红包雨开始咯!");
		int min=1;
		int max = count+1;
		int number=count;
		HashSet<Integer> set =  new HashSet<Integer>();
		while(set.size() <number) {
			int temp = (int) (Math.random() * (max - min)) + min;
			if(set.contains(temp)) {
				System.out.println(temp+"发过了");
			} else {
				set.add(temp);
				System.out.println("给"+temp+"发");
				if(temp==1)
					continue;
			    else if (flag) {
					int total = LuckyMoneyDAO.getTotalbyRound(round,jdbcTemplate);
					int lucknumber = 0;
					Random r = new Random();
					if(total >0) {
						if(total >=5000) {
							lucknumber = r.nextInt(5000);
						} else {
							lucknumber = total;
						}
					} else {
						break;
					}			
				try {
					Thread.sleep(1000);
					////修改系统红包总余额
					int i = LuckyMoneyDAO.luckyRain(round, lucknumber,jdbcTemplate);
					//添加红包记录
					Timestamp currenttime= new Timestamp(System.currentTimeMillis()); 
					int j = LuckyMoneyTradeDAO.newRecord(WalletDAO.getWalletById(temp,jdbcTemplate).getWid(),WalletDAO.getWalletById(temp,jdbcTemplate).getUid(),WalletDAO.getWalletById(temp,jdbcTemplate).getUsername(),lucknumber,currenttime,round,jdbcTemplate);
					//给用户钱包加入指定数额
					int k = WalletDAO.walletAdd(WalletDAO.getWalletById(temp,jdbcTemplate).getWid(), lucknumber,jdbcTemplate);
					//添加交易记录
					Timestamp currenttime2= new Timestamp(System.currentTimeMillis()); 
					int l = TradeDAO.createluckyTrade(WalletDAO.getWalletById(temp,jdbcTemplate).getWid(),WalletDAO.getWalletById(temp,jdbcTemplate).getUid(),WalletDAO.getWalletById(temp,jdbcTemplate).getUsername(), lucknumber, currenttime2,round,jdbcTemplate);
					if(i*j*k*l>0) {
						logger.info("红包雨————成功");
					} else {
						logger.info("红包雨————失败");
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			}
		}		
		flag=false; 
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public JdbcTemplate getTemplate() {
		return jdbcTemplate;
	}

	public void setTemplate(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

}

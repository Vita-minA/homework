package mythread;

import java.sql.Timestamp;
import java.util.Random;

import org.springframework.jdbc.core.JdbcTemplate;

import com.luckymoney.dtss.DAO.LuckyMoneyDAO;
import com.luckymoney.dtss.DAO.LuckyMoneyTradeDAO;
import com.luckymoney.dtss.DAO.TradeDAO;
import com.luckymoney.dtss.DAO.WalletDAO;

public class snatchThread extends Thread {
	boolean flag = false;
	JdbcTemplate jdbcTemplate;	
	int lucky=0;
	String username;    
	@Override
	public void run() {
		
		// TODO Auto-generated method stub
		System.out.println("kkkkkk"+username);
		System.out.println("抢红包咯!");
		if (flag) 
		{
			int total = LuckyMoneyDAO.getTotalbyRound(4,jdbcTemplate);
			int lucknumber = 0;
			Random r = new Random();
			if(total >0) {
				if(total >=5000) {
						lucknumber = r.nextInt(10000);
				} else {
							lucknumber = total;
						}
					} else {
						return;
					}			
				try {
					lucky=lucknumber;
					setLucky(lucky);
					Thread.sleep(1000);
					////修改系统红包总余额
					int i = LuckyMoneyDAO.luckyRain(4, lucknumber,jdbcTemplate);
					//添加红包记录
					Timestamp currenttime= new Timestamp(System.currentTimeMillis()); 
					int j = LuckyMoneyTradeDAO.newRecord(WalletDAO.getWalletByUsername(username,jdbcTemplate).getWid(),WalletDAO.getWalletByUsername(username,jdbcTemplate).getUid(),WalletDAO.getWalletByUsername(username,jdbcTemplate).getUsername(),lucknumber,currenttime,4,jdbcTemplate);
					//给用户钱包加入指定数额
					int k = WalletDAO.walletAdd(WalletDAO.getWalletByUsername(username,jdbcTemplate).getWid(), lucknumber,jdbcTemplate);
					//添加交易记录
					Timestamp currenttime2= new Timestamp(System.currentTimeMillis()); 
					int l = TradeDAO.createsnatchTrade(WalletDAO.getWalletByUsername(username,jdbcTemplate).getWid(),WalletDAO.getWalletByUsername(username,jdbcTemplate).getUid(),WalletDAO.getWalletByUsername(username,jdbcTemplate).getUsername(), lucknumber, currenttime2,4,jdbcTemplate);
					if(i*j*k*l>0) {
						System.out.println("red envelope");
					} else {
						System.out.println("no red envelope");
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		flag=false; 
	}

	public int getLucky() {
		return lucky;
	}

	public void setLucky(int lucky) {
		this.lucky = lucky;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	
}

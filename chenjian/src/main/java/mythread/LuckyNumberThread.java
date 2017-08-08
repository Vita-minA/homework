package mythread;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.jdbc.core.JdbcTemplate;

import com.chenjian.dtss.DB.LuckyMoneyDAO;
import com.chenjian.dtss.DB.LuckyMoneyRecordDAO;
import com.chenjian.dtss.DB.TradeDAO;
import com.chenjian.dtss.DB.WalletDAO;
import com.chenjian.dtss.model.Wallet;

public class LuckyNumberThread extends Thread {
	boolean flag = false;
	JdbcTemplate template;
	int round=0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("红包雨开始咯!");
		List<Wallet> wallets = WalletDAO.getAllWallets(template);
		
		for (Wallet temp :  wallets) {
			if (flag) {
				int total = LuckyMoneyDAO.getTotalbyRound(round,template);
				int lucknumber = 0;
				Random r = new Random();
				if(total >0) {
					if(total >5000) {
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
					int i = LuckyMoneyDAO.luckyRain(round, lucknumber);
					//添加红包记录
					int j = LuckyMoneyRecordDAO.newRecord(temp.getWid(),lucknumber,round,template);
					//给用户钱包加入指定数额
					int k = WalletDAO.walletAdd(temp.getWid(), lucknumber,template);
					//添加交易记录
					int l = TradeDAO.createTrade(temp.getWid(), lucknumber, new Date().toString(), "红包雨",template);
					if(i*j*k*l>0) {
						//ok
					} else {
						//error
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else  {
				break;
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
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

}

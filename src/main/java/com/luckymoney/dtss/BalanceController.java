package com.luckymoney.dtss;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luckymoney.dtss.BalanceController;
import com.luckymoney.dtss.DAO.RestMoneyDAO;
import com.luckymoney.dtss.DAO.TradeDAO;
import com.luckymoney.dtss.DAO.UserDAO;
import com.luckymoney.dtss.DAO.WalletDAO;
import com.luckymoney.dtss.model.Trade;
import com.luckymoney.dtss.model.User;
import com.luckymoney.dtss.model.Wallet;

@Controller
public class BalanceController {
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
	@Autowired
	public JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/balance_add", method = RequestMethod.GET)
	public String balanceAdd() {
		return null;

	}

	@RequestMapping(value = "/inquiry", method = RequestMethod.GET)
	public String inquiry(String from, String to, Model model) {
		logger.info ("from" + from + "to" + to);
		List<Trade> trade = WalletDAO.inquire(from, to, jdbcTemplate);
		model.addAttribute("trade", trade);
		return "inquiry";
	}

	
	/**
	 * 
	 * @param username 用户名
	 * @param itcode 用户id
	 * @param httpsession 对用户名 用户id 以及当前时间做session
	 * @param model 一个user的model
	 * @return 用户登录正常进入用户主页面
	 *   管理员登录正常进入管理员主页面
	 */
	
	//赖
	@RequestMapping(value="/lo", method = RequestMethod.GET)
	public String login(String username,String itcode,HttpSession httpsession,Model model) {
		logger.info("是这里啊"); 
		httpsession.setAttribute("username", username); 
		httpsession.setAttribute("itcode", itcode); 
		//获取当前时间
		java.util.Date date = new java.util.Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		httpsession.setAttribute("timeStamp", timeStamp);
		//检查用户名否存在
		int flag=UserDAO.checkUserInfo(itcode, username, jdbcTemplate);
		//是要在这里写异步的，所以现在就先从简
		if(flag==0) {
			return username;
		}
		if(flag==1) {
			//检查该用户是否为管理员
			boolean isadmin=UserDAO.isadminByItcode(itcode, jdbcTemplate);
			if(isadmin) {	
			return "admin1";
			}  
			else  {     
			
				return "userperinfo1";       
			}	}
		if(flag==-1) {
			return itcode;
		}
		return itcode;
		
	}
	
//person.jsp
/**
 *   在用户个人信息页面返回用户钱包当前余额
 *  
 * @param httpsession 获取登录时该用户的用户名以及用户id 
 * @param model 一个wallet 的model
 * @param response 将用户余额返回到个人信息页面
 * @return   返回到当前页面
 */
@RequestMapping(value = "/MyWallet")
	public String Restmoeny(HttpSession httpsession, Model model,HttpServletResponse response) {
		logger.info(jdbcTemplate.toString());
		//获取用户用户名和用户id的session
		String username = (String) httpsession.getAttribute("username");
		String itcode = (String) httpsession.getAttribute("itcode");
		//执行DAO 获取当前用户的wallet
		Wallet wallet = RestMoneyDAO.restmoney(itcode, username, jdbcTemplate);
	    
		try {
			//返回余额
			response.getWriter().print(wallet.getAmount());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return null;
	}
//MyWallet.jsp
 /**
  *  返回用户的个人详细信息
  * @param model 当前用户的user model
  * @param session 获取当前用户的用户名以及用户id
  * @return  返回到信息页面
  */
@RequestMapping(value = "/perperson") 
	public String personmessage(Model model, HttpSession session) {
		//获取用户名以及id
		String username = (String) session.getAttribute("username");
		String itcode = (String) session.getAttribute("itcode");
		User user = UserDAO.getpreuser(username, itcode, jdbcTemplate);
		//得到user的model
		model.addAttribute("user", user);
		
		return "perperson";
	}  

//perperson.jsp
/**
 *    获取用户所有的交易记录
 * @param httpsession  获取用户的用户名以及用户id
 * @param model 获取当前用户的trade
 * @return  返回交易记录页面
 */
@RequestMapping(value="/jiaoyi_record")
    public String jiaoyi(HttpSession httpsession,Model model) {
    logger.info(jdbcTemplate.toString());
    //获取当前用户的用户名和用户ID
    String username=(String)httpsession.getAttribute("username");
    String itcode=(String)httpsession.getAttribute("itcode");
    // 得到用户交易记录的信息
     List<Trade> trade=TradeDAO.getTrades(username,itcode, jdbcTemplate); 
     model.addAttribute("trade",trade);
     return "jiaoyi_trade";
    }
//jiaoyi_trade.jsp
/**
 *    用户账户充值
 * @param httpsession  获取当前的用户的用户名和用户ID
 * @param amount 充值金额
 * @param response 返回充值结果
 * @param model 获取用户身份
 */
@RequestMapping(value = "/balance_adding")
	public void balanceAdding(HttpSession httpsession,String amount, HttpServletResponse response,Model model) {
		logger.info(jdbcTemplate.toString());
		//获取当前用户用户名和用户ID
		String username=(String)httpsession.getAttribute("username");
		String itcode=(String)httpsession.getAttribute("itcode");
		String userface=UserDAO.getUserface(username, jdbcTemplate);
		model.addAttribute("userface", userface);
		
		 
		try {
			//返回用户充值结果
			PrintWriter out = response.getWriter();
			int i = WalletDAO.balance_add(itcode, username, amount,  jdbcTemplate);
			out.println(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	
//		String result;
//		if(i==1) { 
//			result="充值成功";
//		}else {
//			
//			result="充值失败";
//		}
//		model.addAttribute("result", result);
		
	}

}

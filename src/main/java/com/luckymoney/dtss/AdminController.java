package com.luckymoney.dtss;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.luckymoney.dtss.DAO.LuckyDAO;
import com.luckymoney.dtss.DAO.LuckyMoneyDAO;
import com.luckymoney.dtss.DAO.LuckyMoneyTradeDAO;
import com.luckymoney.dtss.DAO.TradeDAO;
import com.luckymoney.dtss.DAO.UserDAO;
import com.luckymoney.dtss.DAO.WalletDAO;
import com.luckymoney.dtss.model.Lucky_money_record;
import com.luckymoney.dtss.model.Trade;
import com.luckymoney.dtss.model.Wallet;

import mythread.LuckyNumberThread;
import mythread.snatchThread;

@Controller
public class AdminController {
	public static int limit=12;
	public String a="username";//用户名
	public String b="1970-01-01 00:00:00";//开始时间
	public String c="2025-01-01 00:00:00";//截止时间
	public String d="round";//round
	public String a2="username";
	public String b2="1970-01-01 00:00:00";
	public String c2="2025-01-01 00:00:00";
	public String d2="";


	@Autowired
	public JdbcTemplate jdbcTemplate;
	@RequestMapping(value="/ldgf")
	public String login(String username,String itcode,Model model) {
		int flag=UserDAO.checkUserInfo(itcode, username, jdbcTemplate);
		//是要在这里写异步的，所以现在就先从简
		System.out.println(flag);
		if(flag==0) {
			return username;
		}
		if(flag==1) {
			boolean isadmin=UserDAO.isadminByItcode(itcode, jdbcTemplate);
			if(isadmin) {
				int a=LuckyMoneyDAO.getTotalbyRound(1,jdbcTemplate);
				int b=LuckyMoneyDAO.getTotalbyRound(2,jdbcTemplate);
				int c=LuckyMoneyDAO.getTotalbyRound(3,jdbcTemplate);
				int d=LuckyMoneyDAO.getTotalbyRound(4,jdbcTemplate);
				model.addAttribute("a",a);
				model.addAttribute("b",b);
				model.addAttribute("c",c);
				model.addAttribute("d",d);
			return "admin1";
			}
			else
				return "ChatRoom";
		}
		if(flag==-1) {
			return itcode;
		}
		return itcode;
		
	}
	/**
	 * admin红包雨/抢红包--充值
	 * @param round1number
	 * @param round2number
	 * @param round3number
	 * @param number
	 * @param model
	 * @return
	 */

	@RequestMapping("/addadmin") 	
	public String lucky_money(String round1number,String round2number,String round3number,String number,Model model)
	{
		int init = Integer.valueOf(round1number).intValue()+Integer.valueOf(round2number).intValue()+Integer.valueOf(round3number).intValue()+Integer.valueOf(number).intValue();;
		int all=WalletDAO.getWalletById(1, jdbcTemplate).getAmount();
		if(all<(init*100))
		{		
			model.addAttribute("get1",LuckyMoneyDAO.getTotalbyRound(1,jdbcTemplate));
		    model.addAttribute("get2",LuckyMoneyDAO.getTotalbyRound(2,jdbcTemplate));
		    model.addAttribute("get3",LuckyMoneyDAO.getTotalbyRound(3,jdbcTemplate));
		    model.addAttribute("get4",LuckyMoneyDAO.getTotalbyRound(4,jdbcTemplate));
		    model.addAttribute("adminwallet",WalletDAO.getWalletById(1, jdbcTemplate).getAmount());
		    model.addAttribute("flag",0);
			return "adminluckyset1";
		}
		else 
			if(LuckyDAO.LuckyRainTotal(round1number,round2number,round3number, number,jdbcTemplate))	
		{
			if(WalletDAO.walletcut(1, init*100, jdbcTemplate)==1)
			{
				model.addAttribute("get1",LuckyMoneyDAO.getTotalbyRound(1,jdbcTemplate));
				model.addAttribute("get2",LuckyMoneyDAO.getTotalbyRound(2,jdbcTemplate));
				model.addAttribute("get3",LuckyMoneyDAO.getTotalbyRound(3,jdbcTemplate));
				model.addAttribute("get4",LuckyMoneyDAO.getTotalbyRound(4,jdbcTemplate));
				model.addAttribute("adminwallet",WalletDAO.getWalletById(1, jdbcTemplate).getAmount());
				model.addAttribute("flag",1);
				return "adminluckyset1";
			}
			else 
				return "adminluckyset1";
		}
		else 
			return "adminluckyset1";		
	}
	
	
	/**
	 * 开启第i轮红包功能
	 * @param round
	 * @param model
	 * @return
	 */
	@RequestMapping("/lucky_on") 
	public String Lucky_on(String round,Model model) {		
		LuckyNumberThread t = new LuckyNumberThread();
		t.setTemplate(jdbcTemplate);
		int r = 0;
		try {
			r = Integer.parseInt(round);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		if(r!=4)
		{
			t.setRound(r);
			t.setFlag(true);
			t.start();
			model.addAttribute("result", 1);
			System.out.println(round+"轮开始了");
	
		}
		else
		{  
			if(LuckyDAO.startsnatch(jdbcTemplate))
			{				
				model.addAttribute("result", 2);
				System.out.println("抢红包开始了");
			}
			else System.out.println("fa"); 
		}
		return "NewFile";
		
	}

	/**
	 * 刷新页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/gotolucky")
	public String gotolucky(Model model){
		model.addAttribute("get1",LuckyMoneyDAO.getTotalbyRound(1,jdbcTemplate));
		model.addAttribute("get2",LuckyMoneyDAO.getTotalbyRound(2,jdbcTemplate));
		model.addAttribute("get3",LuckyMoneyDAO.getTotalbyRound(3,jdbcTemplate));
		model.addAttribute("get4",LuckyMoneyDAO.getTotalbyRound(4,jdbcTemplate));
		model.addAttribute("adminwallet",WalletDAO.getWalletById(1, jdbcTemplate).getAmount());
		return "adminluckyset1";
	}
	
	/**
	 * 刷新页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/gotostart")
	public String gotostart(Model model){
		model.addAttribute("get1",LuckyMoneyDAO.getTotalbyRound(1,jdbcTemplate));
		model.addAttribute("get2",LuckyMoneyDAO.getTotalbyRound(2,jdbcTemplate));
		model.addAttribute("get3",LuckyMoneyDAO.getTotalbyRound(3,jdbcTemplate));
		model.addAttribute("get4",LuckyMoneyDAO.getTotalbyRound(4,jdbcTemplate));
		model.addAttribute("adminwallet",WalletDAO.getWalletById(1, jdbcTemplate).getAmount());
		model.addAttribute("flag",2);
		return "adminluckyset1";
	}
	
	/**
	 * 根据用户名和时间等条件查询红包发放记录
	 * @param username
	 * @param starttime
	 * @param endtime
	 * @param round
	 * @param model
	 * @return
	 */
	@RequestMapping("/getrecordbyusernameandtimeandround")
	public String getrecordbyusernameandtimeandround(String username,String starttime,String endtime,String round,Model model){
		a=username;
		b=starttime;
		c=endtime;
		d=round;
		Timestamp st = new Timestamp(System.currentTimeMillis());
		Timestamp et = new Timestamp(System.currentTimeMillis());
		try {
			st = Timestamp.valueOf(starttime);
			et = Timestamp.valueOf(endtime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Lucky_money_record> list =new ArrayList<Lucky_money_record>();
		List<Lucky_money_record> list1 =new ArrayList<Lucky_money_record>();
		if(round.equals("round"))
		{
		    if(username.equals("username"))
			    list=LuckyMoneyTradeDAO.getRecordsByTime(st,et,jdbcTemplate);
		    else
			    list=LuckyMoneyTradeDAO.getRecordsByUsernameandTime(username,st,et,jdbcTemplate);
		}
		else
		{
			int r = Integer.parseInt(round);
			if(username.equals("username"))
				list=LuckyMoneyTradeDAO.getAllRecordsbyround(r, jdbcTemplate);
			else
				list=LuckyMoneyTradeDAO.getRecordsByUsernameandTimeandRound(username,st,et,r,jdbcTemplate);
		}
		int num=list.size();
		int pagenum=num/limit;
		if(num>limit) {
		list1 = list.subList(0, limit);
		}
		else list1.addAll(list);
		model.addAttribute("list", list1);
		model.addAttribute("pagenum", pagenum);
		return "adminsearch1";
	}
	
	/**
	 * 通过轮次查询红包发放记录
	 * @param round
	 * @param model
	 * @return
	 */
	@RequestMapping("/getrecordbyround")
	public String getrecordbyround(int round,Model model){
		System.out.println("3");
		List<Lucky_money_record> list =new ArrayList<Lucky_money_record>();
		list=LuckyMoneyTradeDAO.getAllRecordsbyround(round, jdbcTemplate);
		model.addAttribute("list", list);
		return "checkresult";
	}
	
	/**
	 * 显示用户钱包信息
	 * @param username
	 * @param model
	 * @return
	 */
	@RequestMapping("/showwallet")
	public String showwallet(String username,Model model){
		Wallet wallet = new Wallet();
		wallet=WalletDAO.getWalletByUsername(username, jdbcTemplate);
		model.addAttribute("wallet", wallet);		
		return "checkwallet";
	}
	/**
	 * 查询后的首页处理
	 * @param username
	 * @param starttime
	 * @param endtime
	 * @param memo
	 * @param model
	 * @return
	 */
	@RequestMapping("/gettrade")
	public String gettrade(String username,String starttime,String endtime,String memo,Model model){
		a2=username;
		b2=starttime;
		c2=endtime;
		d2=memo;
		Timestamp st = new Timestamp(System.currentTimeMillis());
		Timestamp et = new Timestamp(System.currentTimeMillis());
		try {
			st = Timestamp.valueOf(starttime);
			et = Timestamp.valueOf(endtime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Trade> list = new ArrayList<Trade>();
		List<Trade> list1 = new ArrayList<Trade>();
		if(username.equals("username")||username.equals(""))
		{
			if(memo.equals(""))
			{				
				list=TradeDAO.getTradesByTime(st,et,jdbcTemplate);				
			}
			else 
				list=TradeDAO.getTradesByTimeandMemo(st, et, memo, jdbcTemplate);
		}
		else
		{
			if(memo.equals(""))
			{				
				list=TradeDAO.getTradesByUsernameandTime(username, st, et, jdbcTemplate);				
			}
			else 
				list=TradeDAO.getTradesByUsernameandTimeandMemo(username, st, et, memo, jdbcTemplate);
		}
		int num=list.size();
		int pagenum=num/limit;
		if(num>limit) {
		list1 = list.subList(0, limit);
		}
		else list1.addAll(list);
		model.addAttribute("list", list1);
		model.addAttribute("pagenum", pagenum);
		model.addAttribute("username", a2);
		return "adminusertrade1";
	}

	/**
	 * 目的：根据我们的点击的id获得我们所要得到的页面的数据 传数据给showtrade(一个规定div显示的jsp)
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/gettradebypage")
	public String gettradeBypage(HttpServletResponse response,int id,Model model) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		int rid1=(id-1)*limit;//设置页首id
		int rid2=id*limit;      //设置页尾id
		Timestamp st = new Timestamp(System.currentTimeMillis());
		Timestamp et = new Timestamp(System.currentTimeMillis());
		try {
			st = Timestamp.valueOf(b2);
			et = Timestamp.valueOf(c2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Trade> list = new ArrayList<Trade>();
		List<Trade> list1 = new ArrayList<Trade>();
		if(a2.equals("username")||a2.equals(""))
		{
			if(d2.equals(""))
			{				
				list=TradeDAO.getTradesByTime(st,et,jdbcTemplate);				
			}
			else 
				list=TradeDAO.getTradesByTimeandMemo(st, et, d2, jdbcTemplate);
		}
		else
		{
			if(d2.equals(""))
			{				
				list=TradeDAO.getTradesByUsernameandTime(a2, st, et, jdbcTemplate);				
			}
			else 
				list=TradeDAO.getTradesByUsernameandTimeandMemo(a2, st, et, d2, jdbcTemplate);
		}
		int num=list.size();
		if(num>rid2) {
			list1 = list.subList(rid1, rid2);
			}
			else list1=list.subList(rid1, num);
			model.addAttribute("list", list1);
			model.addAttribute("username", a2);
			return "showtrade"; 
	}


	
	
	
	
	/**
	 * 查询所有红包发放记录
	 * @param model
	 * @return
	 */
	@RequestMapping("/checkluckyrecord")
	public String checkluckyrecord(Model model) {
		List<Lucky_money_record> list =new ArrayList<Lucky_money_record>();
		list=LuckyMoneyTradeDAO.getRecordsByPage(0, limit+1, jdbcTemplate);//获取第一页所要显示的东西
		int num=LuckyMoneyTradeDAO.getnum(jdbcTemplate);//获得交易记录的数目
		int pagenum=num/limit;
		model.addAttribute("list", list);
		model.addAttribute("pagenum", pagenum);
		return "adminsearch1";
		
	}
	
	@RequestMapping("/trydemo")
	public String trydemo(Model model) {
		return "trydemo";
	}

	
	
	
	/**
	 * 目的：根据我们的点击的id获得我们所要得到的页面的数据 传数据给showrecord(一个规定div显示的jsp)
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/getrecordbypage")
	public String getrecordBypage(HttpServletResponse response,int id,Model model) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		response.setHeader("Cache-Control", "no-cache");   
		int rid1=(id-1)*limit;//设置页首id
		int rid2=id*limit;      //设置页尾id
		Timestamp st = new Timestamp(System.currentTimeMillis());
		Timestamp et = new Timestamp(System.currentTimeMillis());
		try {
			st = Timestamp.valueOf(b);
			et = Timestamp.valueOf(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Lucky_money_record> list =new ArrayList<Lucky_money_record>();
		List<Lucky_money_record> list1 =new ArrayList<Lucky_money_record>();
		if(d.equals("round"))
		{
		    if(a.equals("username"))
			    list=LuckyMoneyTradeDAO.getRecordsByTime(st,et,jdbcTemplate);
		    else
			    list=LuckyMoneyTradeDAO.getRecordsByUsernameandTime(a,st,et,jdbcTemplate);
		}
		else
		{
			int r = Integer.parseInt(d);
			if(a.equals("username"))
				list=LuckyMoneyTradeDAO.getAllRecordsbyround(r, jdbcTemplate);
			else
				list=LuckyMoneyTradeDAO.getRecordsByUsernameandTimeandRound(a,st,et,r,jdbcTemplate);
		}
		int num=list.size();
		if(num>rid2) {
			list1 = list.subList(rid1, rid2);
			}
			else list1=list.subList(rid1, num);
			model.addAttribute("list", list1);
		return "showrecord";
	}
	
	@RequestMapping("/loginit")	
	public String login(String username,String itcode,HttpSession httpsession) {
				httpsession.setAttribute("username", username); 		
				System.out.println(username);
				System.out.println("joyfly");
		        return "snatch";
	}
	/**
	 * 调用抢红包线程
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/snatchred")
	public String snatchred(HttpSession session,Model model){
		//图片会动
	    String username=(String)session.getAttribute("username");
	    System.out.println("OK");
	    System.out.println(username);
	    snatchThread t = new snatchThread();
		t.setJdbcTemplate(jdbcTemplate);
		t.setUsername(username);
		t.setFlag(true);
		t.start();
		try {
			t.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int lucky=t.getLucky();
		if(lucky==0)
		{
			model.addAttribute("lucky","很遗憾，红包已发完！");
			return "out2";
		}
		else{
			model.addAttribute("lucky","恭喜您，获得"+lucky/100+"元红包！");
			return "out";
			}
	}
	
	/**
	 * 刷新页面
	 * @return
	 */
	@RequestMapping("/gotosnatch")
	public String gotosnatch(){
		return "snatch";
	}
	
	
}

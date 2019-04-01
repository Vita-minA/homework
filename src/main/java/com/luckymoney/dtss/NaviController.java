package com.luckymoney.dtss;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luckymoney.dtss.DAO.LuckyDAO;
import com.luckymoney.dtss.DAO.LuckyMoneyDAO;
import com.luckymoney.dtss.DAO.LuckyMoneyTradeDAO;
import com.luckymoney.dtss.DAO.OnlineuserDAO;
import com.luckymoney.dtss.DAO.ProgramDAO;
import com.luckymoney.dtss.DAO.ProgramListDAO;
import com.luckymoney.dtss.DAO.RestMoneyDAO;
import com.luckymoney.dtss.DAO.TradeDAO;
import com.luckymoney.dtss.DAO.UserDAO;
import com.luckymoney.dtss.DAO.WalletDAO;
import com.luckymoney.dtss.model.Commons_record;
import com.luckymoney.dtss.model.Lucky_money_record;
import com.luckymoney.dtss.model.Program;
import com.luckymoney.dtss.model.Trade;
import com.luckymoney.dtss.model.User;
import com.luckymoney.dtss.model.Wallet;



import java.util.Locale;


@Controller
public class NaviController {
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
	public String a2="username";
	public String b2="1970-01-01 00:00:00";
	public String c2="2025-01-01 00:00:00";
	public String d2="";
	//存放我们的session-username
		public static Hashtable<String, String> sessions = new Hashtable<String, String>();
		//如果是第一次进入页面的话，就清除onlineUser的所有值
		public static int counter=0;

	public static int limit=12;
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
/**
 * 目的：管理员导航栏点击进入红包管理页面
 * @param model
 * @return
 */
	@RequestMapping("/gotoadminluckyset1")
	public String gotoadminluckyset1(Model model){
		model.addAttribute("get1",LuckyMoneyDAO.getTotalbyRound(1,jdbcTemplate));
		model.addAttribute("get2",LuckyMoneyDAO.getTotalbyRound(2,jdbcTemplate));
	    model.addAttribute("get3",LuckyMoneyDAO.getTotalbyRound(3,jdbcTemplate));
	    model.addAttribute("get4",LuckyMoneyDAO.getTotalbyRound(4,jdbcTemplate));
	    model.addAttribute("adminwallet",WalletDAO.getWalletById(1, jdbcTemplate).getAmount());
	    model.addAttribute("flag",2);
		return "adminluckyset1";
	}
	
	/**
	 * 目的：管理员导航栏点击进入管理员首页
	 * @return
	 */
	@RequestMapping("/gotoadminhome1")
	public String gotoadminhome1(){
		return "adminhome1";
	}
	
	/**
	 * 目的：管理员导航栏点击进入团队信息页面
	 * 
	 */
	@RequestMapping("/gotoadminperinfo1")
	public String gotoadminperinfo1(){
		return "adminperinfo1";
	}


	/**
	 * 从标签页---->显示页
	 * @param model
	 * @return
	 */
	@RequestMapping("/gotoadminsearch2")
	public String checkusertrade(Model model) {
		List<Trade> list = new ArrayList<Trade>();
		list=TradeDAO.getTradesByPage(210, limit+211, jdbcTemplate);//获取第一页要显示的东西
		int num=TradeDAO.getnum(jdbcTemplate);
		int pagenum=num/limit;
		model.addAttribute("list", list);
		model.addAttribute("pagenum", pagenum);
		model.addAttribute("username", a2);
		return "adminusertrade1";
	}
	/**
	 * 从标签页---->显示页
	 * @param model
	 * @return
	 */
	@RequestMapping("/gotoadminsearch1")
	public String gotoadminsearch1(Model model) {
		List<Lucky_money_record> list =new ArrayList<Lucky_money_record>();
		list=LuckyMoneyTradeDAO.getRecordsByPage(0, limit+1, jdbcTemplate);//获取第一页所要显示的东西
		int num=LuckyMoneyTradeDAO.getnum(jdbcTemplate);//获得交易记录的数目
		int pagenum=num/limit;
		model.addAttribute("list", list);
		model.addAttribute("pagenum", pagenum);
		return "adminsearch1";
		
	}

	/**
	 * 从admin1-->到program
	 * @return
	 */
	@RequestMapping("/gotoadminprogram1")
	public String gotoadminprogram1(Model model){
		List<Program> list =new ArrayList<Program>();
		list=ProgramDAO.getAllProgram(jdbcTemplate);
		model.addAttribute("list", list);
		return "adminprogram1";
	}
	/**
	 * 目的：利用传来的id值查询我们的节目详情并返回到我们设计好的格式里面，blk显示这些消息，(装在div里面)
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/getpro")
	public String tr(HttpServletResponse response,int id,Model model) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		response.setHeader("Cache-Control", "no-cache");   
		Program program=new Program();
		program=ProgramDAO.getProgramById(id, jdbcTemplate);
		model.addAttribute("program", program);
		try {
			response.getWriter().print(program);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "blk";
	}
	

	/**
	 * 目的：在管理员操作表单以后更新数据库，以下都是完整信息
	 * @param pid
	 * @param proname
	 * @param name
	 * @param phone
	 * @param type
	 * @param time
	 * @param department
	 * @return
	 */
	@RequestMapping("/updatepro") 
	public String updatepro(HttpServletResponse response,String pid,String proname,String name,String phone,String type,String time,String department,Model model) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		response.setHeader("Cache-Control", "no-cache");  
		ProgramDAO.updateProgramByForm(pid, proname, name, phone, type, time, department, jdbcTemplate);
		List<Program> list =new ArrayList<Program>();
		list=ProgramDAO.getAllProgram(jdbcTemplate);
		model.addAttribute("list", list);
		return "adminprogram1";
	}
	/**
	 * 目的：根据id值删除我们的节目信息
	 * @param response
	 * @param pid
	 * @param model
	 * @return
	 */
	@RequestMapping("/deletepro")
	public String deletepro(HttpServletResponse response,String pid,Model model) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		response.setHeader("Cache-Control", "no-cache");  
		ProgramDAO.deleteProgramByForm(pid, jdbcTemplate);
		List<Program> list =new ArrayList<Program>();
		list=ProgramDAO.getAllProgram(jdbcTemplate);
		model.addAttribute("list", list);
		return "adminprogram1";
	}
	@RequestMapping("/addpro")
	public String addpro() {
		return "blk2";
	}
	@RequestMapping("/insertpro")
	public String insertpro(HttpServletResponse response,String pid,String proname,String name,String phone,String type,String time,String department,Model model) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		response.setHeader("Cache-Control", "no-cache");  
		ProgramDAO.insertProgramByForm(pid, proname, name, phone, type, time, department, jdbcTemplate);
		
		List<Program> list =new ArrayList<Program>();
		list=ProgramDAO.getAllProgram(jdbcTemplate);
		model.addAttribute("list", list);
		return "adminprogram1";
	}
	
	
	
	
	/**
	 * 目的：导航栏点击进入用户首页
	 * @param request 获取用户名session
	 * @param model 用户身份的model
	 * @return 进入用户首页
	 */
	@RequestMapping("/gotouserhome1")
	public String gotouserhome1(HttpServletRequest request, Model model){
		String username=(String) request.getSession().getAttribute("username");
		model.addAttribute("username", username);
		String userface=UserDAO.getUserface(username, jdbcTemplate);
		model.addAttribute("userface", userface);
		return "userhome1";
	}
	
	/**
	 * 目的：导航栏点击进入抢红包页面
	 * @param request  获取用户名session
	 * @param model用户身份的model
	 * @return 进入抢红页面
	 */
	@RequestMapping("/gotouserlucky1")
	public String gotogotouserlucky1(HttpServletRequest request, Model model){
		String username=(String) request.getSession().getAttribute("username");
		System.out.println(username);
		model.addAttribute("username", username);
		String userface=UserDAO.getUserface(username, jdbcTemplate);
		model.addAttribute("userface", userface);
		int judge=LuckyDAO.judgesnatch(jdbcTemplate);
		if(judge==1)
		{
			return "index";
		}
		else return "userlucky1";
	}
	
	/**
	 * 目的：导航栏点击进入评论弹幕页面
	 * @param request  获取用户名
	 * @param model 用户身份的model
	 * @param httpsession  获取用户名session
 	 * @return
	 */
	@RequestMapping("/gotouserchat1")
	public String gotouserchat1(HttpServletRequest request, Model model,HttpSession httpsession){
		String username=(String) request.getSession().getAttribute("username");
		System.out.println(username);
		//修改时间session为当前用户请求时间
		java.util.Date date = new java.util.Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		httpsession.setAttribute("timeStamp", timeStamp);

		model.addAttribute("username", username);
		String userface=UserDAO.getUserface(username, jdbcTemplate);
		model.addAttribute("userface", userface);
		return "userchat1";
	}
	
	/**
	 * 目的：导航栏点击进入用户个人信息页面
	 * @param request  获取当前用户 用户名
	 * @param model  用户身份的Model
	 * @return 返回进入个人信息页面
	 */
	@RequestMapping("/gotouserperinfo1")
	public String gotouserperinfo1(HttpServletRequest request, Model model){
 // 获取用户名
		String username=(String) request.getSession().getAttribute("username");
		
		model.addAttribute("username", username);
		String userface=UserDAO.getUserface(username, jdbcTemplate);
		model.addAttribute("userface", userface);
		return "userperinfo1";
	}
	
	/**
	 * 目的：导航栏点击进入节目打赏页面
	 * @param request  获取当前用户用户名
	 * @param model  用户身份的model 
	 * @return 回到操作前页面
	 */
	@RequestMapping("/gotouserprogram1")
	public String gotouserprogram1(HttpServletRequest request, Model model){
		//获取当前用户用户名
		String username=(String) request.getSession().getAttribute("username");
		
		model.addAttribute("username", username);
		String userface=UserDAO.getUserface(username, jdbcTemplate);
		model.addAttribute("userface", userface);
// 获取优秀节目列表
		List<Program> program_list = ProgramListDAO.Headperfectprogram(jdbcTemplate);
		model.addAttribute("program_list", program_list);
//返回节目打赏页面
		return "userprogram1";
	}

	/**
	 * 
	 * @param req  请求
	 * @param response 响应请求
	 * @param username 用户名
	 * @param password 用户登录密码
	 * @param itcode 用户id
	 * @param model user model
	 * @param httpsession 设置用户名 用户id 以及当前时间的session
	 */
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public void login(HttpServletRequest req,HttpServletResponse response,String username,String password,String itcode,Model model,HttpSession httpsession) {
		//如果是第一次进入这个login的话，我们就清除onlineUser的所有值
		
	
		if(counter==0) {
			OnlineuserDAO.deleteAllOnlineuser(jdbcTemplate);
			counter++;
		}
		//判断用户信息
		int flag=UserDAO.checkUserInfo(itcode, username,password, jdbcTemplate);
		HttpSession session = req.getSession();
		//判断是不是已经登陆了
		boolean flag2=false;
		if(session.getAttribute("username")==null) {
			//为空代表未登录
			flag2=false;
		}else {
			//不为空代表已登录
			flag2=true;
		}
		//判断是不是在别的地方登陆了
		boolean flag3=OnlineuserDAO.IsOnline(username, jdbcTemplate);
		//已登录我们要提示说回到有退出登陆的页面退出登录
		if(flag2==true) {
			try {
				//在这个网页已经登录过得情况
				response.getWriter().print("4");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			if(flag3==true) {
				try {
					//在别的地方已经登陆过这个账号
					response.getWriter().print("5");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				//密码错误，但是有用户名
				if(flag==0||flag==-1) {
					try {
						//密码错误，
						response.getWriter().print("1");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//在这里想想要跳到哪里
				if(flag==1) {
					//这些是关于session的东西
					req.getSession(true).setAttribute("username", username);	
					req.getSession(true).setAttribute("itcode", itcode);//itcode的session
					sessions.put(session.toString(), username);
					java.util.Date date = new java.util.Date();
					System.out.println(date);
					System.out.println("HHHH");
					Timestamp timeStamp = new Timestamp(date.getTime());
					req.getSession(true).setAttribute("timeStamp", timeStamp);
					System.out.println(timeStamp);
					boolean isadmin=UserDAO.isadminByItcode(itcode, jdbcTemplate);
					if(isadmin) {
						try {
							response.getWriter().print("2");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else
						try {
							response.getWriter().print("3");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
			
		}
		
	}
	
	/**
	 * 注销登录并返回登录页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout") 
	public String logout(HttpServletRequest request) {
		System.out.println("111");
		request.getSession().invalidate();
		return "home";
	}
	
	//赖
	/**
	 *  从数据库里面取出评论，并以弹幕形式显示在屏幕上
	 */
	@RequestMapping(value = "/Commons", method = RequestMethod.GET) 
	@ResponseBody
	public List<Commons_record> Commons_rds(HttpSession httpsession, Model model) {
//		logger.info(jdbcTemplate.toString());
		//获取时间的session 以此来记录弹幕形成时间
		Timestamp timeStamp = (Timestamp) httpsession.getAttribute("timeStamp");
		System.out.println(timeStamp);
		List<Commons_record> Commons_record = ProgramDAO.Commons_rds(timeStamp, jdbcTemplate);
		//获取当前时间 并将这个时间作为下一次弹幕开始时间
        java.util.Date date = new java.util.Date();
		Timestamp timeStamp1 = new Timestamp(date.getTime());
		httpsession.setAttribute("timeStamp", timeStamp1);
		//返回弹幕界面
		return Commons_record;  
	}
/**
 * 
 * @param dm  将评论存进数据库
 * @param model
 * @return
 */
@RequestMapping(value = "/gooddanmu", method = RequestMethod.GET)
	public  String danmu(String dm, Model model) {
//		logger.info("msg get!");
	//存入评论操作	
		ProgramDAO.sentmessages_dm(dm, jdbcTemplate);
		return "userchat1";

	}
	/**
	 *  按照被打赏金额 获取打赏列表
	 * @param model 得到一个节目列表的model
	 * @return 返回优秀节目列表
	 */
@RequestMapping(value = "/PerfectProgram", method = RequestMethod.GET)
public String perfectprogram(Model model) {
	logger.info(jdbcTemplate.toString());
	List<Program> program_list = ProgramListDAO.Headperfectprogram(jdbcTemplate);
	model.addAttribute("program_list", program_list);
	return "PerfectProgram";

}
/**
 *  按照节目播放时间获取节目列表
 * @param model  得到一个节目列表的model
 * @return  返回节目列表
 */
@RequestMapping(value = "/ProgramList", method = RequestMethod.GET)
public String Pro_List(Model model) {
	logger.info(jdbcTemplate.toString());
	//返回节目列表
	List<Program> program_list = ProgramListDAO.Program_Lt(jdbcTemplate);
    model.addAttribute("program_list", program_list);
	//返回节目列表页面
	return "userprogram1";

}
/**
 *  节目打赏功能
 * @param model  得到一个节目列表的model
 * @param pid 节目的节目id
 * @param amount 打赏该节目金额
 * @param httpsession 获取当前用户用户名以及id
 * @param response 返回打赏结果 
 * @return
 */
@RequestMapping(value = "/reward")
public String Reward(Model model,String pid,String amount, HttpSession httpsession,HttpServletResponse  response) {
	logger.info(jdbcTemplate.toString()); 
	//设置response的编码
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
	//获取当前用户信息
	String username = (String) httpsession.getAttribute("username");
	String itcode = (String) httpsession.getAttribute("itcode");
	if(amount=="") {
		amount=""+0;
	}
	//将string 类型转化我int
	int aa = Integer.valueOf(amount).intValue();
	//获取节目的列表model
	List<Program> program_list = ProgramListDAO.Program_Lt(jdbcTemplate);
	model.addAttribute("program_list", program_list);
		
		try {
			//执行打赏操作
			int i = ProgramListDAO.reward(itcode, username, pid, aa, jdbcTemplate);
			response.getWriter().print(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return null;

}
/**
 *   获取当前用户信息 
 * @param pid 打赏节目id
 * @param model 
 * @param response 返回改打赏节目名称
 * @return
 */
@RequestMapping(value="/findproname")
public String findproname(int pid,Model model,HttpServletResponse response) {
	//设置编码
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
	//获取当前节目名称
	String proname=ProgramListDAO.fingPronameByPid(pid, jdbcTemplate);
	
	try {
		
		response.getWriter().print(proname);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
	
}

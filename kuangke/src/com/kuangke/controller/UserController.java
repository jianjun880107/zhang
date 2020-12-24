package com.kuangke.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.kuangke.dao.UserDao;
import com.kuangke.domain.User;

@Controller
//@RequestMapping("/user")
public class UserController {
	@Resource 
	UserDao userDao;
	/**
	 * 
	 * ��¼
	 * @return
	 */
	@RequestMapping("/logon")
	public String logon(User user,Map<String, String> outMap){
		int count= userDao.logon(user);
		if(count>0){
			return "redirect:/student/query";
		}else{
			outMap.put("msg_info", "�û������������");
			return "redirect:jsp/index.jsp";
		}		
	}
	
	/**
	 * ע��
	 * @param userName
	 * @param passWord
	 * @param outMap
	 * @return
	 */
	@RequestMapping("/register")
	public String register(@RequestParam("userName") String userName,@RequestParam("passWord") String passWord,Map<String, String> outMap){
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("passWord", passWord);
		int count= userDao.selectByUserName(map);
		if(count>0){
			outMap.put("msg_info", "�û����ظ���");
			return "redirect:jsp/index.jsp";
		}else{
			userDao.insertUser(map);
			return "redirect:/student/query";
		}		
	}
}

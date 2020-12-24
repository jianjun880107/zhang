package com.kuangke.dao;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import com.kuangke.domain.User;

@Service
public interface UserDao {
	public int logon(User user);
	public int selectByUserName(HashMap<String,Object> map);
	public int insertUser(HashMap<String,Object> map);
}

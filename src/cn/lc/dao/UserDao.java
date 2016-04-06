package cn.lc.dao;

import java.awt.Image;
import java.io.InputStream;

import cn.lc.domain.User;

public interface UserDao {

	void add(User user);
    
	void update(User user);
    
	User find(String id);

	User find(String username, String password);

	User findUsername(String username);
	
	InputStream getPhoto(String username);
}
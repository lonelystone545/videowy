package com.wy.user.dao;

import com.wy.user.pojo.User;

public interface IUserDao {
	public User findByUserName(String username);

	public void save(User user);
	
}

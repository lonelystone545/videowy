package com.wy.user.service;

import com.wy.user.pojo.User;

public interface IUserService {
	
	User findByUserName(String username);

	void save(User user);
	
}

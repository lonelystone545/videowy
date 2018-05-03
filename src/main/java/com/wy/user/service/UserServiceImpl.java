package com.wy.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wy.user.dao.IUserDao;
import com.wy.user.pojo.User;

@Service
@Transactional
public class UserServiceImpl implements IUserService{

	private IUserDao userDao;
	@Autowired
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	//根据用户名查询用户
	@Override
	public User findByUserName(String username) {
		User user = userDao.findByUserName(username);
		return user;
	}

	//向数据库中保存一个用户
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}
}

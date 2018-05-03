package com.wy.user.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import com.wy.user.pojo.User;

@Repository
public class UserDaoImpl implements IUserDao {
	private HibernateTemplate hibernateTemplate;
	LocalSessionFactoryBean sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	//根据用户名查询用户
	@Override
	public User findByUserName(String username) {
		String hql = "from User where username=?";
		List<User> list = (List<User>) this.hibernateTemplate.find(hql, username);
		if(list!=null && list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}

//	向数据库中保存一个用户
	@Override
	public void save(User user) {
		this.hibernateTemplate.save(user);
	}
	
}

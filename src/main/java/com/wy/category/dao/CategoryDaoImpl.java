package com.wy.category.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import com.wy.category.pojo.Category;

@Repository
public class CategoryDaoImpl implements ICategoryDao {
	
	private HibernateTemplate hibernateTemplate;
	LocalSessionFactoryBean sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	//查询所有分类
	@Override
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> list = (List<Category>) this.hibernateTemplate.find(hql);
		return list;
	}

}

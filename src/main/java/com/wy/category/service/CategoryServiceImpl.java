package com.wy.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wy.category.dao.ICategoryDao;
import com.wy.category.pojo.Category;

@Service
public class CategoryServiceImpl implements ICategoryService {
	
	private ICategoryDao categoryDao;
	@Autowired
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	//查询所有分类
	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

}

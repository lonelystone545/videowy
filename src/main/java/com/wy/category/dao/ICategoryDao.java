package com.wy.category.dao;

import java.util.List;

import com.wy.category.pojo.Category;

public interface ICategoryDao {

	List<Category> findAll();

}

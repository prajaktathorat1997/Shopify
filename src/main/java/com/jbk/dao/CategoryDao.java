package com.jbk.dao;

import java.util.List;

import com.jbk.entity.CategoryEntity;


public interface CategoryDao {
	
	public int addCategory(CategoryEntity category);
	public CategoryEntity getCategoryById(long categoryId);
	public List<CategoryEntity> getAllCategorys();
	public int deleteCategory(long categoryId);
	public int updateCategory(CategoryEntity category);
	
	

}

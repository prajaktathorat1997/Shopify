package com.jbk.service;

import java.util.List;

import com.jbk.model.CategoryModel;


public interface CategoryService {
	
	public int addCategory(CategoryModel category);
	public CategoryModel getCategoryById(long categoryId);

	public List<CategoryModel> getAllCategorys();
	
	public int deleteCategory(long categoryId);
	
	public int updateCategory(CategoryModel category);
	
	
	
	
}

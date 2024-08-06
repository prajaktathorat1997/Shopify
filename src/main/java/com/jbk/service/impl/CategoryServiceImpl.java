package com.jbk.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jbk.dao.CategoryDao;
import com.jbk.entity.CategoryEntity;
import com.jbk.exception.ResourceNotExistsException;
import com.jbk.model.CategoryModel;
import com.jbk.service.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CategoryDao dao;

	@Override
	public int addCategory(CategoryModel categoryModel) {

		CategoryEntity categoryEntity = mapper.map(categoryModel, CategoryEntity.class);

		return dao.addCategory(categoryEntity);

	}

	@Override
	public CategoryModel getCategoryById(long categoryId) {

		CategoryEntity categoryEntity = dao.getCategoryById(categoryId);
		if (categoryEntity != null) {
			CategoryModel categoryModel = mapper.map(categoryEntity, CategoryModel.class);
			return categoryModel;
		} else {
			throw new ResourceNotExistsException("Category Not Exists With Id : " + categoryId);
		}

	}

	@Override
	public List<CategoryModel> getAllCategorys() {
		List<CategoryEntity> categoryEntityList = dao.getAllCategorys();
		List<CategoryModel> list = new ArrayList<CategoryModel>();
		if (!categoryEntityList.isEmpty()) {

			for (CategoryEntity categoryEntity : categoryEntityList) {

				CategoryModel categoryModel = mapper.map(categoryEntity, CategoryModel.class);
				list.add(categoryModel);
			}

		} else {
			throw new ResourceNotExistsException("Category Not Exists");
		}

		return list;

	}

	@Override
	public int deleteCategory(long categoryId) {

		return dao.deleteCategory(categoryId);

	}

	@Override
	public int updateCategory(CategoryModel category) {
		CategoryEntity categoryEntity = mapper.map(category, CategoryEntity.class);

		return dao.updateCategory(categoryEntity);

	}

}

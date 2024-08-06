package com.jbk.dao.impl;

import java.util.List;

import javax.persistence.RollbackException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.dao.CategoryDao;
import com.jbk.entity.CategoryEntity;
import com.jbk.entity.CategoryEntity;
import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourceNotExistsException;
import com.jbk.exception.SomethingWentWrongException;

@Component
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sf;

	private String ExceptionError = "Something Went Wrong During operation";

	private String resourceExistError = "Resource Already Exists check unique Properties";

	public CategoryDaoImpl() {

	}

	@Override
	public int addCategory(CategoryEntity category) {
		Session session = sf.openSession();
		try {

			CategoryEntity categoryEntity = session.get(CategoryEntity.class, category.getCategoryId());

			if (categoryEntity == null) {
				session.save(category); //

				session.beginTransaction().commit(); // commit - add, update, delete
				return 1;
			} else {
				throw new ResourceAlreadyExistException(resourceExistError);
			}

		} catch (RollbackException e) {
			throw new ResourceAlreadyExistException(resourceExistError);
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}

	}

	@Override
	public CategoryEntity getCategoryById(long categoryId) {
		Session session = sf.openSession();
		CategoryEntity categoryEntity = null;
		try {
			categoryEntity = session.get(CategoryEntity.class, categoryId);

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
		return categoryEntity;

	}

	@Override
	public List<CategoryEntity> getAllCategorys() {
		Session session = sf.openSession();
		List<CategoryEntity> list = null;
		try {
			Criteria criteria = session.createCriteria(CategoryEntity.class);
			criteria.add(Restrictions.gt("categoryId", 3l));

			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
		return list;
	}

	@Override
	public int deleteCategory(long categoryId) {
		Session session = sf.openSession();
		try {
			CategoryEntity dbCategory = session.get(CategoryEntity.class, categoryId);
			if(dbCategory!=null) {
				session.delete(dbCategory);
				return 1;
			}else {
				 throw new ResourceNotExistsException("Category not exists with id = "+categoryId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
	

	}

	@Override
	public int updateCategory(CategoryEntity category) {
		Session session = sf.openSession();
		try {
			CategoryEntity dbSuppplier = getCategoryById(category.getCategoryId());
			if(dbSuppplier!=null) {
				session.update(category);
				session.beginTransaction().commit();
				return 1;
			}else {
				 throw new ResourceNotExistsException("Category not exists with id = "+category.getCategoryId());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
		

	}

}

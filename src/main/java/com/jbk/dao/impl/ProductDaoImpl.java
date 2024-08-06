package com.jbk.dao.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.dao.ProductDao;
import com.jbk.entity.ProductEntity;
import com.jbk.entity.ProductEntity;
import com.jbk.exception.ResourceNotExistsException;
import com.jbk.exception.SomethingWentWrongException;

@Component
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sf;

	private String ExceptionError = "Something Went Wrong During operation";

	public ProductDaoImpl() {

	}

	@Override
	public int addProduct(ProductEntity product) {
		Session session = sf.openSession();
		try {
		
			
				session.save(product); //
				session.beginTransaction().commit();

				return 1;
			
			

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}

	}

	@Override
	public ProductEntity getProductById(long productId) {
		Session session = sf.openSession();
		ProductEntity productEntity = null;
		try {
			productEntity = session.get(ProductEntity.class, productId);

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
		return productEntity;

	}

	@Override
	public List<ProductEntity> getAllProducts() {
		Session session = sf.openSession();
		List<ProductEntity> list = null;
		try {

			LocalDate currentDate = LocalDate.now();

			// Define the date format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			// Format the current date
			String formattedDate = currentDate.format(formatter);
			Criteria criteria = session.createCriteria(ProductEntity.class);

			Criteria criteria2 = session.createCriteria(ProductEntity.class);
			criteria2.setProjection(Projections.min("expDate"));
			criteria2.add(Restrictions.gt("expDate", formattedDate));

			List<String> list2 = criteria2.list();

			if (!list2.isEmpty()) {
				String date = list2.get(0);
				criteria.add(Restrictions.eq("expDate", date));
				list = criteria.list();

			}

			// criteria.add(Restrictions.gt("productId", 3l));

			// list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
		return list;
	}

	@Override
	public int deleteProduct(long productId) {
		Session session = sf.openSession();
		try {
			ProductEntity dbProduct = session.get(ProductEntity.class, productId);
			if (dbProduct != null) {
				session.delete(dbProduct);
				return 1;
			} else {
				throw new ResourceNotExistsException("Product not exists with id = " + productId);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}

	}

	@Override
	public int updateProduct(ProductEntity product) {
		Session session = sf.openSession();
		try {
			ProductEntity dbSuppplier = getProductById(product.getProductId());
			if (dbSuppplier != null) {
				session.update(product);
				session.beginTransaction().commit();
				return 1;
			} else {
				throw new ResourceNotExistsException("Product not exists with id = " + product.getProductId());
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}

	}

	@Override
	public ProductEntity getProductByName(String productName) {
		Session session = sf.openSession();
		List<ProductEntity> list = null;
		ProductEntity productEntity = null;
		try {

			Criteria criteria = session.createCriteria(ProductEntity.class);

			SimpleExpression eq = Restrictions.eq("productName", productName);

			list = criteria.add(Restrictions.eq("productName", productName)).list();
			if (!list.isEmpty()) {
				productEntity = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
		return productEntity;

	}

	@Override
	public List<ProductEntity> getProductByPriceRange(double minPrice, double maxPrice) {
		Session session = sf.openSession();
		List<ProductEntity> list = null;
		try {

			Criteria criteria = session.createCriteria(ProductEntity.class);

			list = criteria.add(Restrictions.between("productPrice", minPrice, maxPrice)).list();

			for (ProductEntity productEntity : list) {
				System.out.println(productEntity);
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}

	}

	@Override
	public List<ProductEntity> getAllProductStartWith(String expression) {
		Session session = sf.openSession();
		try {

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
		return null;

	}

	@Override
	public List<ProductEntity> sortProducts(String orderType, String field) {
		Session session = sf.openSession();
		try {

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
		return null;

	}

	@Override
	public double getMaxPrice() {
		Session session = sf.openSession();
		double maxPrice = 0;
		try {
			Criteria criteria = session.createCriteria(ProductEntity.class);

			List list = criteria.setProjection(Projections.max("productPrice")).list();

			if (!list.isEmpty())

			{
				maxPrice = (double) list.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
		return maxPrice;
	}

	@Override
	public List<ProductEntity> getMaxPriceProduct() {
		Session session = sf.openSession();
		List<ProductEntity> list = null;
		try {
			double maxPrice = getMaxPrice();
			Criteria criteria = session.createCriteria(ProductEntity.class);

			list = criteria.add(Restrictions.eq("productPrice", maxPrice)).list();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductEntity> hqlEx() {
		Session session = sf.openSession();
		List<ProductEntity> list = null;
		try {
			// select product_name from product select * from product
			// select productName from ProductEntity FROM ProductEntity

			// select * from product where product_name=?
			// FROM ProductEntity WHERE productName= :paramatername

			// Query<ProductEntity> query = session.createQuery("FROM ProductEntity WHERE
			// productName= :pname");
			
			//FROM ProductEntity p WHERE p.expDate = (SELECT MIN(p2.expDate) FROM ProductEntity p2 WHERE p2.expDate > CURRENT_DATE)
			Query<ProductEntity> query = session.createQuery(
					"FROM ProductEntity p JOIN p.category c JOIN p.supplier s");
			//query.setParameter("pname", "aa");
			list = query.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
		return list;
	}

}

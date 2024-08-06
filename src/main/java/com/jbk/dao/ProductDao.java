package com.jbk.dao;

import java.util.List;

import com.jbk.entity.ProductEntity;
import com.jbk.model.ProductModel;


public interface ProductDao {
	
	public int addProduct(ProductEntity product);
	public ProductEntity getProductById(long productId);
	public List<ProductEntity> getAllProducts();
	public int deleteProduct(long productId);
	public int updateProduct(ProductEntity product);
	
	public ProductEntity getProductByName(String productName);
	public List<ProductEntity> getProductByPriceRange(double minPrice, double maxPrice);
	public List<ProductEntity> getAllProductStartWith(String expression);
	public List<ProductEntity> sortProducts(String orderType, String field);
	public double getMaxPrice();
	public List<ProductEntity> getMaxPriceProduct();
	
	public List<ProductEntity> hqlEx();

}

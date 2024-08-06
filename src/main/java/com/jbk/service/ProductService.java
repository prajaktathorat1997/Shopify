package com.jbk.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jbk.entity.ProductEntity;
import com.jbk.model.ProductModel;

public interface ProductService {
	
	public int addProduct(ProductEntity product);
	public ProductEntity getProductById(long productId);

	public List<ProductEntity> getAllProducts();
	
	public int deleteProduct(long productId);
	
	public int updateProduct(ProductEntity product);
	
	public ProductEntity getProductByName(String productName);  // select * from product where product_name="pen";
	public List<ProductEntity> getProductByPriceRange(double minPrice, double maxPrice);
	public List<ProductEntity> getAllProductStartWith(String expression);
	public List<ProductEntity> sortProducts(String orderType, String field);
	public double getMaxPrice();
	public List<ProductEntity> getMaxPriceProduct();
	
	
	public List<ProductModel> hqlEx();
	
	public LinkedHashMap<String, Object> uploadSheet(MultipartFile file);
	
	
}

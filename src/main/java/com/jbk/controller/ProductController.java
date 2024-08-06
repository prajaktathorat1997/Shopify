package com.jbk.controller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.entity.ProductEntity;
import com.jbk.exception.ResourceNotExistsException;
import com.jbk.model.ProductModel;
import com.jbk.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
// ProductService service=new ProductServiceImpl();
	@Autowired
	ProductService service;

	@PostMapping("/add-product")
	public String addProduct(@RequestBody @Valid ProductEntity product) {

		service.addProduct(product);

		return "Added Successfully";

	}

	@GetMapping("/get-product-by-id/{id}") // {_} placeholder
	public ResponseEntity<ProductEntity> getProductById(@PathVariable("id") long productId) {

		ProductEntity product = service.getProductById(productId);
		return new ResponseEntity<ProductEntity>(product, HttpStatus.OK);
	}

	@GetMapping("/get-all-products")
	public ResponseEntity<List<ProductEntity>> getAllProducts() {

		List<ProductEntity> productList = service.getAllProducts();
		if (!productList.isEmpty()) {
			return new ResponseEntity<List<ProductEntity>>(productList, HttpStatus.OK);
		} else {
			throw new ResourceNotExistsException("Product Not Exists, List Is Empty");
		}

	}

	@DeleteMapping("/delete-product-by-id")
	public ResponseEntity<String> deleteProduct(@RequestParam long productId) {

		service.deleteProduct(productId);
		// return ResponseEntity.ok("Deleted");
		return new ResponseEntity<String>("Deleted", HttpStatus.MOVED_PERMANENTLY);

	}

	@PutMapping("/update-product")
	public ResponseEntity<String> updateUpdateProduct(@RequestBody ProductEntity product) {

		int status = service.updateProduct(product);
		if (status == 1) {
			return new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("Not Found", HttpStatus.OK);

		}

	}

	// ***********************************************************************

	@GetMapping("/byName/{productName}")
	public ResponseEntity<ProductEntity> getProductByName(@PathVariable String productName) {
		ProductEntity product = service.getProductByName(productName);
		return ResponseEntity.ok(product);
	}

	@GetMapping("/byPriceRange")
	public ResponseEntity<List<ProductEntity>> getProductByPriceRange(@RequestParam double minPrice,
			@RequestParam double maxPrice) {
		List<ProductEntity> products = service.getProductByPriceRange(minPrice, maxPrice);
		return ResponseEntity.ok(products);
	}

	@GetMapping("/startWith/{expression}")
	public ResponseEntity<List<ProductEntity>> getAllProductStartWith(@PathVariable String expression) {
		List<ProductEntity> products = service.getAllProductStartWith(expression);
		return ResponseEntity.ok(products);
	}

	@GetMapping("/sort")
	public ResponseEntity<List<ProductEntity>> sortProducts(@RequestParam String orderType,
			@RequestParam String field) {
		List<ProductEntity> products = service.sortProducts(orderType, field);
		return ResponseEntity.ok(products);
	}

	@GetMapping("/maxPrice")
	public ResponseEntity<Double> getMaxPrice() {
		double maxPrice = service.getMaxPrice();
		return ResponseEntity.ok(maxPrice);
	}

	@GetMapping("/maxPriceProduct")
	public ResponseEntity<List<ProductEntity>> getMaxPriceProduct() {
		List<ProductEntity> products = service.getMaxPriceProduct();
		return ResponseEntity.ok(products);
	}

	@GetMapping("/hqlex")
	public ResponseEntity<List<ProductModel>> hqlEx() {

		return new ResponseEntity<List<ProductModel>>(service.hqlEx(), HttpStatus.OK);

	}

	@PostMapping("/upload-sheet")
	public ResponseEntity<LinkedHashMap<String, Object>> uploadSheet(@RequestParam MultipartFile file) {

		LinkedHashMap<String, Object> result = service.uploadSheet(file);

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}

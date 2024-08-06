package com.jbk.validation;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.exception.ResourceNotExistsException;
import com.jbk.model.CategoryModel;
import com.jbk.model.ProductModel;
import com.jbk.model.SupplierModel;
import com.jbk.service.CategoryService;
import com.jbk.service.SupplierService;

@Component
public class ObjectValidation {
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SupplierService supplierService;

	public Map<String, String> validateProduct(ProductModel productModel) {

		Map<String, String> errorMap = new HashedMap<>();
		String productName = productModel.getProductName();
		double productPrice = productModel.getProductPrice();
		int productQty = productModel.getProductQty();
		String mfgDate = productModel.getMfgDate();
		String expDate = productModel.getExpDate();
		int deliveryCharges = productModel.getDeliveryCharges();
		CategoryModel category = productModel.getCategory();
		SupplierModel supplier = productModel.getSupplier();

		if (productName == null || productName.trim().equals("")) {
			errorMap.put("Product Name", "Invalid product Name");
		}

		if (productPrice <= 0) {
			errorMap.put("Product Price", "Invalid Product Price");
		}

		if (productQty <= 0) {
			errorMap.put("Product Qty", "Invalid Product Qty");
		}

		if (mfgDate == null) {
			errorMap.put("Product MFG Date", "Invalid MFG Date");
		}

		if (expDate == null) {
			errorMap.put("Product EXP Date", "Invalid EXP Date");
		}

		if (deliveryCharges <= 0) {
			errorMap.put("Delivery Charges", "Invalid Delivery Charges");
		}

		try {
			categoryService.getCategoryById(category.getCategoryId());
		} catch (ResourceNotExistsException e) {
			errorMap.put("Category", "Category Not Exists");
		}

		try {
			supplierService.getSupplierById(supplier.getSupplierId());
		} catch (ResourceNotExistsException e) {
			errorMap.put("Supplier", "Supplier Not Exists");
		}

		return errorMap;
	}

}

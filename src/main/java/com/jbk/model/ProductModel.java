package com.jbk.model;


public class ProductModel {

	
	private long productId;
	
	private String productName;
	
	
	private SupplierModel supplier;
	
	private CategoryModel category;
	
	private int productQty;
	
	private double productPrice;
	
	private String mfgDate;
	
	private String expDate;
	
	private int deliveryCharges;
	
	
	public ProductModel() {
		// TODO Auto-generated constructor stub
	}


	
	public ProductModel(long productId, String productName, SupplierModel supplier, CategoryModel category,
			int productQty, double productPrice, String mfgDate, String expDate, int deliveryCharges) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.supplier = supplier;
		this.category = category;
		this.productQty = productQty;
		this.productPrice = productPrice;
		this.mfgDate = mfgDate;
		this.expDate = expDate;
		this.deliveryCharges = deliveryCharges;
	}



	public SupplierModel getSupplier() {
		return supplier;
	}


	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}


	public CategoryModel getCategory() {
		return category;
	}


	public void setCategory(CategoryModel category) {
		this.category = category;
	}


	public int getDeliveryCharges() {
		return deliveryCharges;
	}


	public void setDeliveryCharges(int deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	public long getProductId() {
		return productId;
	}


	public void setProductId(long productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getProductQty() {
		return productQty;
	}


	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}


	public double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}


	public String getMfgDate() {
		return mfgDate;
	}


	public void setMfgDate(String mfgDate) {
		this.mfgDate = mfgDate;
	}


	public String getExpDate() {
		return expDate;
	}


	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}


	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productQty=" + productQty
				+ ", productPrice=" + productPrice + ", mfgDate=" + mfgDate + ", expDate=" + expDate + "]";
	}
	
	
	

}

package com.jbk.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class SupplierModel {
	
	@Min(value = 1,  message = "Invalid Supplier Id")
	private long supplierId;

	@Pattern(regexp = "^[A-Za-z]+\\s+[A-Za-z]+$", message = "Invalid Supplier Name")
	private String supplierName;
	
	@Pattern(regexp = "^[A-Za-z]+\\s+[A-Za-z]+$", message = "Invalid City Name")
	private String city;
	
	// 100000  111111 234563    987654    999999   
	// 000000   0987654   
	
	@Min(value= 100000, message="Invalid Postal Code")
	@Max(value=999999,  message="Invalid Postal Code")
//	@Pattern(regexp = "[1-9]\\d{5}", message = "Invalid postal Name")
	private int postalCode;
	
	@Pattern(regexp = "^[A-Za-z]+\\s+[A-Za-z]+$", message = "Invalid Country Name")
	private String country;
	
	@Pattern(regexp = "[1-9]\\d{9}", message = "Invalid Mobile  Number")
	private String mobileNumber;

	public SupplierModel() {
		// TODO Auto-generated constructor stub
	}

	public SupplierModel(long supplierId, String supplierName, String city, int postalCode, String country,
			String mobileNumber) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
		this.mobileNumber = mobileNumber;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "SupplierEntity [supplierId=" + supplierId + ", supplierName=" + supplierName + ", city=" + city
				+ ", postalCode=" + postalCode + ", country=" + country + ", mobileNumber=" + mobileNumber + "]";
	}

}

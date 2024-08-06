package com.jbk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "supplier")
public class SupplierEntity {
	
	@Id
	private long supplierId;

	@Column(nullable = false, unique = true)
	private String supplierName;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private int postalCode;
	
	@Column(nullable = false)
	private String country;
	
	@Column(nullable = false, unique = true)
	private String mobileNumber;

	public SupplierEntity() {
		// TODO Auto-generated constructor stub
	}

	public SupplierEntity(long supplierId, String supplierName, String city, int postalCode, String country,
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

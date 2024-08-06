package com.jbk.service;

import java.util.List;

import com.jbk.model.SupplierModel;


public interface SupplierService {
	
	public int addSupplier(SupplierModel supplier);
	public SupplierModel getSupplierById(long supplierId);

	public List<SupplierModel> getAllSuppliers();
	
	public int deleteSupplier(long supplierId);
	
	public int updateSupplier(SupplierModel supplier);
	
	
	
	
}

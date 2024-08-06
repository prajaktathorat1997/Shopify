package com.jbk.dao;

import java.util.List;

import com.jbk.entity.SupplierEntity;
import com.jbk.model.SupplierModel;


public interface SupplierDao {
	
	public int addSupplier(SupplierEntity supplier);
	public SupplierEntity getSupplierById(long supplierId);
	public List<SupplierEntity> getAllSuppliers();
	public int deleteSupplier(long supplierId);
	public int updateSupplier(SupplierEntity supplier);
	
	
	
	

}

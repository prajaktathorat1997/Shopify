package com.jbk.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jbk.dao.SupplierDao;
import com.jbk.entity.SupplierEntity;
import com.jbk.exception.ResourceNotExistsException;
import com.jbk.model.SupplierModel;
import com.jbk.service.SupplierService;

@Component
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private SupplierDao dao;

	@Override
	public int addSupplier(SupplierModel supplierModel) {

		SupplierEntity supplierEntity = mapper.map(supplierModel, SupplierEntity.class);

		return dao.addSupplier(supplierEntity);

	}

	@Override
	public SupplierModel getSupplierById(long supplierId) {

		SupplierEntity supplierEntity = dao.getSupplierById(supplierId);
		if (supplierEntity != null) {
			SupplierModel supplierModel = mapper.map(supplierEntity, SupplierModel.class);
			return supplierModel;
		} else {
			throw new ResourceNotExistsException("Supplier Not Exists With Id : " + supplierId);
		}

	}

	@Override
	public List<SupplierModel> getAllSuppliers() {
		List<SupplierEntity> supplierEntityList = dao.getAllSuppliers();
		List<SupplierModel> list = new ArrayList<SupplierModel>();
		if (!supplierEntityList.isEmpty()) {

			for (SupplierEntity supplierEntity : supplierEntityList) {

				SupplierModel supplierModel = mapper.map(supplierEntity, SupplierModel.class);
				list.add(supplierModel);
			}

		} else {
			throw new ResourceNotExistsException("Supplier Not Exists");
		}

		return list;

	}

	@Override
	public int deleteSupplier(long supplierId) {

		return dao.deleteSupplier(supplierId);

	}

	@Override
	public int updateSupplier(SupplierModel supplier) {
		SupplierEntity supplierEntity = mapper.map(supplier, SupplierEntity.class);
		return dao.updateSupplier(supplierEntity);

	}

}

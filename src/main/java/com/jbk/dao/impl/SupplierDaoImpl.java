package com.jbk.dao.impl;

import java.util.List;

import javax.persistence.RollbackException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.dao.SupplierDao;
import com.jbk.entity.ProductEntity;
import com.jbk.entity.SupplierEntity;
import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourceNotExistsException;
import com.jbk.exception.SomethingWentWrongException;

@Component
public class SupplierDaoImpl implements SupplierDao {

	@Autowired
	private SessionFactory sf;

	private String ExceptionError = "Something Went Wrong During operation";

	private String resourceExistError = "Resource Already Exists check unique Properties";

	public SupplierDaoImpl() {

	}

	@Override
	public int addSupplier(SupplierEntity supplier) {
		Session session = sf.openSession();
		try {

			List<SupplierEntity> list = getSupplierByUniqueFields(supplier.getSupplierId(), supplier.getSupplierName(), supplier.getMobileNumber());
			if (list.isEmpty()) {
				session.save(supplier); //

				session.beginTransaction().commit(); // commit - add, update, delete
				return 1;
			} else {
				throw new ResourceAlreadyExistException(resourceExistError);
			}

		} catch (ResourceAlreadyExistException e) {
			throw new ResourceAlreadyExistException(resourceExistError);
		} 
		
		catch (RollbackException e) {
			throw new ResourceAlreadyExistException(resourceExistError);
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}

	}

	public List<SupplierEntity> getSupplierByUniqueFields(long supplierId, String supplierName, String mobileNumber) {
		Session session = sf.openSession();
		List list = null;
		try {
			Criteria criteria = session.createCriteria(SupplierEntity.class);
			Criterion id = Restrictions.eq("supplierId", supplierId);
			Criterion name = Restrictions.eq("supplierName", supplierName);
			Criterion mobile = Restrictions.eq("mobileNumber", mobileNumber);

			criteria.add(Restrictions.or(id, name, mobile));

			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}

		return list;

	}

	@Override
	public SupplierEntity getSupplierById(long supplierId) {
		Session session = sf.openSession();
		SupplierEntity supplierEntity = null;
		try {
			supplierEntity = session.get(SupplierEntity.class, supplierId);

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
		return supplierEntity;

	}

	@Override
	public List<SupplierEntity> getAllSuppliers() {
		Session session = sf.openSession();
		List<SupplierEntity> list = null;
		try {
			Criteria criteria = session.createCriteria(SupplierEntity.class);
			criteria.add(Restrictions.gt("supplierId", 3l));

			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
		return list;
	}

	@Override
	public int deleteSupplier(long supplierId) {
		Session session = sf.openSession();
		try {
			SupplierEntity dbSupplier = session.get(SupplierEntity.class, supplierId);
			if (dbSupplier != null) {
				session.delete(dbSupplier);
				return 1;
			} else {
				throw new ResourceNotExistsException("Supplier not exists with id = " + supplierId);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}

	}

	@Override
	public int updateSupplier(SupplierEntity supplier) {
		Session session = sf.openSession();
		try {
			SupplierEntity dbSuppplier = getSupplierById(supplier.getSupplierId());
			if (dbSuppplier != null) {
				session.update(supplier);
				session.beginTransaction().commit();
				return 1;
			} else {
				throw new ResourceNotExistsException("Supplier not exists with id = " + supplier.getSupplierId());
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}

	}

}

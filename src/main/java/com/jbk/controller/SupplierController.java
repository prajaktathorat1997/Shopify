package com.jbk.controller;

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

import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourceNotExistsException;
import com.jbk.model.SupplierModel;
import com.jbk.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	SupplierService service;

	@PostMapping("/add-supplier")
	public String addSupplier(@RequestBody @Valid SupplierModel supplier) {

		service.addSupplier(supplier);

		return "Added Successfully";

	}

	@GetMapping("/get-supplier-by-id/{id}") // {_} placeholder
	public ResponseEntity<SupplierModel> getSupplierById(@PathVariable("id") long supplierId) {

		return new ResponseEntity<SupplierModel>(service.getSupplierById(supplierId), HttpStatus.OK);

	}

	@GetMapping("/get-all-suppliers")
	public ResponseEntity<List<SupplierModel>> getAllSuppliers() {

		return new ResponseEntity<List<SupplierModel>>(service.getAllSuppliers(), HttpStatus.OK);

	}
//	
//	@DeleteMapping("/delete-supplier-by-id")
//	public ResponseEntity<String> deleteSupplier(@RequestParam long supplierId){
//		
//		 service.deleteSupplier(supplierId);
//		//return ResponseEntity.ok("Deleted");
//		return new ResponseEntity<String>("Deleted",HttpStatus.MOVED_PERMANENTLY);
//		
//	}
//	
//	@PutMapping("/update-supplier")
//	public ResponseEntity<String> updateUpdateSupplier(@RequestBody  SupplierEntity supplier){
//		
//		int status = service.updateSupplier(supplier);
//		if(status==1) {
//			return new ResponseEntity<String>("Updated Successfully",HttpStatus.OK);
//			
//		}else {
//			return new ResponseEntity<String>("Not Found",HttpStatus.OK);
//			
//		}
//	
//	}
//	
//	

	// ***********************************************************************

}

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
import com.jbk.model.CategoryModel;
import com.jbk.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService service;

	@PostMapping("/add-category")
	public String addCategory(@RequestBody @Valid CategoryModel category) {

		service.addCategory(category);

		return "Added Successfully";

	}

	@GetMapping("/get-category-by-id/{id}") // {_} placeholder
	public ResponseEntity<CategoryModel> getCategoryById(@PathVariable("id") long categoryId) {

		return new ResponseEntity<CategoryModel>(service.getCategoryById(categoryId), HttpStatus.OK);

	}

	@GetMapping("/get-all-categorys")
	public ResponseEntity<List<CategoryModel>> getAllCategorys() {

		return new ResponseEntity<List<CategoryModel>>(service.getAllCategorys(), HttpStatus.OK);

	}
	
	@DeleteMapping("/delete-category-by-id")
	public ResponseEntity<String> deleteCategory(@RequestParam long categoryId){
		
		 service.deleteCategory(categoryId);
		//return ResponseEntity.ok("Deleted");
		return new ResponseEntity<String>("Deleted",HttpStatus.MOVED_PERMANENTLY);
		
	}
	
	@PutMapping("/update-category")
	public ResponseEntity<String> updateUpdateCategory(@RequestBody @Valid  CategoryModel category){
		
		int status = service.updateCategory(category);
		if(status==1) {
			return new ResponseEntity<String>("Updated Successfully",HttpStatus.OK);
			
		}else {
			return new ResponseEntity<String>("Not Found",HttpStatus.OK);
			
		}
	
	}
	
	

	// ***********************************************************************

}

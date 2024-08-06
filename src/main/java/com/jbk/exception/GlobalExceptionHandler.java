package com.jbk.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<String>  methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		//Map<String , String> errorMap=new HashMap<>();
		List<String >list=new  ArrayList<>();
	List<FieldError> fieldErrors = ex.getFieldErrors(); // alt + shift + L
		
	for (FieldError fieldError : fieldErrors) {
		
		//errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
		list.add(fieldError.getDefaultMessage());
		
	}
	//errorMap.put("Path", request.getRequestURI());
		
		return list;
		
		
		
	}
	
	@ExceptionHandler(ArithmeticException.class)
	public String arithmeticException (ArithmeticException ex) {
		return ex.getMessage();
		
	}
	
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public ExceptionResponse resourceAlreadyExistException(ResourceAlreadyExistException ex, HttpServletRequest request) {
		ExceptionResponse response=new ExceptionResponse();
		
		response.setMessage(ex.getMessage());
		response.setPath(request.getRequestURI());
		response.setStatusCode(HttpStatus.CONFLICT);
		response.setTime(LocalDateTime.now());
		
		return response;
		
	}
	
	@ExceptionHandler(ResourceNotExistsException.class)
	@ResponseStatus(code = HttpStatus.OK)
	public ExceptionResponse resourceNotExistsException(ResourceNotExistsException ex, HttpServletRequest request) {
		ExceptionResponse response=new ExceptionResponse();
		
		response.setMessage(ex.getMessage());
		response.setPath(request.getRequestURI());
		response.setStatusCode(HttpStatus.NO_CONTENT);
		response.setTime(LocalDateTime.now());
		
		return response;
		
	}

	@ExceptionHandler(SomethingWentWrongException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse somethingWentWrongException(SomethingWentWrongException ex, HttpServletRequest request) {
		ExceptionResponse response=new ExceptionResponse();
		
		response.setMessage(ex.getMessage());
		response.setPath(request.getRequestURI());
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setTime(LocalDateTime.now());
		
		return response;
		
	}

}

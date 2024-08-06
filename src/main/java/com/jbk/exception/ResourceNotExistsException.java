package com.jbk.exception;

public class ResourceNotExistsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotExistsException(String msg) {
		super(msg);
	}
}

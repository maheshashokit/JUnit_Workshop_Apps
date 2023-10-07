package com.ashokit.exceptions;

public class VendorNotFoundException extends RuntimeException {

	public VendorNotFoundException(String errorMessage) {

		super(errorMessage);
	}
}

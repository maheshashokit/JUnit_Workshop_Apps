package com.ashokit;

public class Contact {
	
	private String firstName;
	
	private String lastName;
	
	private String contactNo;
	
	public Contact() {
		
	}
	
	public Contact(String firstName, String lastName, String contactNo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getContactNo() {
		return contactNo;
	}
	
	//additionally methods
	public void validateFirstName() {
		if(this.firstName.isBlank()) {
			throw new RuntimeException("FirstName can't be Empty");
		}
	}
	
	public void validateLastName() {
		if(this.lastName.isBlank()) {
			throw new RuntimeException("LastName can't be Empty");
		}
	}
	
	public void validateContactNo() {
		if(this.contactNo.isBlank()) {
			throw new RuntimeException("ContactNo can't be Empty");
		}
		
		if(this.contactNo.length() != 10) {
			throw new RuntimeException("ContactNo Length Shoule be 10.");
		}
		
		if(!this.contactNo.startsWith("0")) {
			throw new RuntimeException("ContactNumber should start with Zero.");
		}
	}
}

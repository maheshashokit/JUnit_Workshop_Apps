package com.ashokit;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactManager {

	//created Map Object for Storing the Contact Information.
	Map<String, Contact> contactList = new ConcurrentHashMap<String, Contact>();

	//Adding the new Contact
	public void addContact(String firstName, String lastName, String contactNo) {
		
		//created the object for holding contact information
		Contact contact = new Contact(firstName, lastName, contactNo);
		
		//calling the validate logic
		validateContact(contact);
		
		//checking contact information is existed or not
		checkIfContactAlreadyExist(contact);
		
		//will add the contact information into Map object
		//String generateKey(contact) >>>>>> firstName-LastName i.e.,Mahesh-Kumar
		contactList.put(generateKey(contact), contact);
	}

	public Collection<Contact> getAllContacts() {
		return contactList.values();
	}

	private void checkIfContactAlreadyExist(Contact contact) {
		if (contactList.containsKey(generateKey(contact)))
			throw new RuntimeException("Contact Already Exists");
	}

	private void validateContact(Contact contact) {
		contact.validateFirstName();
		contact.validateLastName();
		contact.validateContactNo();
	}

	private String generateKey(Contact contact) {
		//Generating key for map is firstName-LastName
		return String.format("%s-%s", contact.getFirstName(), contact.getLastName());
	}

}

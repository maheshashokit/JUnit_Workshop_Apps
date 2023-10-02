package com.ashokit;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

//Test Class
public class ContactManagerTest {
	
	private ContactManager contactManager;
	
	@BeforeAll
	public static void oneTimeSetUp() {
		System.out.println("Inside the BeforeAll......");
	}
	
	@AfterAll
	public static void oneTimeClearDown() {
		System.out.println("Inside the AfterAll......");
	}
	
	@BeforeEach
	public void setUpData() {
		System.out.println("Inside the BeforeEach......");
		//creating ContactManager Object
	    contactManager = new ContactManager();
	}
	
	@AfterEach
	public void clearDown() {
		System.out.println("Inside the AfterEach......");
		contactManager = null;
	}
	
	@Test
	@DisplayName("Test Case For Creating Contact Successfully....")
	public void shoudCreateContact() {
			
		//adding the contact
		contactManager.addContact("Mahesh", "Kumar", "0123456789");
		
		//ensuring the contact is added or not
		//contactManager.getAllContacts().size() >>> Map object size
		Assertions.assertEquals(1, contactManager.getAllContacts().size());
		Assertions.assertEquals(false,contactManager.getAllContacts().isEmpty());
	}
	
	@Test
	@RepeatedTest(value = 5)
	public void testDisplay() {
		System.out.println("Welcome To Ashok IT");
	}
	
	
	@Test
	@DisplayName("Test Case For Not Creating Contact when FirstName is Empty....")
	public void validateFirstNameIsEmpty() {
		Assertions.assertThrows(RuntimeException.class, ()->{
			//adding the contact
			contactManager.addContact(null, "Kumar", "0123456789");
			
		});
	}
	
	@Test
	@DisplayName("Test Case For Not Creating Contact when LastName is Empty....")
	public void validateLastNameIsEmpty() {
		Assertions.assertThrows(RuntimeException.class, ()->{
			//adding the contact
			contactManager.addContact("Mahesh", null, "0123456789");
			
		});
	}
	
	@Test
	@DisplayName("Test Case For Not Creating Contact when ContactNo is Empty....")
	public void validateContactNoIsEmpty() {			
		Assertions.assertThrows(RuntimeException.class, ()->{
			//adding the contact
			contactManager.addContact("Mahesh", "Kumar", null);
			
		});
	}
	
	@Test
	@DisplayName("Test Case For Not Creating Contact when ContactNo morethan size of 10....")
	public void validateContactNoLengthMoreThan10IsEmpty() {			
		Assertions.assertThrows(RuntimeException.class, ()->{
			//adding the contact
			contactManager.addContact("Mahesh", "Kumar", "012345678911");
		});
	}
	
	@Test
	@DisplayName("Test Case For Not Creating Contact when ContactNo is Starting with Zero or not....")
	public void validateContactNoStartingWithZeroOrNot() {			
		Assertions.assertThrows(RuntimeException.class, ()->{
			//adding the contact
			contactManager.addContact("Mahesh", "Kumar", "12345678911");
		});
	}
	
	@Test
	@DisplayName("Test Case For Validating the Contact is already existed or not")
	public void validateContactExistedOrNot() {
		//adding the contact
		contactManager.addContact("Suresh", "Kumar", "0123456789");
		
		Assertions.assertThrows(RuntimeException.class, () -> {
			contactManager.addContact("Suresh", "Kumar", "0123456789");
		}, 
		"Contact Already Exists");
	}
	
	//Day-2 Test Case Methods
	@DisplayName("Test Case For Validating Bulk of Contact Numbers")
    @ParameterizedTest
    @ValueSource(strings = {"0123456789", "0123456798", "0123456897"})
    public void shouldTestPhoneNumberFormatUsingValueSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }
	
	@DisplayName("CSV Source Case - Phone Number should match the required Format")
	@ParameterizedTest
	@CsvSource({"0123456789", "0123456798", "0123456897"})
	public void shouldTestPhoneNumberFormatUsingCSVSource(String phoneNumber) {
		contactManager.addContact("John", "Doe", phoneNumber);
		assertFalse(contactManager.getAllContacts().isEmpty());
		assertEquals(1, contactManager.getAllContacts().size());
	}
	
	@DisplayName("CSV File Source Case - Phone Number should match the required Format")
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void shouldTestPhoneNumberFormatUsingCSVFileSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }
	
	@DisplayName("Method Source Case - Phone Number should match the required Format")
    @ParameterizedTest
    @MethodSource("phoneNumberList")
    public void shouldTestPhoneNumberFormatUsingMethodSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    private static List<String> phoneNumberList() {
        return Arrays.asList("0123456789", "0123456798", "0123456897");
    }
}

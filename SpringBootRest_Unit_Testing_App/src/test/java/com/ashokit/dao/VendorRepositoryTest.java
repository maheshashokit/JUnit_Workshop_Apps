package com.ashokit.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ashokit.entities.Vendor;

//Below Annotation is used to activate the JPA Components
@DataJpaTest
//Below annotation is used to enable In-memory database instead of application connected database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) 
//Writing the Test Class for VendorRepository
public class VendorRepositoryTest {
	
	@Autowired
	private VendorRespoistory vendorRespoistory;

	// creating the Vendor references
	private Vendor vendor;

	@BeforeEach
	public void setup() {
		vendor = new Vendor(01, "Amazon", "Hyderabad", "1234567890");
		Vendor savedVendorDetails = vendorRespoistory.save(vendor);
		System.out.println("Vendor Details:::::" + savedVendorDetails);
	}

	//positive Test Case
	@Test
	@DisplayName("Test Case for Get Vendors Found Scenario")
	public void testFindByVendorName_Found() {
		List<Vendor> vendorsList = vendorRespoistory.findByVendorName("Amazon");
		assertNotNull(vendorsList.get(0));
		assertThat(vendorsList.get(0).getVendorId()).isEqualTo(vendor.getVendorId());
		assertThat(vendorsList.get(0).getVendorName()).isEqualTo(vendor.getVendorName());
		assertThat(vendorsList.get(0).getLocation()).isEqualTo(vendor.getLocation());
	}
	
	//Negative Test Case
	@Test
	@DisplayName("Test Case for Get Vendors Not Found Scenario")
	public void testFindByVendorName_NotFound() {
		List<Vendor> vendorsList = vendorRespoistory.findByVendorName("GCP");
		assertThat(vendorsList.isEmpty()).isTrue();
	}

	@AfterEach
	public void tearDown() {
		vendor = null;
		vendorRespoistory.deleteAll();
	}

}

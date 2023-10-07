package com.ashokit.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ashokit.dao.VendorRespoistory;
import com.ashokit.entities.Vendor;
import com.ashokit.exceptions.VendorNotFoundException;

class VendorServiceImplTest {
	
	@Mock
	private VendorRespoistory vendorRespoistory;
	
	private VendorService vendorService;
	
	private AutoCloseable autoCloseable;
	
	private Vendor vendor; 	

	@BeforeEach
	void setUp() throws Exception {		
		autoCloseable = MockitoAnnotations.openMocks(this);
		vendorService = new VendorServiceImpl(vendorRespoistory);
		vendor = new Vendor(1,"Amazon","USA","12345566");
	}
	
	@Test
	@DisplayName("Test Case For Creating Vendor Record")
	public void testCreateVendor() {
		//setting behaviour for DAO Method Call
		when(vendorRespoistory.save(any())).thenReturn(vendor);
		//verifying the service method 
		assertThat(vendorService.createVendor(vendor)).isEqualTo("SUCCESS");
	}
	
	@Test
	@DisplayName("Test Case For Getting Vendor Details By Id")
	public void testGetVendorDetailsById() {
		//setting behaviour for DAO Method Call
		when(vendorRespoistory.findById(1)).thenReturn(Optional.ofNullable(vendor));
		//verifying the service method call
		assertThat(vendorService.getVendor(1).getVendorName()).isEqualTo(vendor.getVendorName());
		assertThat(vendorService.getVendor(1).getLocation()).isEqualTo(vendor.getLocation());
		assertThat(vendorService.getVendor(1).getContactNo()).isEqualTo(vendor.getContactNo());
	}
	
	@Test
	@DisplayName("Test Case for Getting Vendor Details By Exception")
	public void testGetVendorDetailsById_Exception() {
		//Setting behaviour For DAO Method Call
		when(vendorRespoistory.findById(12323)).thenThrow(VendorNotFoundException.class);
		assertThrows(VendorNotFoundException.class,() -> vendorService.getVendor(12233),"Requested Vendor does not exist");
	}
	
	@Test
	@DisplayName("Test Case for Getting Vendor details By Vendorname")
	public void testGetVendorDetailsByName() {
		//setting behaviour For DAO Method
		when(vendorRespoistory.findByVendorName("Amazon")).thenReturn(new ArrayList<Vendor>(Collections.singleton(vendor)));
		//verifying the service call
		assertThat(vendorService.getVendorInfoByVendorName("Amazon").get(0).getVendorId()).isEqualTo(vendor.getVendorId());
	}
	
	@Test
	@DisplayName("Test Case for Getting All Vendors List")
	public void testGetAllVendors() {
		//setting the behaviour for DAO method
		when(vendorRespoistory.findAll()).thenReturn(new ArrayList<Vendor>(Collections.singleton(vendor)));
		//verifying the service method call
		assertThat(vendorService.getAllVendors().get(0).getVendorName()).isEqualTo(vendor.getVendorName());
		assertThat(vendorService.getAllVendors().size()).isEqualTo(1);
	}
	
	@Test
	@DisplayName("Test Case for Deleteing Vendor Details By VendorID")
	public void testDeleteVendor() {		
		//Some what different for testing void return type.
		mock(VendorRespoistory.class,Mockito.CALLS_REAL_METHODS);
		//setting the behaviour of DAO doAnswer(Answers.CALLS_REAL_METHODS)
		doNothing().when(vendorRespoistory).deleteById(any());
		//verifying the service method
		assertThat(vendorService.deleteVendor(1)).isEqualTo("SUCCESS");
	}

	@AfterEach
	void tearDown() throws Exception {
		//autoCloseable.close();
	}
}

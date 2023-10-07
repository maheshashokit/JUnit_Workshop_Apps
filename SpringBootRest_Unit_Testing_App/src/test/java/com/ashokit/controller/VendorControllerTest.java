package com.ashokit.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ashokit.entities.Vendor;
import com.ashokit.services.VendorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@WebMvcTest(VendorController.class)
public class VendorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	//@Mock
	private VendorService vendorService;
	
	private Vendor vendor1;
	
	private Vendor vendor2;
	
	private List<Vendor> vendorList = new ArrayList<>();
	
	@BeforeEach
	public void setUp() {
		vendor1 = new Vendor(1,"Amazon","USA","1234567890");
		vendor2 = new Vendor(2,"GCP","UK","1234567890");
		vendorList.add(vendor1);
		vendorList.add(vendor2);
	}
	
	@AfterEach
	public void tearDown() {
		vendorList.clear();
		vendor1 = null;
		vendor2 = null;
	}
	
	@Test
	public void testGetVendorDetails() throws Exception {
		//setting behaviour for Service method for Controller 
		when(vendorService.getVendor(1)).thenReturn(vendor1);
 		
		mockMvc.perform(get("/vendors/1"))
					.andDo(print())
					.andExpect(status().isOk());
	}
	
	@Test
	public void testGetAllVendors()throws Exception {
		//setting Behaviour for service method for controller
		when(vendorService.getAllVendors()).thenReturn(vendorList);
		
		mockMvc.perform(get("/vendors/"))
				    .andDo(print())
				     .andExpect(status().isOk());
	}
	
	@Test
	@Disabled
	public void testDeleteVendorDetails()throws Exception{
	
		//setting behaviour for service method for controller
		 when(vendorService.deleteVendor(1)).thenReturn("SUCCESS");
		
		 mockMvc.perform(delete("/vendors/1"))
		             .andDo(print())
		             .andExpect(status().isOk());
	} 
	
	@Test
	public void testCreateVendorDetails() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
				
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(vendor1);
		System.out.println("Request Json:::::" + requestJson);
		
		//setting behaviour for service method in controller
		when(vendorService.createVendor(vendor1)).thenReturn("SUCCESS");
		
		mockMvc.perform(post("/vendors/")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(requestJson))
		            .andDo(print())
		            .andExpect(status().isCreated());
	}

}

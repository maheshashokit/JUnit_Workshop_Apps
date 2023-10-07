package com.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.entities.Vendor;
import com.ashokit.services.VendorService;

@RestController
@RequestMapping("/vendors")
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@GetMapping("/{vendorId}")
	public ResponseEntity<Object> getVendorDetails(@PathVariable("vendorId") int vendorId) {
		return new ResponseEntity<Object>(vendorService.getVendor(vendorId), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<Object> getAllVendorDetails() {
		return new ResponseEntity<Object>(vendorService.getAllVendors(), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<String> createCloudVendorDetails(@RequestBody Vendor cloudVendor) {
		vendorService.createVendor(cloudVendor);
		return new ResponseEntity<String>("Vendor Created Successfully", HttpStatus.CREATED);
	}

	@DeleteMapping("/{vendorId}")
	public ResponseEntity<String> deleteVendorDetails(@PathVariable("vendorId") int vendorId) {
		vendorService.deleteVendor(vendorId);
		return new ResponseEntity<>("Vendor Deleted Successfully", HttpStatus.OK);
	}
}

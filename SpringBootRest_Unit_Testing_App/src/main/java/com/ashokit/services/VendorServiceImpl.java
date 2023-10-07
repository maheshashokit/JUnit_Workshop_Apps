package com.ashokit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.dao.VendorRespoistory;
import com.ashokit.entities.Vendor;
import com.ashokit.exceptions.VendorNotFoundException;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRespoistory vendorRespoistory;

	// Additional We have written for Unit testing as constructor
	public VendorServiceImpl(VendorRespoistory vendorRespoistory) {
		this.vendorRespoistory = vendorRespoistory;
	}

	@Override
	public String createVendor(Vendor vendor) {
		vendorRespoistory.save(vendor);
		return "SUCCESS";
	}

	@Override
	public List<Vendor> getVendorInfoByVendorName(String vendorName) {
		return vendorRespoistory.findByVendorName(vendorName);
	}

	public Vendor getVendor(int vendorId) {
		// More Business Logic
		if (vendorRespoistory.findById(vendorId).isEmpty()) {
			throw new VendorNotFoundException("Requested Vendor does not exist");
		}
		return vendorRespoistory.findById(vendorId).get();
	}

	@Override
	public List<Vendor> getAllVendors() {
		return vendorRespoistory.findAll();
	}

	@Override
	public String deleteVendor(int vendorId) {
		// More Business Logic
		vendorRespoistory.deleteById(vendorId);
		return "SUCCESS";
	}

}

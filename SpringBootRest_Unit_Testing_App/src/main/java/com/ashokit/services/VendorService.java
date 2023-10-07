package com.ashokit.services;

import java.util.List;

import com.ashokit.entities.Vendor;

public interface VendorService {

	public String createVendor(Vendor vendor);

	public Vendor getVendor(int vendorId);

	public List<Vendor> getVendorInfoByVendorName(String vendorName);

	public List<Vendor> getAllVendors();

	public String deleteVendor(int vendorId);

}

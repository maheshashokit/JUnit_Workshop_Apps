package com.ashokit.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entities.Vendor;

public interface VendorRespoistory extends JpaRepository<Vendor, Serializable> {

	// finder method from Spring DataJPA
	public List<Vendor> findByVendorName(String vendorName);

}

package com.ashokit.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "ashokit_vendors")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Vendor {

	@Id
	@Column(name = "vendor_id", nullable = false)
	private Integer vendorId;

	@Column(name = "vendor_name", nullable = false)
	private String vendorName;

	@Column(name = "vendor_location", nullable = false)
	private String location;

	@Column(name = "vendor_contact", nullable = false)
	private String contactNo;

}

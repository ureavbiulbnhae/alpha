package com.example.testh2.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class School {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name; 
	@OneToOne(cascade = {CascadeType.ALL})
	private Address address;
	@Column
	private String district;
	@Column
	private String region;
	@Column
	private String footprint;
	
	public School(int id, String name, Address address, 
					String district, String region, String footprint) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.district = district;
		this.region = region;
		this.footprint = footprint;
	}

	public School() {

	}
	
	
	@Override
	public String toString() {
		return "School [id=" + id + ", name=" + name +	", address=" + address 
				+ ", district="	+ district + ", region=" + region + ", footprint=" + footprint + "]";
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getFootprint() {
		return footprint;
	}

	public void setFootprint(String foorprint) {
		this.footprint = foorprint;
	}	
}
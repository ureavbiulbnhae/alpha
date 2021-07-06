package com.example.testh2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String street;
	@Column
	private String village;
	@Column
	private String zip;
	
	
	public Address() {

	}
	
	public Address(String street, String village, String zip) {
		this.street = street;
		this.village = village;
		this.zip = zip;
	}
	
	public Address(int id, String street, String village, String zip) {
		this.id = id;
		this.street = street;
		this.village = village;
		this.zip = zip;
	}
	
	
	@Override
	public String toString() {
		return "Address [id=" + id +  ", street=" + street 
				+ ", region=" + village	+ ", zip=" + zip + "]";
	}
		
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}	
}